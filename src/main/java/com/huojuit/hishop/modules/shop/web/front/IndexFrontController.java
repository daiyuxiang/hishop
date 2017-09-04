/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.web.front;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huojuit.hishop.common.web.BaseController;
import com.huojuit.hishop.modules.shop.service.ShopService;

/**
 * 网站Controller
 * 
 * @author daiyuxiang
 * @version 2017-06-24
 */
@Controller
@RequestMapping(value = "/")
public class IndexFrontController extends BaseController {

	@Autowired
	private ShopService shopService;
	
	@RequestMapping(value = { "" })
	public String index(Model model,HttpServletRequest request) {

		// 当前面积
		int countArea = shopService.countArea1()+1660440;

		// 当前商铺数
		int countAll = shopService.countAll()+14129;
		
		// 成交数
		int countSale = shopService.countSaleSuccess()+582;
		
		model.addAttribute("countArea", countArea);
		model.addAttribute("countAll", countAll);
		model.addAttribute("countSale", countSale);

		return "modules/shop/front/themes/basic/index";
	}
	@RequestMapping(value = { "contact_us" })
	public String contact_us(Model model,HttpServletRequest request) {
		return "modules/shop/front/themes/basic/contact_us";
	}
	
}