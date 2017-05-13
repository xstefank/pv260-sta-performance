#!/bin/bash

MYSQL_HOME=$1
cd $MYSQL_HOME

echo "MYSQL_HOME=$MYSQL_HOME" 

echo "creating database pv260-library..."
bin/mysql -u root --password=root -e "create database testdb" 

echo "creating tables..."
bin/mysql -u root --password=root -v testdb < ../tables-create.sql

echo "importing test data..."
bin/mysql -u root --password=root -v testdb < ../data-import.sql



