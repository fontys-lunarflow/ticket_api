# Lunarflow Ticket API

With the Ticket API it is possible to manage a GitLab ticket, corresponding to the content item being operated on.

### Deployment

- `$ kubectl -n NAMESPACE -f src/main/kubernetes/deployment.yaml`
- Open _src/main/kubernetes/config.yaml_ and match the values with your setup.
- `$ kubectl -n NAMESPACE -f src/main/kubernetes/config.yaml`
