FROM openjdk:8

ADD ./H2WebRAPI.jar JavaRAPI.jar

EXPOSE 8080

CMD ["java", "-jar", "/JavaRAPI.jar"]