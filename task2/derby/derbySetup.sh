#!/bin/bash

DERBY_HOME=$1

cd $DERBY_HOME

echo "creating tables for the database 'testdb'..."
java -jar lib/derbyrun.jar ij ../tables-create.sql

echo "importing test data to 'testdb'..."
java -jar lib/derbyrun.jar ij ../data-import.sql

echo "done"

