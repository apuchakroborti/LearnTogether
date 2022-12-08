This is an MCQ test-type project but in this, a user can share questions as a post, 
and his/her partners/friends can comment as an answer. 
Users can create groups and share their questions in those groups only. 
A user can give unlimited tests from an existing question bank. They can check their results to improve themselves. 

Techs:
SpringBoot, \
Hibernate, \
Spring Data JPA[ 
We can use Spring Data JPA to reduce the amount of boilerplate code required to implement the data access object (DAO) layer.            
Spring Data JPA is not a JPA provider. It is a library/framework that adds an extra layer of abstraction on the top of our JPA provider (like Hibernate). 
It uses Hibernate as a default JPA provider. ], \
Flyway, and PostgreSQL 

Steps to run this project: \
Step 1. create a database with a user using password \
$ DROP DATABASE if exists learntogether; \
$ CREATE DATABASE learntogether; \
$ CREATE USER apu WITH ENCRYPTED PASSWORD 'tigerit'; \
$ GRANT ALL PRIVILEGES ON DATABASE learntogether to apu; \
$ ALTER USER apu WITH SUPERUSER; 

Or shortcut way to create a user \
$ CREATE ROLE apu WITH LOGIN SUPERUSER PASSWORD 'tigerit';

Build project: $ mvn clean package
Run: mvn spring-boot: run 

To store all of the occupations into db from file through API call: \
API: POST http://127.0.0.1:8080/service-api/api/profession/save-from-file \
BODY: src\main\resources\profession_list.txt

Access and Refresh Token generation: for Admin user \
URL: http://127.0.0.1:8080/service-api/oauth/token \
Request Method: POST \
Headers: 
Content-Type: application/x-www-form-urlencoded \
Accept: application/json \
Authorization: Basic {{$token}} \
Here, $token: 'dGVzdC13ZWJhcHAtcnc6dGVzdC13ZWJhcHAtcnctMTIzNA==' \
How to make token: Base64Encoded string of 'test-webapp-rw:test-webapp-rw-1234' \
Body: 
grant_type: password \
username: YOUR_USERNAME (here, admin@gmail.com) \
password: YOUR_PASSWORD (1234) 
You will get the access and refresh token here

Now You can call the User related crud services: \
HEADERS:
Content-Type: application/json
Authorization: Bearer {{access_token}} 


Hazlecast Cache integration: \
1. Add the below depdendency to the pom.xml for the hazlecast secondary hibernate caching: \
<dependency>
    <groupId>com.hazelcast</groupId>
    <artifactId>hazelcast-all</artifactId>
    <version>3.12.5</version>
</dependency>

2. then add the configuration bean.
3. add the @EnableCaching to the main class
4. use the annotations: \     
    while inserting or modification of data: @CachePut(value = "userCache", key = "#inputVariableName.id") \
    while searching by id: @Cacheable(value = "userCache", key = "#id", unless = "#result==null") \
    while deleting any data: @CacheEvict(value = "userCache", key = "#id")
    

Common Error: \
Caused by: org.postgresql.util.PSQLException:  
The authentication type 10 is not supported. Check that you have configured the pg_hba.conf file to include the client's IP address or subnet, and that it is using an authentication scheme supported by the driver.

Probable Solution: \
Open postgresql.conf file location in windows: C:\Program Files\PostgreSQL\15\data

Update: \
host    all             all             127.0.0.1/32            scram-sha-256 \
to \
host    all             all             127.0.0.1/32            trust

Then restart the system
