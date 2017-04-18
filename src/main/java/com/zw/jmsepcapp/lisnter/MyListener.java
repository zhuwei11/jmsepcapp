package com.zw.jmsepcapp.lisnter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.zw.jmsepcapp.silkie.jms.DefaultJmsMessageListenerContainer;


@Service
public class MyListener implements ServletContextListener {
	DefaultJmsMessageListenerContainer traderResponseListenerContainer;
	
	private static Logger logger = Logger.getLogger(MyListener.class);
	public void contextInitialized(ServletContextEvent evt) {
		logger.info("ApplicationListener Starting...............................");
//		@SuppressWarnings("resource")
//		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:spring-context.xml");
//		Lifecycle traderResponseListenerContainer = (DefaultJmsMessageListenerContainer) ac.getBean("traderResponseListenerContainer");
//		traderResponseListenerContainer.start();
		System.out.println("activemq监听启动");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
}
