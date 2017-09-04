/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.huojuit.hishop.common.config.Global;
import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.utils.ConstantsUtil;
import com.huojuit.hishop.common.utils.StringUtils;
import com.huojuit.hishop.common.web.BaseController;
import com.huojuit.hishop.modules.shop.entity.CooperationBrand;
import com.huojuit.hishop.modules.shop.service.CooperationBrandService;
import com.huojuit.hishop.modules.shop.utils.ShopConstants;

/**
 * 合作品牌Controller
 * @author daiyuxiang
 * @version 2017-06-18
 */
@Controller
@RequestMapping(value = "${adminPath}/shop/cooperationBrand")
public class CooperationBrandController extends BaseController {

	@Autowired
	private CooperationBrandService cooperationBrandService;
	
	@ModelAttribute
	public CooperationBrand get(@RequestParam(required=false) String id) {
		CooperationBrand entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cooperationBrandService.get(id);
		}
		if (entity == null){
			entity = new CooperationBrand();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(CooperationBrand cooperationBrand, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CooperationBrand> page = cooperationBrandService.findPage(new Page<CooperationBrand>(request, response), cooperationBrand); 
		model.addAttribute("page", page);
		return "modules/shop/cooperationBrandList";
	}

	@RequestMapping(value = "form")
	public String form(CooperationBrand cooperationBrand, Model model) {
		model.addAttribute("cooperationBrand", cooperationBrand);
		return "modules/shop/cooperationBrandForm";
	}

	@RequestMapping(value = "save")
	public String save(CooperationBrand cooperationBrand, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cooperationBrand)){
			return form(cooperationBrand, model);
		}
		
		if (!cooperationBrandNumValidator(model, cooperationBrand)){
			return form(cooperationBrand, model);
		}
		
		cooperationBrandService.save(cooperationBrand);
		addMessage(redirectAttributes, "保存合作品牌成功");
		return "redirect:"+Global.getAdminPath()+"/shop/cooperationBrand/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(CooperationBrand cooperationBrand, RedirectAttributes redirectAttributes) {
		cooperationBrandService.delete(cooperationBrand);
		addMessage(redirectAttributes, "删除合作品牌成功");
		return "redirect:"+Global.getAdminPath()+"/shop/cooperationBrand/?repage";
	}

	/**
	 * 商场数量规则验证
	 * @param shoppingMall 验证的实体对象
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
	 */
	private boolean cooperationBrandNumValidator(Model model, CooperationBrand cooperationBrand) {
		try{
			String topTop = cooperationBrand.getTopFlag();
			
			List<String> list = new ArrayList<String>();
			boolean flag = true;
			
			if(ConstantsUtil.YES_FLAG.equals(topTop)) {
				CooperationBrand param = new CooperationBrand();
				param.setTopFlag(topTop);
				int pageNum = cooperationBrandService.checkMaxNum(param);
				if(pageNum>=ShopConstants.BRAND_TOP_MAX_NUM) {
					list.add("品牌置顶最多为"+ShopConstants.BRAND_TOP_MAX_NUM+"个");
					flag = false;
				}
			}
						
			
			if(!flag) {
				list.add(0, "数据验证失败：");
				addMessage(model, list.toArray(new String[]{}));
				return false;
			}
			
		}catch(ConstraintViolationException ex){		
			return false;
		}
		return true;
	}
}