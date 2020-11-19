properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', numToKeepStr: '10']]])

node {
	 	stage('SCM Checkout'){
	    // Clone repo
		git branch: 'master', 
		credentialsId: 'github', 
		url: 'https://github.com/sofdev/Bank-Account-Kata'
	   
	   }
        stage('Build') { 
        withMaven {
   		sh 'mvn -B -DskipTests clean package'
        }
               
        }
        stage('Test') {
                sh 'mvn test'
        }
}