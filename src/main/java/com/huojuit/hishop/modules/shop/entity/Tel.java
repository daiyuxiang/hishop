/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.huojuit.hishop.common.persistence.DataEntity;

/**
 * 电话记录Entity
 * @author daiyuxiang
 * @version 2017-07-06
 */
public class Tel extends DataEntity<Tel> {
	
	private static final long serialVersionUID = 1L;
	private Date callTime;		// 拨打时间
	private String talkTime;		// 通话时长
	private Date callDate;		// 日期
	private String caller;		// 呼叫号码
	private String callee;		// 被叫号码
	
	private Date callTimeBegin;
	private Date callTimeEnd;

	public Tel() {
		super();
	}

	public Tel(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}
	
	@Length(min=0, max=10, message="通话时长长度必须介于 0 和 10 之间")
	public String getTalkTime() {
		return talkTime;
	}

	public void setTalkTime(String talkTime) {
		this.talkTime = talkTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCallDate() {
		return callDate;
	}

	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}
	
	@Length(min=0, max=20, message="呼叫号码长度必须介于 0 和 20 之间")
	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}
	
	@Length(min=0, max=20, message="被叫号码长度必须介于 0 和 20 之间")
	public String getCallee() {
		return callee;
	}

	public void setCallee(String callee) {
		this.callee = callee;
	}

	public Date getCallTimeBegin() {
		return callTimeBegin;
	}

	public void setCallTimeBegin(Date callTimeBegin) {
		this.callTimeBegin = callTimeBegin;
	}

	public Date getCallTimeEnd() {
		return callTimeEnd;
	}

	public void setCallTimeEnd(Date callTimeEnd) {
		this.callTimeEnd = callTimeEnd;
	}

	
	
	
	
}