FROM maven:3.6.3-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim
COPY --from=build /target/train-info-0.0.1-SNAPSHOT.jar train-info.jar
EXPOSE 8080
ENTRYPOINT ["java", "jar", "train-info.jar"]