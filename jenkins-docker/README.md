

# Running Jenkins under docker (local unix machine)


## Prerequisites


- A docker installation
- A unix/linux/macos machine


## Start command

```
docker run -it -p 9000:8080 -v /var/run/docker.sock:/var/run/docker.sock -v optjenkins:/opt/jenkins --name jenkins renewinkler/jenkins-openjdk
```

What this does:
- `run`: run an image, namely `renewinkler/jenkins-openjdk`
- `-it`: interactive in a terminal
- `-v /var/run..`:  create a docker volume, in this case linking the local
  machine's docker socket to the docker socket within the jenkins docker
  container
- `-v optjenkins..`: create another docker volume, this one links the
  `/opt/jenkins` directory structure in the jenkins docker container to the
  `optjenkins` file storage area on the docker host.  If no file storage by 
  that name exists, it will be created.  File storage is persisted over
  docker sessions and container lifecycles.  
- `--name jenkins`: name of the container. 
- `-d` (optional): run container as a background process

## Restarting it

Because the container is named, you have to remove an old one before you
start a new instance (or give the new one a different name):
```
docker rm jenkins
```

## Running commmands on the container

Similar to `cf ssh`, you can run interactive commands in the running docker
container.  This uses the already running container to spin up a Bash shell:

```
docker exec -it jenkins /bin/bash
```

## Creating a backup

You should use the `/opt/jenkins` directory like a database, it contains all
your configuration and your credentials.

- Determine where the `optjenkins` volume is located on your host (unix
  machine):  
  `docker inspect jenkins | grep Source.*optjenkins`.   
- On a Ubuntu machine, this will point to
  `/var/lib/docker/volumes/optjenkins/_data`, which we assume as the location
  below
- Elevate to root and zip up the tree:
  `sudo tar czvf - /var/lib/docker/volumes/optjenkins/_data | cat -> "backup-$(date --iso-8601).tgz"`
- This creates a copy of the full tree, you could use `--exclude` to not zip
  up the contents of the Jenkins war file for example. 



# References

Pipeline syntax overview: https://github.com/jenkinsci/pipeline-model-definition-plugin/wiki/Syntax-Reference

More syntax: https://jenkins.io/doc/book/pipeline/syntax/
