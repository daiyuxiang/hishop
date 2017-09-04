/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.service.CrudService;
import com.huojuit.hishop.modules.shop.dao.HobbyDao;
import com.huojuit.hishop.modules.shop.entity.Hobby;
import com.huojuit.hishop.modules.shop.entity.Shop;

/**
 * 收藏Service
 * @author daiyuxiang
 * @version 2017-06-26
 */
@Service
@Transactional(readOnly = true)
public class HobbyService extends CrudService<HobbyDao, Hobby> {

	public Hobby get(String id) {
		return super.get(id);
	}
	
	public List<Hobby> findList(Hobby hobby) {
		return super.findList(hobby);
	}
	
	public Page<Hobby> findPage(Page<Hobby> page, Hobby hobby) {
		return super.findPage(page, hobby);
	}
	
	@Transactional(readOnly = false)
	public void save(Hobby hobby) {
		super.save(hobby);
	}
	
	@Transactional(readOnly = false)
	public void delete(Hobby hobby) {
		super.delete(hobby);
	}
	
	public List<Shop> findShopList(Hobby hobby) {
		return dao.findShopList(hobby);
	}
	
	@Transactional(readOnly = false)
	public int physicalDelete(Hobby hobby) {
		return dao.physicalDelete(hobby);
	}
		
	
}