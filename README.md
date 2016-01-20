# Lunch Voting System
Implemented a JSON API using Hibernate/Spring/SpringMVC without frontend
Voting system for deciding where to have lunch

# Task
- 2 types of users: admin and regular users
- Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
- Menu changes each day (admins do the updates)
- Users can vote on which restaurant they want to have lunch at
- Only one vote counted per user
- If user votes again the same day:
  * If it is before 11:00 we asume that he changed his mind.
  * If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides new menu each day.

### Installation
##### 1. Create MySQL database "voting"
In the project directory lunch/sql/ run commands
```sh
mysql -u root -p password < create-db.sql
mysql -u root -p password < voting.sql
```
##### 2. Build Maven project
In the project directory lunch/ run command
```sh
mvn clean package
```
##### 3. Run maven project using Jetty
In the project directory lunch/ run command
```sh
mvn jetty:run
```
# REST API
#### Authentication and authorization
Spring Security is used for providing both authentication (Token Based Authentication) and authorization.
#### How Token Based Works
Token based authentication is stateless. We are not storing any information about our user on the server or in a session.
######  User Requests Access with Username / Password
```sh
curl -v -H "Content-Type: application/json" -X POST -d '{"username":"admin", "password":"admin"}' http://localhost:8080/login
```
After above request application validates credentials and provides a signed token (named as X-AUTH-TOKEN) to the client. Client stores that token and sends it along with every request. This token should be sent in the HTTP header.
# CURL commands samples
#### 1. Admin users
#### 1.1 Create/Update user
```sh
curl -H "Content-Type: application/json" \
     -H "X-AUTH-TOKEN: QTJDRkFENUExNjY2QjE0OTc3MUIzNEMxNDU4OUIzNTA2NzEySDM4MUgzODhBMTk0N0QwMTEyMkMxNDIxRDFBNjY3MTUwMjU5NDkxNzA4RQ==" \
     -X POST \
     -d '{"id": null, "fullName": "Cristiano Ronaldo", "username": "ronaldo",
           "role": {
                     "id": 2,
                     "name": "ROLE_USER",
                     "authority": "ROLE_USER"
                    }
        }' http://localhost:8080/user/add
```
#### 1.2 Get user list
```sh
curl -X GET \
     -H "Content-Type: application/json" \
     -H "X-AUTH-TOKEN: QTJDRkFENUExNjY2QjE0OTc3MUIzNEMxNDU4OUIzNTA2NzEySDM4MUgzODhBMTk0N0QwMTEyMkMxNDIxRDFBNjY3MTUwMjU5NDkxNzA4RQ==" http://localhost:8080/user/list
```
#### 1.3 Get user by {userId}
```sh
curl -X GET \
     -H "Content-Type: application/json" \
     -H "X-AUTH-TOKEN: QTJDRkFENUExNjY2QjE0OTc3MUIzNEMxNDU4OUIzNTA2NzEySDM4MUgzODhBMTk0N0QwMTEyMkMxNDIxRDFBNjY3MTUwMjU5NDkxNzA4RQ==" http://localhost:8080/user/get/2
```
#### 1.4 Create/Update Restaurant
```sh
curl -H "Content-Type: application/json" \
     -H "X-AUTH-TOKEN: QTJDRkFENUExNjY2QjE0OTc3MUIzNEMxNDU4OUIzNTA2NzEySDM4MUgzODhBMTk0N0QwMTEyMkMxNDIxRDFBNjY3MTUwMjU5NDkxNzA4RQ==" \
     -X POST \
     -d '{
           "id": 4,
           "name": "Restaurant 4",
          "dishes": [
                        {
                          "id": 9,
                          "name": "dish D11",
                          "price": 40
                        },
                        {
                          "id": 10,
                          "name": "dish D2",
                          "price": 38
                        },
                         {
                          "id": null,
                          "name": "dish new D",
                          "price": 33
                        }
                    ]
        }' http://localhost:8080/restaurant/add
```
#### 1.4 Get todays' votes list
```sh
curl -X GET \
     -H "Content-Type: application/json" \
     -H "X-AUTH-TOKEN: QTJDRkFENUExNjY2QjE0OTc3MUIzNEMxNDU4OUIzNTA2NzEySDM4MUgzODhBMTk0N0QwMTEyMkMxNDIxRDFBNjY3MTUwMjU5NDkxNzA4RQ==" http://localhost:8080/vote/today-list
```
#### 2. Regular users
#### 2.1 Voting Restaurant by {restaurantId}
```sh
curl -X GET \
     -H "Content-Type: application/json" \
     -H "X-AUTH-TOKEN: ODkwMkNBRkExMTM4RDE0OTQzMUE2NzMxMjVDMEZEQTk4Q0U0SDM4MkgzOEQ3ODVGNjFEMTBCQ0QxNDlCRTE5QUFEMUM5MjUyQUE5NjUwMQ==" http://localhost:8080/vote/get/3
```
#### 3. Admin and Regular users
#### 3.1 Get Restaurants list
```sh
curl -X GET \
     -H "Content-Type: application/json" \
     -H "X-AUTH-TOKEN: QTJDRkFENUExNjY2QjE0OTc3MUIzNEMxNDU4OUIzNTA2NzEySDM4MUgzODhBMTk0N0QwMTEyMkMxNDIxRDFBNjY3MTUwMjU5NDkxNzA4RQ==" http://localhost:8080/restaurant/list
```
#### 3.2 Get Restaurant information by {restaurantId}
```sh
curl -X GET \
     -H "Content-Type: application/json" \
     -H "X-AUTH-TOKEN: QTJDRkFENUExNjY2QjE0OTc3MUIzNEMxNDU4OUIzNTA2NzEySDM4MUgzODhBMTk0N0QwMTEyMkMxNDIxRDFBNjY3MTUwMjU5NDkxNzA4RQ==" http://localhost:8080/restaurant/get/2
```

