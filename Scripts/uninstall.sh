#!/bin/bash

# Initialize a variable to count failures
failures=0

# Function to run a command and check for errors
run_command() {
  "$@"
  local status=$?
  if [ $status -ne 0 ]; then
    echo "Error: $1 command failed with status $status."
    failures=failures+1
    exit "total failures: $failures"
  fi
}

# Stop front-end (if running)
echo "Stopping front-end (if running)..."
run_command pm2 stop front-End

# Stop the back-end(if running)
echo "Stopping back-end (if running)..."
run_command pkill -f "java -cp target/classes Run"

# Stop and remove Docker database container
echo "Stopping and removing MySQL database container"
run_command docker stop mysql
run_command docker rm mysql

# Remove installed software and dependencies
echo "Removing Docker..."
run_command sudo apt remove -y docker.io docker-compose

# Clean up any additional files or configurations
echo "Removing generated files..."
run_command git checkout main
run_command git pull

# Check the number of failures and prompt the user
if [ $failures -gt 0 ]; then
    echo "Uninstallation encountered $failures failures."
    read -p "Do you want to try uninstalling again (y) or quit (q)? " choice
    if [ "$choice" == "y" ]; then
        ./uninstall.sh
    else
        echo "Existing uninstallation."
    fi
else
    echo "Uninstallation successful."
fi
