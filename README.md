
# Spring Boot & Kafka

> Simple Java implementation using Spring Kafka and Spring Boot.

### Prerequisite

* Java 1.8
* Docker
* Docker Compose

### Run the Demo

1. Build the Project
```
mvnw clean package
```

2. Launch Kafka
```
docker-compose up
```

3. Run the Publisher (in a new Shell)
```
java -jar publisher\target\publisher-0.0.1-SNAPSHOT.jar
```

4. Run the Receiver (in a new Shell)
```
java -jar receiver\target\receiver-0.0.1-SNAPSHOT.jar
```


You can terminate the processes by pressing `Ctrl-C`.
