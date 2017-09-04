/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.entity;

import org.hibernate.validator.constraints.Length;

import com.huojuit.hishop.common.persistence.DataEntity;

/**
 * 收藏Entity
 * @author daiyuxiang
 * @version 2017-06-26
 */
public class Hobby extends DataEntity<Hobby> {
	
	private static final long serialVersionUID = 1L;
	private String openId;		// open_id
	private String shopId;		// 店铺ID
	
	public Hobby() {
		super();
	}

	public Hobby(String id){
		super(id);
	}

	@Length(min=0, max=64, message="open_id长度必须介于 0 和 64 之间")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	@Length(min=0, max=64, message="店铺ID长度必须介于 0 和 64 之间")
	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
}