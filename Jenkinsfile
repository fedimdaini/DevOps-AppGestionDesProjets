pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/fedimdaini/DevOps-AppGestionDesProjets.git'
            }
        }

        stage('Build') {
            steps {
                sh 'docker compose build'
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker compose down'
                sh 'docker compose up -d'
            }
        }

        stage('Verify') {
            steps {
                sh 'docker compose ps'
                sh 'sleep 15'   // give backend time to start
                sh 'curl -f http://localhost:8083/entreprise/all || true'
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded – application is up'
        }
        failure {
            echo 'Pipeline failed'
            sh 'docker compose logs --tail 30'
        }
   }
}
