# Spring Boot Master Slave Datasource

Example of Spring Boot application using 2 datasources (1 master, 1 slave).
<br/>
The idea is implementing an `AbstractRoutingDataSource` that base on the `readOnly` value of the `@Transactional` annotation to route to master or slave datasource.

Reference: https://github.com/jonathanmdr/DynamicDataSourceRouting