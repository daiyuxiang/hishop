/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.huojuit.hishop.common.persistence.DataEntity;
import com.huojuit.hishop.common.utils.excel.annotation.ExcelField;
import com.huojuit.hishop.modules.sys.entity.Area;

/**
 * 店铺Entity
 * 
 * @author daiyuxiang
 * @version 2017-06-19
 */
/**
 * @author Administrator
 *
 */
public class Shop extends DataEntity<Shop> {

	private static final long serialVersionUID = 1L;
	private String shopNo; // 商铺编号
	private String openId; // 微信ID
	private String nickName; // 昵称
	private String mobile; // 电话
	private String shopName; // 店铺名称
	private String shopAddress; // 店铺地址
	private String shopType; // 商铺类型
	private Double fee; // 月租金(万元)
	private Double transferFee; // 转让费(万元)
	private String goldFlag; // 金牌店铺标志(0:不是 1:是)
	private String goldApplicationFlag; // 金牌店铺申请(0:未申请 1:申请中 2:通过)
	private String showFlag; // 店铺审核状态标志(0:未审核 1:发布中 2:已下架)
	private String province; // 省
	private String city; // 城市
	private Area area; // 区域
	private Area town; // 镇
	private String shoppingMall; // 商场
	private String managementStatus; // 经营状态
	private String operatingLife; // 经营年限
	private String brandInfo; // 品牌情况
	private String currentManagement; // 当前经营
	private String street; // 街道
	private String position; // 位置
	private Double coveredArea; // 建筑面积
	private Double useArea; // 使用面积
	private Double depth; // 进深
	private Double floorHeight; // 层高
	private String segmentation; // 是否分割
	private String paymentMethod; // 支付方式
	private String deposit; // 押金
	private String maximumLease; // 最长可租
	private String leaseStatus; // 租赁状态
	private Double faceWidth; // 面宽
	private String floor; // 楼层
	private String managementFormat; // 经营业态
	private String image1; // 图片1
	private String image2; // 图片2
	private String image3; // 图片3
	private String image4; // 图片4
	private String image5; // 图片5
	private String image6; // 图片6
	private String publishType; //发布类型
	private String sellType; //出让类型
	private String shopContent; //实勘信息
	private String referrer;  //推荐人
	private String memberName; //会员姓名
	private Date passDate; //通过时间
	private Date goldDate; //金牌通过时间

	private String hobbyFlag; //收藏标志
	private List<Mating> matingList = Lists.newArrayList(); // 拥有配套列表
	private List<Hot> hotList = Lists.newArrayList(); // 拥有热门推荐列表
	
	private String feeString; //月租金下拉框值
	private String feeBegin;  //月租金区间小值
	private String feeEnd;    //月租金区间大值
	
	
	private String transferFeeString; //转让费下拉框值
	private String transferFeeBegin; //转让费区间小值
	private String transferFeeEnd; //转让费区间大值

	private String useAreaString; //使用面积下拉框值
	private String useAreaBegin; //使用面积区间小值
	private String useAreaEnd; //使用面积区间大值
	
	private String hotString;
	private String[] hotArray;
	
	private String shopTypeString;
	private String[] shopTypeArray;
	
	private String todayFlag; //今日店铺查询标志
	
	private Date createDateBegin;
	private Date createDateEnd;
	
	private Date passDateBegin;
	private Date passDateEnd;
	
	
	private String followNote; //跟进备注内容
	private String updateDateStr; // 修改时间字符串
	
	public Shop() {
		super();
	}
	
	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public Shop(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "微信ID长度必须介于 0 和 64 之间")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Length(min = 0, max = 64, message = "昵称长度必须介于 0 和 64 之间")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Length(min = 0, max = 20, message = "电话长度必须介于 0 和 20 之间")
	@ExcelField(title="电话", align=2, sort=80)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Length(min = 0, max = 100, message = "店铺名称长度必须介于 0 和 100 之间")
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Length(min = 0, max = 500, message = "店铺地址长度必须介于 0 和 500 之间")
	@ExcelField(title="地址", align=2, sort=30)
	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	@Length(min = 0, max = 64, message = "商铺类型长度必须介于 0 和 64 之间")
	@ExcelField(title="商铺类型", align=2, sort=10,dictType="shop_shop_type")
	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	@ExcelField(title="月租金(万元)", align=2, sort=70)
	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Double getTransferFee() {
		return transferFee;
	}

	public void setTransferFee(Double transferFee) {
		this.transferFee = transferFee;
	}

	@Length(min = 0, max = 2, message = "金牌店铺标志(0:不是 1:是)长度必须介于 0 和 2 之间")
	@ExcelField(title="是否为金牌店铺", align=2, sort=100,dictType="yes_no")
	public String getGoldFlag() {
		return goldFlag;
	}

	public void setGoldFlag(String goldFlag) {
		this.goldFlag = goldFlag;
	}

