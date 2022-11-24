# Swarm-Experimente

- `docker swarm init` bzw. `docker swarm init --advertise-addr 10.0.5.4`
- Deploy Portainer
- go to website https://localhost:9443, admin ...
- environments > add
  - agent, linux, docker swarm, run on manager node: `curl -L https://downloads.portainer.io/agent-stack-ce29.yml -o agent-stack.yml && docker stack deploy --compose-file=agent-stack.yml portainer-agent`
- ENV URLI 10.0.5.4:9001
- `docker stack deploy --compose-file=docker-compose.yml my-deployment-name`


version: "3.9"

services:
  app:
    image: marloto/simple-express-js
    ports:
      - '3000:3000'
    volumes:
      - 'data:/tmp/example'

# Volumes?
volumes:
  data:
    driver: local
    driver_opts:
      type: nfs
      o: nfsvers=4,addr=10.0.5.6,rw
      device: ":/media/nfs/"