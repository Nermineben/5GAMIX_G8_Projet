pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
    stages {
        stage ('Checkout to SCM') { 
            steps {
                git branch: 'abdouabbdelrahim-5GAMIX-G8', url: 'https://github.com/Nermineben/5GAMIX_G8_Projet.git'
                sh 'git checkout abdouabbdelrahim-5GAMIX-G8'
                sh 'ls -la'
                sh "echo 'Hello World'"
            }
        }
        stage ('Build') { 
            steps {
                sh 'mvn clean package'
            }
        }
         stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarInstallation') {
                    sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=abdou -Dsonar.projectName=\'5GAMIX_G8_Projet\' -Dsonar.projectVersion=1.0  -Dsonar.sources=src/main/java -Dsonar.sourceEncoding=UTF-8 -Dsonar.language=java -Dsonar.java.binaries=target/classes'
                }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}