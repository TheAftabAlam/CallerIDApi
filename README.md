# CallerId API 

* We have developed this REST API for an callerID API. This API performs all the fundamental CRUD operations of callerID with user validation at every step.

## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL

## Modules

* Login, Logout Module
* User Module

## Features

*User authentication & validation with session uuid having.
* User Features:
    * User Role of the entire application
    * Only registered admins with valid session token can add/update/delete driver or customer from main database
    * Admin can access the details of different customers, drivers and trip bookings
    


## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties](MycallerIdApplication\src\main\resources\application.properties) file. 
* Update the port number, username and password as per your local database config.

```
    server.port=8888

    spring.datasource.url=jdbc:mysql://localhost:3306/cabdb;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root

```

## API Root Endpoint

`https://localhost:8888/`

`http://localhost:8888/swagger-ui.html`


## API Module Endpoints

### User Module

* `POST /addUser` : Register a new user with proper data validation and user session
* `POST /login` : user can login with mobile number and password provided at the time of registation
* `Post /logout` : Logging out user based on session token
* `POST /user/addContact` : Add Contact in the list of User.
* `GET /user/search/{name}` : A user can search for a person by name in the global database. Search results display the name,
phone number and spam likelihood for each result matching that name completely or partially.
* `GET /user/searchPerson/number={num}` : A user can search for a person by phone number in the global database. If there is a registered user with that phone number, it shows only that result. Otherwise, it shows all results matching that phone number completely - note that there can be multiple names for a particular phone number
in the global database, since contact books of multiple registered users may have different names for the same phone number.
* `POST /spam/add/{number}` :A user should be able to mark a number as spam so that other users can identify spammers via the global database. Note that the number may or may not belong to any registered user or contact - it could be a random number.



### Sample API Response for User addUser

`POST   localhost:8888/addUser`

* Request Body

```
    {
     "userName":"Amir",
     "phoneNumber":"9935895648",
     "email":"r@gmail.com",
     "password":"12346"
    }
```

* Response

```
   User Registeration Success
```



`POST   localhost:8888/login`

* Request Body

```
    { 
    "phoneNumber":"9935895648",
     "password":"12346"
    }
```

* Response

```
  CurrentUserSession(userId=16, uuid=60HYEW, localDateTime=2022-11-30T20:58:34.987496700)
```

`POST  localhost:8888/user/addContact/?key=60HYEW`


* Request Body

```
    { 
     "name":"Ravi Kumar",
     "number":"9876543210"
   
    }
```

* Response

```
 {
        "contactID": 17,
        "name": "Ravi Kumar",
        "number": "9876543210"

}

```
