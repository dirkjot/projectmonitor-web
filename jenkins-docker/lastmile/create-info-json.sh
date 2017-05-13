#!/bin/bash
#
# Create  info.json file for consumption by LastMile
#
# The first and only argument is the location and name of the file to write
#
# The script reads environment variables ${storySHA} and #{pivotalTrackerStoryID} to create the file

cat > $1  <<EOF
  { "storySHA": "${storySHA}",
    "pivotalTrackerStoryID": "${pivotalTrackerStoryID}"
  }
EOF
