/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.huojuit.hishop.common.persistence.DataEntity;
import com.huojuit.hishop.modules.sys.entity.Area;

/**
 * 找铺Entity
 * @author daiyuxiang
 * @version 2017-06-24
 */
public class FindShop extends DataEntity<FindShop> {
	
	private static final long serialVersionUID = 1L;
	private String purpose;		// 意向
	private String province;		// 省
	private String city;		// 城市
	private String areaId;		// 区域
	private Area town; // 镇
	private String coveredArea;		// 面积
	private String fee;		// 租金范围
	private String shopType;		// 商铺类型
	private String managementFormat;		// 经营状态
	private String openId;		// 微信ID
	private String nickName;		// 昵称
	private String mobile;		// 电话
	private String referrer; //推荐人
	
	private String areaName; //区域名称
	
	private List<Mating> matingList = Lists.newArrayList(); // 拥有配套列表

	public FindShop() {
		super();
	}

	public FindShop(String id){
		super(id);
	}

	@Length(min=0, max=50, message="意向长度必须介于 0 和 50 之间")
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	@Length(min=0, max=64, message="省长度必须介于 0 和 64 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=64, message="城市长度必须介于 0 和 64 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
		
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Length(min=0, max=64, message="面积长度必须介于 0 和 64 之间")
	public String getCoveredArea() {
		return coveredArea;
	}

	public void setCoveredArea(String coveredArea) {
		this.coveredArea =coveredArea;
	}
	
	@Length(min=0, max=64, message="租金范围长度必须介于 0 和 64 之间")
	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}
	
	@Length(min=0, max=64, message="商铺类型长度必须介于 0 和 64 之间")
	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	
	@Length(min=0, max=64, message="经营状态长度必须介于 0 和 64 之间")
	public String getManagementFormat() {
		return managementFormat;
	}

	public void setManagementFormat(String managementFormat) {
		this.managementFormat = managementFormat;
	}
	
	@Length(min=0, max=64, message="微信ID长度必须介于 0 和 64 之间")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	@Length(min=0, max=64, message="昵称长度必须介于 0 和 64 之间")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Length(min=0, max=20, message="电话长度必须介于 0 和 20 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public List<Mating> getMatingList() {
		return matingList;
	}

	public void setMatingList(List<Mating> matingList) {
		this.matingList = matingList;
	}

	@JsonIgnore
	public List<String> getMatingIdList() {
		List<String> matingIdList = Lists.newArrayList();
		for (Mating mating : matingList) {
			matingIdList.add(mating.getMatingId());
		}
		return matingIdList;
	}

	public void setMatingIdList(List<String> matingIdList) {
		matingList = Lists.newArrayList();

		if(matingIdList==null) return;
		
		for (String matingId : matingIdList) {
			Mating mating = new Mating();
			mating.setMatingId(matingId);
			matingList.add(mating);
		}

	}

	public Area getTown() {
		return town;
	}

	public void setTown(Area town) {
		this.town = town;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}
	
	
	
	
}