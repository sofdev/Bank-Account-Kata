properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', numToKeepStr: '10']]])

pipeline {
 agent any
    parameters {
        // GIT
        string(name: 'GIT_URL', defaultValue: 'https://github.com/sofdev/Bank-Account-Kata.git', description: 'Url git du produit')
    }
    stages{
      stage('Checkout Git repository') {
	            steps {
					
					sh 'java -version'
                    git branch: 'master', url: params.GIT_URL
                }
      }
	  stage('Build') { 
	  
        steps {
                sh 'mvn clean install' 
                
                }
            
               
      }
      stage('Test') {
        steps {
                sh 'mvn test'
                
              }
      }
	}
}