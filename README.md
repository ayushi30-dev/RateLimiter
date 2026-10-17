# RateLimiter - Steps for testing
TOKEN-BUCKET ALGORITHM
1.First run the springboot application using the command
mvn spring-boot:run

2.Hit the api endpoint 5 times quickly within 1 minute using postman.
GET http://localhost:8080/api/demo

3.When we hit the api endpoint 6th time,it will give the error "429 Too Many Requests"
