/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.web.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.huojuit.hishop.common.utils.ConstantsUtil;
import com.huojuit.hishop.common.web.BaseController;
import com.huojuit.hishop.modules.shop.entity.FindShop;
import com.huojuit.hishop.modules.shop.entity.Hobby;
import com.huojuit.hishop.modules.shop.entity.Hot;
import com.huojuit.hishop.modules.shop.entity.Member;
import com.huojuit.hishop.modules.shop.entity.Shop;
import com.huojuit.hishop.modules.shop.service.FindShopService;
import com.huojuit.hishop.modules.shop.service.HobbyService;
import com.huojuit.hishop.modules.shop.service.HotService;
import com.huojuit.hishop.modules.shop.service.MemberService;
import com.huojuit.hishop.modules.shop.service.ShopService;

/**
 * 个人中心Controller
 * 
 * @author daiyuxiang
 * @version 2017-06-24
 */
@Controller
@RequestMapping(value = "${frontPath}/wx/my")
public class MyFrontController extends BaseController {

	@Autowired
	private FindShopService findShopService;
	@Autowired
	private HobbyService hobbyService;
	@Autowired
	private HotService hotService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "myPublish")
	public String myPublish(Model model,HttpServletRequest request) {
		String openId = getUser(request).getOpenid();
		Member member = new Member();
		member.setOpenId(openId);
		
		// 找出所有号码
		List<Member> memberList = memberService.findList(member);

		Shop shopParam = new Shop();
		List<Shop> shopList = Lists.newArrayList();
		for(Member m : memberList) {
			// 查找我的转铺信息
			if(StringUtils.isNotBlank(m.getMobile())) {
				shopParam.setMobile(m.getMobile());
				List<Shop> tempList = shopService.findList(shopParam);
				shopList.addAll(tempList);
			}			
		}
		
		// 查找我的找铺信息
		FindShop findShopParam = new FindShop();
		findShopParam.setOpenId(openId);
		List<FindShop> findList = findShopService.findList(findShopParam);

		model.addAttribute("shopList", shopList);
		model.addAttribute("findList", findList);

		return "modules/shop/front/themes/weixin/myPublish";
	}

	@RequestMapping(value = "help")
	public String help(Model model) {
		return "modules/shop/front/themes/weixin/help";
	}

	@RequestMapping(value = "myHobby")
	public String myHobby(Model model,HttpServletRequest request) {
		Hobby hobbyParam = new Hobby();
		String openId = getUser(request).getOpenid();
		hobbyParam.setOpenId(openId);

		List<Shop> shopList = hobbyService.findShopList(hobbyParam);

		// 设置收藏标志
		for (Shop shop : shopList) {
			shop.setHobbyFlag(ConstantsUtil.YES_FLAG);
		}
		
		// 查找热门推荐
		Hot hotParam = new Hot();
		for (Shop shop : shopList) {
			hotParam.setShopId(shop.getId());
			List<Hot> hotList = hotService.findList(hotParam);
			if (hotList != null && hotList.size() > 0) {
				shop.setHotList(hotList);
			}
		}

		model.addAttribute("shopList", shopList);
		return "modules/shop/front/themes/weixin/myHobby";
	}

}