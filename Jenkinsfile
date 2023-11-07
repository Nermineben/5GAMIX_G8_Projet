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
    }
}