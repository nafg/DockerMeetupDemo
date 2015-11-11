1. Install Typesafe Activator from https://www.typesafe.com/activator/download

2. Run `activator stage`

3. Install Docker 1.9 and docker-compose 1.5.0 (instructions at https://www.docker.com/)

4. Run `docker-compose --x-networking up`

5. ```
curl -v --data '{"bins":[3,3],"items":[1,2,3]}' -H 'Content-Type: application/json' localhost:9000/getSolution
```
