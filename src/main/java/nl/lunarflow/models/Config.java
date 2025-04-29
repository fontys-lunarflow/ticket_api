package nl.lunarflow.models;

public class Config {
    public String serverURL;

    public String projectPath;

    public String token;

    public Config() {
        /*
         * Altough Quarkus has support for getting environment variables from the Kubernetes API,
         * manually reading them does not incur costs and may prove more portable.
         */
        this.serverURL = System.getenv("GITLAB_SERVER");
        this.projectPath = System.getenv("GITLAB_PROJECT");
        this.token = System.getenv("GITLAB_TOKEN");
    }

}
