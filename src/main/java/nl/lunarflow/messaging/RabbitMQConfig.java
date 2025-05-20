package nl.lunarflow.messaging;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RabbitMQConfig {

//    @ConfigProperty(name = "rabbitmq.host")
    public String host = "rabbitmq";

//    @ConfigProperty(name = "rabbitmq.port")
    public int port = 5672;

//    @ConfigProperty(name = "rabbitmq.username")
    public String username = "guest";

//    @ConfigProperty(name = "rabbitmq.password")
    public String password = "guest";

//    @ConfigProperty(name = "rabbitmq.exchange")
    public String exchange = "ticket.exchange";

//    @ConfigProperty(name = "rabbitmq.response-queue")
    public String requestQueue = "ticketapi.response";

//    @ConfigProperty(name = "rabbitmq.request-queue")
    public String responseQueue = "ticketapi.request";
}