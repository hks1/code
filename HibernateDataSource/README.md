# Hibernate Tomcat JNDI DataSource

-

<!-- https://www.digitalocean.com/community/tutorials/hibernate-tomcat-jndi-datasource-example-tutorial -->

- configure `DataSource` properties in hibernate configuration file.

## setup test database


```employee.sql
CREATE TABLE `Employee` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

INSERT INTO `Employee` (`id`, `name`, `role`, `insert_time`)
VALUES
	(3, 'Pankaj', 'CEO', now());
INSERT INTO `Employee` (`id`, `name`, `role`, `insert_time`)
VALUES
	(14, 'David', 'Developer', now());
```

## configure JNDI DataSource in tomcat container (server.xml and context.xml)

- add in `GlobalNamingResource` Element of server.xml

```server.xml
<Resource auth="Container" driverClassName="com.mysql.jdbc.Driver" global="jdbc/MyDB" maxActive="100" maxIdle="20" maxWait="10000" minIdle="5" name="jdbc/MyDB" password="demo" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/demo" username="demo"/>
```

- add in context.xml

```context.xml
<ResourceLink name="jdbc/MyLocalDB"
				global="jdbc/MyDB"
				auth="container"
				type="javax.sql.DataSource" />
```

add mysql driver jar in tomcat lib directory

## Hibernate DataSource Example Dynamic Web Project

- create a dynamic web priject in Eclipse and then configure it as Maven project.

## Hibernate Maven Dependencies

```pom.xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>HibernateDataSource</groupId>
  <artifactId>HibernateDataSource</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>war</packaging>
  <dependencies>
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>4.3.5.Final</version>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.0.5</version>
			<scope>provided</scope>
		</dependency>
	</dependencies>
  <build>
    <sourceDirectory>src</sourceDirectory>
    <plugins>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.1</version>
        <configuration>
          <release>15</release>
        </configuration>
      </plugin>
      <plugin>
        <artifactId>maven-war-plugin</artifactId>
        <version>3.2.3</version>
        <configuration>
          <warSourceDirectory>WebContent</warSourceDirectory>
        </configuration>
      </plugin>
    </plugins>
    <finalName>${project.artifactId}</finalName>
  </build>
</project>
```

## Hibernate DataSource configuration

```hibernate.cfg.xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
	"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
		"https://hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
	<session-factory>
		<property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
		<property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
		<property name="hibernate.connection.datasource">java:/comp/env/jdbc/MyLocalDB</property>
		<property name="hibernate.current_session_context_class">thread</property>
		
		<!-- Mapping with model class containing annotations -->
		<mapping class="com.hks.servlet.hibernate.model.Employee"/>
	</session-factory>
</hibernate-configuration>
```

## Model class

```java
package com.hks.servlet.hibernate.model;

import 
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Entity;

@Entity
@Table(name = "Employee",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true, length = 11)
	private int id;
	
	@Column(name = "NAME", length = 20, nullable = true)
	private String name;
	
	@Column(name="ROLE", length=20, nullable=true)
	private String role;
	
	@Column(name="insert_time", nullable=true)
	private Date insertTime;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getRole() {
		return role;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

}

```

## Hibernate DataSource Tomcat JNDI Servlet Listener

Since we have to initialize Hibernate `SessionFactory` because we can use it in the application and also when web application is destroyed, we need to destroy SessionFactory. So the best place to do this in a `ServletContextListener` implementation.

```java
package com.hks.servlet.hibernate.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger;

/**
 * Application Lifecycle Listener implementation class HibernateSessionFactoryListener
 *
 */
@WebListener
public class HibernateSessionFactoryListener implements ServletContextListener {
	
	public final Logger logger = Logger.getLogger(HibernateSessionFactoryListener.class);

    /**
     * Default constructor. 
     */
    public HibernateSessionFactoryListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent)  { 
         // TODO Auto-generated method stub
    	SessionFactory sessionFactory = (SessionFactory) servletContextEvent.getServletContext().getAttribute("SessionFactory");
    	if(sessionFactory != null && !sessionFactory.isClosed()) {
    		logger.info("Closing sessionFactory");
    		sessionFactory.close();
    	}
    	logger.info("Released Hibernate sessionFactory resource");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
         // TODO Auto-generated method stub
    	Configuration configuration = new Configuration();
    	configuration.configure("hibernate.cfg.xml");
    	logger.info("Hibernate configuration created successfully.");
    	
    	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
    			.applySettings(configuration.getProperties())
    			.build();
    	logger.info("serviceRegistry create successfully");
    	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    	logger.info("SessionFactory created successfully.");
    	
    	servletContextEvent.getServletContext().setAttribute("SessionFactory", sessionFactory);
    	logger.info("Hibernate SessionFactory Configured successfully");
    }
	
}

```

## Servlet implementation






<!--
Ref:
https://www.digitalocean.com/community/tutorials/hibernate-tutorial-for-beginners
-->