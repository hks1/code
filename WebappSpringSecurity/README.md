# Spring Security

-

<!-- https://www.digitalocean.com/community/tutorials/spring-security-example-tutorial -->


- benefits
   - proven technology
   - prevents some of the common attacks such as CSRF, session fixation attacks
   - easy to integrate, no need to modify web application configurations, spring automatically injects security filters to the web application.
   - Provides support for authentication by different ways - in-memory, DAO, JDBC, LDAP and many more.
   - Provides option to ignore specific URL patterns, good for serving static HTML, image files.
   - Support for groups and roles.
   
- in-memory
- DAO
- JDBC


- sample data to work with JDBC [MySQL database]
- We would also need to configure JDBC DataSource as JNDI in our servlet container.

<!-- https://www.digitalocean.com/community/tutorials/tomcat-datasource-jndi-example-java -->

```sql
CREATE TABLE `Employees` (
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(20) NOT NULL DEFAULT '',
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Roles` (
  `username` varchar(20) NOT NULL DEFAULT '',
  `role` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`username`,`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `Employees` (`username`, `password`, `enabled`)
VALUES
	('pankaj', 'pankaj123', 1);

INSERT INTO `Roles` (`username`, `role`)
VALUES
	('pankaj', 'Admin'),
	('pankaj', 'CEO');

commit;
```

- create a dynamic project in Eclipse
- convert project to maven

## Maven dependencies

```pom.xml
<dependencies>
		<!-- Spring Security Artifacts - START -->
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-web</artifactId>
			<version>3.2.3.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-config</artifactId>
			<version>3.2.3.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-taglibs</artifactId>
			<version>3.0.5.RELEASE</version>
		</dependency>
		<!-- Spring Security Artifacts - END -->

		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.1</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>3.0.1</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>commons-logging</groupId>
			<artifactId>commons-logging</artifactId>
			<version>1.1.1</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>4.0.2.RELEASE</version>
		</dependency>
	</dependencies>
```

1. __spring-jdbc:__  used for JDBC operations by JDBC authentication method, requires DataSource setup as JNDI.
2. __spring-security-taglibs:__  
3. __spring-security-config__  to configure the authentication providers, whether to use JDBC, DAO, LDAP etc.
4. __spring-security-web__  This component integrates the Spring Security to the Servlet API. We need it to plugin our security configuration in web application.

## View Pages

applying authentication in all the pages other than html pages


```health.html
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Health Check</title>
</head>
<body>
	<h3>Service is up and running!!</h3>
</body>
</html>
```

```index.jsp
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="https://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="https://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
	<p>
		Hello <b><c:out value="${pageContext.request.remoteUser }"/></b>
		Roles: <b><sec:authentication property="principal.authorities"/></b>
	</p>
	
	<form action="logout" method="post">
		<input type="submit" value="Logout" />
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
	</form>

</body>
</html>
```

Spring Security takes care of CSRF attack, so when we are submitting form for logout, we are sending the CSRF token back to server to delete it. The CSRF object set by Spring Security component is _csrf and we are using it’s property name and token value to pass along in the logout request.

## UserDetailsService - DAO implementation

- need to implement `UserDetailsService` interface and provide the implementation for `loadUserByUsername()` method.

```java
package com.hks.webapp.spring.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserDetailsServiceDAO implements UserDetailsService{
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		logger.info("loadUserByusername username="+username);
		
		if(!username.equals("user1")) {
			throw new UsernameNotFoundException(username + " not found.");
		}
		
		// dummy userdetails
		return new UserDetails() {
			private static final long serialVersionUID = 123L;
			
			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean isAccountNonLocked() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean isAccountNonExpired() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public String getUsername() {
				// TODO Auto-generated method stub
				return username;
			}
			
			@Override
			public String getPassword() {
				// TODO Auto-generated method stub
				return "password";
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				// TODO Auto-generated method stub
				List<SimpleGrantedAuthority> auths = new ArrayList();
				auths.add(new SimpleGrantedAuthority("admin"));
				return auths;
			}
		};
	}

}

```


## WebSecurityConfigurer implementation

- implement `WebSecurityConfigurer` interface, or
- extend the base implementation class `WebSecurityConfigurerAdapter`

- override the methods.

```java
package com.hks.webapp.spring.security;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.hks.webapp.spring.dao.AppUserDetailsServiceDAO;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(auth);
		
		// in-memory authentication
		//auth.inMemoryAuthentication().withUser("user1").password("password").roles("USER");
		
		// using custom UserDetailsService DAO
		//auth.userDetailsService(new AppUserDetailsServiceDAO());
		
		// using JDBC
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyLocalDB");
		
		final String findUserQuery = "select username, password, enabled"
				+ "from Employees"
				+ "where username = ?";
		final String findRoles = "select username, role"
				+ "from Roles"
				+ "where username = ?";
		
		auth.jdbcAuthentication().dataSource(ds)
			.usersByUsernameQuery(findUserQuery)
			.authoritiesByUsernameQuery(findRoles);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(web);
		web
			.ignoring()
			.antMatchers("/*.html"); // ignore urls ending with .html
		
	}

}

```

- ignoring all HTML files by overriding `configure(WebSecurity web)` method.

The code shows how to plugin JDBC authentication. We need to configure it by providing DataSource. Since we are using custom tables, we are also required to provide the select queries to get the user details and it’s roles. 

in-memory and DAO based authentication are commented in above code

```
		// in-memory authentication
		//auth.inMemoryAuthentication().withUser("user1").password("password").roles("USER");
		
		// using custom UserDetailsService DAO
		//auth.userDetailsService(new AppUserDetailsServiceDAO());

```

## Integrating Spring Security Web with Servlet API

- this can be done by extending `AbstractSecurityWebApplicationInitializer` class and passing the Security configuration class in the super class constructor.

```java
package com.hks.webapp.spring.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{
	
	public SecurityWebApplicationInitializer() {
		// TODO Auto-generated constructor stub
		super(SecurityConfig.class);
	}

}

```

When our context startup, it uses ServletContext to add ContextLoaderListener listener and register our configuration class as Servlet Filter.

To use a Servlet container that doesn't support Servlet specs 3, register `DispatcherServlet` through deployment descriptor. See JavaDoc of `WebApplicationInitializer`for more details.


