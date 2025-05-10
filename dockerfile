FROM eclipse-temurin:21
WORKDIR /opt/spring_boot_todo_api
COPY target/spring_boot_todo_api-0.0.1-SNAPSHOT.jar /opt/spring_boot_todo_api/api.jar
EXPOSE 8080
CMD ["java", "-jar", "/opt/spring_boot_todo_api/api.jar"]
