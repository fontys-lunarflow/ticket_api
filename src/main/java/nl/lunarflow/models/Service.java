package nl.lunarflow.models;

import java.util.List;

public interface Service {
    public Ticket createIssue(Config conf, Ticket issue) throws Exception;
    public Ticket readIssue(Config conf, Ticket issue) throws Exception;
    public Ticket closeIssue(Config conf, Ticket issue) throws Exception;
    public Ticket setLabels(Config conf, Ticket issue) throws Exception;

    public Taglabel createLabel(Config conf, Taglabel label) throws Exception;
    public Taglabel deleteLabel(Config conf, Taglabel label) throws Exception;
    public List<Taglabel> listLabels(Config conf) throws Exception;
}