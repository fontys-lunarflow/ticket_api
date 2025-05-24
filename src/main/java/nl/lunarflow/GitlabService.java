package nl.lunarflow;

import java.util.ArrayList;
import java.util.List;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.models.Assignee;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.Label;

import nl.lunarflow.models.Config;
import nl.lunarflow.models.Ticket;
import nl.lunarflow.models.Taglabel;
import nl.lunarflow.models.Service;

public class GitlabService implements Service {

    static List<Assignee> mapAssignees(List<String> mailList) {
        List<Assignee> assignList = new ArrayList<Assignee>();
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
        ticket.labels = issue.getLabels();
        if (issue.getDueDate() != null) {
            ticket.dueDate = issue.getDueDate().toString();
        } else {
            ticket.dueDate = "";
        }
        return ticket;
    }

    static List<Taglabel> mapLabels(List<Label> labelList) {
        List<Taglabel> res = new ArrayList<Taglabel>();
        for (Label label : labelList) {
            Taglabel lab = new Taglabel();
            lab.id = label.getId();
            lab.name = label.getName();
            lab.color = label.getColor();
            res.add(lab);
        }
        return res;
    }

    @Override
    public Ticket createIssue(Config conf, Ticket ticket) throws Exception {
        GitLabApi api = new GitLabApi(conf.serverURL, conf.token);
        Issue issue = api.getIssuesApi().createIssue(conf.projectPath, ticket.title, ticket.desc);
        issue.setAssignees(mapAssignees(ticket.assignees));

        ticket.id = issue.getIid();
        ticket.url = issue.getWebUrl();
        api.close();

        return ticket;
    }

    @Override
    public Ticket readIssue(Config conf, Ticket ticket) throws Exception {
        GitLabApi api = new GitLabApi(conf.serverURL, conf.token);
        Issue issue = api.getIssuesApi().getIssue(conf.projectPath, ticket.id);
        Ticket ret = mapTicket(issue);

        api.close();
        return ret;
    }

    @Override
    public Ticket closeIssue(Config conf, Ticket ticket) throws Exception {
        GitLabApi api = new GitLabApi(conf.serverURL, conf.token);
        api.getIssuesApi().closeIssue(conf.projectPath, ticket.id);
        api.close();
        return ticket;
    }

    @Override
    public Ticket setLabels(Config conf, Ticket ticket) throws Exception {
        GitLabApi api = new GitLabApi(conf.serverURL, conf.token);
        Issue issue = api.getIssuesApi().getIssue(conf.projectPath, ticket.id);
        issue.setLabels(ticket.labels);
        Ticket ret = mapTicket(issue);

        api.close();
        return ret;
    }

    @Override
    public Taglabel createLabel(Config conf, Taglabel tlabel) throws Exception {
        GitLabApi api = new GitLabApi(conf.serverURL, conf.token);
        Label glLabel = api.getLabelsApi().createProjectLabel(conf.projectPath, new Label().withName(tlabel.name));
        tlabel.id = glLabel.getId();
        api.close();
        return tlabel;
    }

    @Override
    public Taglabel deleteLabel(Config conf, Taglabel tlabel) throws Exception {
        GitLabApi api = new GitLabApi(conf.serverURL, conf.token);
        api.getLabelsApi().deleteProjectLabel(conf.projectPath, tlabel.id);
        tlabel.id = (long) -1;
        api.close();
        return tlabel;
    }

    @Override
    public List<Taglabel> listLabels(Config conf) throws Exception {
        GitLabApi api = new GitLabApi(conf.serverURL, conf.token);
        var res = mapLabels(api.getLabelsApi().getProjectLabels(conf.projectPath));
        api.close();
        return res;
    }

    @Override
    public List<Taglabel> listFilteredLabels(Config conf, List<Taglabel> list) throws Exception {
        // Allocate an intermidiate list
        List<Label> ilist = new ArrayList<Label>();
        GitLabApi api = new GitLabApi(conf.serverURL, conf.token);

        for (Taglabel taglab : list) {
            try {
                ilist.add(api.getLabelsApi().getProjectLabel(conf.projectPath, taglab.id));
            } finally {}
        }
        
        api.close();
        return mapLabels(ilist);
    }
}
