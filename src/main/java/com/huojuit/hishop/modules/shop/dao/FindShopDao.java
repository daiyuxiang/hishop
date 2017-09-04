/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.dao;

import com.huojuit.hishop.common.persistence.CrudDao;
import com.huojuit.hishop.common.persistence.annotation.MyBatisDao;
import com.huojuit.hishop.modules.shop.entity.FindShop;

/**
 * 找铺DAO接口
 * @author daiyuxiang
 * @version 2017-06-24
 */
@MyBatisDao
public interface FindShopDao extends CrudDao<FindShop> {
	public int countFind();
	
	/**
	 * 删除找铺配套关联表数据
	 * @param shop
	 * @return
	 */
	public int deleteShopMating(FindShop findShop);
}