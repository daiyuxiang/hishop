/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.dao;

import java.util.List;
import java.util.Map;

import com.huojuit.hishop.common.persistence.CrudDao;
import com.huojuit.hishop.common.persistence.annotation.MyBatisDao;
import com.huojuit.hishop.modules.shop.entity.Shop;

/**
 * 店铺DAO接口
 * @author daiyuxiang
 * @version 2017-06-19
 */
@MyBatisDao
public interface ShopDao extends CrudDao<Shop> {
	public int countToday();
	
	public int countAll();
	
	/**
	 * 删除店铺配套关联表数据
	 * @param shop
	 * @return
	 */
	public int deleteShopMating(Shop shop);
	
	/**
	 * 删除店铺热门推荐关联表数据
	 * @param shop
	 * @return
	 */
	public int deleteShopHot(Shop shop);
	
	/**
	 * 根据类型统计不同商场的店铺面积
	 * @param type shoppingMall
	 * @return
	 */
	public int countArea(String type,String shoppingMall);
	
	/**
	 * 商场的店铺面积
	 * @param 
	 * @return
	 */
	public int countArea1();
	
	/**
	 * 根据业态统计不同商场的店铺数量
	 * @param paramMap
	 * @return
	 */
	public int countDynamic(Map paramMap);
	
	/**
	 * 转让成功的店铺数
	 * @param 
	 * @return
	 */
	public int countSaleSuccess();
	
	public List<Shop> phone(String mobile);

	
}