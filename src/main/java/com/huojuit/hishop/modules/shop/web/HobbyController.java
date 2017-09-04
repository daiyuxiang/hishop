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
import com.huojuit.hishop.modules.shop.entity.Hobby;
import com.huojuit.hishop.modules.shop.service.HobbyService;

/**
 * 收藏Controller
 * @author daiyuxiang
 * @version 2017-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/shop/hobby")
public class HobbyController extends BaseController {

	@Autowired
	private HobbyService hobbyService;
	
	@ModelAttribute
	public Hobby get(@RequestParam(required=false) String id) {
		Hobby entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hobbyService.get(id);
		}
		if (entity == null){
			entity = new Hobby();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Hobby hobby, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Hobby> page = hobbyService.findPage(new Page<Hobby>(request, response), hobby); 
		model.addAttribute("page", page);
		return "modules/shop/hobbyList";
	}

	@RequestMapping(value = "form")
	public String form(Hobby hobby, Model model) {
		model.addAttribute("hobby", hobby);
		return "modules/shop/hobbyForm";
	}

	@RequestMapping(value = "save")
	public String save(Hobby hobby, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, hobby)){
			return form(hobby, model);
		}
		hobbyService.save(hobby);
		addMessage(redirectAttributes, "保存收藏成功");
		return "redirect:"+Global.getAdminPath()+"/shop/hobby/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(Hobby hobby, RedirectAttributes redirectAttributes) {
		hobbyService.delete(hobby);
		addMessage(redirectAttributes, "删除收藏成功");
		return "redirect:"+Global.getAdminPath()+"/shop/hobby/?repage";
	}

}