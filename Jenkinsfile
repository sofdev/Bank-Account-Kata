properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', numToKeepStr: '10']]])

pipeline {
 agent any
 
      tools { 
        maven 'Maven 3.5.4' 
        java 'jdk-11.0.1'
    }
 
    parameters {
        // GIT
        string(name: 'GIT_URL', defaultValue: 'https://github.com/sofdev/Bank-Account-Kata.git', description: 'Url git du produit')
    }
    stages{
      stage('Checkout Git repository') {
	            steps {
					
			 sh '''
					echo "JAVA = ${JAVA_HOME}"
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
                    git branch: 'master', url: params.GIT_URL
                }
      }
	  stage('Build') { 
	  
        steps {
        withMaven(maven: 'Maven 3.5.4'){
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