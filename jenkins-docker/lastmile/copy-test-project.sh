#!/bin/bash

set -e

JENKINS_HOME=/opt/jenkins

mkdir -p ${JENKINS_HOME}/jobs 
cp -fR /tmp/jenkins/jobs/* ${JENKINS_HOME}/jobs/  
cp -f  /tmp/jenkins/hudson.tasks.Maven.xml ${JENKINS_HOME}

pushd $JENKINS_HOME
patch < lastmile/config.xml.patch
popd

