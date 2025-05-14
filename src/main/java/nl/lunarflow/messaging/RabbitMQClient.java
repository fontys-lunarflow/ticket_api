package nl.lunarflow.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rabbitmq.client.*;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.lunarflow.models.Ticket;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Startup
@ApplicationScoped
public class RabbitMQClient {
    private Channel channel;
    private Connection connection;

    @Inject
    ResponseHandler responseHandler;

    @Inject
    RabbitMQConfig rabbitMQConfig;

    @PostConstruct
    void init() throws IOException, TimeoutException {
        System.out.println("Connecting to RabbitMQ");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(rabbitMQConfig.host);
        factory.setUsername(rabbitMQConfig.username);
        factory.setPassword(rabbitMQConfig.password);
        factory.setPort(rabbitMQConfig.port);

        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.exchangeDeclare(rabbitMQConfig.exchange, BuiltinExchangeType.DIRECT, true);

        // Setup 2 different channels, one for sending messages to ticket API, one for receiving
        for (Subjects subject : Subjects.values()) {
            String queueName = "ticket_api." + subject.toString();
            channel.queueDeclare(queueName, true, false, false, null);

            channel.queueBind(queueName, rabbitMQConfig.exchange, queueName);

            channel.basicConsume(queueName,true, (consumerTag, delivery) ->{
                System.out.println(new String(delivery.getBody()));
                String correlationId = delivery.getProperties().getCorrelationId();
                String replyTo = delivery.getProperties().getReplyTo();
                String body = new String(delivery.getBody());

                Ticket responseTicket = null;
                switch (subject) {
                    case Subjects.TICKET_CREATE -> responseTicket = responseHandler.handleCreateTicket(correlationId, body, subject);
//                    case Subjects.TICKET_READ -> ;
//                    case Subjects.TICKET_CLOSE -> ;
//                    case Subjects.TICKET_SETLABELS -> ;
//
//                    case Subjects.LABEL_CREATE -> ;
//                    case Subjects.LABEL_DELETE -> ;
//                    case Subjects.LABEL_LIST -> ;
                }


                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String json = ow.writeValueAsString(responseTicket);

                System.out.println(replyTo);
                if (replyTo != null && !replyTo.isEmpty()) {
                    AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                            .Builder()
                            .correlationId(correlationId)
                            .build();

                    channel.basicPublish("", replyTo, replyProps, json.getBytes());
                }
            }, consumerTag -> {});
        }


    }


    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
