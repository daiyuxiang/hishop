/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.entity;

import org.hibernate.validator.constraints.Length;

import com.huojuit.hishop.common.persistence.DataEntity;

/**
 * 合作品牌Entity
 * @author daiyuxiang
 * @version 2017-06-18
 */
public class CooperationBrand extends DataEntity<CooperationBrand> {
	
	private static final long serialVersionUID = 1L;
	private String formatId;		// 业态ID
	private String brandName;		// 品牌名称
	private String company;		// 运营公司
	private String linkUrl;		// 链接地址
	private String topFlag;		// 推荐标志
	
	public CooperationBrand() {
		super();
	}

	public CooperationBrand(String id){
		super(id);
	}

	@Length(min=0, max=64, message="业态ID长度必须介于 0 和 64 之间")
	public String getFormatId() {
		return formatId;
	}

	public void setFormatId(String formatId) {
		this.formatId = formatId;
	}
	
	@Length(min=0, max=200, message="品牌名称长度必须介于 0 和 200 之间")
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	@Length(min=0, max=200, message="运营公司长度必须介于 0 和 200 之间")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	@Length(min=0, max=200, message="链接地址长度必须介于 0 和 200 之间")
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	@Length(min=0, max=2, message="推荐标志长度必须介于 0 和 2 之间")
	public String getTopFlag() {
		return topFlag;
	}

	public void setTopFlag(String topFlag) {
		this.topFlag = topFlag;
	}
	
}