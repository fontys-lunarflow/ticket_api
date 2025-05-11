package nl.lunarflow.models;

import java.util.List;

public interface Service {
    public Ticket reqTicket(Config conf, Ticket ticket) throws Exception;
    public Ticket newTicket(Config conf, Ticket ticket) throws Exception;
    public Ticket doneTicket(Config conf, Ticket ticket) throws Exception;
    public Ticket setLabels(Config conf, Ticket ticket) throws Exception;
    
    public Label newLabel(Config conf, Label label) throws Exception;
    public Label delLabel(Config conf, Label label) throws Exception;
    public List<Label> listLabel(Config conf) throws Exception;
}
