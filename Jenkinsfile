pipeline {
    agent any
    tools {
        maven 'maven'
    }

    stages {
        stage('Build') {
            steps {
                // Build steps, e.g., compiling code, running tests, etc.
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/rakshithbollu/EtsyJenkins/']])
                bat 'mvn clean install'
            }
        }
        stage('Build Docker image') {
            steps {
                bat 'docker build -t rakshithreddy/springdockerimg .'
            }
        }
        stage('Push Docker image to docker hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                    bat 'echo ${dockerhubpwd}'
                    bat 'docker login -u rakshithreddy -p Git@m12345'
                    bat 'docker push rakshithreddy/springdockerimg'
                }
            }
        }
    }
    }
}
