FROM openjdk:17

WORKDIR /app

COPY target/fantasy-0.0.1-SNAPSHOT.jar fantasy_game.jar

ENTRYPOINT [ "java", "-jar",  "fantasy_game.jar"]