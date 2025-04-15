package nl.lunarflow.models;

public class Status {
    public String ping = "pong";
    public Config config;

    public Status() {
        this.config = new Config();
    }
}
