pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t bitcoin-price-service .'
            }
        }
        stage('Docker Run') {
            steps {
                sh 'docker run -d -p 8080:8080 bitcoin-price-service'
            }
        }
    }
}
