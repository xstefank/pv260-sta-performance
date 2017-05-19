
#  PerfCake Database performance comparison

## Used hardware
* Lenovo ThinkPad T440s
    * Intel(R) Core(TM) i7-4600U CPU @ 2.10 GHz
    * RAM - 12 GiB

# Vertx library application

## PostgreSQL

![PostgreSQL throughput](charts/postgres-throughput.png)

![PostgreSQL response time](charts/postgres-resp-time.png)

![PostgreSQL memory usage](charts/postgres-memory.png)


## MySQL

![MySQL throughput](charts/mysql-throughput.png)

![MySQL response time](charts/mysql-resp-time.png)

![MySQL memory usage](charts/mysql-memory.png)

## Derby

![Derby throughput](charts/derby-throughput.png)

![Derby response time](charts/derby-resp-time.png)

![Derby memory usage](charts/derby-memory.png)


# JDBC sender
 
## PostgreSQL

![PostgreSQL JDBC throughput](charts/postgres-jdbc-throughput.png)

![PostgreSQL JDBC throughput](charts/postgres-jdbc-resp-time.png)

## MySQL

![MySQL JDBC throughput](charts/mysql-jdbc-throughput.png)

![MySQL JDBC response time](charts/mysql-jdbc-resp-time.png)

## Derby

![Derby JDBC throughput](charts/derby-jdbc-throughput.png)

![Derby JDBC response time](charts/derby-jdbc-resp-time.png)

### Final remarks

As the Vertx application is generating SQL queries by random year on each request it is in my opinion not a valid performance measure. The query is selection loans for books published after some random year which could mean in one case nothing and in second request a whole database. We can then see and possibly compare only the peek cases shown in the diagrams in which none of databases seems to go very distant from others. One interesting observation can be the MySQL is using constantly more memory then PostgreSQL and Derby.

Second measurements made by the Perfcake's JdbcSender are more accurate. We've created a separate scenario and message containg a SQL query. Each query retrieves around 4000 lines from cross join of all tables (similar to vertx app query). Again MySQL is a little bit behind other databases in the throughtput measures. For the PostgreSQL and Derby it isteresting to see the peeks in iterations per second where for a few moments the throughput is enourmously scaled.
 
