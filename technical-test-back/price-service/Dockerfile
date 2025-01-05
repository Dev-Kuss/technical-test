FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /workspace/app

# Copy the source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package 

FROM eclipse-temurin:21-jre-alpine
VOLUME /tmp
COPY --from=build /workspace/app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
