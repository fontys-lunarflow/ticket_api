package nl.lunarflow.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rabbitmq.client.Delivery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.lunarflow.services.GitlabService;
import nl.lunarflow.models.Config;
import nl.lunarflow.models.Ticket;

import java.io.IOException;

@ApplicationScoped
public class RabbitMQUser implements RabbitMQConsumer {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init(QueueDeclarer queueDeclarer) throws IOException {
        // Converts the enum to a list of strings, so the client doesn't have to know about the enum
        for (Subjects subject : Subjects.values()) {
            queueDeclarer.declareQueue(subject.name());
        }
    }

    @Override
    public String handleCallWithResponse(String correlationId, String body, String queueName, Delivery delivery) {
        System.out.println(correlationId);
        if (!correlationId.startsWith("content_api.content_item.")) return null;

        JsonNode json = null;
        try {
            json = mapper.readTree(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        try {
            Ticket ticket = new Ticket();
            ticket.title = json.get("title").asText();
            ticket.desc = json.get("subject").asText();

            Ticket responseTicket = new GitlabService().newTicket(new Config(), ticket);
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(responseTicket);
        } catch (Exception err) {
            System.out.println(err);
            return null;
        }
    }

    @Override
    public void handleCall(String correlationId, String body, String queueName, Delivery delivery) {
        handleCallWithResponse(correlationId, body, queueName, delivery);
    }
}
