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
import com.huojuit.hishop.modules.shop.entity.SliderImg;
import com.huojuit.hishop.modules.shop.service.SliderImgService;

/**
 * 轮播图Controller
 * @author daiyuxiang
 * @version 2017-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/shop/sliderImg")
public class SliderImgController extends BaseController {

	@Autowired
	private SliderImgService sliderImgService;
	
	@ModelAttribute
	public SliderImg get(@RequestParam(required=false) String id) {
		SliderImg entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sliderImgService.get(id);
		}
		if (entity == null){
			entity = new SliderImg();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(SliderImg sliderImg, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SliderImg> page = sliderImgService.findPage(new Page<SliderImg>(request, response), sliderImg); 
		model.addAttribute("page", page);
		return "modules/shop/sliderImgList";
	}

	@RequestMapping(value = "form")
	public String form(SliderImg sliderImg, Model model) {
		model.addAttribute("sliderImg", sliderImg);
		return "modules/shop/sliderImgForm";
	}

	@RequestMapping(value = "save")
	public String save(SliderImg sliderImg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sliderImg)){
			return form(sliderImg, model);
		}
		sliderImgService.save(sliderImg);
		addMessage(redirectAttributes, "保存轮播图成功");
		return "redirect:"+Global.getAdminPath()+"/shop/sliderImg/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(SliderImg sliderImg, RedirectAttributes redirectAttributes) {
		sliderImgService.delete(sliderImg);
		addMessage(redirectAttributes, "删除轮播图成功");
		return "redirect:"+Global.getAdminPath()+"/shop/sliderImg/?repage";
	}

}