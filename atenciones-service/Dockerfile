FROM eclipse-temurin:26-jdk AS buildstage
RUN apt-get update && apt-get install -y maven
WORKDIR /app

COPY pom.xml .
COPY src /app/src
COPY Wallet_E2ZMYH5TEDWODP39 /app/Wallet

ENV TNS_ADMIN=/app/Wallet
RUN mvn clean package



FROM eclipse-temurin:26-jre
COPY --from=buildstage /app/target/atenciones-service-0.0.1-SNAPSHOT.jar /app/app.jar
COPY Wallet_E2ZMYH5TEDWODP39 /app/Wallet

ENV TNS_ADMIN=/app/Wallet
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/app.jar"]