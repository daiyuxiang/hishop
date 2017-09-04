/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.web.front;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huojuit.hishop.common.config.Global;
import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.utils.ConstantsUtil;
import com.huojuit.hishop.common.utils.StringUtils;
import com.huojuit.hishop.common.web.BaseController;
import com.huojuit.hishop.modules.shop.entity.CooperationBrand;
import com.huojuit.hishop.modules.shop.entity.FindShop;
import com.huojuit.hishop.modules.shop.entity.Hobby;
import com.huojuit.hishop.modules.shop.entity.Hot;
import com.huojuit.hishop.modules.shop.entity.Member;
import com.huojuit.hishop.modules.shop.entity.Shop;
import com.huojuit.hishop.modules.shop.entity.ShoppingMall;
import com.huojuit.hishop.modules.shop.service.CooperationBrandService;
import com.huojuit.hishop.modules.shop.service.FindShopService;
import com.huojuit.hishop.modules.shop.service.HobbyService;
import com.huojuit.hishop.modules.shop.service.HotService;
import com.huojuit.hishop.modules.shop.service.MemberService;
import com.huojuit.hishop.modules.shop.service.ShopService;
import com.huojuit.hishop.modules.shop.utils.ShopConstants;
import com.huojuit.hishop.modules.shop.utils.ShopUtils;
import com.huojuit.hishop.modules.sys.entity.Area;
import com.huojuit.hishop.modules.sys.service.AreaService;
import com.huojuit.hishop.modules.wx.entity.WxUserinfo;
import com.huojuit.hishop.modules.wx.service.WxUserinfoService;

/**
 * 找铺Controller
 * 
 * @author daiyuxiang
 * @version 2017-06-24
 */
@Controller
@RequestMapping(value = "${frontPath}/wx/findShop")
public class FindShopFrontController extends BaseController {

	@Autowired
	private FindShopService findShopService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private WxUserinfoService userinfoService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private CooperationBrandService cooperationBrandService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private HobbyService hobbyService;
	@Autowired
	private HotService hotService;

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

	@RequestMapping(value = "form")
	public String form(FindShop findShop, Model model) {
		// 查找区域
		Area city = new Area();
		city.setId(ShopConstants.CITY_ID);
		List<Area> areaList = areaService.findAreaByParentId(city);

		// 查询镇
		// List<Area> townList =
		// areaService.findAreaByParentId(areaList.get(0));

		model.addAttribute("areaList", areaList);
		// model.addAttribute("townList", townList);
		model.addAttribute("findShop", findShop);

		return "modules/shop/front/themes/weixin/findShopForm";
	}

	@RequestMapping(value = "findSuccess")
	public String findSuccess(FindShop findShop, Model model, HttpServletRequest request) {
		// 推荐品牌
		CooperationBrand cbParam = new CooperationBrand();
		cbParam.setFormatId(findShop.getManagementFormat());

		List<CooperationBrand> cbList = cooperationBrandService.findList(cbParam);
		model.addAttribute("cbList", cbList);

		// 区域店铺数
		Area area = areaService.get(findShop.getAreaId());

		// 统计符合条件的商铺
		Shop shopParam = new Shop();

		// 区域
		shopParam.setArea(area);
		// 费用
		shopParam.setFeeString(findShop.getFee());
		// 面积
		shopParam.setUseAreaString(findShop.getCoveredArea());
		// 商铺类型
		shopParam.setShopType(findShop.getShopType());
		// 业态
		shopParam.setManagementFormat(findShop.getManagementFormat());

		// 查询条件处理
		ShopUtils.preQuery(shopParam);
		// 默认设置上架
		shopParam.setShowFlag(ShopConstants.SHOW_FLAG_1);

		List shopList = shopService.findList(shopParam);

		// 查询该区域的金牌店铺
		Shop goldParam = new Shop();

		goldParam.setArea(area);
		goldParam.setShowFlag(ShopConstants.SHOW_FLAG_1);
		goldParam.setGoldFlag(ConstantsUtil.YES_FLAG);

		Page<Shop> page = shopService.findPage(new Page<Shop>(0, 20), goldParam);

		// 查找是否被收藏,热门推荐
		Hobby hobbyParam = new Hobby();
		Hot hotParam = new Hot();
		for (Shop shop : page.getList()) {
			hobbyParam.setShopId(shop.getId());
			String openId = getUser(request).getOpenid();
			hobbyParam.setOpenId(openId);
			List<Hobby> hobbyList = hobbyService.findList(hobbyParam);
			if (hobbyList != null && hobbyList.size() > 0) {
				shop.setHobbyFlag(ConstantsUtil.YES_FLAG);
			}

			hotParam.setShopId(shop.getId());
			List<Hot> hotList = hotService.findList(hotParam);
			if (hotList != null && hotList.size() > 0) {
				shop.setHotList(hotList);
			}
		}

		// 购物中心数 学校 范围 调取百度地图
		model.addAttribute("shopCount", shopList.size());

		if (area != null) {
			model.addAttribute("shopNum", area.getShopNum());
		} else {
			model.addAttribute("shopNum", 0);
		}
	
		model.addAttribute("page", page);

		return "modules/shop/front/themes/weixin/findSuccess";
	}

	@RequestMapping(value = "save")
	public String save(FindShop findShop, Model model, HttpServletRequest request) {
		// 设置openId 和 nickName
		WxUserinfo user = getUser(request);
		String openId = user.getOpenid();
		String nickName = user.getNickname();
		findShop.setOpenId(openId);
		findShop.setNickName(nickName);

		preSave(findShop, areaService);

		findShopService.saveFindShop(findShop);

		// 新增需求信息
		Member member = new Member();
		member.setOpenId(openId);
		member.setNickName(user.getNickname());
		member.setMobile(findShop.getMobile());
		member.setSaleFlag(ConstantsUtil.NO_FLAG);
		member.setPublishSaleFlag(ConstantsUtil.NO_FLAG);
		member.setFindFlag(ConstantsUtil.YES_FLAG);
		member.setFindId(findShop.getId());
		member.setFindDate(new Date());
		memberService.save(member);

		// 删除游客数据
		memberService.deleteVistor(openId);

		return "redirect:" + Global.getFrontPath() + "/wx/findShop/findSuccess?id=" + findShop.getId();
	}

	public static void preSave(FindShop findShop, AreaService areaService) {
		// 设置省份和城市 areaId为空代表全部
		if (StringUtils.isNoneBlank(findShop.getAreaId())) {
			Area area = areaService.get(findShop.getAreaId());
			String parentIds = area.getParentIds();

			String[] idArray = parentIds.split(",");
			findShop.setProvince(idArray[2]);
			findShop.setCity(idArray[3]);
		}

	}

}