properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', numToKeepStr: '10']]])

pipeline {
 agent any
   tools {
        maven 'Maven 3.5.2'
    }
    parameters {
        // GIT
        string(name: 'GIT_URL', defaultValue: 'https://github.com/sofdev/Bank-Account-Kata.git', description: 'Url git du produit')
    }
    stages{
      stage('Checkout Git repository') {
	            steps {
					 withMaven(maven: 'Maven 3.5.2') {
              sh 'mvn -version'
                }
					
					sh 'java -version'
                    git branch: 'master', url: params.GIT_URL
                }
      }
	  stage('Build') { 
	  
        steps {
         withMaven(maven: 'Maven 3.5.2') {
                sh 'mvn clean install' 
                }
                }
            
               
      }
      stage('Test') {
        steps {
                sh 'mvn test'
                
              }
      }
	}
}