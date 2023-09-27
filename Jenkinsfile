pipeline{
    agent any
    tools{
        maven 'maven-3.9'
    }
    stages{
        stage('Build maven'){
            steps{
            checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/abhishek14june/employee-service']])
            sh 'mvn clean install'
            }
        }
    }
}