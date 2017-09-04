/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.wx.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huojuit.hishop.common.persistence.DataEntity;

/**
 * 微信tokenEntity
 * @author daiyuxiang
 * @version 2016-12-13
 */
public class WxToken extends DataEntity<WxToken> {
	
	private static final long serialVersionUID = 1L;
	private String tokenType;		// token类型
	private String tokenValue;		// token值
	private Date tokenExpire;		// token有效期
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public WxToken() {
		super();
	}

	public WxToken(String id){
		super(id);
	}

	@Length(min=0, max=200, message="token类型长度必须介于 0 和 200 之间")
	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
	@Length(min=0, max=1000, message="token值长度必须介于 0 和 1000 之间")
	public String getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTokenExpire() {
		return tokenExpire;
	}

	public void setTokenExpire(Date tokenExpire) {
		this.tokenExpire = tokenExpire;
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