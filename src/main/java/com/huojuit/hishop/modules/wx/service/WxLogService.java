/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.wx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.service.CrudService;
import com.huojuit.hishop.modules.wx.dao.WxLogDao;
import com.huojuit.hishop.modules.wx.entity.WxLog;

/**
 * 微信日志Service
 * @author daiyuxiang
 * @version 2016-12-13
 */
@Service
@Transactional(readOnly = true)
public class WxLogService extends CrudService<WxLogDao, WxLog> {

	public WxLog get(String id) {
		return super.get(id);
	}
	
	public List<WxLog> findList(WxLog taWxLog) {
		return super.findList(taWxLog);
	}
	
	public Page<WxLog> findPage(Page<WxLog> page, WxLog taWxLog) {
		return super.findPage(page, taWxLog);
	}
	
	@Transactional(readOnly = false)
	public void save(WxLog taWxLog) {
		super.save(taWxLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(WxLog taWxLog) {
		super.delete(taWxLog);
	}
	
}