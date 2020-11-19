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
					script {
										 def build = currentBuild.rawBuild
def cause = build.getCause(hudson.model.Cause.UserIdCause.class)
def name = cause.getUserName()
echo "User: " + name
					}
					 sh '''

                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
                sh 'mvn clean'
                    git branch: 'master', url: params.GIT_URL
                }
      }
	  stage('Build') { 
	  
        steps {
        withMaven(maven: 'Maven 3.5.2'){
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