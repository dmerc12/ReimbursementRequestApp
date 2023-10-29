#!/bin/bash

# Stop the front-end
echo "Stopping front-end..."
pm2 stop front-end

# Stop the back-end API
echo "Stopping back-end..."
pkill -f "java -cp target/classes Run"

# Stop the database
echo "Stopping database..."
docker stop mysql

# Alert user of completion
echo "Application stopped!"
