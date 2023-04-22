FROM openjdk
WORKDIR /app
COPY target/mini-autorizador-0.0.1.jar /app/mini-autorizador.jar
ENTRYPOINT ["java", "-jar", "mini-autorizador.jar"]
