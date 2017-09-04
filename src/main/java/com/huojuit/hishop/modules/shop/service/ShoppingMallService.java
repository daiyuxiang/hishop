/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.service.CrudService;
import com.huojuit.hishop.modules.shop.entity.ShoppingMall;
import com.huojuit.hishop.modules.shop.dao.ShoppingMallDao;

/**
 * 商场Service
 * @author daiyuxiang
 * @version 2017-06-18
 */
@Service
@Transactional(readOnly = true)
public class ShoppingMallService extends CrudService<ShoppingMallDao, ShoppingMall> {

	public ShoppingMall get(String id) {
		return super.get(id);
	}
	
	public List<ShoppingMall> findList(ShoppingMall shoppingMall) {
		return super.findList(shoppingMall);
	}
	
	public Page<ShoppingMall> findPage(Page<ShoppingMall> page, ShoppingMall shoppingMall) {
		return super.findPage(page, shoppingMall);
	}
	
	@Transactional(readOnly = false)
	public void save(ShoppingMall shoppingMall) {
		super.save(shoppingMall);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShoppingMall shoppingMall) {
		super.delete(shoppingMall);
	}
	
	public int checkMaxNum(ShoppingMall shoppingMall) {
		return dao.checkMaxNum(shoppingMall);
	}
}