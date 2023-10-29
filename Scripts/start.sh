#!/bin/bash

# Start the database
echo "Starting database..."
docker start mysql

# Start the back-end API
echo "Starting back-end API..."
cd ../Back-End
nohup java -cp target/classes Run &

# Start the front-end
echo "Starting front-end..."
cd ../front-end
pm2 start npm --name "front-end" --start

# Alert the user of completion
echo "Application started!"
