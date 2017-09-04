/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.service.CrudService;
import com.huojuit.hishop.modules.shop.entity.Follow;
import com.huojuit.hishop.modules.shop.dao.FollowDao;

/**
 * 跟进Service
 * @author daiyuxiang
 * @version 2017-08-17
 */
@Service
@Transactional(readOnly = true)
public class FollowService extends CrudService<FollowDao, Follow> {

	public Follow get(String id) {
		return super.get(id);
	}
	
	public List<Follow> findList(Follow follow) {
		return super.findList(follow);
	}
	
	public Page<Follow> findPage(Page<Follow> page, Follow follow) {
		return super.findPage(page, follow);
	}
	
	@Transactional(readOnly = false)
	public void save(Follow follow) {
		super.save(follow);
	}
	
	@Transactional(readOnly = false)
	public void delete(Follow follow) {
		super.delete(follow);
	}
	
}