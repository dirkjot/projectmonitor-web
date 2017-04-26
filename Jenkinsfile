node {
   echo 'Branch $BRANCH_NAME, jobname $JOB_NAME'

   def mvnHome
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/projectmonitor/projectmonitor-web'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'M3'
   }
   stage('Test') {
      // Run the maven tests

         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean test"
    
   }
   stage('Results') {
      junit '**/target/surefire-reports/TEST-*.xml'
   }
}