	@Length(min = 0, max = 2, message = "金牌店铺申请(0:未申请 1:申请中 2:通过)长度必须介于 0 和 2 之间")
	public String getGoldApplicationFlag() {
		return goldApplicationFlag;
	}

	public void setGoldApplicationFlag(String goldApplicationFlag) {
		this.goldApplicationFlag = goldApplicationFlag;
	}

	@Length(min = 1, max = 2, message = "上架下架标志(0:下架 1:上架)长度必须介于 1 和 2 之间")
	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

	@Length(min = 0, max = 64, message = "省长度必须介于 0 和 64 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Length(min = 0, max = 64, message = "城市长度必须介于 0 和 64 之间")
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

	@ExcelField(title="镇", align=2, sort=20)
	public Area getTown() {
		return town;
	}

	public void setTown(Area town) {
		this.town = town;
	}

	public String getShoppingMall() {
		return shoppingMall;
	}

	public void setShoppingMall(String shoppingMall) {
		this.shoppingMall = shoppingMall;
	}

	@Length(min = 0, max = 64, message = "经营状态长度必须介于 0 和 64 之间")
	public String getManagementStatus() {
		return managementStatus;
	}

	public void setManagementStatus(String managementStatus) {
		this.managementStatus = managementStatus;
	}

	@Length(min = 0, max = 50, message = "经营年限长度必须介于 0 和 50 之间")
	public String getOperatingLife() {
		return operatingLife;
	}

	public void setOperatingLife(String operatingLife) {
		this.operatingLife = operatingLife;
	}

	@Length(min = 0, max = 200, message = "品牌情况长度必须介于 0 和 200 之间")
	public String getBrandInfo() {
		return brandInfo;
	}

	public void setBrandInfo(String brandInfo) {
		this.brandInfo = brandInfo;
	}

	@Length(min = 0, max = 500, message = "当前经营长度必须介于 0 和 500 之间")
	public String getCurrentManagement() {
		return currentManagement;
	}

	public void setCurrentManagement(String currentManagement) {
		this.currentManagement = currentManagement;
	}

	@Length(min = 0, max = 500, message = "街道长度必须介于 0 和 500 之间")
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Length(min = 0, max = 500, message = "位置长度必须介于 0 和 500 之间")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Double getCoveredArea() {
		return coveredArea;
	}

	public void setCoveredArea(Double coveredArea) {
		this.coveredArea = coveredArea;
	}

	@ExcelField(title="使用面积", align=2, sort=40)
	public Double getUseArea() {
		return useArea;
	}

	public void setUseArea(Double useArea) {
		this.useArea = useArea;
	}

	public Double getDepth() {
		return depth;
	}

	public void setDepth(Double depth) {
		this.depth = depth;
	}

	public Double getFloorHeight() {
		return floorHeight;
	}

	public void setFloorHeight(Double floorHeight) {
		this.floorHeight = floorHeight;
	}

	@Length(min = 0, max = 2, message = "是否分割长度必须介于 0 和 2 之间")
	public String getSegmentation() {
		return segmentation;
	}

	public void setSegmentation(String segmentation) {
		this.segmentation = segmentation;
	}

	@Length(min = 0, max = 200, message = "支付方式长度必须介于 0 和 200 之间")
	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Length(min = 0, max = 200, message = "押金长度必须介于 0 和 200 之间")
	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	@Length(min = 0, max = 200, message = "最长可租长度必须介于 0 和 200 之间")
	public String getMaximumLease() {
		return maximumLease;
	}

	public void setMaximumLease(String maximumLease) {
		this.maximumLease = maximumLease;
	}

	@Length(min = 0, max = 64, message = "租赁状态长度必须介于 0 和 64 之间")
	public String getLeaseStatus() {
		return leaseStatus;
	}

	public void setLeaseStatus(String leaseStatus) {
		this.leaseStatus = leaseStatus;
	}

	public Double getFaceWidth() {
		return faceWidth;
	}

	public void setFaceWidth(Double faceWidth) {
		this.faceWidth = faceWidth;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	@Length(min = 0, max = 64, message = "当前经营业态长度必须介于 0 和 64 之间")
	public String getManagementFormat() {
		return managementFormat;
	}

	public void setManagementFormat(String managementFormat) {
		this.managementFormat = managementFormat;
	}

	@Length(min = 0, max = 100, message = "图片1长度必须介于 0 和 100 之间")
	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	@Length(min = 0, max = 100, message = "图片2长度必须介于 0 和 100 之间")
	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	@Length(min = 0, max = 100, message = "图片3长度必须介于 0 和 100 之间")
	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	@Length(min = 0, max = 100, message = "图片4长度必须介于 0 和 100 之间")
	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}

	@Length(min = 0, max = 100, message = "图片5长度必须介于 0 和 100 之间")
	public String getImage5() {
		return image5;
	}

	public void setImage5(String image5) {
		this.image5 = image5;
	}

	@Length(min = 0, max = 100, message = "图片6长度必须介于 0 和 100 之间")
	public String getImage6() {
		return image6;
	}

	public void setImage6(String image6) {
		this.image6 = image6;
	}

	public String getHobbyFlag() {
		return hobbyFlag;
	}

	public void setHobbyFlag(String hobbyFlag) {
		this.hobbyFlag = hobbyFlag;
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

	public List<Hot> getHotList() {
		return hotList;
	}

	public void setHotList(List<Hot> hotList) {
		this.hotList = hotList;
	}

	@JsonIgnore
	public List<String> getHotValueList() {
		List<String> hotValueList = Lists.newArrayList();
		for (Hot hot : hotList) {
			hotValueList.add(hot.getHotValue());
		}
		return hotValueList;
	}

	public void setHotValueList(List<String> hotValueList) {
		hotList = Lists.newArrayList();

		if(hotValueList==null) return;

		for (String hotValue : hotValueList) {
			Hot hot = new Hot();
			hot.setHotValue(hotValue);
			hotList.add(hot);
		}
	}

	public String getFeeString() {
		return feeString;
	}

	public void setFeeString(String feeString) {
		this.feeString = feeString;
	}

	public String getFeeBegin() {
		return feeBegin;
	}

	public void setFeeBegin(String feeBegin) {
		this.feeBegin = feeBegin;
	}

	public String getFeeEnd() {
		return feeEnd;
	}

	public void setFeeEnd(String feeEnd) {
		this.feeEnd = feeEnd;
	}

	public String getTransferFeeString() {
		return transferFeeString;
	}

	public void setTransferFeeString(String transferFeeString) {
		this.transferFeeString = transferFeeString;
	}

	public String getTransferFeeBegin() {
		return transferFeeBegin;
	}

	public void setTransferFeeBegin(String transferFeeBegin) {
		this.transferFeeBegin = transferFeeBegin;
	}

	public String getTransferFeeEnd() {
		return transferFeeEnd;
	}

	public void setTransferFeeEnd(String transferFeeEnd) {
		this.transferFeeEnd = transferFeeEnd;
	}
	
	
	public String getUseAreaString() {
		return useAreaString;
	}

	public void setUseAreaString(String useAreaString) {
		this.useAreaString = useAreaString;
	}

	public String getUseAreaBegin() {
		return useAreaBegin;
	}

	public void setUseAreaBegin(String useAreaBegin) {
		this.useAreaBegin = useAreaBegin;
	}

	public String getUseAreaEnd() {
		return useAreaEnd;
	}

	public void setUseAreaEnd(String useAreaEnd) {
		this.useAreaEnd = useAreaEnd;
	}

	public String getHotString() {
		return hotString;
	}

	public void setHotString(String hotString) {
		this.hotString = hotString;
	}

	public String[] getHotArray() {
		return hotArray;
	}

	public void setHotArray(String[] hotArray) {
		this.hotArray = hotArray;
	}

	public String getShopTypeString() {
		return shopTypeString;
	}

	public void setShopTypeString(String shopTypeString) {
		this.shopTypeString = shopTypeString;
	}

	public String[] getShopTypeArray() {
		return shopTypeArray;
	}

	public void setShopTypeArray(String[] shopTypeArray) {
		this.shopTypeArray = shopTypeArray;
	}

	public String getTodayFlag() {
		return todayFlag;
	}

	public void setTodayFlag(String todayFlag) {
		this.todayFlag = todayFlag;
	}

	public Date getCreateDateBegin() {
		return createDateBegin;
	}

	public void setCreateDateBegin(Date createDateBegin) {
		this.createDateBegin = createDateBegin;
	}

	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public String getPublishType() {
		return publishType;
	}

	public void setPublishType(String publishType) {
		this.publishType = publishType;
	}

	@ExcelField(title="出让类型", align=2, sort=50,dictType="shop_sell_type")
	public String getSellType() {
		return sellType;
	}

	public void setSellType(String sellType) {
		this.sellType = sellType;
	}

	public String getShopContent() {
		return shopContent;
	}

	public void setShopContent(String shopContent) {
		this.shopContent = shopContent;
	}

	@ExcelField(title="推荐人", align=2, sort=90)
	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getFollowNote() {
		return followNote;
	}

	public void setFollowNote(String followNote) {
		this.followNote = followNote;
	}

	public Date getGoldDate() {
		return goldDate;
	}

	public void setGoldDate(Date goldDate) {
		this.goldDate = goldDate;
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

	public String getUpdateDateStr() {
		return updateDateStr;
	}

	public void setUpdateDateStr(String updateDateStr) {
		this.updateDateStr = updateDateStr;
	}

	
	
	
	
}