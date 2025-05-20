package nl.lunarflow.models;

import java.util.List;

/*
 * This service is just here to implement a contract
 * And to get an impression for how to approach future work
 */

public interface Service {
    public Ticket createIssue(Config conf, Ticket ticket) throws Exception;
    public Ticket readIssue(Config conf, Ticket ticket) throws Exception;
    public Ticket closeIssue(Config conf, Ticket ticket) throws Exception;
    public Ticket setLabels(Config conf, Ticket ticket) throws Exception;

    public Taglabel createLabel(Config conf, Taglabel tlabel) throws Exception;
    public Taglabel deleteLabel(Config conf, Taglabel tlabel) throws Exception;
    public List<Taglabel> listLabels(Config conf) throws Exception;
}