/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.dao;

import com.huojuit.hishop.common.persistence.CrudDao;
import com.huojuit.hishop.common.persistence.annotation.MyBatisDao;
import com.huojuit.hishop.modules.shop.entity.CooperationBrand;

/**
 * 合作品牌DAO接口
 * @author daiyuxiang
 * @version 2017-06-18
 */
@MyBatisDao
public interface CooperationBrandDao extends CrudDao<CooperationBrand> {
	
	public int checkMaxNum(CooperationBrand cooperationBrand);
}