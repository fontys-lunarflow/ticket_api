package nl.lunarflow.services;

import nl.lunarflow.models.Config;
import nl.lunarflow.models.Service;
import nl.lunarflow.models.Ticket;

import java.util.ArrayList;
import java.util.List;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.models.Assignee;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.Label;

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

    static ArrayList<nl.lunarflow.models.Label> mapLabels(List<Label> labelList) {
        ArrayList<nl.lunarflow.models.Label> res = new ArrayList<nl.lunarflow.models.Label>();
        for (Label label : labelList) {
            nl.lunarflow.models.Label lab = new nl.lunarflow.models.Label();
            lab.id = label.getId();
            lab.name = label.getName();
            lab.color = label.getColor();
            res.add(lab);
        }
        return res;
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
        api.getIssuesApi().closeIssue(conf.projectPath, ticket.id);
        api.close();
        return ticket;
    }

    @Override
    public nl.lunarflow.models.Label newLabel(Config conf, nl.lunarflow.models.Label label) throws Exception {
        GitLabApi api = new GitLabApi(conf.serverURL, conf.token);
        Label glLabel = api.getLabelsApi().createProjectLabel(conf.projectPath, new Label().withName(label.name));
        label.id = glLabel.getId();
        api.close();
        return label;
    }

    @Override
    public nl.lunarflow.models.Label delLabel(Config conf, nl.lunarflow.models.Label label) throws Exception {
        GitLabApi api = new GitLabApi(conf.serverURL, conf.token);
        api.getLabelsApi().deleteProjectLabel(conf.projectPath, label.id);
        label.id = (long) -1;
        api.close();
        return label;
    }

    @Override
    public ArrayList<nl.lunarflow.models.Label> listLabel(Config conf, nl.lunarflow.models.Label label) throws Exception {
        GitLabApi api = new GitLabApi(conf.serverURL, conf.token);
        var res = mapLabels(api.getLabelsApi().getProjectLabels(conf.projectPath));
        api.close();
        return res;
    }
}
