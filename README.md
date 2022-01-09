# arek-homework

## Quick start

1. Build application using maven e.g. from command line you can use ./mvnw clean install
2. Run application using docker-compose file in project directory.
    * docker-compose up --build  -> run one instance of the application
    * docker-compose --file docker-compose-scale.yml up -d --build --scale shopping-list-server=2  -> run multiple instances of the application
3. Application should be available on http://localhost:9000/
     
## Database viewer

Docker compose also exposes tool for postgres viewer (pgAdmin) and it should be available here -> http://localhost:9010. You have to only configure new server there. 
 * host name/address: postgres
 * port: 5432
 * Maintenance:local_db
 * Username: admin
 * Password: admin   

## Api documentation
Documentation is available on http://localhost:9000/swagger-ui/#/

Swagger also allows you to execute your request by clicking "Try it out" button in endpoint details 


