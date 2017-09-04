/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.entity;

import org.hibernate.validator.constraints.Length;

import com.huojuit.hishop.common.persistence.DataEntity;

/**
 * 跟进Entity
 * @author daiyuxiang
 * @version 2017-08-17
 */
public class Follow extends DataEntity<Follow> {
	
	private static final long serialVersionUID = 1L;
	private String memberId;		// 会员ID
	private String followNote;		// 备注内容
	private String operator;		// 操作人
	
	public Follow() {
		super();
	}

	public Follow(String id){
		super(id);
	}

	@Length(min=0, max=64, message="会员ID长度必须介于 0 和 64 之间")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Length(min=0, max=200, message="备注内容长度必须介于 0 和 200 之间")
	public String getFollowNote() {
		return followNote;
	}

	public void setFollowNote(String followNote) {
		this.followNote = followNote;
	}
	
	@Length(min=0, max=64, message="操作人长度必须介于 0 和 64 之间")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
}