FROM node as client
WORKDIR /client
COPY client .
RUN npm install
RUN npm run build

FROM maven:3.6.3-jdk-11 as server
WORKDIR /server
COPY server .
RUN mkdir -p src/main/resources/static
COPY --from=client /client/build src/main/resources/static
RUN mvn clean verify

FROM openjdk:11-jdk
COPY --from=server /server/target/TheRush*jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./app.jar"]
CMD ["--spring.profiles.active=postgres"]
