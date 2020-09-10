# VProject
Layers of the architecture defined.

Authorization Code flow yet to be dubugged.(Rectified)

27-august -2020 --> OAuth implmentation, a new directory containing code for implemention of a simpler oauth client, authorization server and resource configuration.

30-aug-2020 ---> Configured Zuul server, next step is to configure Authorization server... Error: Exceeded maxRedirects. Probably stuck in a redirect loop http://localhost:8763/login (Rectified)

30-August- 2020 -->Authorization Code flow yet to be dubugged.(Rectified)

Next task --> Add ssl certificate
                |-30 aug 2020- added in api gate- product api.
                |-removed ssl for postman testing

2-sept-2020 --> Started With Dockerizing the microservices.

NOTE -->
Check if the database user exists and can connect
In MySQL, each database user is defined with IP address in it, so you can have for example a root user allowed to connect from localhost (127.0.0.1) but not from other IP addresses. With a container, you never access to the database from 127.0.0.1, it could explain the problem.

To check it, you can do the following:

1* From a terminal, connect you to your MySQL running container
docker exec -it your_container_name_or_id bash
2* In your container, connect you to the MySQL database
mysql -u your_user -p
It will ask you your password, you have to write it and press enter.

3* In your MySQL database, execute this SQL script to list all existing database users
SELECT host, user FROM mysql.user;
It will display a table, for example like this:

+------------+------------------+
| host       | user             |
+------------+------------------+
| %          | root             |
| 127.0.0.1  | root             |
| ::1        | root             |
| localhost  | mysql.sys        |
| localhost  | root             |
| localhost  | sonar            |
+------------+------------------+
It has to contain a line with your database user and '%' to works (% means "every IP addresses are allowed"). Example:

+------------+------------------+
| host       | user             |
+------------+------------------+
| %          | root             |
+------------+------------------+
My root user can connect itself from any IP addresses.

Are external connections allowed?
After that, like @ltangvald said, it could be a problem of allowing external connections to the container.

To check it, you can do the following:

1* From a terminal, connect you to your MySQL running container
docker exec -it your_container_name_or_id bash
2* In your container, run this command
mysqld --verbose --help | grep bind-address
It will display address to bind to, for example like this:

  --bind-address=name IP address to bind to.
bind-address                                                 0.0.0.0
The bind address have to be 0.0.0.0 (which means "every IP addresses") to work.

Also, a note:
Using docker-compose, if you link a volume, the parameters

    environment:
       MYSQL_ROOT_PASSWORD: 'pass'
       MYSQL_DATABASE: 'db'
       MYSQL_USER: 'user'
       MYSQL_PASSWORD: 'pass'
in your docker-compose.yml will not be used, so default user will not be created: you have to create it manually or remove the volume declaration.
