# Prerequisite
1. Docker should be present and running on the machine.
2. Port 8989 and 5492 should be available.

# Start the services
1. Go to secure-jwt-api folder
2. docker-compose up --build

#Auth headers
Since the project uses `x-authentication-token` and the token type is `Bearer` lease provider the header as:
--header 'x-authentication-token: Bearer YOUR_TOKEN'

###Complete example
```
curl --location --request GET 'localhost:8989/users' \
--header 'x-authentication-token: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGF4aW9temVuLmNvIiwiaWF0IjoxNjU1NDkyNTc3LCJleHAiOjE2NTU1Nzg5Nzd9.BYDL8DMeYwlWHyL71_3jlJZB3EfKGD4N0ZAs4CfgIEYitDOVCtauz--AtUgQINGdfmEMZ_0jqQR5yvsgQT1MVg' \
--data-raw ''
```

# EndPoints
POST http://localhost:8989/signup

POST http://localhost:8989/login

GET http://localhost:8989/users

PUT http://localhost:8989/users

