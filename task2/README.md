
# Task 2 - Database performace comparisons

## Report

* all reports for the performace tests are located in the [report](report) directory
* summary can be found in the [report.md](report/report.md) file

## PostgreSQL setup
1. download postgres binaries from https://www.enterprisedb.com/products-services-training/pgbindownload
1. untar / unzip binary (**Note** `PGSQL_HOME` refers to the uncompressed
   directory)
1. run the postgres-startup.sh
    * **Note that when running for the first time you need to uncomment the initialization line**
    * `./postgres-startup.sh $PGSQL_HOME`
1. import sample data into the DB
    * `./initPostgres.sh $PGSQL_HOME`

## MySQL setup
1. Download MySQL Community Server
    * https://dev.mysql.com/downloads/mysql/
1. add mysql user and group
    * `groupadd mysql`
    * `useradd -r -g mysql -s /bin/false mysql`
1. untar / unzip mySQL server (**Note** `MYSQL_HOME` refers to the uncompressed
   directory)
1. `cd $MYSQL_HOME`
1. initialize server
    * `bin/mysqld --initialize --user=mysql --basedir=$MYSQL_HOME`
    * save the temporary generated password for the root user
        * [Note] A temporary password is generated for root@localhost: _
1. start daemon
    * `bin/mysqld_safe --user=mysql --basedir=$MYSQL_HOME &`
1. change the MySQL server root password
    * `bin/mysql -u root -p -e "ALTER USER 'root'@'localhost' IDENTIFIED BY 'root'" --connect-expired-password`
    * you will be asked to enter the temporary password
    * you can test the password by enterint mysql CLI - `bin/mysql -u root -p`
        * the password should be "root"
1. `cd repository_dir/task2/mysql`
1. run the mysql-setup script
    * `./mysql-setup.sh $MYSQL_HOME`

* **Note** MySQL daemon is still running as background process

## Derby setup
1. Download Derby server from https://db.apache.org/derby/derby_downloads.html
1. untar / unzip server to `DERBY_HOME`
1. `./derbySetup.sh $DERBY_HOME`
1. `cd $DERBY_HOME`
1. `bin/startNerworkServer`






