/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.huojuit.hishop.common.persistence.DataEntity;
import com.huojuit.hishop.common.utils.ConstantsUtil;
import com.huojuit.hishop.modules.sys.entity.Area;

/**
 * 商场Entity
 * @author daiyuxiang
 * @version 2017-06-18
 */
public class ShoppingMall extends DataEntity<ShoppingMall> {
	
	private static final long serialVersionUID = 1L;
	private String mallName;		// 商场名称
	private String province;		// 省
	private String city;		// 城市
	private Area area;			// 区域
	private Area town; // 镇
	private String mallUrl;		// 商场图片
	private String pageTop;		// 首页置顶(0:不置顶 1:置顶 最多4个)
	private String areaTop;		// 区域置顶(0:不置顶 1:置顶 该区域最多4个)
	private String scrollTop;		// 轮播置顶(0:不置顶 1:置顶 最多4个)
	private String provinceName;
	private String cityName;
	private String areaName;
	
	private String shoppingArea;	//百货/购物面积万平米
	private String dynamicFlow;		//动态客流万人
	private String staticFlow;		//静态客流万人
	private String officeArea;		//写字楼面积万平米
	
	private String percent1; //餐饮店铺百分比;
	private String percent2; //购物店铺百分比;
	private String percent3; //休闲娱乐百分比;
	private String percent4; //教育培训百分比;
	
	private String shopNum; // 店铺数量
	
	public ShoppingMall() {
		super();
		this.pageTop = ConstantsUtil.NO_FLAG;
		this.areaTop = ConstantsUtil.NO_FLAG;
		this.scrollTop = ConstantsUtil.NO_FLAG;
		this.percent1 = "0"; 
		this.percent2 = "0";
		this.percent3 = "0";
		this.percent4 = "0";
	}

	public ShoppingMall(String id){
		super(id);
	}

	@Length(min=0, max=64, message="商场名称长度必须介于 0 和 64 之间")
	public String getMallName() {
		return mallName;
	}

	public void setMallName(String mallName) {
		this.mallName = mallName;
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
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@NotEmpty(message="商场图片不能为空")
	@Length(min=0, max=200, message="商场图片长度必须介于 0 和 200 之间")
	public String getMallUrl() {
		return mallUrl;
	}

	public void setMallUrl(String mallUrl) {
		this.mallUrl = mallUrl;
	}
	
	@Length(min=0, max=2, message="首页置顶(0:不置顶 1:置顶 最多4个)长度必须介于 0 和 2 之间")
	public String getPageTop() {
		return pageTop;
	}

	public void setPageTop(String pageTop) {
		this.pageTop = pageTop;
	}
	
	@Length(min=0, max=2, message="区域置顶(0:不置顶 1:置顶 该区域最多4个)长度必须介于 0 和 2 之间")
	public String getAreaTop() {
		return areaTop;
	}

	public void setAreaTop(String areaTop) {
		this.areaTop = areaTop;
	}
	
	@Length(min=0, max=2, message="轮播置顶(0:不置顶 1:置顶 最多4个)长度必须介于 0 和 2 之间")
	public String getScrollTop() {
		return scrollTop;
	}

	public void setScrollTop(String scrollTop) {
		this.scrollTop = scrollTop;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getShoppingArea() {
		return shoppingArea;
	}

	public void setShoppingArea(String shoppingArea) {
		this.shoppingArea = shoppingArea;
	}

	public String getDynamicFlow() {
		return dynamicFlow;
	}

	public void setDynamicFlow(String dynamicFlow) {
		this.dynamicFlow = dynamicFlow;
	}

	public String getStaticFlow() {
		return staticFlow;
	}

	public void setStaticFlow(String staticFlow) {
		this.staticFlow = staticFlow;
	}

	public String getOfficeArea() {
		return officeArea;
	}

	public void setOfficeArea(String officeArea) {
		this.officeArea = officeArea;
	}

	public String getPercent1() {
		return percent1;
	}

	public void setPercent1(String percent1) {
		this.percent1 = percent1;
	}

	public String getPercent2() {
		return percent2;
	}

	public void setPercent2(String percent2) {
		this.percent2 = percent2;
	}

	public String getPercent3() {
		return percent3;
	}

	public void setPercent3(String percent3) {
		this.percent3 = percent3;
	}

	public String getPercent4() {
		return percent4;
	}

	public void setPercent4(String percent4) {
		this.percent4 = percent4;
	}

	public String getShopNum() {
		return shopNum;
	}

	public void setShopNum(String shopNum) {
		this.shopNum = shopNum;
	}

	@NotNull
	public Area getTown() {
		return town;
	}

	public void setTown(Area town) {
		this.town = town;
	}

	
	
	
	
	
	
}