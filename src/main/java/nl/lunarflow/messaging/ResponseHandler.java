package nl.lunarflow.messaging;

import nl.lunarflow.models.Ticket;

public interface ResponseHandler {
    Ticket handleCreateTicket(String correlationId, String responseJson, Subjects subject);

}