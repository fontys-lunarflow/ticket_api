package nl.lunarflow.services;

import nl.lunarflow.models.Config;
import nl.lunarflow.models.Service;
import nl.lunarflow.models.Ticket;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.models.Issue;

public class GitlabService implements Service {

    @Override
    public Ticket reqTicket(Config conf, Ticket ticket) throws Exception{
        throw new UnsupportedOperationException("Unimplemented method 'reqTicket'");
    }

    @Override
    public Ticket newTicket(Config conf, Ticket ticket) throws Exception {
        GitLabApi api = new GitLabApi(conf.serverURL, conf.token);
        Issue issue = api.getIssuesApi().createIssue(api, ticket.title, "");

        ticket.id = issue.getIid();
        ticket.url = issue.getWebUrl();

        return ticket;
    }

    @Override
    public Ticket doneTicket(Config conf, Ticket ticket) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doneTicket'");
    }
    
}
