/**
 * 
 */
package com.zw.jmsepcapp.silkie.jms;

import com.zw.jmsepcapp.silkie.epc.EpcEvent;
import com.zw.jmsepcapp.silkie.jms.message.field.DataField;

/**
 * 协议工厂
 * @author zw
 * 2011-4-15 下午02:28:19
 * @since 1.0
 */
public interface EventFieldFactory {
	
	/**
	 * 创建协议编码解码对象
	 * @param fid
	 * @return
	 * @throws Exception
	 */
	public DataField createField(int fid) throws Exception;
	
	/**
	 * 创建消息驱动的事件
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	public EpcEvent createEvent(int tid) throws Exception;
	
}
