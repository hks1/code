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
