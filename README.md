# INDIO Infrastructure Manager

Application web de gestion de projets d'infrastructure pour **INDIO Group**.  
Développée dans le cadre de la réponse à l'appel d'offres **RFP-INDIO-2025-DSI002**.

## Stack technique

- Java 21 + Spring Boot 3.2
- Thymeleaf (vues HTML)
- H2 (base en mémoire)
- Maven

## Fonctionnalités

- Lister les projets d'infrastructure (transports, énergie, aménagement urbain)
- Créer / modifier / supprimer un projet
- Tableau de bord avec compteurs par statut
- Healthcheck via Spring Actuator (`/actuator/health`)

## Lancer en local

```bash
mvn spring-boot:run
```

Accès : http://localhost:8080

## Pipeline CI/CD

Le `Jenkinsfile` inclus décrit le pipeline complet :
- Compilation Maven
- Tests unitaires
- Analyse SonarQube + Quality Gate
- OWASP Dependency Check
- Build image Docker
- Scan Trivy
- Push Docker Hub
- Déploiement conteneur
