package nl.lunarflow.models;

public class Config {
    public String serverURL;
    public String projectPath;
    public String token;

    public String mqHost;
    public String mqPort;
    public String mqUser;
    public String mqPass;
    public String mqExchange;

    public Config() {
        this.serverURL = System.getenv("GITLAB_SERVER");
        this.projectPath = System.getenv("GITLAB_PROJECT");
        this.token = System.getenv("GITLAB_TOKEN");
    }
}