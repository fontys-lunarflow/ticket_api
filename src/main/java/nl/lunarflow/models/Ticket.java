package nl.lunarflow.models;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    public Long id;
    public String title;
    public String desc;
    public String dueDate;
    public String url;
    public List<String> labels;
    public ArrayList<String> assignees;

    public Ticket() {
        
    }
}
