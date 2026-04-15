FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/patient-system-1.0.jar app.jar
CMD ["java", "-jar", "app.jar", "k8s"]