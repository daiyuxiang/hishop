/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huojuit.hishop.common.service.TreeService;
import com.huojuit.hishop.modules.sys.dao.AreaDao;
import com.huojuit.hishop.modules.sys.entity.Area;
import com.huojuit.hishop.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {

	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}

	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	public String getParentIds(String areaId) {
		Area a = dao.get(areaId);
		String parentIds = a.getParentIds();
		return parentIds;
	}
	
	public List<Area> findMallNumByCityId(Area entity) {
		return dao.findMallNumByCityId(entity);
	}
	
	public List<Area> findAreaByParentId(Area entity) {
		return dao.findAreaByParentId(entity);
	}

}
