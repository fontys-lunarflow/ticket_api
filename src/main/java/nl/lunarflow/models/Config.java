package nl.lunarflow.models;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class Config {
    @ConfigProperty(name = "GITLAB_SERVER")
    public String serverURL;

    @ConfigProperty(name = "GITLAB_PROJECT")
    public String projectPath;

    @ConfigProperty(name = "GITLAB_TOKEN")
    public String token;

    @Override
    public String toString() {
        return String.format("Configuration:\n- Server: %s\n- Project: %s\n- Token: %s", this.serverURL, this.projectPath, this.token);
    }
}
