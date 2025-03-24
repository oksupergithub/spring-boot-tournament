pipeline {
    agent any

    tools {
        maven 'Maven 3.9'
    }

    environment {
        IMAGE_NAME = 'springboot-tournament' // Nom de l’image Docker
    }

    stages {
        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    sh "docker build -t $IMAGE_NAME ."
                }
            }
        }
    }
}
