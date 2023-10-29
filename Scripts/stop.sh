#!/bin/bash

# Stop the React front-end
pm2 delete front-end

# Stop the back-end API

# Stop the database
docker stop mysql
