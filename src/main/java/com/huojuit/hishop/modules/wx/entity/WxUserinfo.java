/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.wx.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huojuit.hishop.common.persistence.DataEntity;

/**
 * 微信用户信息Entity
 * @author daiyuxiang
 * @version 2016-12-16
 */
public class WxUserinfo extends DataEntity<WxUserinfo> {
	
	private static final long serialVersionUID = 1L;
	private String openid;		// openid
	private String nickname;		// 昵称
	private String sex;		// 性别
	private String headimgurl;		// 头像
	private String country;		// 区
	private String province;		// 省
	private String city;		// 市
	private Date subscribeTime;		// subscribe_time
	private String subscribe;		// subscribe
	private Date createTime;		// 新建时间
	private Date updateTime;		// 更新时间
	private Long inviteUserId;		// 邀请人的userid
	private String userId;		// 微信对应登陆后的userid
	
	
	private String goodsNum;//商品收藏数
	private String shopNum;//商铺收藏数
	
	private String pendingPaymen;//待付款
	private String pendingGoods;//待发货
	private String pendingEvaluate;//待评论
	private String pendingService;//售后中
	
	public WxUserinfo() {
		super();
	}

	public WxUserinfo(String id){
		super(id);
	}

	
	
	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getShopNum() {
		return shopNum;
	}

	public void setShopNum(String shopNum) {
		this.shopNum = shopNum;
	}

	public String getPendingPaymen() {
		return pendingPaymen;
	}

	public void setPendingPaymen(String pendingPaymen) {
		this.pendingPaymen = pendingPaymen;
	}

	public String getPendingGoods() {
		return pendingGoods;
	}

	public void setPendingGoods(String pendingGoods) {
		this.pendingGoods = pendingGoods;
	}

	public String getPendingEvaluate() {
		return pendingEvaluate;
	}

	public void setPendingEvaluate(String pendingEvaluate) {
		this.pendingEvaluate = pendingEvaluate;
	}

	public String getPendingService() {
		return pendingService;
	}

	public void setPendingService(String pendingService) {
		this.pendingService = pendingService;
	}

	@Length(min=0, max=100, message="openid长度必须介于 0 和 100 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Length(min=0, max=200, message="昵称长度必须介于 0 和 200 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Length(min=0, max=11, message="性别长度必须介于 0 和 11 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=200, message="头像长度必须介于 0 和 200 之间")
	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	@Length(min=0, max=100, message="区长度必须介于 0 和 100 之间")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Length(min=0, max=100, message="省长度必须介于 0 和 100 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=100, message="市长度必须介于 0 和 100 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	
	@Length(min=0, max=11, message="subscribe长度必须介于 0 和 11 之间")
	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
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
	
	public Long getInviteUserId() {
		return inviteUserId;
	}

	public void setInviteUserId(Long inviteUserId) {
		this.inviteUserId = inviteUserId;
	}
	
	@Length(min=0, max=20, message="微信对应登陆后的userid长度必须介于 0 和 20 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}