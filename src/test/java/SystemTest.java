import org.junit.jupiter.api.Test;
public class SystemTest {
    // Change these values to match reality
    static class Config {
        static Boolean Active = false;
        static String ServerURL = "https://gitlab.com/";
        static String ProjectPath = "org/team/project";
        static String Token = "glpat-0000";
    }

    @Test
    void testTemplate() {
        if (!Config.Active) {
            return;
        }
        // Put tests here!
    }
}
