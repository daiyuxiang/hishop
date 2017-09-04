/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.huojuit.hishop.common.config.Global;
import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.utils.StringUtils;
import com.huojuit.hishop.common.web.BaseController;
import com.huojuit.hishop.modules.shop.entity.FindShop;
import com.huojuit.hishop.modules.shop.entity.Follow;
import com.huojuit.hishop.modules.shop.entity.Member;
import com.huojuit.hishop.modules.shop.service.FindShopService;
import com.huojuit.hishop.modules.shop.service.FollowService;
import com.huojuit.hishop.modules.shop.service.MemberService;

/**
 * 找铺Controller
 * 
 * @author daiyuxiang
 * @version 2017-06-24
 */
@Controller
@RequestMapping(value = "${adminPath}/shop/findShop")
public class FindShopController extends BaseController {

	@Autowired
	private FindShopService findShopService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private FollowService followService;
	
	
	@ModelAttribute
	public FindShop get(@RequestParam(required = false) String id) {
		FindShop entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = findShopService.get(id);
		}
		if (entity == null) {
			entity = new FindShop();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(FindShop findShop, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<FindShop> page = findShopService.findPage(new Page<FindShop>(
				request, response), findShop);
		model.addAttribute("page", page);
		return "modules/shop/findShopList";
	}

	@RequestMapping(value = "form")
	public String form(FindShop findShop, Model model) {
		model.addAttribute("findShop", findShop);
		return "modules/shop/findShopForm";
	}

	@RequestMapping(value = "save")
	public String save(FindShop findShop, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, findShop)) {
			return form(findShop, model);
		}
		findShopService.save(findShop);
		addMessage(redirectAttributes, "保存找铺成功");
		return "redirect:" + Global.getAdminPath() + "/shop/findShop/?repage";
	}

	@RequestMapping(value = "delete")
	public String delete(FindShop findShop,
			RedirectAttributes redirectAttributes) {
		findShopService.delete(findShop);
		addMessage(redirectAttributes, "删除找铺成功");
		return "redirect:" + Global.getAdminPath() + "/shop/findShop/?repage";
	}

	@RequestMapping(value = "auditFind")
	public String auditFind(Model model, RedirectAttributes redirectAttributes,
			String memberId,String auditFlag,String followNote) {
		// 修改会员信息
		if (StringUtils.isNotBlank(memberId)) {
			Member member = memberService.get(memberId);
			member.setFindFlag(auditFlag);
			memberService.save(member);

			// 添加跟进记录
			Follow follow = new Follow();
			follow.setMemberId(memberId);
			follow.setFollowNote(followNote);
			followService.save(follow);
		}
		
		addMessage(redirectAttributes, "审核完成");
		return "redirect:" + Global.getAdminPath()
				+ "/shop/member/list?sourceType=2";
	}
}