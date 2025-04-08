package nl.lunarflow.models;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class Config {
    @ConfigProperty(name = "GITLAB_SERVER")
    public String serverURL;

    @ConfigProperty(name = "GITLAB_PROJECT")
    public String projectPath;

    @ConfigProperty(name = "GITLAB_TOKEN")
    public String token;
}
