package com.zw.jmsepcapp.lisnter;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.Lifecycle;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.zw.jmsepcapp.silkie.jms.DefaultJmsMessageListenerContainer;


@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
	private static Logger logger = Logger.getLogger(StartupListener.class);
//	@Resource
//	JobManager jobManager;
//	@Resource
//	RedisUtil redis;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent evt) {

        // 防止启动两次

        if (evt.getApplicationContext().getParent() != null) {
        	logger.info("StartupListener启动");
        	@SuppressWarnings("resource")
    		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:spring-context.xml");
    		Lifecycle traderResponseListenerContainer = (DefaultJmsMessageListenerContainer) ac.getBean("traderResponseListenerContainer");
    		traderResponseListenerContainer.start();
    		System.out.println("activemq监听启动");
//            buildIndex();
//            Thread subThread = new Thread(new Runnable() {  
//                @Override  
//                public void run() {  
//                    try{  
//                    	JedisPubSub listener = new ChatListener();
//                    	redis.sub(listener, "test");
//                    }catch(Exception e){  
//                        e.printStackTrace();  
//                    }  
//                      
//                }  
//            });  
//            subThread.start();

        }

    }


//    private void buildIndex() {
//    	logger.info("StartupListener启动");
//    	jobManager.startJob();
//
//    }
}
