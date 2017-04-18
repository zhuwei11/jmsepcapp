/**
 * Copyright (c) Since 2014, Power by Pw186.com
 */
package com.zw.jmsepcapp.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zw.jmsepcapp.protocol.EpcPidConstant;
import com.zw.jmsepcapp.protocol.EpcTidConstant;
import com.zw.jmsepcapp.protocol.p110.P110990;
import com.zw.jmsepcapp.protocol.p120.P120010;
import com.zw.jmsepcapp.protocol.p120.P120020;
import com.zw.jmsepcapp.service.AccountFund;
import com.zw.jmsepcapp.service.base.DefaultResult;
import com.zw.jmsepcapp.service.base.JmsMessageProducer;
import com.zw.jmsepcapp.service.base.Result;
import com.zw.jmsepcapp.service.base.ResultCode;
import com.zw.jmsepcapp.silkie.jms.epc.JmsEventParam;



/**
 * 帐户资金变更交易接口
 *
 * 2016年5月28日上午10:54:52
 *
 * @author zw
 */
@Service
public class DefaultAccountFund implements AccountFund {

	private static final Logger LOG = Logger.getLogger(DefaultAccountFund.class);
	@Resource
	private JmsMessageProducer traderEventProducer;
	
	@Override
	public Result recharge(int tenantId, int memberId, int rechargeId) {
		DefaultResult result = new DefaultResult();
		ResultCode resultCode = new ResultCode(ResultCode.SUCCESS, "帐户充值成功");
		try {
			P120010 p120010 = new P120010();
			p120010.setTenantId(tenantId);
			p120010.setMemberId(memberId);
			p120010.setRechargeId(rechargeId);
			JmsEventParam param = traderEventProducer.sendRequestMessage(EpcTidConstant.T320011, p120010);
			if(param == null) {
				resultCode = new ResultCode(ResultCode.SUCCESS, "帐户充值已提交,正在处理中");
			} else {
				P110990 p110990 = (P110990) param.queryFirst(EpcPidConstant.P110990);
				resultCode = new ResultCode(p110990.getRtnCode(), p110990.getRtnMsg());
			}
			
		} catch (Exception e) {
			resultCode = new ResultCode(ResultCode.FAILURE, "帐户充值失败");
			LOG.error("帐户充值异常", e);
		}
		result.setResultCode(resultCode);
		return result;
	}

	@Override
	public Result withdraw(int tenantId,int memberId) {
		DefaultResult result = new DefaultResult();
		ResultCode resultCode = new ResultCode(ResultCode.SUCCESS, "帐户充值成功");
		try {
			P120020 p120020 = new P120020();
			p120020.setTenantId(tenantId);
			p120020.setMemberId(memberId);
			JmsEventParam param = traderEventProducer.sendRequestMessage(EpcTidConstant.T320021, p120020);
			if(param == null) {
				resultCode = new ResultCode(ResultCode.SUCCESS, "帐户充值已提交,正在处理中");
			} else {
				P110990 p110990 = (P110990) param.queryFirst(EpcPidConstant.P110990);
				resultCode = new ResultCode(p110990.getRtnCode(), p110990.getRtnMsg());
			}
			
		} catch (Exception e) {
			resultCode = new ResultCode(ResultCode.FAILURE, "帐户提现失败");
			LOG.error("帐户提现异常", e);
		}
		result.setResultCode(resultCode);
		return result;
	}




}
