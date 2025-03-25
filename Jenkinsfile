pipeline {
    agent any

    tools {
        maven 'Maven 3.9'
    }

    environment {
        IMAGE_NAME = 'springboot-tournament'
        CONTAINER_NAME = 'springboot-tournament-container'
    }

    stages {
        stage('Vérification Docker') {
            steps {
                sh '''
                echo "--- Utilisateur courant ---"
                whoami
                echo "--- Version de Docker ---"
                docker --version
                echo "--- Liste des containers ---"
                docker ps -a
                '''
            }
        }
        stage('Build App') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }


        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Run Docker Container') {
            steps {
                // Stop container s’il existe déjà
                sh '''
                    docker stop $CONTAINER_NAME || true
                    docker rm $CONTAINER_NAME || true
                    docker run -d --name $CONTAINER_NAME -p 8082:8080 $IMAGE_NAME
                '''
            }
        }
        stage('Docker Test') {
            steps {
                sh 'whoami && docker info && docker ps -a'
            }
        }
        stage('Liste containers') {
            steps {
                sh 'docker ps -a'
            }
        }
    }
}
