# Lunarflow Ticket API

With the Ticket API it is possible to manage a GitLab ticket, corresponding to the content item being operated on.

### Building

-   `$ ./mvwn package`
-   `$ docker build -t image:tag -f src/main/docker/Dockerfile.jvm .`

### Deployment

-   Open _src/main/kubernetes/config.yaml_ and match the values with your setup.
-   `$ kubectl -n NAMESPACE -f src/main/kubernetes/config.yaml`
-   `$ kubectl -n NAMESPACE -f src/main/kubernetes/deployment.yaml`
