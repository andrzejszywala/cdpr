CD PROJEKT RED Backend Engineer Recruitment Task



![architecture](https://user-images.githubusercontent.com/10794859/192143508-b5743187-ff31-4c7f-8bd8-b6eb16087b42.png)


Architecture:

**Keycloak** - Authorization and authentication server. Authentication is executed by gshop-ui application when user or admin logins. In response keycloak returns JWT token with roles. For the sake of simplicity I've omit keycloak configuration. I've generated JWT token manually and paste it to file gshop.resource.ts. Token is generated by class GenerateToken.java.

**gshop-ui** - Frontend appliaction.

Technologies:
 - Angular - frontend
 - Quarkus - backend and api gateway to other microservices
 - microprofile-rest-client - comminication layer to the microservices
 - quarkus-quinoa - quarkus extension to create UI
 - JWT - to secure rest endpoints
 - openapi

**gshop-games** - Games catalog microservice.

Technologies:

 - Quarkus
 - mariadb database - to store microservice data.
 - Hibernate as ORM with hibernate-panache
 - liquibase - database migration tool
 - openapi
 - JAX-RS - api layer
 - Beans Validation - to validate incoming data and by hibernate when entities are stored
 - JWT - to secure rest endpoints

**gshop-cart** - Cart microservice.

Technologies:

 - Quarkus
 - mariadb database - to store microservice data.
 - Hibernate as ORM with hibernate-panache
 - liquibase - database migration tool
 - openapi
 - JAX-RS - api layer
 - Beans Validation - to validate incoming data and by hibernate when entities are stored.
 - JWT - to secure rest endpoints

**Projects description:**

**gshop-ui** - In folder `gshop-ui\src\main\webui` there is angular application generated by angular cli. All logic is in `app.component.ts`. Model classes are in `src/app/business/gshop/entity` folder and there is only one resource class in `src/app/business/gshop/boundary/gshop.resource.ts` which represent `GshopResouce` on server side. Quinoa quarkus extension is responsible for the building process of UI.
Backend is standard quarkus application. Comminication with other microservices is done with microprofile rest client. JWT is automatically propagated to microservices.
Description of microservices api is available on http://localhost:10000/q/openapi

**gshop-games** - The microservice responsible for manage games catalog. Structure of a project is a standard BCE pattern. Application uses its own mariadb database to store games. Rest endpoint is secured by JWT.
Configuration is in `application.properties` file. As database migration tool I've use liquibase. The migration file with database structure is in `src/main/resources/db/games.xml` file. There is also sample data.
There is also integration test for the service in `CartServiceTest` class. It uses H2 in memory database as data storage. Description of microservices api is available on http://localhost:10001/q/openapi

**gshop-cart** - The microservice responsible for manage user cart. The structure is analogous to the gshop-games project. Description of microservices api is available on http://localhost:10002/q/openapi

Because of using JWT, microservices are stateless so they can be scaled horizontally by increasing instances amount. Service discovery should be realized by orchestrator such as Kubernetes or Docker Swarm.
For the sake of simplicity, I omit the topics of monitoring and tracking applications. In production application I'd use micrometer + prometeus + graphana and opentracing + jaeger.
Logs from application should by stored in centralized log management such a graylog.

**Run the application:**

1. Run games-db database in docker and expose it on local port 13306:

```docker run --detach --name games-db --env MARIADB_USER=games-user --env MARIADB_PASSWORD=games-pw --env MARIADB_ROOT_PASSWORD=games-pw --env MARIADB_DATABASE=games -p 13306:3306 mariadb:latest```

2. Run cart-db database in docker and expose it on local port 23306:

```docker run --detach --name cart-db --env MARIADB_USER=cart-user --env MARIADB_PASSWORD=cart-pw --env MARIADB_ROOT_PASSWORD=cart-pw --env MARIADB_DATABASE=cart -p 23306:3306 mariadb:latest```

3. In folder gshop-cart run `mvnw quarkus:dev` it will start cart microservice on port 10002

4. In folder gshop-games run `mvnw quarkus:dev` it will start cart microservice on port 10001

5. In folder gshop-ui run `mvnw quarkus:dev` it will start cart microservice on port 10000 - you have to wait some time because for the first time node must download half of the internet ;) When you see quarkus logo it is started.

6. In browser open url http://localhost:10000/index.html there is simple angular application. It presents all games and current cart state for user "andrzej" name of the user is hardcoded in generated jwt token. Clicking on add button causes adding game to cart.

