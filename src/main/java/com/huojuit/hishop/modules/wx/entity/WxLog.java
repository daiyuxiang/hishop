/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.wx.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huojuit.hishop.common.persistence.DataEntity;

/**
 * 微信日志Entity
 * @author dixyuxiang
 * @version 2016-12-13
 */
public class WxLog extends DataEntity<WxLog> {
	
	private static final long serialVersionUID = 1L;
	private String interfaceName;		// 接口名称
	private String interfaceMethod;		// 请求参数
	private String redMoney;		// 红包金额
	private String code;		// 返回code
	private String result;		// 请求结果
	private String openId;		// 微信id
	private Date createTime;		// 请求时间
	private Date updateTime;		// 更新时间
	
	public WxLog() {
		super();
	}

	public WxLog(String id){
		super(id);
	}

	@Length(min=0, max=200, message="接口名称长度必须介于 0 和 200 之间")
	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	
	@Length(min=0, max=2000, message="请求参数长度必须介于 0 和 2000 之间")
	public String getInterfaceMethod() {
		return interfaceMethod;
	}

	public void setInterfaceMethod(String interfaceMethod) {
		this.interfaceMethod = interfaceMethod;
	}
	
	@Length(min=0, max=50, message="红包金额长度必须介于 0 和 50 之间")
	public String getRedMoney() {
		return redMoney;
	}

	public void setRedMoney(String redMoney) {
		this.redMoney = redMoney;
	}
	
	@Length(min=0, max=100, message="返回code长度必须介于 0 和 100 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	@Length(min=0, max=100, message="微信id长度必须介于 0 和 100 之间")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}