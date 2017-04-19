/**
 * Copyright (c) Since 2014, Power by Pw186.com
 */
package com.zw.jmsepcapp.service.base;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zw.jmsepcapp.silkie.jms.AppInfo;
import com.zw.jmsepcapp.silkie.jms.DialogModeLock;
import com.zw.jmsepcapp.silkie.jms.epc.JmsEventParam;
import com.zw.jmsepcapp.silkie.jms.handler.DefaultJmsMessageHandler;
import com.zw.jmsepcapp.silkie.jms.message.JmsBody;
import com.zw.jmsepcapp.silkie.jms.message.field.ByteDataField;
import com.zw.jmsepcapp.silkie.jms.message.impl.DefaultJmsMessage;


/**
 * 消息生产者
 * @author defier
 * 2014年12月27日 下午5:21:14
 * @version 1.0
 */
@Component
public class JmsMessageProducer {
	
	private static final Logger LOG = Logger.getLogger(JmsMessageProducer.class);
	
	@Resource
	private Queue traderNotice;
	@Resource
	private Queue traderRequest;
	@Resource
	private DialogModeLock dialogModeLock;
	@Resource
	private DefaultJmsMessageHandler jmsMessageHandler;
	
	public JmsEventParam sendRequestMessage(int tid, ByteDataField... fields) throws Exception {
		try {
			dialogModeLock.lock();
			
			DefaultJmsMessage message = new DefaultJmsMessage();
			JmsBody body = new JmsBody();
			for (ByteDataField field : fields) {
				body.addField(field);
			}
			body.setTid(tid);
			message.setBody(body);

			AppInfo srcApp = new AppInfo();
			srcApp.setId(1);
			srcApp.setType(3);
			
			AppInfo destApp = new AppInfo();
			destApp.setId(1);
			destApp.setType(1);
			
			jmsMessageHandler.setDialogResponseParam(null); // 先置空
			
			jmsMessageHandler.sendDialogRequest(traderRequest, message, srcApp, destApp);

			dialogModeLock.await();

		} catch (Exception e) {
			LOG.error("发送请求异常！！！", e);
		} finally {
			dialogModeLock.unlock();
		}
		return jmsMessageHandler.getDialogResponseParam();
	}

	public void sendNoticeMessage(int tid, ByteDataField... fields) throws Exception {
		try {
			DefaultJmsMessage message = new DefaultJmsMessage();
			JmsBody body = new JmsBody();
			for (ByteDataField field : fields) {
				body.addField(field);
			}
			body.setTid(tid);
			message.setBody(body);

			AppInfo srcApp = new AppInfo();
			srcApp.setId(1);
			srcApp.setType(3);
			
			AppInfo destApp = new AppInfo();
			destApp.setId(1);
			destApp.setType(1);

			jmsMessageHandler.sendPrivateMsg(traderNotice, message, srcApp, destApp);

		} catch (Exception e) {
			LOG.error("发送通知异常！！！", e);
		} finally {
			
		}
	}
	
}
