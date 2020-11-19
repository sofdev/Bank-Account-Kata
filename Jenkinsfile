properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', numToKeepStr: '10']]])

node {
  def mvn = tool (name: 'maven3', type: 'maven') + '/usr/share/apache-maven/bin/mvn'
	 	stage('SCM Checkout'){
	    // Clone repo
		git branch: 'master', 
		credentialsId: 'github', 
		url: 'https://github.com/sofdev/Bank-Account-Kata'
	   
	   }
        stage('Build') { 
                sh 'mvn -B -DskipTests clean package' 
        }
        stage('Test') {
                sh 'mvn test'
        }
}