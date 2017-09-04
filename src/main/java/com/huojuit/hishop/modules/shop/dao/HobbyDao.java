/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.dao;

import java.util.List;

import com.huojuit.hishop.common.persistence.CrudDao;
import com.huojuit.hishop.common.persistence.annotation.MyBatisDao;
import com.huojuit.hishop.modules.shop.entity.Hobby;
import com.huojuit.hishop.modules.shop.entity.Shop;

/**
 * 收藏DAO接口
 * 
 * @author daiyuxiang
 * @version 2017-06-26
 */
@MyBatisDao
public interface HobbyDao extends CrudDao<Hobby> {
	public List<Shop> findShopList(Hobby hobby);

	public int physicalDelete(Hobby hobby);
}