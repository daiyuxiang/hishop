/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.wx.service;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.do1.component.common.weixin.util.WxClient;
import cn.com.do1.component.common.weixin.util.WxapiConstants;

import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.service.CrudService;
import com.huojuit.hishop.common.utils.ConstantsUtil;
import com.huojuit.hishop.common.utils.DateUtils;
import com.huojuit.hishop.modules.shop.entity.Member;
import com.huojuit.hishop.modules.shop.service.MemberService;
import com.huojuit.hishop.modules.wx.dao.WxUserinfoDao;
import com.huojuit.hishop.modules.wx.entity.WxToken;
import com.huojuit.hishop.modules.wx.entity.WxUserinfo;

/**
 * 微信用户信息Service
 * @author daiyuxiang
 * @version 2016-12-16
 */
@Service
@Transactional(readOnly = true)
public class WxUserinfoService extends CrudService<WxUserinfoDao, WxUserinfo> {
	@Autowired
	private WxTokenService taWxTokenService;
	
	@Autowired
	private MemberService memberService;
	
	
	public WxUserinfo get(String id) {
		return super.get(id);
	}
	
	public List<WxUserinfo> findList(WxUserinfo taWxUserinfo) {
		return super.findList(taWxUserinfo);
	}
	
	public Page<WxUserinfo> findPage(Page<WxUserinfo> page, WxUserinfo taWxUserinfo) {
		return super.findPage(page, taWxUserinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(WxUserinfo taWxUserinfo) {
		super.save(taWxUserinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(WxUserinfo taWxUserinfo) {
		super.delete(taWxUserinfo);
	}
	@Transactional(readOnly = false)
	public WxUserinfo saveOrUpdateUser(String openid,String evevkey) throws Exception {

		// 获取用户信息
		String accessToken = queryAccessToken();

		JSONObject userInfo = WxClient.getUserInfo(accessToken, openid);

		WxUserinfo wu = new WxUserinfo();
		wu.setOpenid(openid);
		List<WxUserinfo> userinfols = findList(wu);

		WxUserinfo wud = new WxUserinfo();
//		System.out.println("==============userinfo json:"+userInfo.toString());
//		System.out.println("==============nickname:"+userInfo.getString("nickname"));
//		String nickname = Base64Util.decryptBASE64(userInfo.getString("nickname"));
		if (userinfols == null || userinfols.size() < 1) {
//			wud.setOpenid(userInfo.getString("openid"));
			wud.setOpenid(openid);
			try{
				String nickname = userInfo.getString("nickname");
				if(!StringUtils.isEmpty(nickname)){
					nickname = getEmojStr(nickname.getBytes());
				}
				if(StringUtils.isEmpty(nickname)){
					nickname="游客";
				}
				wud.setNickname(nickname);
				wud.setSex(userInfo.getInt("sex")+"");
				wud.setHeadimgurl(userInfo.getString("headimgurl"));
				wud.setCity(userInfo.getString("country"));
				wud.setProvince(userInfo.getString("province"));
				wud.setCity(userInfo.getString("city"));
				wud.setSubscribe(userInfo.getInt("subscribe")+"");
				wud.setCreateDate(new Date());
				
				//插入member信息
				Member member = new Member();
				member.setOpenId(openid);
				member.setNickName(nickname);
				member.setFindFlag(ConstantsUtil.NO_FLAG);
				member.setSaleFlag(ConstantsUtil.NO_FLAG);
				member.setPublishSaleFlag(ConstantsUtil.NO_FLAG);
				member.setLastDate(new Date());

				memberService.save(member);
				
			}catch(Exception e){
				
			}
			if(!StringUtils.isEmpty(evevkey)){
				wud.setInviteUserId(Long.parseLong(evevkey));
			}
			
		}else{
			wud = userinfols.get(0);
			wud.setOpenid(openid);
//			userinfoPO.setOpenid(userInfo.getString("openid"));
			try{
				String nickname = userInfo.getString("nickname");
				if(!StringUtils.isEmpty(nickname)){
					nickname = getEmojStr(nickname.getBytes());
				}
				if(StringUtils.isEmpty(nickname)){
					nickname="游客";
				}
				wud.setNickname(nickname);
				wud.setSex(userInfo.getInt("sex")+"");
				wud.setHeadimgurl(userInfo.getString("headimgurl"));
				wud.setCity(userInfo.getString("country"));
				wud.setProvince(userInfo.getString("province"));
				wud.setCity(userInfo.getString("city"));
				wud.setSubscribe(userInfo.getInt("subscribe")+"");
				wud.setUpdateDate(new Date());	
				
				//修改member信息
				Member memberParam = new Member();
				memberParam.setOpenId(openid);
				List<Member> memberList = memberService.findList(memberParam);
				for(Member m : memberList) {
					m.setLastDate(new Date());
					memberService.save(m);
				}
				
			}catch(Exception e){
				
			}
			
		}
		
		save(wud);
		return wud;
		
	}
	private String getEmojStr(byte[] b_text){
		try{
			for (int i = 0; i < b_text.length; i++) {
				if ((b_text[i] & 0xF8) == 0xF0) {
					for (int j = 0; j < 4; j++) {
						b_text[i + j] = 0x30;
					}
					i += 3;
				}
			}
		}catch(Exception e){
			return "游客";
		}
		return new String (b_text).replaceAll("0", "");
	}
	@Transactional(readOnly = false)
	public WxUserinfo unsubscribe(String openid) throws Exception {

		// 获取用户信息
		String accessToken = queryAccessToken();


		WxUserinfo wu = new WxUserinfo();
		wu.setOpenid(openid);
		List<WxUserinfo> userinfols = findList(wu);

		WxUserinfo wud = new WxUserinfo();
//		System.out.println("==============userinfo json:"+userInfo.toString());
//		System.out.println("==============nickname:"+userInfo.getString("nickname"));
//		String nickname = Base64Util.decryptBASE64(userInfo.getString("nickname"));
		if (userinfols == null || userinfols.size() < 1) {
			
		}else{
			wud = userinfols.get(0);
			wud.setOpenid(openid);
			wud.setSubscribe("0");			
		}
		
		save(wud);
		return wud;
		
	}
	public String queryAccessToken() throws Exception {

		String accessToken = taWxTokenService.getAccessToken();

		if (accessToken != null) {
			return accessToken;
		}

		JSONObject json = WxClient.getAccessToken();

		int expiresIn = json.getInt("expires_in");
		accessToken = json.getString("access_token");

		WxToken po = new WxToken();
		po.setTokenType("access_token");
		po.setTokenValue(accessToken);
		po.setCreateTime(new Date());
		po.setTokenExpire(DateUtils.addSeconds(new Date(), expiresIn - 120));

		taWxTokenService.save(po);

		return accessToken;
	}
	@Transactional(readOnly = false)
    public String queryJsapiTicket() throws Exception {

        String ticket = taWxTokenService.getJsapiTicket();

        if (ticket != null) {
            return ticket;
        }

        JSONObject json = WxClient.getJsapiTicket(queryAccessToken());

        int expiresIn = json.getInt("expires_in");
        ticket = json.getString("ticket");

        WxToken po = new WxToken();
        po.setTokenType("jsapi_ticket");
        po.setTokenValue(ticket);
        po.setCreateTime(new Date());
        po.setTokenExpire(DateUtils.addSeconds(new Date(), expiresIn - 120));

        taWxTokenService.save(po);

        return ticket;
    }
	
    public String queryOpenid(String code) throws Exception {

        JSONObject json = WxClient.getOpenid(code);

        String openid = json.getString("openid");

        // 获取用户信息
        if (WxapiConstants.SCOPE_USERINFO.equals(json.getString("scope"))) {
        	saveOrUpdateUser(openid,null);
        }

        return openid;
    }

}