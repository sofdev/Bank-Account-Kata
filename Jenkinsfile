properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', numToKeepStr: '10']]])

pipeline {
 agent any
        parameters {
        // GIT
        string(name: 'GIT_URL', defaultValue: 'gh repo clone sofdev/Bank-Account-Kata', description: 'Url git du produit')
    }
    stages{
      stage('Checkout Git repository') {
	            steps {
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