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
import com.huojuit.hishop.modules.shop.entity.ShoppingMall;
import com.huojuit.hishop.modules.shop.service.ShoppingMallService;
import com.huojuit.hishop.modules.shop.utils.ShopConstants;
import com.huojuit.hishop.modules.sys.entity.Area;
import com.huojuit.hishop.modules.sys.service.AreaService;

/**
 * 商场Controller
 * @author daiyuxiang
 * @version 2017-06-18
 */
@Controller
@RequestMapping(value = "${adminPath}/shop/shoppingMall")
public class ShoppingMallController extends BaseController {

	@Autowired
	private ShoppingMallService shoppingMallService;
	
	@Autowired
	private AreaService areaService;
	
	@ModelAttribute
	public ShoppingMall get(@RequestParam(required=false) String id) {
		ShoppingMall entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shoppingMallService.get(id);
		}
		if (entity == null){
			entity = new ShoppingMall();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(ShoppingMall shoppingMall, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShoppingMall> page = shoppingMallService.findPage(new Page<ShoppingMall>(request, response), shoppingMall); 
		model.addAttribute("page", page);
		return "modules/shop/shoppingMallList";
	}

	@RequestMapping(value = "form")
	public String form(ShoppingMall shoppingMall, Model model) {
		/*
		User user = UserUtils.getUser();
		if (shoppingMall.getArea()==null){
			shoppingMall.setArea(user.getOffice().getArea());
		}
		*/
		model.addAttribute("shoppingMall", shoppingMall);
		return "modules/shop/shoppingMallForm";
	}

	@RequestMapping(value = "save")
	public String save(ShoppingMall shoppingMall, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shoppingMall)){
			return form(shoppingMall, model);
		}
		
		if (!shoppingMallNumValidator(model, shoppingMall)){
			return form(shoppingMall, model);
		}
		
		
		String townId = shoppingMall.getTown().getId();
		String parentIds = areaService.getParentIds(townId);
		String[] parentIdArray = parentIds.split(",");
		shoppingMall.setProvince(parentIdArray[2]);
		shoppingMall.setCity(parentIdArray[3]);
		Area area = new Area();
		area.setId(parentIdArray[4]);
		shoppingMall.setArea(area);
	
		
		shoppingMallService.save(shoppingMall);
		addMessage(redirectAttributes, "保存商场成功");
		return "redirect:"+Global.getAdminPath()+"/shop/shoppingMall/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(ShoppingMall shoppingMall, RedirectAttributes redirectAttributes) {
		shoppingMallService.delete(shoppingMall);
		addMessage(redirectAttributes, "删除商场成功");
		return "redirect:"+Global.getAdminPath()+"/shop/shoppingMall/?repage";
	}
	
	/**
	 * 商场数量规则验证
	 * @param shoppingMall 验证的实体对象
	 * @return 验证成功：返回true；验证失败：将错误信息添加到 message 中
	 */
	private boolean shoppingMallNumValidator(Model model, ShoppingMall shoppingMall) {
		try{
			String pageTop = shoppingMall.getPageTop();
			String scrollTop = shoppingMall.getScrollTop();
			String areaTop = shoppingMall.getAreaTop();
			Area area = shoppingMall.getArea();
			List<String> list = new ArrayList<String>();
			boolean flag = true;
			
			if(ConstantsUtil.YES_FLAG.equals(pageTop)) {
				ShoppingMall param = new ShoppingMall();
				param.setPageTop(pageTop);
				int pageNum = shoppingMallService.checkMaxNum(param);
				if(pageNum>=ShopConstants.PAGE_TOP_MAX_NUM) {
					list.add("首页置顶最多为"+ShopConstants.PAGE_TOP_MAX_NUM+"个");
					flag = false;
				}
			}
			
			if(ConstantsUtil.YES_FLAG.equals(scrollTop)) {
				ShoppingMall param = new ShoppingMall();
				param.setScrollTop(scrollTop);
				int scrollNum = shoppingMallService.checkMaxNum(param);
				if(scrollNum>=ShopConstants.SCROLL_TOP_MAX_NUM) {
					list.add("轮播置顶最多为"+ShopConstants.SCROLL_TOP_MAX_NUM+"个");
					flag = false;
				}
			}
			
			if(ConstantsUtil.YES_FLAG.equals(areaTop)) {
				ShoppingMall param = new ShoppingMall();
				param.setAreaTop(areaTop);
				param.setArea(area);
				int areaNum = shoppingMallService.checkMaxNum(param);
				if(areaNum>=ShopConstants.AREA_TOP_MAX_NUM) {
					list.add(area+"区域置顶最多为"+ShopConstants.AREA_TOP_MAX_NUM);
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