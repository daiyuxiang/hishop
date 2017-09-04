/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.service.CrudService;
import com.huojuit.hishop.modules.shop.entity.Tel;
import com.huojuit.hishop.modules.shop.dao.TelDao;

/**
 * 电话记录Service
 * @author daiyuxiang
 * @version 2017-07-06
 */
@Service
@Transactional(readOnly = true)
public class TelService extends CrudService<TelDao, Tel> {

	public Tel get(String id) {
		return super.get(id);
	}
	
	public List<Tel> findList(Tel tel) {
		return super.findList(tel);
	}
	
	public Page<Tel> findPage(Page<Tel> page, Tel tel) {
		return super.findPage(page, tel);
	}
	
	@Transactional(readOnly = false)
	public void save(Tel tel) {
		super.save(tel);
	}
	
	@Transactional(readOnly = false)
	public void delete(Tel tel) {
		super.delete(tel);
	}
	
}