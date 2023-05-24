FROM gradle:jdk11

WORKDIR /app

COPY . /app

ENV DB_PATH=jdbc:sqlite:TodoSql.db

RUN gradle build

ENTRYPOINT ["java", "-jar", "/app/build/libs/demo-0.0.1-SNAPSHOT.jar"]
