import org.junit.jupiter.api.Test;
import nl.lunarflow.controllers.Ping;
import nl.lunarflow.models.Config;
import nl.lunarflow.models.Taglabel;
import nl.lunarflow.models.Ticket;
import nl.lunarflow.GitlabService;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemTest {
    // Change these values to match reality
    static class TestConfig {
        static String ServerURL = "https://gitlab.com/";
        static String ProjectPath = "org/team/project";
        static String Token = "glpat-xxx";

        // Only change when you want to test
        static Boolean Active = false;
    }

    // A config "factory"
    static Config configFactory() {
        var cfg = new Config();
        cfg.serverURL = TestConfig.ServerURL;
        cfg.projectPath = TestConfig.ProjectPath;
        cfg.token = TestConfig.Token;
        return cfg;
    }

    @Test
    void testTemplate() {
        if (!TestConfig.Active) {
            return;
        }
        // Put tests here!
    }

    @Test
    void testPing() {
        if (!TestConfig.Active) {
            return;
        }
        assertEquals("\"pong\"", new Ping().ping());
    }

    @Test
    void testService() throws Exception {
        if (!TestConfig.Active) {
            return;
        }
        // Arrange
        var ticket = new Ticket();
        var label = new Taglabel();
        label.name = "Testlabel";
        label.color = "#000000";
        ticket.title = "Test ticket";

        // Act
        ticket = new GitlabService().createIssue(configFactory(), ticket);
        ticket = new GitlabService().readIssue(configFactory(), ticket);
        label = new GitlabService().createLabel(configFactory(), label);
        
        ticket.labels.add(label.name);
        ticket = new GitlabService().setLabels(configFactory(), ticket);

        ticket = new GitlabService().closeIssue(configFactory(), ticket);
        label = new GitlabService().deleteLabel(configFactory(), label);

        // Assertion is the throw of an exception
    }
}
