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
                sh 'docker rm -f mysql-db backend-api frontend-ui 2>/dev/null || true'
                sh 'docker compose down --remove-orphans || true'
                sh 'docker compose up -d'
            }
        }

        stage('Verify') {
            steps {
                sh 'docker compose ps'
                script {
                    def maxAttempts = 60
                    def attempt = 0
                    def ready = false
                    while (attempt < maxAttempts && !ready) {
                        attempt++
                        def status = sh(
                            script: 'curl -s -o /dev/null -w "%{http_code}" http://localhost:8083/entreprise/all || echo "000"',
                            returnStdout: true
                        ).trim()
                        if (status == '200') {
                            echo "Backend is ready after ${attempt} attempt(s)"
                            ready = true
                        } else {
                            echo "Attempt ${attempt}/${maxAttempts}: backend not ready yet (HTTP ${status})"
                            sleep 10
                        }
                    }
                    if (!ready) {
                        error 'Backend did not become ready within the timeout'
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded – application is up'
        }
        failure {
            echo 'Pipeline failed'
            sh 'docker compose logs --tail 30 || true'
        }
    }
}
