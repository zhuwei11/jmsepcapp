package com.zw.jmsepcapp.protocol.p120;

import com.zw.jmsepcapp.protocol.EpcPidConstant;
import com.zw.jmsepcapp.silkie.jms.message.field.ByteDataField;

/**
 * 在线账户提现
 *
 * 2016年5月25日下午2:52:49
 *
 * @author zw
 */
public class P120020 extends ByteDataField {

	/**
	 * 租户ID
	 */
	private int tenantId;
	/**
	 * 用户ID
	 * 冲突体  同一用户不能并发操作
	 */
	private int memberId;
	
	
	@Override
	public int id() {
		return EpcPidConstant.P120020;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	@Override
	protected void readData() throws Exception {
		tenantId = readInt();
		memberId = readInt();
	}

	@Override
	protected void writeData() throws Exception {
		writeInt(tenantId);
		writeInt(memberId);
	}

	

}
