/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.huojuit.hishop.common.config.Global;
import com.huojuit.hishop.common.utils.MessageBean;
import com.huojuit.hishop.common.utils.StringUtils;
import com.huojuit.hishop.common.web.BaseController;
import com.huojuit.hishop.modules.shop.entity.Follow;
import com.huojuit.hishop.modules.shop.service.FollowService;

/**
 * FollowController
 * 
 * @author daiyuxiang
 * @version 2017-06-19
 */
@Controller
@RequestMapping(value = "${adminPath}/shop/follow")
public class FollowController extends BaseController {
	
	@Autowired
	private FollowService followService;
	

	@ModelAttribute
	public Follow get(@RequestParam(required = false) String id) {
		Follow entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = followService.get(id);
		}
		if (entity == null) {
			entity = new Follow();
		}
		return entity;
	}

	
	@RequestMapping(value = "save")
	public @ResponseBody MessageBean<String> save(Follow follow, Model model) {
		MessageBean<String> messageBean = new MessageBean<String>();
		
		// 添加跟进记录
		followService.save(follow);
		
		messageBean.setStatus(1);

		return messageBean;
	}


}