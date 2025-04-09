package nl.lunarflow.services;

import nl.lunarflow.models.Config;
import nl.lunarflow.models.Service;
import nl.lunarflow.models.Ticket;

import java.util.ArrayList;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.models.Assignee;
import org.gitlab4j.api.models.Issue;

public class GitlabService implements Service {

    static ArrayList<Assignee> mapAssignees(ArrayList<String> mailList) {
        ArrayList<Assignee> assignList = new ArrayList<Assignee>();
        for (String s : mailList) {
            assignList.add(new Assignee().withEmail(s));
        }
        return assignList;
    }

    @Override
    public Ticket reqTicket(Config conf, Ticket ticket) throws Exception{
        throw new UnsupportedOperationException("Unimplemented method 'reqTicket'");
    }

    @Override
    public Ticket newTicket(Config conf, Ticket ticket) throws Exception {
        GitLabApi api = new GitLabApi(conf.serverURL, conf.token);
        Issue issue = api.getIssuesApi().createIssue(api, ticket.title, "");
        issue.setAssignees(mapAssignees(ticket.assignees));

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
