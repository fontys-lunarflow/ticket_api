# Lunarflow Ticket API

With the Ticket API it is possible to manage GitLab issues and labels, corresponding to the content items being operated on.

### Building

-   `$ ./mvwn package`
-   `$ docker build -t image:tag -f src/main/docker/Dockerfile.jvm .`

### Deployment

-   Open `src/main/kubernetes/config.yaml` and match the values with your setup.
-   `$ kubectl -n NAMESPACE -f src/main/kubernetes/config.yaml`
-   `$ kubectl -n NAMESPACE -f src/main/kubernetes/deployment.yaml`

### Testing

Testing the ticket API is a bit more involved.
But can be done when following these steps carefully.

- Open the file `src/test/java/SystemTest.java`.
- Observe the following snippet from `SystemTest`:
```java
// Change these values to match reality
static class TestConfig {
    static String ServerURL = "https://gitlab.com/";
    static String ProjectPath = "org/team/project";
    static String Token = "glpat-xxx";

    // Only change when you want to test
    static Boolean Active = false;
}
```
- Configure the variables inside `SystemTest.TestConfig` to match your situation.
- When you are ready change the `SystemTest.TestConfig.Active` to `true`.
- Execute `$ ./mvwn test`, and wait.
