# springboot-keycloak-example

https://www.youtube.com/playlist?list=PLHXvj3cRjbzs8TaT-RX1qJYYK2MjRro-P

Authentication can be done in 2 ways for OAuth2 Resource
1. Jwt token
    1. The Authentication validation can be happen with in the Application by taking the property "exp" 
       from jwt token decode and will verify the jwt issuer-uri (Specified in application.yaml file)
   2. The Autherization can be happen with in Application. It has 2 types
      1. Method level Authorization
      2. Security filter level Autherization (for this configuration see in SecurityConfig.java)
2. Opaque Token
   1. The Authentication Validation can be happen in Auth Provider here in this case keycloak 
      by making another restAPI.
   2. The Autherization can be happen with in Application. It has 2 types
       1. Method level Authorization
       2. Security filter level Autherization (for this configuration see in SecurityConfig.java)


https://medium.com/javarevisited/keycloak-integration-with-spring-security-6-37999f43ec85

https://medium.com/@disa2aka/docker-deployments-for-keycloak-and-postgresql-e75707b155e5