/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.entity;

import org.hibernate.validator.constraints.Length;

import com.huojuit.hishop.common.persistence.DataEntity;

/**
 * 热门推荐Entity
 * @author daiyuxiang
 * @version 2017-06-27
 */
public class Hot extends DataEntity<Hot> {
	
	private static final long serialVersionUID = 1L;
	private String shopId;		// 店铺ID
	private String hotKey;		// 推荐key
	private String hotValue;		// 推荐value(限制4个汉字)
	
	public Hot() {
		super();
	}

	public Hot(String id){
		super(id);
	}

	@Length(min=0, max=64, message="店铺ID长度必须介于 0 和 64 之间")
	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	@Length(min=0, max=64, message="推荐key长度必须介于 0 和 64 之间")
	public String getHotKey() {
		return hotKey;
	}

	public void setHotKey(String hotKey) {
		this.hotKey = hotKey;
	}
	
	@Length(min=0, max=10, message="推荐value(限制4个汉字)长度必须介于 0 和 10 之间")
	public String getHotValue() {
		return hotValue;
	}

	public void setHotValue(String hotValue) {
		this.hotValue = hotValue;
	}
	
}