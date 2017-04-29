# based on renewinkler/jenkins-openjdk

FROM openjdk:8

ENV DOCKER_VERSION 1.12.0
ENV DOCKER_COMPOSE_VERSION 1.8.0
ENV DOCKER_MACHINE_VERSION 0.8.0
ENV MAVEN_HOME /opt/maven
ENV MAVEN_VERSION 3.3.9
ENV JENKINS_HOME /opt/jenkins
ENV JENKINS_MIRROR http://mirrors.jenkins-ci.org

WORKDIR /home/toolbox/


# Tools
RUN     apt-get update -qq \
    &&  apt-get install -qq curl wget git joe iputils-ping net-tools patch \
    &&  apt-get clean \
    &&  curl -sf -o /opt/apache-maven-bin.tar.gz http://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
    &&  tar xzf /opt/apache-maven-bin.tar.gz -C /opt/ \
    &&  rm /opt/apache-maven-bin.tar.gz \
    &&  ln -s /opt/apache-maven-${MAVEN_VERSION} /opt/maven	\
    &&  curl -L -o /tmp/docker-latest.tgz https://get.docker.com/builds/Linux/x86_64/docker-${DOCKER_VERSION}.tgz  \
    && 	tar -xvzf /tmp/docker-latest.tgz \
    &&	mv docker/* /usr/bin/ \
    &&  curl -L https://github.com/docker/compose/releases/download/1.8.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose \
    &&	chmod +x /usr/local/bin/docker-compose \
    &&  curl -L https://github.com/docker/machine/releases/download/v${DOCKER_MACHINE_VERSION}/docker-machine-`uname -s`-`uname -m` > /usr/local/bin/docker-machine \
    &&	chmod +x /usr/local/bin/docker-machine \
    &&  mkdir -p $JENKINS_HOME \
    &&  curl -sf -o /opt/jenkins/jenkins.war -L $JENKINS_MIRROR/war/latest/jenkins.war \
    &&  mkdir -p $JENKINS_HOME/plugins; for plugin in  \
            workflow-durable-task-step git-server pipeline-model-extensions  \
            workflow-multibranch docker-workflow github-organization-folder  \
            pipeline-input-step gradle workflow-aggregator pipeline-rest-api  \
            resource-disposer antisamy-markup-formatter pipeline-graph-analysis  \
            workflow-scm-step pipeline-model-definition run-condition pam-auth  \
            workflow-basic-steps plain-credentials jquery-detached scm-api  \
            matrix-project pipeline-stage-step docker-commons structs timestamper  \
            icon-shim windows-slaves workflow-api pipeline-model-declarative-agent  \
            workflow-cps-global-lib ant pipeline-stage-view git token-macro  \
            conditional-buildstep maven-plugin script-security  \
            external-monitor-job ldap pipeline-github-lib workflow-support  \
            credentials handlebars ssh display-url-api pipeline-milestone-step  \
            pipeline-build-step mailer authentication-tokens greenballs github-api  \
            momentjs workflow-cps branch-api ace-editor build-name-setter  \
            bouncycastle-api dashboard-view ws-cleanup junit github-branch-source  \
            email-ext credentials-binding github pipeline-model-api build-timeout  \
            cloudbees-folder matrix-auth git-client workflow-job workflow-step-api  \
            pipeline-stage-tags-metadata ssh-credentials javadoc ssh-slaves  \
            durable-task  ;  \
	do echo ${plugin}; curl -sf -o $JENKINS_HOME/plugins/${plugin}.hpi -L $JENKINS_MIRROR/plugins/${plugin}/latest/${plugin}.hpi; done  \
    &&  mkdir -p ${JENKINS_HOME}/lastmile

COPY jenkins.sb.tgz .
COPY lastmile/* ${JENKINS_HOME}/lastmile/

RUN     tar xf jenkins.sb.tgz -C /tmp  \
    &&  rm jenkins.sb.tgz   \
    &&  chmod a+x ${JENKINS_HOME}/lastmile/*.sh
    
VOLUME $JENKINS_HOME/data
WORKDIR $JENKINS_HOME
EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=10s CMD curl --fail http://localhost:8080/ || exit 1
CMD [ "java", "-jar", "jenkins.war" ]


# docker build -t dirkjot/j3 .
# docker run -it -p 9090:8080 -v /var/run/docker.sock:/var/run/docker.sock  --name j3 dirkjot/j3


# TODO we should run as user jenkins
