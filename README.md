## Getting Started

First, create your postgres database and set your properties in application.properties file, you will need
* database URL
* database USER
* database PASSWORD

You should have a file as follows

```properties
spring.jpa.generate-ddl=false
spring.jpa.hibernate.ddl-auto=none
spring.sql.init.mode=always
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${USER}
spring.datasource.password=${PASSWORD}
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
auth0.issuer=http://localhost:8080/
auth0.apiAudience=https://contacts.localhost:8080/
server.error.include-message = always

```
In order to launch this API you will need to run this commands
```bash
mvnw clean install
```

```bash
mvnw spring-boot:run
```

Open [http://localhost:8080](http://localhost:8080) to reach endpoints. Most of endpoints are available only if you are register on the application.  Try [http://localhost:8080/comments](http://localhost:8080/comments) to ensure API is running. 