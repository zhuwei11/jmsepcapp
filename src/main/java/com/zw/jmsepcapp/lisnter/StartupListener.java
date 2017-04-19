package com.zw.jmsepcapp.lisnter;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.Lifecycle;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.zw.jmsepcapp.silkie.jms.DefaultJmsMessageListenerContainer;
import com.zw.jmsepcapp.spring.SpringBeanManager;


@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
	private static Logger logger = Logger.getLogger(StartupListener.class);
//	@Resource
//	JobManager jobManager;
//	@Resource
//	RedisUtil redis;
	@Resource
	DefaultJmsMessageListenerContainer traderResponseListenerContainer;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent evt) {

        // 防止启动两次

        if (evt.getApplicationContext().getParent() != null) {
    		Lifecycle traderListener = traderResponseListenerContainer;
    		traderListener.start();
    		logger.info("StartupListener启动");
    		logger.info("activemq监听启动");
    		System.out.println(traderResponseListenerContainer == SpringBeanManager.getBean("traderResponseListenerContainer"));
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
