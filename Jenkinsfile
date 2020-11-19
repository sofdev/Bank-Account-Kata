properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', numToKeepStr: '10']]])

pipeline {
 agent any
    tools {
        maven 'Maven 3.5.2'
        jdk 'jdk11'
    }
    stages{
	 	stage('SCM Checkout'){
	    // Clone repo
		git branch: 'master', 
		credentialsId: 'github', 
		url: 'https://github.com/sofdev/Bank-Account-Kata'
	   
	   }
        stage('Build') { 
        steps {
                sh 'mvn clean install' 
            }
               
        }
        stage('Test') {
                sh 'mvn test'
        }
}
}