# Speedment Secure REST Example
An example project showcasing how to build a secure REST API with [Speedment](https://github.com/speedment/speedment) and [Spring Boot](https://projects.spring.io/spring-boot/).

### Usage
Clone the project and build it using Maven.

Set up the database using this SQL code:
```sql
create database `securerest`;
use `securerest`;

create table `account` (
    `id` bigint not null auto_increment primary key,
    `username` varchar(30) not null unique,
    `password` char(60) not null,
    `role` enum('USER', 'ADMIN') not null
);
```

Configure the MySQL username and password in the `application.properties`-file in case you don't use "root" and "password" as credentials.

Start the application. The following endpoints will be mapped:

* POST /account
* GET /account
* GET /account/{id}

Here are some example cURL-commands that you can try:

##### Register an Account
```
curl -X POST "http://localhost:9777/account
    ?username=my_user
    &password=my_pass"
```

##### See Information About User #1
```
curl -X  GET -u my_user:my_pass "http://localhost:9777/account/1"
```

##### See Information About All Users
```
curl -X  GET -u my_user:my_pass "http://localhost:9777/account"
```
