package nl.lunarflow.services;

import nl.lunarflow.models.Config;
import nl.lunarflow.models.Service;
import nl.lunarflow.models.Ticket;

import java.util.ArrayList;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.models.Assignee;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.models.Constants.IssueState;

public class GitlabService implements Service {

    static ArrayList<Assignee> mapAssignees(ArrayList<String> mailList) {
        ArrayList<Assignee> assignList = new ArrayList<Assignee>();
        if (mailList != null) {
            for (String s : mailList) {
                assignList.add(new Assignee().withEmail(s));
            }
        }
        return assignList;
    }

    static Ticket mapTicket(Issue issue) {
        Ticket ticket = new Ticket();
        ticket.id = issue.getIid();
        ticket.title = issue.getTitle();
        ticket.desc = issue.getDescription();
        ticket.url = issue.getWebUrl();
        if (issue.getDueDate() != null) {
            ticket.dueDate = issue.getDueDate().toString();
        } else {
            ticket.dueDate = "";
        }
        return ticket;
    }

    @Override
    public Ticket reqTicket(Config conf, Ticket ticket) throws Exception {
        GitLabApi api = new GitLabApi(conf.serverURL, conf.token);
        Issue issue = api.getIssuesApi().getIssue(conf.projectPath, ticket.id);
        Ticket ret = mapTicket(issue);

        api.close();
        return ret;
    }

    @Override
    public Ticket newTicket(Config conf, Ticket ticket) throws Exception {
        GitLabApi api = new GitLabApi(conf.serverURL, conf.token);
        Issue issue = api.getIssuesApi().createIssue(conf.projectPath, ticket.title, ticket.desc);
        issue.setAssignees(mapAssignees(ticket.assignees));

        ticket.id = issue.getIid();
        ticket.url = issue.getWebUrl();
        api.close();

        return ticket;
    }

    @Override
    public Ticket doneTicket(Config conf, Ticket ticket) throws Exception {
        GitLabApi api = new GitLabApi(conf.serverURL, conf.token);
        Issue issue = api.getIssuesApi().getIssue(conf.projectPath, ticket.id);
        issue.setState(IssueState.CLOSED);

        api.close();
        return ticket;
    }
}
