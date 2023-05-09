FROM openjdk:21-slim
# Копируем файл сборки приложения в контейнер
COPY target/tafl-1.0.0.jar /app/tafl.jar
# Определяем порты
EXPOSE 8080
CMD ["java", "-jar", "/app/tafl.jar"]