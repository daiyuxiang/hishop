/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.wx.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.utils.StringUtils;
import com.huojuit.hishop.common.web.BaseController;
import com.huojuit.hishop.common.web.WxBaseController;
import com.huojuit.hishop.modules.cms.entity.Site;
import com.huojuit.hishop.modules.cms.utils.CmsUtils;
import com.huojuit.hishop.modules.wx.entity.WxUserinfo;
import com.huojuit.hishop.modules.wx.service.WxUserinfoService;

/**
 * 微信用户信息Controller
 * 
 * @author daiyuxiang
 * @version 2016-12-16
 */
@Controller
@RequestMapping(value = "${frontPath}/wx/user")
public class UserinfoController extends WxBaseController {

	@Autowired
	private WxUserinfoService wxUserInfoService;

	@ModelAttribute
	public WxUserinfo get(@RequestParam(required = false) String id) {
		WxUserinfo entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = wxUserInfoService.get(id);
		}
		if (entity == null) {
			entity = new WxUserinfo();
		}
		return entity;
	}

	@RequestMapping(value = { "index", "" })
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		WxUserinfo user = this.getUser(request);

		System.out.println("========userid:" + user.getOpenid());
		model.addAttribute("userInfo", user);
		return "modules/wx/my";
	}

	@RequestMapping(value = { "detail", "" })
	public String detail(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		WxUserinfo user = this.getUser(request);

		model.addAttribute("userInfo", user);
		return "modules/wx/my_detail";
	}
}