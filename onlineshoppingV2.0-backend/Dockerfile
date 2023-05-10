FROM adoptopenjdk/openjdk8
EXPOSE 8080
ADD /target/shoppingapp-microservice-0.0.1-SNAPSHOT.jar shoppingapp-microservice.jar
ENTRYPOINT ["java", "-jar","/shoppingapp-microservice.jar"]