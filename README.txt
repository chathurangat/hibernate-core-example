this example will demonstrate you how to use the hibernate for building the core of the project without depending on the
other layer(specially view layer/Web) frameworks.

here i have used the annotation based mapping.(NOT the XML based mapping).
i am cool person like to work with annotations and hate the load of XML mappings.


in this example, i have used the MySQL as the database layer. for your convenience, i will list down the technologies used in this example with their versions.

Hibernate 4.3.4
Maven 3
My SQL
Java 7
TestNG for Unit Testing


i have used the Generic DAO design pattern here


there are two hibernate configuration files here.
1. src/main/resources/hibernate.cfg.xml
   this is the configuration file used in the production mode.
   in production mode the application will use the session factory declared here.

2. src/test/resources/hibernate.cfg.xml
   this is the configuration file used in the test driven development mode.
   all the test cases will be executed using the session factory declared here.


make sure that you have CHANGED the following properties in the hibernate.cfg.xml before executing the test cases.


        <property name="connection.url">jdbc:mysql://localhost/DATABASE_NAME</property>
        <property name="connection.username">YOUR_MY_SQL_SERVER_USERNAME</property>
        <property name="connection.password">YOUR_MY_SQL_SERVER_PASSWORD</property>


if you have any query, feel free to contact me.

 website: www.chathurangaonline.com
    blog: chathurangat.blogspot.com
   email: chathuranga.t@gmail.com






