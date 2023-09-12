### Box Office Service

This Spring boot application enables calculation of the total cost for movie tickets based on the age groups and the number of customers.

### Prerequisites
1. Java : 17
2. Spring Boot 3
3. Database configuration is not required as the application utilizes an in-memory H2 database. 
### Setup
1. Clone [BoxOfficeManager](https://github.com/aswathyAyyappan/box_office_manager.git)
2. Run app
3. The Spring Boot application will be accessible on port 8090.
### Sample

```
POST http://localhost:8090/rest/ticket/book
```
Request Body

```json
{
  "transactionId": 1,
  "customers": [
    {
      "name": "John Smith",
      "age": 70
    },
    {
      "name": "Jane Doe",
      "age": 5
    },
    {
      "name": "Bob Doe",
      "age": 6
    }
  ]
}
```
Response
```json
{
  "transactionId": 1,
  "tickets": [
    {
      "ticketType": "Children",
      "quantity": 2,
      "totalCost": 10.00
    },
    {
      "ticketType": "Senior",
      "quantity": 1,
      "totalCost": 17.50
    }
  ],
  "totalCost": 27.50
}
```



