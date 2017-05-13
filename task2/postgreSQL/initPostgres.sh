#!/bin/bash

PGSQL_HOME=$1

echo "Creating tables..."
$PGSQL_HOME/bin/psql -U postgres -d postgres -a -f tables-create.sql

echo "Importing sample data"
$PGSQL_HOME/bin/psql -U postgres -d postgres -a -f data-import.sql


