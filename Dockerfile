FROM eclipse-temurin:19-jdk-jammy
WORKDIR /app

COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN chmod +x mvnw

COPY src src

RUN ./mvnw clean package -DskipTests

EXPOSE 8080
ENTRYPOINT ["java","-jar","target/urlshortener-0.0.1-SNAPSHOT.jar"]
