FROM openjdk:21-jdk-slim

COPY ./out/artifacts/ProgettinoSER_jar/ProgettinoSER.jar /app/ProgettinoSER.jar
COPY ./Progettino/Server/Mappa-delle-antenne-in-Italia.csv /app/Mappa-delle-antenne-in-Italia.csv

WORKDIR /app

EXPOSE 1050

CMD ["java", "jar", "ProgettinoSER.jar"]