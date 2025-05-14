package nl.lunarflow.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import nl.lunarflow.controllers.Handler;
import nl.lunarflow.models.Config;
import nl.lunarflow.models.Ticket;
import nl.lunarflow.services.GitlabService;

@ApplicationScoped
public class TicketResponseHandler implements ResponseHandler {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    @Transactional
    public Ticket handleCreateTicket(String correlationId, String response, Subjects subject) {
        if (!correlationId.startsWith("content_api.content_item.")) return null;

        JsonNode json = null;
        try {
            json = mapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        try {
            Ticket ticket = new Ticket();
            ticket.title = json.get("title").asText();
            ticket.desc = json.get("subject").asText();

            return Handler.newTicket(new Config(), ticket, new GitlabService());
        } catch (Exception err) {
            return null;
        }
    }
}
