/**
 * 
 */
package com.silu.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author 24209
 *
 */
@WebListener("初始化 相关 配置在 application")
public class InitWebListener implements ServletContextListener {
	@Value("${app.host}")
	private String host;
	private static Logger logger = Logger.getLogger(InitWebListener.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stubS
		logger.info("App is Start ...... application host =》" + host);

		sce.getServletContext().setAttribute("host", host);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
