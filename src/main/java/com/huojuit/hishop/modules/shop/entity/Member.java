/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.huojuit.hishop.common.persistence.DataEntity;
import com.huojuit.hishop.common.utils.excel.annotation.ExcelField;
import com.huojuit.hishop.modules.sys.entity.Area;

/**
 * 会员Entity
 * 
 * @author daiyuxiang
 * @version 2017-06-20
 */
public class Member extends DataEntity<Member> {

	private static final long serialVersionUID = 1L;
	private String openId; // 微信ID
	private String nickName; // 昵称
	private String mobile; // 电话
	private String saleFlag; // 转铺申请标志(0- 1待审核 2通过 3不通过)
	private String publishSaleFlag; // 一键转铺申请标志(0- 1待审核 2通过 3不通过)
	private String findFlag; // 找铺申请标志 (1未审核 2没匹配 3已匹配)
	private String publishFindFlag; // 一键找铺申请标志(0未申请 1申请 2处理完成)
	private String shopId; // 转铺ID
	private String findId; // 找铺ID
	private Date shopDate; // 转铺时间
	private Date findDate; // 找铺时间
	private Date lastDate; // 最后浏览时间

	private String publishAndSaleFlag; // 查询条件 转铺和一键转铺
	private String publishAndFindFlag; // 查询条件 找铺和一键找铺

	private String shopName; // 店铺名称
	private String showFlag; // 审核状态
	private Date passDate; // 发布时间
	private Date goldDate; // 金牌通过时间

	private Area area; // 查询条件 区域
	private String shopType; // 查询条件 店铺类型

	private String saleType;
	private String saleMember;
	private String findType;

	private String referrer; // 推荐人
	private Date shopDateBegin; // 转铺开始时间
	private Date shopDateEnd; // 转铺结束时间
	private Date findDateBegin; // 找铺开始时间
	private Date findDateEnd; // 找铺结束时间
	private Date lastDateBegin; // 最后登录开始时间
	private Date lastDateEnd; // 最后登录结束时间
	
	private String tempFollowNote; //跟进内容
	private Date passDateBegin;  // 发布开始时间
	private Date passDateEnd; // 发布结束时间
	private Date goldDateBegin; //金牌通过开始时间
	private Date goldDateEnd;	//金牌通过结束时间
	
	
	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getSaleMember() {
		return saleMember;
	}

	public void setSaleMember(String saleMember) {
		this.saleMember = saleMember;
	}

	public String getFindType() {
		return findType;
	}

	public void setFindType(String findType) {
		this.findType = findType;
	}

	public Member() {
		super();
	}

	public Member(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "微信ID长度必须介于 0 和 64 之间")
	@ExcelField(title = "微信ID", align = 2, sort = 10)
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Length(min = 0, max = 64, message = "昵称长度必须介于 0 和 64 之间")
	@ExcelField(title = "昵称", align = 2, sort = 10)
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Length(min = 0, max = 20, message = "电话长度必须介于 0 和 20 之间")
	@ExcelField(title = "电话", align = 2, sort = 10)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSaleFlag() {
		return saleFlag;
	}

	public void setSaleFlag(String saleFlag) {
		this.saleFlag = saleFlag;
	}

	public String getFindFlag() {
		return findFlag;
	}

	public void setFindFlag(String findFlag) {
		this.findFlag = findFlag;
	}

	public String getPublishSaleFlag() {
		return publishSaleFlag;
	}

	public void setPublishSaleFlag(String publishSaleFlag) {
		this.publishSaleFlag = publishSaleFlag;
	}

	public String getPublishFindFlag() {
		return publishFindFlag;
	}

	public void setPublishFindFlag(String publishFindFlag) {
		this.publishFindFlag = publishFindFlag;
	}

	public String getPublishAndSaleFlag() {
		return publishAndSaleFlag;
	}

	public void setPublishAndSaleFlag(String publishAndSaleFlag) {
		this.publishAndSaleFlag = publishAndSaleFlag;
	}

	public String getPublishAndFindFlag() {
		return publishAndFindFlag;
	}

	public void setPublishAndFindFlag(String publishAndFindFlag) {
		this.publishAndFindFlag = publishAndFindFlag;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

	public Date getPassDate() {
		return passDate;
	}

	public void setPassDate(Date passDate) {
		this.passDate = passDate;
	}

	public Date getPassDateBegin() {
		return passDateBegin;
	}

	public void setPassDateBegin(Date passDateBegin) {
		this.passDateBegin = passDateBegin;
	}

	public Date getPassDateEnd() {
		return passDateEnd;
	}

	public void setPassDateEnd(Date passDateEnd) {
		this.passDateEnd = passDateEnd;
	}

	public String getFindId() {
		return findId;
	}

	public void setFindId(String findId) {
		this.findId = findId;
	}

	public Date getShopDate() {
		return shopDate;
	}

	public void setShopDate(Date shopDate) {
		this.shopDate = shopDate;
	}

	public Date getFindDate() {
		return findDate;
	}

	public void setFindDate(Date findDate) {
		this.findDate = findDate;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public Date getShopDateBegin() {
		return shopDateBegin;
	}

	public void setShopDateBegin(Date shopDateBegin) {
		this.shopDateBegin = shopDateBegin;
	}

	public Date getShopDateEnd() {
		return shopDateEnd;
	}

	public void setShopDateEnd(Date shopDateEnd) {
		this.shopDateEnd = shopDateEnd;
	}

	public Date getFindDateBegin() {
		return findDateBegin;
	}

	public void setFindDateBegin(Date findDateBegin) {
		this.findDateBegin = findDateBegin;
	}

	public Date getFindDateEnd() {
		return findDateEnd;
	}

	public void setFindDateEnd(Date findDateEnd) {
		this.findDateEnd = findDateEnd;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public Date getLastDateBegin() {
		return lastDateBegin;
	}

	public void setLastDateBegin(Date lastDateBegin) {
		this.lastDateBegin = lastDateBegin;
	}

	public Date getLastDateEnd() {
		return lastDateEnd;
	}

	public void setLastDateEnd(Date lastDateEnd) {
		this.lastDateEnd = lastDateEnd;
	}

	public String getTempFollowNote() {
		return tempFollowNote;
	}

	public void setTempFollowNote(String tempFollowNote) {
		this.tempFollowNote = tempFollowNote;
	}

	public Date getGoldDate() {
		return goldDate;
	}

	public void setGoldDate(Date goldDate) {
		this.goldDate = goldDate;
	}

	public Date getGoldDateBegin() {
		return goldDateBegin;
	}

	public void setGoldDateBegin(Date goldDateBegin) {
		this.goldDateBegin = goldDateBegin;
	}

	public Date getGoldDateEnd() {
		return goldDateEnd;
	}

	public void setGoldDateEnd(Date goldDateEnd) {
		this.goldDateEnd = goldDateEnd;
	}

	
	
}