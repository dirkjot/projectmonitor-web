# Using lastmile with NGINX

Lastmile requires a json file to be served at `${SERVER_ROOT}/info`.
This file should contain the SHA and story that this build was based
on.


## Serving info.json from the /info endpoint

If you can create the correct json in a file at build time (see
below), you can make NGINX serve this file at the right location:
- Make sure `application/json json` appears in your `mime.types` file
- Create an `info` directory next to your main content directory.
- In this directory, create a file `info.json` with the information you want to serve.
- Include the following in your `nginx.conf` file, ideally as the
  first `location` under your main `server` directive:
```
  location  /info {
    root /home/vcap/app; index info.json; 
  } 
```
  Here, `/home/vcap/app` should refer to the parent directory of the
  `info` directory you just created.
- Restart the server.  There are many ways, `kill -HUP
  <MASTER_PROCESS>` is one of them.


## Creating info.json at build time

A simple script has been provided as `create-info-json.sh`, which will
take the value of the environment variables `$storySHA` and
`$pivotalTrackerStoryID` and format those into json.

Example invocation:
```
${JENKINS_HOME}/lastmile/create-info-json.sh info/info.json
```


