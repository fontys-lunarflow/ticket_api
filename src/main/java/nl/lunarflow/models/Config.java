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

        this.mqHost = System.getenv("MQ_HOST");
        this.mqPort = System.getenv("MQ_PORT");
        this.mqUser = System.getenv("MQ_USER");
        this.mqPass = System.getenv("MQ_PASS");
        this.mqExchange = System.getenv("MQ_EXCHANGE");
    }
}