FROM openjdk:11
EXPOSE 8080
ADD target/todo_backend.jar todo_backend.jar
ENTRYPOINT ["java","-jar","/todo_backend.jar"]