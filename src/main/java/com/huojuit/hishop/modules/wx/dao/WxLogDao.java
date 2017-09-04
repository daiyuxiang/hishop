/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.wx.dao;

import com.huojuit.hishop.common.persistence.CrudDao;
import com.huojuit.hishop.common.persistence.annotation.MyBatisDao;
import com.huojuit.hishop.modules.wx.entity.WxLog;

/**
 * 微信日志DAO接口
 * @author daiyuxiang
 * @version 2016-12-13
 */
@MyBatisDao
public interface WxLogDao extends CrudDao<WxLog> {
	
}