/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.entity;

import org.hibernate.validator.constraints.Length;

import com.huojuit.hishop.common.persistence.DataEntity;
import com.huojuit.hishop.common.utils.ConstantsUtil;

/**
 * 轮播图Entity
 * @author daiyuxiang
 * @version 2017-06-26
 */
public class SliderImg extends DataEntity<SliderImg> {
	
	private static final long serialVersionUID = 1L;
	private String imgTitle;		// 图片标题
	private String imgContent;		// 图片介绍
	private String btnText;		// 按钮文字
	private String btnUrl;		// 按钮链接
	private String type;		// 轮播类型
	private String url;		// 图片地址
	private String showUrl;  //大图地址
	private String imgType; 	// 显示内容
	
	public SliderImg() {
		super();
		this.imgType = ConstantsUtil.YES_FLAG;
	}

	public SliderImg(String id){
		super(id);
	}

	@Length(min=0, max=200, message="图片标题长度必须介于 0 和 200 之间")
	public String getImgTitle() {
		return imgTitle;
	}

	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}
	
	@Length(min=0, max=300, message="图片介绍长度必须介于 0 和 300 之间")
	public String getImgContent() {
		return imgContent;
	}

	public void setImgContent(String imgContent) {
		this.imgContent = imgContent;
	}
	
	@Length(min=0, max=100, message="按钮文字长度必须介于 0 和 100 之间")
	public String getBtnText() {
		return btnText;
	}

	public void setBtnText(String btnText) {
		this.btnText = btnText;
	}
	
	@Length(min=0, max=200, message="按钮链接长度必须介于 0 和 200 之间")
	public String getBtnUrl() {
		return btnUrl;
	}

	public void setBtnUrl(String btnUrl) {
		this.btnUrl = btnUrl;
	}
	
	@Length(min=0, max=64, message="轮播类型长度必须介于 0 和 64 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=200, message="图片地址长度必须介于 0 和 200 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	
	
	
	
}