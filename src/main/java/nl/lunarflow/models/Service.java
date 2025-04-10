package nl.lunarflow.models;

public interface Service {
    public Ticket reqTicket(Config conf, Ticket ticket) throws Exception;
    public Ticket newTicket(Config conf, Ticket ticket) throws Exception;
    public Ticket doneTicket(Config conf, Ticket ticket) throws Exception;
}
