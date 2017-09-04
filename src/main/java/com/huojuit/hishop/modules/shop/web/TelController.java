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
import com.huojuit.hishop.common.web.BaseController;
import com.huojuit.hishop.common.utils.StringUtils;
import com.huojuit.hishop.modules.shop.entity.Tel;
import com.huojuit.hishop.modules.shop.service.TelService;

/**
 * 电话记录Controller
 * 
 * @author daiyuxiang
 * @version 2017-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/shop/tel")
public class TelController extends BaseController {

	@Autowired
	private TelService telService;

	@ModelAttribute
	public Tel get(@RequestParam(required = false) String id) {
		Tel entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = telService.get(id);
		}
		if (entity == null) {
			entity = new Tel();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(Tel tel, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Tel> page = telService.findPage(new Page<Tel>(request, response),
				tel);
		model.addAttribute("page", page);
		return "modules/shop/telList";
	}

	@RequestMapping(value = "form")
	public String form(Tel tel, Model model) {
		model.addAttribute("tel", tel);
		return "modules/shop/telForm";
	}

	@RequestMapping(value = "save")
	public String save(Tel tel, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tel)) {
			return form(tel, model);
		}
		telService.save(tel);
		addMessage(redirectAttributes, "保存电话记录成功");
		return "redirect:" + Global.getAdminPath() + "/shop/tel/?repage";
	}

	@RequestMapping(value = "delete")
	public String delete(Tel tel, RedirectAttributes redirectAttributes) {
		telService.delete(tel);
		addMessage(redirectAttributes, "删除电话记录成功");
		return "redirect:" + Global.getAdminPath() + "/shop/tel/?repage";
	}

}