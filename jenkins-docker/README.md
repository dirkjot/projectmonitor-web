

# Running Jenkins under docker 


## Cloud Foundry Installation

To deploy to __Cloud Foundry__, you need insert come up with your own
APPNAME.  Your Jenkins will show as APPNAME.cfapps.io (on PWS,
similar for other versions of CF):

```
cf push APPNAME --docker-image dirkjot/j3 
```

Watch the logs for the admin password.  If you missed it, you can use
the web console or try `cf logs APPNAME --recent`.


### Running commmands on the Cloud Foundry container

To finish setup, we will run commands on the container by creating an ssh connection to it.
```
cf ssh APPNAME COMMANDS ...
```

See the next step below for details.



## Local Installation


First, create the docker image

```
cd jenkins-docker
docker build -t dirkjot/j3 .
```

To deploy __locally__ on port 9090:
```
docker run -it -p 9090:8080 -v /var/run/docker.sock:/var/run/docker.sock  \
  --name j3 dirkjot/j3
```

Watch the logs for the admin password.  

What this command does:
- `-it`: interactive in a terminal
- `-v /var/run..`:  create a docker volume, in this case linking the local
  machine's docker socket to the docker socket within the jenkins docker
  container
- `--name j3`: name of the container. 
- `-d` (optional): run container as a background process




### Restarting a docker container

Because the container is named, you have to remove an old one before you
start a new instance (or give the new one a different name):
```
docker rm jenkins
```

Do not use the `--rm` flag on `docker run`, as the procedure relies on
restarting your container.


### Running commmands on the local container

Similar to `cf ssh` and `cf run-task`, you can run interactive
commands in the running docker container.  This uses the already
running container to spin up a Bash shell:

```
docker exec -it jenkins /bin/bash
```

## Completing the setup - Jenkins web interface

First, point your browser to the site (APPNAME.cfapps.io or similar;
localhost:9090 for local installs).  On the 'first login' page, paste
the admin password you found in the logs.

On the next page, do not install any plugins.

Finish the setup wizard and you will land at the Jenkins main screen,
with zero jobs showing.

## Completing the setup - Running the setup script

For Cloud Foundry:
```
cf ssh APPNAME -c 'app/lastmile/copy-test-project.sh'
cf restart APPNAME
```

For local setups:
```
docker exec -it bash
# inside docker container:
./lastmile/copy-test-project.sh
exit
# back on host:
docker restart j3

```

## OPEN

- how to specify tracker project #
- deploy last mile from jenkins









# References


More syntax: https://jenkins.io/doc/book/pipeline/syntax/
