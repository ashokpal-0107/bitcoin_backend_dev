FROM openjdk:11-jre
COPY target/bitcoin-price-service.jar /app/bitcoin-price-service.jar
ENTRYPOINT ["java", "-jar", "/app/bitcoin-price-service.jar"]