#!/bin/bash

echo "Setting environment variables..."

PGSQL_HOME=$1
export PATH=$PATH:$PGSQL_HOME/bin
export PGDATA=$PGSQL_HOME/data
export PGDATABASE=postgres
export PGUSER=postgres
export PGPORT=5432
export PGLOCALEDIR=$PGSQL_HOME/share/locale

# init DB - uncomment this if you are running this script for the first time
# echo "Initializing DB..." && $PGSQL_HOME/bin/initdb -U postgres -A trust

echo "Starting ctl..."
$PGSQL_HOME/bin/pg_ctl -D $PGSQL_HOME/data -l logfile start

echo "Press enter to exit"
read

$PGSQL_HOME/bin/pg_ctl -D $PGSQL_HOME/data stop


