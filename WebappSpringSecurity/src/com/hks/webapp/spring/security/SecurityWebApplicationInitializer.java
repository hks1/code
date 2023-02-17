package com.hks.webapp.spring.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{
	
	public SecurityWebApplicationInitializer() {
		// TODO Auto-generated constructor stub
		super(SecurityConfig.class);
	}

}
