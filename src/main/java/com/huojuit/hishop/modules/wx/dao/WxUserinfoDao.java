/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.wx.dao;

import com.huojuit.hishop.common.persistence.CrudDao;
import com.huojuit.hishop.common.persistence.annotation.MyBatisDao;
import com.huojuit.hishop.modules.wx.entity.WxUserinfo;

/**
 * 微信用户信息DAO接口
 * @author daiyuxiang
 * @version 2016-12-16
 */
@MyBatisDao
public interface WxUserinfoDao extends CrudDao<WxUserinfo> {
	
}