pipeline {
    agent any

    options {
        buildDiscarder(logRotator(artifactNumToKeepStr: '5'))
    }

    stages {
        stage ('Gradle clean') {
            steps {
                sh './gradlew clean'
            }
        }

        stage ('Gradle build') {
            steps {
                sh './gradlew jar'
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true
                }
            }
        }

        stage ('Gradle test') {
            steps {
                sh './gradlew test'
            }
            post {
                always {
                    junit '**/build/test-results/test/*.xml'
                }
            }
        }
    }
}