package nl.lunarflow.models;

import java.util.ArrayList;

public class Ticket {
    public Long id;
    public String title;
    public String desc;
    public String dueDate;
    public String url;
    public ArrayList<String> assignees;
}
