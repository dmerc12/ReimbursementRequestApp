#!/bin/bash

# Start the database
docker start mysql

# Start the back-end API
cd ../Back-End

# Start the front-end
cd ../front-end
pm2 start npm --name "front-end" --start
