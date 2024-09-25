pipeline {
    agent {
        docker {
            image 'maven:3.8.5-openjdk-17'
        }
    }

    tools {
        jdk 'jdk17'
        maven 'maven'
        nodejs 'nodejs'
    }

    environment {
        JAVA_HOME = "${tool 'jdk17'}"
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }

    stages{
        stage('Build Backend'){
            steps{
                dir('backend'){
                    sh 'mvn -B -DskipTests clean package'
                }
            }
            post{
                always {
                    archiveArtifacts artifacts: 'backend/.m2/repository/**', allowEmptyArchive: true
                }
            }
        }

        stage('Test Backend'){
            steps {
                dir('backend') {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit 'backend/test-results/TEST-*.xml'
                }
            }
        }
        stage('Build Frontend App') {
            steps {
                dir('frontend') {
                    sh 'npm ci'
                    sh 'npm install --prefer-offline'
                    sh 'npm run build'
                }
            }
            post {
                always {
                    archiveArtifacts artifacts: 'frontend/dist/**', allowEmptyArchive: true
                }
            }
        }
        stage('Build and Push Docker Images') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: "docker-cred", passwordVariable: 'DOCKERHUB_PASS', usernameVariable: 'DOCKERHUB_USER')]) {

                        sh 'echo $DOCKERHUB_PASS | docker login -u $DOCKERHUB_USER --password-stdin'

                        dir('backend') {
                            sh 'mvn package'
                            sh "docker build -t $DOCKERHUB_REPO/backend-image:$GIT_COMMIT ."
                            sh "docker push $DOCKERHUB_REPO/backend-image:$GIT_COMMIT"
                        }

                        dir('frontend') {
                            sh "docker build -t $DOCKERHUB_REPO/front-image:$GIT_COMMIT ."
                            sh "docker push $DOCKERHUB_REPO/front-image:$GIT_COMMIT"
                        }
                    }
                }
            }
        }
    }
}