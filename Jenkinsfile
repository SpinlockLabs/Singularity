pipeline {
    agent any

    stages {
        stage ('Gradle clean') {
            steps {
                sh './gradlew clean'
            }
        }

        stage ('Gradle build') {
            steps {
                sh './gradlew distZip'
                archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true
            }
        }
    }
}