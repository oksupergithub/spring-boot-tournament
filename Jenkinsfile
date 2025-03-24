pipeline {
    agent any

    tools {
        maven 'Maven 3.9' // On va configurer ce Maven dans Jenkins ensuite
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/oksupergithub/spring-boot-tournament.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
