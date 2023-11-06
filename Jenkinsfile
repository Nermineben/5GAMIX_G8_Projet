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

                   stage('Docker Image') {
                            steps {
                                  script {
                                    dir('/var/lib/jenkins/workspace/asmaspring') {
                                    sh 'docker build -t asmasrairi/srairiasma5gamixg8kaddem:1.0 -f Dockerfile .'
                                    }
                                  }
                                }
                            }
                   stage('Docker Hub') {
                             steps {
                                   withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: '19451945.', usernameVariable: 'asmasrairi')]) {
                                   sh 'docker login -u asmasrairi -p 19451945.'
                                   sh 'docker push asmasrairi/srairiasma5gamixg8kaddem:1.0'

                                    }
                                  }
                                }
                           stage('Docker-compose') {
                               steps {
                                   script {
                                       // Change the working directory to the directory containing docker-compose.yml
                                       dir('/var/lib/jenkins/workspace/asmaspring') {
                                           // Execute the docker-compose command
                                           sh 'docker compose up -d'
                                       }
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
