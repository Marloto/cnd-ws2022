# Kubernetes Example

Run ingress and hpa ressources with simple-express-example.

HPA requires metric components, apply them before: `kubectl apply -f https://github.com/kubernetes-sigs/metrics-server/releases/latest/download/components.yaml`

Ingress requires ingress controller, see README for information how to get one.