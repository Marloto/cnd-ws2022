# Ingress Controller

You can find instructions for installing an Ingress controller in the official Kubernetes installation: https://kubernetes.github.io/ingress-nginx/deploy/

In case of using a minikube, just run `minikube addons enable ingress`. In case of Docker Desktop you can use:

```
helm upgrade --install ingress-nginx ingress-nginx \
  --repo https://kubernetes.github.io/ingress-nginx \
  --namespace ingress-nginx --create-namespace \
  --set ingressClassResource.default=true
```

This requires to install helm. The ingressClassResource can be used to solve a [current issue](https://github.com/rancher/rancher/issues/35053).

As an alternative, this can be used, but might run in comparable issues.

```
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.1.0/deploy/static/provider/cloud/deploy.yaml
```

## Ingress Resource

```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
    name: demo-ingress
spec:
    ingressClassName: nginx
    rules:
      - host: localhost
        http:
          paths:
            - backend:
                service:
                  name: demo
                  port:
                    number: 3000
              path: /hello #prefix has to be processed by pod
              pathType: Prefix
```

## Troubleshooting

- Ingress Controller will start some kind of pod, use `kubectl get all -n ingress-nginx`
- Use `kubectl describe <your-pod> -n ingress-nginx` for more informations
- Take a look at logs, e.g. `kubectl logs <your-pod> -n ingress-nginx`