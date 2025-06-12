# Hotels API RESTful app (Spring Boot + H2)
_This is technical task for testing my Java possibilities_


**Queries:**
```
GET /hotels
```
- get list of all hotels with summary info
```
GET /hotels/{id}
```
- get specific hotel with full info
```
GET /search
```
- search, get list of all hotels with summary info based on params (name, brand, city, country)
```
POST /hotels
```
- add new hotel 
```
POST /hotels/{id}/amenities
```
- add list of amenities to specific hotel 
```
GET /histogram/{params}
```
- get amount of hotels grouped by each value of {params}

**Used frameworks:**
 - Spring Boot
 - Spring JPA
 - Liquibase
 - Lombok
 - JUnit

**Used database:** H2
