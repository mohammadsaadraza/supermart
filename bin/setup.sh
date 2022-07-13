#!/usr/bin/env bash

# Add script to:
# * Install dependencies
# * Build/Compile
# * Run Test Suit to validate
# This should generate the jar file needed to run the application.

WORKING_DIR="$(cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"/../supermarket
echo $WORKING_DIR
cd $WORKING_DIR

# This assumes the code to be in the folder <project_root>/supermarket, which is
# used as working directory

./gradlew build
