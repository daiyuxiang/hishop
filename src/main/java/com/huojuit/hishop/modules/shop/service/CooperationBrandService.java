/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.service.CrudService;
import com.huojuit.hishop.modules.shop.dao.CooperationBrandDao;
import com.huojuit.hishop.modules.shop.entity.CooperationBrand;

/**
 * 合作品牌Service
 * @author daiyuxiang
 * @version 2017-06-18
 */
@Service
@Transactional(readOnly = true)
public class CooperationBrandService extends CrudService<CooperationBrandDao, CooperationBrand> {

	public CooperationBrand get(String id) {
		return super.get(id);
	}
	
	public List<CooperationBrand> findList(CooperationBrand cooperationBrand) {
		return super.findList(cooperationBrand);
	}
	
	public Page<CooperationBrand> findPage(Page<CooperationBrand> page, CooperationBrand cooperationBrand) {
		return super.findPage(page, cooperationBrand);
	}
	
	@Transactional(readOnly = false)
	public void save(CooperationBrand cooperationBrand) {
		super.save(cooperationBrand);
	}
	
	@Transactional(readOnly = false)
	public void delete(CooperationBrand cooperationBrand) {
		super.delete(cooperationBrand);
	}
	
	public int checkMaxNum(CooperationBrand cooperationBrand) {
		return dao.checkMaxNum(cooperationBrand);
	}
	
}