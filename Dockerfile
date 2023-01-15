FROM openjdk:11

RUN mkdir -p /app
WORKDIR /app

COPY . .
RUN ./mvnw clean package

EXPOSE 8080

ADD target/todo_backend.jar todo_backend.jar

ENTRYPOINT ["java","-jar","/todo_backend.jar"]