FROM mysql:latest
COPY init.sql /docker-entrypoint-initdb.d/
ENV   MYSQL_USER=mysql MYSQL_PASSWORD=password MYSQL_DATABASE=user_db  MYSQL_ROOT_PASSWORD=password
