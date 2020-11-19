properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', numToKeepStr: '10']]])

pipeline {
    stages {
		  stage('SCM Checkout'){
		    // Clone repo
			git branch: 'master', 
			credentialsId: 'github', 
			url: 'https://github.com/sofdev/Bank-Account-Kata'
		   
		   }
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }
}