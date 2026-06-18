FROM eclipse-temurin:21-jre-alpine

LABEL maintainer="groupe10@indio.local"
LABEL project="INDIO Infrastructure Manager"
LABEL version="1.0.0"

WORKDIR /app

# Utilisateur non-root (principe moindre privilège)
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

COPY target/*.jar app.jar

RUN chown appuser:appgroup app.jar
USER appuser

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=10s --retries=3 \
    CMD wget -qO- http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
