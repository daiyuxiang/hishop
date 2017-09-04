/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.dao;

import com.huojuit.hishop.common.persistence.CrudDao;
import com.huojuit.hishop.common.persistence.annotation.MyBatisDao;
import com.huojuit.hishop.modules.shop.entity.ShoppingMall;

/**
 * 商场DAO接口
 * @author daiyuxiang
 * @version 2017-06-18
 */
@MyBatisDao
public interface ShoppingMallDao extends CrudDao<ShoppingMall> {
	public int checkMaxNum(ShoppingMall shoppingMall);
}