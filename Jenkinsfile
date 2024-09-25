pipeline {
    agent any

    environment {
        MAVEN_OPTS = "-Dmaven.test.failure.ignore=true"
        NODE_ENV = 'dev'
        MAVEN_CLI_OPTS = "-B -ntp"
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk'
    }

    tools {
        jdk 'jdk17'
        maven 'maven'
    }

    stages{
        stage('Prepare Envirement'){
            steps {
                sh 'apk update && apk add git maven openjdk17 nodejs'
                sh 'export PATH="$JAVA_HOME/bin:$PATH"'
                sh 'echo "Java version:" && java --version'
                sh 'echo "Maven version:" && mvn --version'
                sh 'echo "Docker version:" && docker --version'
                sh 'echo "Node.js version:" && node --version'
            }
        }

        stage('Build Backend'){
            steps{
                dir('backend'){
                    sh 'mvn dependency:go-offline'
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
                    sh 'apk update && apk add npm'
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