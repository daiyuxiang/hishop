/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.sys.dao;

import java.util.List;

import com.huojuit.hishop.common.persistence.TreeDao;
import com.huojuit.hishop.common.persistence.annotation.MyBatisDao;
import com.huojuit.hishop.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	
	public List<Area> findMallNumByCityId(Area entity);
	
	public List<Area> findAreaByParentId(Area entity);
	
}
