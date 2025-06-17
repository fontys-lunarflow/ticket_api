package nl.lunarflow.models;

import java.util.List;
import jakarta.validation.constraints.NotNull;


public class Ticket {
    
    @NotNull
    public Long id;
    
    public String title;
    public String desc;
    public String dueDate;
    public String url;
    public List<String> labels;
    public List<String> assignees;

    public Ticket() {
        
    }
}