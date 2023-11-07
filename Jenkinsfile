pipeline {
    agent any
    
    tools {
        maven 'M2_HOME'
    }
    stages {
        stage ('GIT') { 
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
         //stage('SonarQube Analysis') {
          //  steps {
           //         sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=abdou -Dsonar.projectName=\'5GAMIX_G8_Projet\' -Dsonar.projectVersion=1.0  -Dsonar.sources=src/main/java -Dsonar.sourceEncoding=UTF-8 -Dsonar.language=java -Dsonar.java.binaries=target/classes'
          // }
         //   post {
       //         success {
         //           junit '**/target/surefire-reports/TEST-*.xml'
          //          archiveArtifacts 'target/*.jar'
        //        }
        stage('MVN SONARQUBE') {
            steps {
               script {
                   sh 'mvn -f /var/lib/jenkins/workspace/pipe/pom.xml sonar:sonar -Dsonar.login=sqa_f4f84a389a5721c2bf33df9c565380b84d45d42d'
                }
            }
        }
        stage ('Deploy to Nexus') {
            steps {
                sh 'mvn -f /var/lib/jenkins/workspace/pipe/pom.xml deploy'
            }
        }
     }
}
    
