/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.wx.dao;

import java.util.List;

import com.huojuit.hishop.common.persistence.CrudDao;
import com.huojuit.hishop.common.persistence.annotation.MyBatisDao;
import com.huojuit.hishop.modules.wx.entity.WxToken;

/**
 * 微信tokenDAO接口
 * @author daiyuxiang
 * @version 2016-12-13
 */
@MyBatisDao
public interface WxTokenDao extends CrudDao<WxToken> {
	public List<WxToken> getAccessToken() throws Exception ;

    public List<WxToken> getJsapiTicket() throws Exception ;
}