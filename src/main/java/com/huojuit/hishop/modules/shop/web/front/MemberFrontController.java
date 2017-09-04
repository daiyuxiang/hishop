/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.web.front;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huojuit.hishop.common.utils.ConstantsUtil;
import com.huojuit.hishop.common.utils.MessageBean;
import com.huojuit.hishop.common.web.BaseController;
import com.huojuit.hishop.modules.shop.entity.FindShop;
import com.huojuit.hishop.modules.shop.entity.Hobby;
import com.huojuit.hishop.modules.shop.entity.Member;
import com.huojuit.hishop.modules.shop.entity.Shop;
import com.huojuit.hishop.modules.shop.service.FindShopService;
import com.huojuit.hishop.modules.shop.service.HobbyService;
import com.huojuit.hishop.modules.shop.service.MemberService;
import com.huojuit.hishop.modules.shop.service.ShopService;
import com.huojuit.hishop.modules.wx.entity.WxUserinfo;

/**
 * 会员Controller
 * 
 * @author daiyuxiang
 * @version 2017-06-24
 */
@Controller
@RequestMapping(value = "${frontPath}/wx/member")
public class MemberFrontController extends BaseController {

	@Autowired
	private FindShopService findShopService;
	@Autowired
	private HobbyService hobbyService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ShopService shopService;
	
	/**
	 * 申请发布
	 */
	@RequestMapping(value = "/publish")
	public @ResponseBody MessageBean<String> publish(Member temp,
			Model model,HttpServletRequest request) {
		MessageBean<String> messageBean = new MessageBean<String>();

		// 新增需求信息
		WxUserinfo user = getUser(request);
		String openId = user.getOpenid();
		Member member = new Member();
		member.setOpenId(openId);
		member.setNickName(user.getNickname());
		member.setMobile(temp.getMobile());
		member.setSaleFlag(ConstantsUtil.NO_FLAG);
		if(StringUtils.isNotBlank(temp.getPublishSaleFlag())) {
			// 新增店铺信息
			Shop shop = new Shop();
			shop.setMobile(temp.getMobile());
			shop.setShowFlag(ConstantsUtil.NO_FLAG);
			shopService.saveShop(shop);

			member.setPublishSaleFlag(temp.getPublishSaleFlag());
			member.setShopId(shop.getId());
			member.setShopDate(new Date());
			member.setFindFlag(ConstantsUtil.NO_FLAG);
			
			//删除游客数据
			memberService.deleteVistor(openId);
		}
		if(StringUtils.isNotBlank(temp.getFindFlag())) {
			// 新增找铺信息
			FindShop findShop = new FindShop();
			findShop.setOpenId(openId);
			findShop.setNickName(user.getNickname());
			findShop.setMobile(temp.getMobile());
			
			findShopService.save(findShop);
			
			member.setSaleFlag(ConstantsUtil.NO_FLAG);
			member.setPublishSaleFlag(ConstantsUtil.NO_FLAG);
			member.setFindFlag(temp.getFindFlag());
			member.setFindId(findShop.getId());
			member.setFindDate(new Date());
			
			//删除游客数据
			memberService.deleteVistor(openId);
		}
		
		memberService.save(member);
		messageBean.setStatus(1);

		return messageBean;
	}

	/**
	 * 设置收藏
	 */
	@RequestMapping(value = "/setHobby")
	public @ResponseBody MessageBean<String> setHobby(Hobby hobby,
			Model model,HttpServletRequest request) {
		MessageBean<String> messageBean = new MessageBean<String>();

		String openId = getUser(request).getOpenid();
		hobby.setOpenId(openId);

		List<Hobby> hobbyList = hobbyService.findList(hobby);
		if(hobbyList!=null&&hobbyList.size()>0) {
			hobbyService.physicalDelete(hobby);
		} else {
			hobbyService.save(hobby);
		}
		
		messageBean.setStatus(1);

		return messageBean;
	}

}