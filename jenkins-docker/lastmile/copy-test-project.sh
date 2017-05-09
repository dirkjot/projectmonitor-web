#!/bin/bash

set -e

JENKINS_HOME=/opt/jenkins
pushd $JENKINS_HOME
cp -f  lastmile/hudson.tasks.Maven.xml ${JENKINS_HOME}/
patch < lastmile/config.xml.patch
popd






