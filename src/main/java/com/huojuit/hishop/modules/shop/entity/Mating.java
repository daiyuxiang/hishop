/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.entity;

import org.hibernate.validator.constraints.Length;

import com.huojuit.hishop.common.persistence.DataEntity;

/**
 * 商铺配套Entity
 * @author daiyuxiang
 * @version 2017-06-27
 */
public class Mating extends DataEntity<Mating> {
	
	private static final long serialVersionUID = 1L;
	private String shopId;		// 店铺ID
	private String matingId;		// 配套ID
	private String sourceType;		// 配套来源类型(1:新增/转铺  2:找铺)
	
	public Mating() {
		super();
	}

	public Mating(String id){
		super(id);
	}

	@Length(min=0, max=64, message="店铺ID长度必须介于 0 和 64 之间")
	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	@Length(min=0, max=64, message="配套ID长度必须介于 0 和 64 之间")
	public String getMatingId() {
		return matingId;
	}

	public void setMatingId(String matingId) {
		this.matingId = matingId;
	}
	
	@Length(min=0, max=2, message="配套来源类型(1:新增/转铺  2:找铺)长度必须介于 0 和 2 之间")
	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
}