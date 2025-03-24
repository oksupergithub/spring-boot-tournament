pipeline {
    agent any

    tools {
        maven 'Maven 3.9' // On va configurer ce Maven dans Jenkins ensuite
    }

    stages {

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
