pipeline {
    agent any
    stages {
        stage('Testing maven') {
            steps {
                echo 'hhhhhhhhh'
            }
        }

        stage('Checkout from GitHub') {
            steps {
                script {
                    // Cloner le référentiel GitHub en spécifiant l'URL
                    checkout([$class: 'GitSCM', branches: [[name: 'AsmaSrairi5Gamix']], userRemoteConfigs: [[url: 'https://github.com/Nermineben/5GAMIX_G8_Projet']]])
                }
            }
        }

            stage('Maven Clean') {
            steps {
                sh 'mvn -f /var/lib/jenkins/workspace/asmaspring/pom.xml clean'
            }
            }

        stage('Maven Compile') {
            steps {
                sh 'mvn -f /var/lib/jenkins/workspace/asmaspring/pom.xml compile'
            }
        }


        stage('JUNIT/MOCKITO') {
            steps {
                script {
                    sh 'mvn -f /var/lib/jenkins/workspace/asmaspring/pom.xml clean test'
                }
            }
        }
                stage('MVN SONARQUBE') {
                    steps {
                        script {
                            sh 'mvn -f /var/lib/jenkins/workspace/asmaspring/pom.xml sonar:sonar -Dsonar.login=sqa_ce78aa88c949d6490d2600eaefa801e9039b12b6'
                        }
                    }
                }
                   stage('Nexus') {
                            steps {
                                script {
                                    sh 'mvn -f /var/lib/jenkins/workspace/asmaspring/pom.xml deploy'
                                }
                            }
                        }

    // Ajoutez d'autres étapes de votre pipeline ici
    }
    post {
        always {
            junit '**/target/surefire-reports/**/*.xml'
        }
    }
}
