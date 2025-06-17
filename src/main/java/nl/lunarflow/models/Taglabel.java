package nl.lunarflow.models;

import jakarta.validation.constraints.NotNull;

public class Taglabel {

    @NotNull
    public Long id;
    public String name;
    public String color;

    public Taglabel() {
        
    }
}