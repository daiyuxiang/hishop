/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.service.CrudService;
import com.huojuit.hishop.modules.shop.entity.Mating;
import com.huojuit.hishop.modules.shop.dao.MatingDao;

/**
 * 商铺配套Service
 * @author daiyuxiang
 * @version 2017-06-27
 */
@Service
@Transactional(readOnly = true)
public class MatingService extends CrudService<MatingDao, Mating> {

	public Mating get(String id) {
		return super.get(id);
	}
	
	public List<Mating> findList(Mating mating) {
		return super.findList(mating);
	}
	
	public Page<Mating> findPage(Page<Mating> page, Mating mating) {
		return super.findPage(page, mating);
	}
	
	@Transactional(readOnly = false)
	public void save(Mating mating) {
		super.save(mating);
	}
	
	@Transactional(readOnly = false)
	public void delete(Mating mating) {
		super.delete(mating);
	}
	
}