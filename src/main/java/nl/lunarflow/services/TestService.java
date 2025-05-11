package nl.lunarflow.services;

import java.util.ArrayList;

import nl.lunarflow.models.Config;
import nl.lunarflow.models.Label;
import nl.lunarflow.models.Service;
import nl.lunarflow.models.Ticket;

public class TestService implements Service {

    private Ticket genTicket() {
        Ticket ticket = new Ticket();
        ticket.id = (long) 1;
        ticket.title = "Demo ticket";
        ticket.desc = "This is a demo ticket, made for demo purposes.";
        ticket.url = "http://some.domain.tld/tickets/1";
        ticket.assignees = new ArrayList<String>();
        ticket.assignees.add("user@somedomain.tld");
        return ticket;
    }

    @Override
    public Ticket reqTicket(Config conf, Ticket ticket) {
        if (ticket.id == 1) {
            return genTicket();
        }
        return ticket;
    }

    @Override
    public Ticket newTicket(Config conf, Ticket ticket) {
        return genTicket();
    }

    @Override
    public Ticket doneTicket(Config conf, Ticket ticket) {
        return ticket;
    }

    @Override
    public Label newLabel(Config conf, Label label) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newLabel'");
    }

    @Override
    public Label delLabel(Config conf, Label label) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delLabel'");
    }

    @Override
    public ArrayList<Label> listLabel(Config conf, Label label) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listLabel'");
    }
    
}
