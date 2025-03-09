pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'gestion-etablissement'
        VERSION = "${BUILD_NUMBER}"
        DOCKER_LOCAL_REGISTRY = 'localhost:5000'
        DOCKER_HUB_REGISTRY = 'your-dockerhub-username'
        ARTIFACTORY_CREDS = credentials('artifactory-credentials')
        SONAR_TOKEN = credentials('sonar-token')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh """
                        mvn sonar:sonar \
                        -Dsonar.projectKey=gestion-etablissement \
                        -Dsonar.host.url=http://localhost:9000 \
                        -Dsonar.login=\${SONAR_TOKEN}
                    """
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                script {
                    def dockerImage
                    
                    // Build Docker image
                    dockerImage = docker.build("${DOCKER_IMAGE}:${VERSION}")
                    
                    // Push to local registry for dev/staging
                    docker.withRegistry("http://${DOCKER_LOCAL_REGISTRY}") {
                        dockerImage.push()
                        dockerImage.push('latest')
                    }
                    
                    // For prod, push to Docker Hub
                    if (env.BRANCH_NAME == 'main') {
                        docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credentials') {
                            dockerImage.push("${VERSION}")
                            dockerImage.push('latest')
                        }
                    }
                }
            }
        }

        stage('Deploy to Dev') {
            when { branch 'develop' }
            steps {
                sh """
                    docker-compose -f docker-compose.dev.yml down || true
                    docker-compose -f docker-compose.dev.yml up -d
                """
            }
        }

        stage('Deploy to Staging') {
            when { branch 'staging' }
            steps {
                sh """
                    kubectl apply -f k8s/staging/
                    kubectl set image deployment/gestion-etablissement gestion-etablissement=${DOCKER_LOCAL_REGISTRY}/${DOCKER_IMAGE}:${VERSION} -n staging
                """
            }
        }

        stage('Deploy to Production') {
            when { 
                branch 'main'
                beforeInput true
            }
            input {
                message "Deploy to production?"
                ok "Yes"
            }
            steps {
                sh """
                    terraform -chdir=terraform/prod init
                    terraform -chdir=terraform/prod apply -auto-approve
                    kubectl apply -f k8s/prod/
                    kubectl set image deployment/gestion-etablissement gestion-etablissement=${DOCKER_HUB_REGISTRY}/${DOCKER_IMAGE}:${VERSION} -n production
                """
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
} 