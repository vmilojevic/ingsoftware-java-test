# Game Achievement API 

Ingsoftware Java Test

## Tech Stack
<pre>Java 8, Spring Boot, Maven, PostgreSQL, Liquibase, Lombok, JUnit, Mockito</pre>
## Build
<pre>mvn clean install</pre>
## Test
<pre>mvn test</pre>
## Run
<pre>mvn spring-boot:run</pre>

http://localhost:8090/

## Database

Before starting the application DB url, username and password need to be changed in application.properties

## Limitations and missing features
<pre>
Rest API documentation
Rest API Versioning
Rest API Rate Limiting
Integration testing
Achievement reordering feature
</pre>

## Achievement Reordering - possible solutions

Right now, reordering of Achievements is not supported. 
When new Achievement is created, it is given the highest display order for that game + 1

Some possible solutions could be:

1. "Head first" approach

    - Order of achievements is determined by integer display_order value.
    - Every insert or update of achievement requires updating other display_order values for that game.

2. Floating point approach

    - Order of achievements is determined by float display_order value.
    - When achievement is moved between two other achievements, 
    its display_order value is computed as: (display_order_top + display_order_bottom) / 2
    - Cons: After many reordering floating point values could exceed their precision

3. Using strings

    - Order of achievements is determined by string display_order value.
    - Reordering can be solved by appending new characters to strings.
