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

# Prompt for MySQL environment variables
read -p "Enter your MySQL username: " mysql_user
read -s -p "Enter your MySQL password: " mysql_password

# Update and upgrade the system
echo "Updating and upgrading the system..."
run_command sudo apt update
run_command sudo apt upgrade -y

# Install required software
echo "Installing Docker and Maven..."
run_command sudo apt install -y docker.io docker-compose
run_command sudo apt install -y maven

# Start Docker and enable it to start on boot
echo "Enabling and starting Docker..."
run_command sudo systemctl start docker
run_command sudo systemctl enable docker

# Install MySQL Docker container with user-provided credentials
echo "Starting MySQL database container..."
run_command docker run --name mysql -e MYSQL_ROOT_PASSWORD="$mysql_password" -d -p 3306:3306 mysql:latest

# Build and run the back-end API
echo "Starting back-end API..."
cd ../Back-End
mvn clean install
nohup java -jar target/javalin-api-1.0.0-SNAPSHOT.jar &

# Run unit tests
echo "Running unit tests..."

# Install Node.js dependencies and start the React front-end
echo "Starting front-end..."
cd ../../front-end
run_command npm install
pm2 start npm --name "front-end" --start

# Run Selenium end-to-end tests
echo "Running end-to-end tests..."

# Reset database for production
echo "Resetting database for production..."

# Stop the back-end, front-end, and database
echo "Stopping back-end, front-end, and database..."

# Tell user if install is successful or unsuccessful (if statements will probably need to be included)
if [ $failures -gt 0 ]; then
  echo "Installation encountered $failures failures."
  read -p "Do you want to try again (t) or uninstall (u)? " choice
  if [ "$choice" == "t" ]; then
    echo "Uninstalling..."
    ./uninstall.sh
    ./install.sh
  elif [ "$choice" == "u" ]; then
    echo "Uninstalling..."
    ./uninstall.sh
  else
    echo "Invalid choice. Exiting."
  fi
else
  echo "Installation was successful."
fi
