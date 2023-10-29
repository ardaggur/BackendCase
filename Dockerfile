FROM openjdk:17-jdk-alpine
LABEL authors="ardagur"
ADD target/trendyolBackendCase-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]