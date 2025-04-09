package nl.lunarflow.services;

import nl.lunarflow.models.Config;
import nl.lunarflow.models.Service;
import nl.lunarflow.models.Ticket;

public class BaseService {

    public static Ticket reqTicket(Config conf, Ticket ticket, Service handler) throws Exception {
        return handler.reqTicket(conf, ticket);
    }

    public static Ticket newTicket(Config conf, Ticket ticket, Service handler) throws Exception {
        return handler.newTicket(conf, ticket);
    }

    public static Ticket doneTicket(Config conf, Ticket ticket, Service handler) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'doneTicket'");
    }
    
}
