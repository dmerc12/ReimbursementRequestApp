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

# Compiling Java back-end API and run unit tests
echo "Running unit tests..."
cd Back-End
run_command mvn compile
run_command mvn package
run_command java -cp target/classes:target/test-classes org.junit.runner.JUnitCore CategoryDALTests 
run_command java -cp target/classes:target/test-classes org.junit.runner.JUnitCore SessionDALTests 
run_command java -cp target/classes:target/test-classes org.junit.runner.JUnitCore EmployeeDALTests 
run_command java -cp target/classes:target/test-classes org.junit.runner.JUnitCore RequestDALTests 
run_command java -cp target/classes ResetDatabaseTest
run_command java -cp target/classes:target/test-classes org.junit.runner.JUnitCore CategorySALTests 
run_command java -cp target/classes:target/test-classes org.junit.runner.JUnitCore SessionSALTests 
run_command java -cp target/classes:target/test-classes org.junit.runner.JUnitCore EmployeeSALTests 
run_command java -cp target/classes:target/test-classes org.junit.runner.JUnitCore RequestSALTests 
run_command java -cp target/classes ResetDatabaseTest

# Running the back-end API
echo "Starting back-end API..."
nohup java -cp target/classes Run &

# Install Node.js dependencies and start the React front-end
echo "Starting front-end..."
cd ../Front-End/front-end
run_command npm install
run_command pm2 start npm --name "front-end" -- dev

# Run Selenium end-to-end tests
echo "Running end-to-end tests..."
run_command mvn test -Dcucumber.features=src/test/resources/Features

# Reset database for production
echo "Resetting database for production..."
run_command java -cp target/classes ResetDatabaseProduction

# Stop the back-end, front-end, and database
echo "Stopping back-end, front-end, and database..."
run_command pm2 stop "front-end"
run_command pkill -f "java -cp target/classes Run"
run_command docker stop mysql

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
