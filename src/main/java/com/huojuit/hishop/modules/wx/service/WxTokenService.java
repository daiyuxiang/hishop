/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.wx.service;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.do1.component.common.weixin.util.WxClient;

import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.service.CrudService;
import com.huojuit.hishop.common.utils.DateUtils;
import com.huojuit.hishop.modules.wx.dao.WxTokenDao;
import com.huojuit.hishop.modules.wx.entity.WxToken;

/**
 * 微信tokenService
 * @author daiyuxiang
 * @version 2016-12-13
 */
@Service
@Transactional(readOnly = true)
public class WxTokenService extends CrudService<WxTokenDao, WxToken> {

	public WxToken get(String id) {
		return super.get(id);
	}
	
	public List<WxToken> findList(WxToken taWxToken) {
		return super.findList(taWxToken);
	}
	
	public Page<WxToken> findPage(Page<WxToken> page, WxToken taWxToken) {
		return super.findPage(page, taWxToken);
	}
	
	@Transactional(readOnly = false)
	public void save(WxToken taWxToken) {
		super.save(taWxToken);
	}
	
	@Transactional(readOnly = false)
	public void delete(WxToken taWxToken) {
		super.delete(taWxToken);
	}
    public String getAccessToken() throws Exception {
		
		List<WxToken> wt = dao.getAccessToken();
		if(wt != null && wt.size()>0 && !StringUtils.isEmpty(wt.get(0).getTokenValue())){
			return wt.get(0).getTokenValue();
		}
		
		JSONObject json = WxClient.getAccessToken();

		int expiresIn = json.getInt("expires_in");
		String accessToken = json.getString("access_token");

		WxToken po = new WxToken();
		po.setTokenType("access_token");
		po.setTokenValue(accessToken);
		po.setCreateTime(new Date());
		po.setTokenExpire(DateUtils.addSeconds(new Date(), expiresIn - 120));

		save(po);

		return accessToken;
    }
    
    @Transactional(readOnly = false)
    public String getJsapiTicket() throws Exception {
    	List<WxToken> wt = dao.getJsapiTicket();
    	if(wt != null && wt.size() >0 && !StringUtils.isEmpty(wt.get(0).getTokenValue())){
			return wt.get(0).getTokenValue();
		}
    	
    	JSONObject json = WxClient.getJsapiTicket(getAccessToken());

        int expiresIn = json.getInt("expires_in");
        String ticket = json.getString("ticket");

        WxToken po = new WxToken();
        po.setTokenType("jsapi_ticket");
        po.setTokenValue(ticket);
        po.setCreateTime(new Date());
        po.setTokenExpire(DateUtils.addSeconds(new Date(), expiresIn - 120));

        super.save(po);
        
		return ticket;
    }
}