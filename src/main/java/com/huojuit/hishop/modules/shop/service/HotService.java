/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.service.CrudService;
import com.huojuit.hishop.modules.shop.entity.Hot;
import com.huojuit.hishop.modules.shop.dao.HotDao;

/**
 * 热门推荐Service
 * @author daiyuxiang
 * @version 2017-06-27
 */
@Service
@Transactional(readOnly = true)
public class HotService extends CrudService<HotDao, Hot> {

	public Hot get(String id) {
		return super.get(id);
	}
	
	public List<Hot> findList(Hot hot) {
		return super.findList(hot);
	}
	
	public Page<Hot> findPage(Page<Hot> page, Hot hot) {
		return super.findPage(page, hot);
	}
	
	@Transactional(readOnly = false)
	public void save(Hot hot) {
		super.save(hot);
	}
	
	@Transactional(readOnly = false)
	public void delete(Hot hot) {
		super.delete(hot);
	}
	
}