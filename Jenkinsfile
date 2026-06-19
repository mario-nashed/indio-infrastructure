pipeline {
    agent any

    tools {
        jdk 'jdk21'
        maven 'maven3'
    }

    environment {
        SCANNER_HOME  = tool 'sonar-scanner'
        APP_NAME      = 'indio-app'
        DOCKER_IMAGE  = 'marionashed/indio-app'
        DOCKER_TAG    = 'latest'
        SONAR_PROJECT = 'indio-infrastructure'
    }

    stages {

        stage('clean Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('checkout scm') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/mario-nashed/indio-infrastructure.git'
            }
        }

        stage('maven compile') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('maven Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Sonarqube Analysis') {
            steps {
                withSonarQubeEnv('sonar-server') {
                    sh """mvn sonar:sonar \
                        -Dsonar.projectKey=${SONAR_PROJECT} \
                        -Dsonar.projectName=${APP_NAME} \
                        -Dsonar.java.binaries=target/classes"""
                }
            }
        }

        stage('quality gate') {
            steps {
                script {
                    waitForQualityGate abortPipeline: true,
                                      credentialsId: 'sonar-token'
                }
            }
        }

        stage('Build jar file') {
            steps {
                sh 'mvn clean install -DskipTests=true'
            }
        }

        stage('OWASP Dependency Check') {
            steps {
                dependencyCheck additionalArguments: "--scan ./ --disableYarnAudit --disableNodeAudit --nvdApiKey ${NVD_API}",
                                odcInstallation: 'DP-Check'
                dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
            }
        }

        stage('Docker build') {
            steps {
                script {
                    withDockerRegistry(credentialsId: 'Docker') {
                        sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                    }
                }
            }
        }

        stage('Trivy scan') {
            steps {
                sh "trivy image --timeout 15m --severity HIGH,CRITICAL ${DOCKER_IMAGE}:${DOCKER_TAG}"
            }
        }

        stage('Docker Tag & Push') {
            steps {
                script {
                    withDockerRegistry(credentialsId: 'Docker') {
                        sh "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                    }
                }
            }
        }

        stage('Deploy to container') {
            steps {
                sh """
                    docker stop ${APP_NAME} || true
                    docker rm ${APP_NAME} || true
                    docker run -d \\
                        --name ${APP_NAME} \\
                        -p 8080:8080 \\
                        --restart unless-stopped \\
                        ${DOCKER_IMAGE}:${DOCKER_TAG}
                """
            }
        }
    }

    post {
        success {
            echo "Pipeline réussi – application déployée sur http://10.10.10.50:8080"
        }
        failure {
            echo "Pipeline en échec – vérifier les logs"
        }
        always {
            sh "docker rmi ${DOCKER_IMAGE}:${DOCKER_TAG} || true"
        }
    }
}
