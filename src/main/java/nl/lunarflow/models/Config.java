package nl.lunarflow.models;

public class Config {
    public String serverURL;

    public String projectPath;

    public String token;

    public Config() {
        this.serverURL = System.getenv("GITLAB_SERVER");
        this.projectPath = System.getenv("GITLAB_PROJECT");
        this.token = System.getenv("GITLAB_TOKEN");
    }

    @Override
    public String toString() {
        return String.format("Configuration:\n- Server: %s\n- Project: %s\n- Token: %s", this.serverURL, this.projectPath, this.token);
    }
}
