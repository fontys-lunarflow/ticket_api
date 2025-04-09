package nl.lunarflow.services;

import java.util.ArrayList;

import nl.lunarflow.models.Config;
import nl.lunarflow.models.Service;
import nl.lunarflow.models.Ticket;

public class TestService implements Service {

    @Override
    public Ticket reqTicket(Config conf, Ticket ticket) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reqTicket'");
    }

    @Override
    public Ticket newTicket(Config conf, Ticket ticket) {
        ticket.id = (long) 1;
        ticket.title = "Demo ticket";
        ticket.url = "http://some.domain.tld/tickets/1";
        ticket.assignees = new ArrayList<String>();
        ticket.assignees.add("user@somedomain.tld");
        
        return ticket;
    }

    @Override
    public Ticket doneTicket(Config conf, Ticket ticket) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doneTicket'");
    }
    
}
