#!/bin/bash

set -e

JENKINS_HOME=/opt/jenkins

mkdir -p ${JENKINS_HOME}/jobs 
cp -fR /tmp/jenkins/jobs/* ${JENKINS_HOME}/jobs/  
cp -f  /tmp/jenkins/hudson.tasks.Maven.xml ${JENKINS_HOME}

pushd $JENKINS_HOME
patch < lastmile/config.xml.patch
popd



-------------


cd ${JENKINS_HOME}/lastmile
tar xf jenkins.sb.tgz
cp -fR ./jenkins/jobs/sandbox ./jenkins/jobs/testAnyFeature ./jenkins/jobs/testMaster ${JENKINS_HOME}/jobs/
cp -f ./jenkins/hudson.tasks.Maven.xml ${JENKINS_HOME}/
cd ${JENKINS_HOME}
patch  < lastmile/config.xml.patch


