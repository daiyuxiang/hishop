/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.web.front;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.huojuit.hishop.common.config.Global;
import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.utils.ConstantsUtil;
import com.huojuit.hishop.common.utils.DateUtils;
import com.huojuit.hishop.common.utils.MessageBean;
import com.huojuit.hishop.common.utils.StringUtils;
import com.huojuit.hishop.common.web.BaseController;
import com.huojuit.hishop.modules.shop.entity.Hobby;
import com.huojuit.hishop.modules.shop.entity.Hot;
import com.huojuit.hishop.modules.shop.entity.Mating;
import com.huojuit.hishop.modules.shop.entity.Member;
import com.huojuit.hishop.modules.shop.entity.Shop;
import com.huojuit.hishop.modules.shop.entity.ShoppingMall;
import com.huojuit.hishop.modules.shop.entity.SliderImg;
import com.huojuit.hishop.modules.shop.service.FindShopService;
import com.huojuit.hishop.modules.shop.service.HobbyService;
import com.huojuit.hishop.modules.shop.service.HotService;
import com.huojuit.hishop.modules.shop.service.MatingService;
import com.huojuit.hishop.modules.shop.service.MemberService;
import com.huojuit.hishop.modules.shop.service.ShopService;
import com.huojuit.hishop.modules.shop.service.ShoppingMallService;
import com.huojuit.hishop.modules.shop.service.SliderImgService;
import com.huojuit.hishop.modules.shop.utils.ShopConstants;
import com.huojuit.hishop.modules.shop.utils.ShopUtils;
import com.huojuit.hishop.modules.sys.entity.Area;
import com.huojuit.hishop.modules.sys.service.AreaService;
import com.huojuit.hishop.modules.wx.entity.WxUserinfo;

/**
 * 网站Controller
 * 
 * @author ThinkGem
 * @version 2013-5-29
 */
@Controller
@RequestMapping(value = "${frontPath}/wx/shop/")
public class ShopFrontController extends BaseController {

	@Autowired
	private ShopService shopService;
	@Autowired
	private ShoppingMallService shoppingMallService;
	@Autowired
	private FindShopService findShopService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private SliderImgService sliderImgService;
	@Autowired
	private HobbyService hobbyService;
	@Autowired
	private HotService hotService;
	@Autowired
	private MatingService matingService;
	@Autowired
	private MemberService memberService;
	
	@ModelAttribute
	public Shop get(@RequestParam(required = false) String id) {
		Shop entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = shopService.get(id);
		}
		if (entity == null) {
			entity = new Shop();
		}
		return entity;
	}

	/**
	 * 微信首页
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		// 轮播图
		List<SliderImg> sliderImgList = sliderImgService
				.findList(new SliderImg());

		// 今日新铺数
		int countToday = shopService.countToday();
		// 当前商铺数
		int countAll = shopService.countAll()+10000;

		// 正在找铺数
		int countFind = findShopService.countFind()+1000;

		// 查找首页置顶的商场
		ShoppingMall pageMallParam = new ShoppingMall();
		pageMallParam.setPageTop(ConstantsUtil.YES_FLAG);
		List<ShoppingMall> pageMallList = shoppingMallService
				.findList(pageMallParam);

		// 查找轮播置顶的商场
		ShoppingMall scrollMallParam = new ShoppingMall();
		scrollMallParam.setScrollTop(ConstantsUtil.YES_FLAG);
		List<ShoppingMall> scrollMallList = shoppingMallService
				.findList(scrollMallParam);

		// 合并两类商场
		pageMallList.addAll(scrollMallList);

		// 查找推荐的店铺
		Shop shopParam = new Shop();
		shopParam.setShowFlag(ShopConstants.SHOW_FLAG_1);
		shopParam.setGoldFlag(ConstantsUtil.YES_FLAG);
		Page<Shop> page = shopService
				.findPage(new Page<Shop>(0, 20), shopParam);

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

		model.addAttribute("sliderImgList", sliderImgList);
		model.addAttribute("countToday", countToday);
		model.addAttribute("countToday", countToday);
		model.addAttribute("countAll", countAll);
		model.addAttribute("countFind", countFind);
		model.addAttribute("shoppingMallList", pageMallList);
		model.addAttribute("page", page);

		return "modules/shop/front/themes/weixin/index";
	}

	/**
	 * 商铺首页
	 */
	@RequestMapping(value = "shopIndex")
	public String shopIndex(Shop shop, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 查找区域
		Area city = new Area();
		city.setId(ShopConstants.CITY_ID);
		List<Area> areaList = areaService.findAreaByParentId(city);

		// 查询条件处理
		ShopUtils.preQuery(shop);
		// 默认设置上架
		shop.setShowFlag(ShopConstants.SHOW_FLAG_1);
		
		Page<Shop> p = new Page<Shop>(0,12);
		String requestPageSize = request.getParameter("pageSize");
		if(StringUtils.isNoneBlank(requestPageSize)) {
			p = new Page<Shop>(request, response);
		}

		Page<Shop> page = shopService.findPage(p, shop);

		// 查找是否被收藏,热门推荐
		Hobby hobbyParam = new Hobby();
		Hot hotParam = new Hot();
		for (Shop s : page.getList()) {
			hobbyParam.setShopId(s.getId());
			String openId = getUser(request).getOpenid();
			hobbyParam.setOpenId(openId);
			List<Hobby> hobbyList = hobbyService.findList(hobbyParam);
			if (hobbyList != null && hobbyList.size() > 0) {
				s.setHobbyFlag(ConstantsUtil.YES_FLAG);
			}

			hotParam.setShopId(s.getId());
			List<Hot> hotList = hotService.findList(hotParam);
			if (hotList != null && hotList.size() > 0) {
				s.setHotList(hotList);
			}
			
			s.setUpdateDateStr(DateUtils.differentTime(s.getUpdateDate()));
		}
		
		if (page.getCount() <= page.getPageSize()) {
			model.addAttribute("moreFlag", false);
		} else {
			model.addAttribute("moreFlag", true);
		}


		model.addAttribute("areaList", areaList);
		model.addAttribute("page", page);

		return "modules/shop/front/themes/weixin/shopIndex";
	}
	
	/**
	 * 加载更多数据
	 */
	@RequestMapping(value = "moreShop")
	@ResponseBody
	public Page<Shop> moreShop(Shop shop, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 查询条件处理
		ShopUtils.preQuery(shop);
		// 默认设置上架
		shop.setShowFlag(ShopConstants.SHOW_FLAG_1);

		Page<Shop> page = shopService.findPage(new Page<Shop>(request, response), shop);

		// 查找是否被收藏,热门推荐
		Hobby hobbyParam = new Hobby();
		Hot hotParam = new Hot();
		for (Shop s : page.getList()) {
			hobbyParam.setShopId(s.getId());
			String openId = getUser(request).getOpenid();
			hobbyParam.setOpenId(openId);
			List<Hobby> hobbyList = hobbyService.findList(hobbyParam);
			if (hobbyList != null && hobbyList.size() > 0) {
				s.setHobbyFlag(ConstantsUtil.YES_FLAG);
			}

			hotParam.setShopId(s.getId());
			List<Hot> hotList = hotService.findList(hotParam);
			if (hotList != null && hotList.size() > 0) {
				s.setHotList(hotList);
			}
			
			s.setUpdateDateStr(DateUtils.differentTime(s.getUpdateDate()));
		}
		
		return page;
	}

	/**
	 * 发布首页
	 */
	@RequestMapping(value = "publishIndex")
	public String publishIndex(Model model) {
		return "modules/shop/front/themes/weixin/publishIndex";
	}

	/**
	 * 个人中心首页
	 */
	@RequestMapping(value = "myIndex")
	public String myIndex(Model model,HttpServletRequest request) {
		// 用户信息
		WxUserinfo user = getUser(request);
		 
		model.addAttribute("user", user);
		
		return "modules/shop/front/themes/weixin/myIndex";
	}

	/**
	 * 商铺详情页
	 */
	@RequestMapping(value = "shopInfo")
	public String shopInfo(Model model, Shop shop,HttpServletRequest request) {
		//查找是否被收藏
		Hobby hobbyParam = new Hobby();
		hobbyParam.setShopId(shop.getId());
		String openId = getUser(request).getOpenid();
		hobbyParam.setOpenId(openId);
		List<Hobby> hobbyList = hobbyService.findList(hobbyParam);
		if (hobbyList != null && hobbyList.size() > 0) {
			shop.setHobbyFlag(ConstantsUtil.YES_FLAG);
		}
		
		// 查找热门推荐
		Hot hotParam = new Hot();
		hotParam.setShopId(shop.getId());
		List<Hot> hotList = hotService.findList(hotParam);
		if (hotList != null && hotList.size() > 0) {
			shop.setHotList(hotList);
		}
		
		// 查询配套
		Mating matingParam = new Mating();
		matingParam.setShopId(shop.getId());
		List<Mating> matingList = matingService.findList(matingParam);
		if (matingList != null && matingList.size() > 0) {
			shop.setMatingList(matingList);
		}
		
		model.addAttribute("shop", shop);
		return "modules/shop/front/themes/weixin/shopInfo";
	}

	/**
	 * shoppingMall分区域首页
	 */
	@RequestMapping(value = "mallIndex")
	public String mallIndex(Model model) {
		return "modules/shop/front/themes/weixin/mallIndex";
	}

	/**
	 * shoppingMall分析页面
	 */
	@RequestMapping(value = "mallAnalysis")
	public String mallAnalysis(Model model) {
		return "modules/shop/front/themes/weixin/mallAnalysis";
	}

	/**
	 * shoppingMall详情页
	 */
	@RequestMapping(value = "mallInfo")
	public String mallInfo(Model model) {
		return "modules/shop/front/themes/weixin/mallInfo";
	}

	/**
	 * 发布转铺页面
	 */
	@RequestMapping(value = "saleShopForm")
	public String saleShopForm(Shop shop, Model model) {
		// 查找区域
		Area city = new Area();
		city.setId(ShopConstants.CITY_ID);
		List<Area> areaList = areaService.findAreaByParentId(city);
		
		// 查询镇
		List<Area> townList = areaService.findAreaByParentId(areaList.get(0));

		// 根据区域查询商场
		ShoppingMall paramModel = new ShoppingMall();
		paramModel.setArea(areaList.get(0));
		List<ShoppingMall> shoppingMallList = shoppingMallService.findList(paramModel);
		
		// 查找转让成功数
		int saleSucessNum = shopService.countSaleSuccess();
		
		model.addAttribute("areaList", areaList);
		model.addAttribute("townList", townList);
		model.addAttribute("shoppingMallList", shoppingMallList);
		model.addAttribute("shop", shop);
		model.addAttribute("saleSucessNum", saleSucessNum);

		return "modules/shop/front/themes/weixin/saleShopForm";
	}

	/**
	 * 完善信息页面
	 */
	@RequestMapping(value = "saleMoreForm")
	public String saleMoreForm(Shop shop, Model model) {
		model.addAttribute("shop", shop);

		return "modules/shop/front/themes/weixin/saleMoreForm";
	}

	@RequestMapping(value = "save")
	public String save(Shop shop, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		// 新增店铺
		ShopUtils.preSave(shop, areaService);

		shopService.saveShop(shop);
		
		// 新增需求信息
		WxUserinfo user = getUser(request);
		String openId = user.getOpenid();
		Member member = new Member();
		member.setOpenId(openId);
		member.setNickName(user.getNickname());
		member.setMobile(shop.getMobile());
		member.setSaleFlag(ConstantsUtil.YES_FLAG);
		member.setPublishSaleFlag(ConstantsUtil.NO_FLAG);
		member.setFindFlag(ConstantsUtil.NO_FLAG);
		member.setShopId(shop.getId());
		member.setShopDate(new Date());
		memberService.save(member);
		
		//删除游客数据
		memberService.deleteVistor(openId);
				
		redirectAttributes.addFlashAttribute("shop", shop);
		
		return "redirect:" + Global.getFrontPath() + "/wx/shop/saleSuccess";

	}
	
	@RequestMapping(value = "saveMore")
	public @ResponseBody MessageBean<String> saveMore(Shop shop, Model model) {
		MessageBean<String> messageBean = new MessageBean<String>();
		
		// 新增店铺
		ShopUtils.preSave(shop, areaService);

		shopService.saveShop(shop);
		
		messageBean.setStatus(1);

		return messageBean;
	}
	
	
	@RequestMapping(value = "saleSuccess")
	public String save(Shop shop, Model model) {	
		return "modules/shop/front/themes/weixin/saleSuccess";
	}

	@RequestMapping(value = "slideshow")
	public String slideShow() {	
		return "modules/shop/front/themes/weixin/slideshow";
	}

	/**
	 * 根据区域查询商场
	 * 
	 * @param area
	 *            区域
	 * @return
	 */
	@RequestMapping(value = "getShoppingMallByArea")
	public @ResponseBody List<ShoppingMall> getShoppingMallByArea(String areaId) {
		// 根据区域查询商场
		List<ShoppingMall> shoppingMallList = new ArrayList<ShoppingMall>();

		if (StringUtils.isNoneBlank(areaId)) {
			ShoppingMall paramModel = new ShoppingMall();
			Area area = new Area();
			area.setId(areaId);
			paramModel.setArea(area);
			shoppingMallList = shoppingMallService.findList(paramModel);
		}

		return shoppingMallList;
	}
	
	/**
	 * 根据区域查询镇
	 * 
	 * @param area
	 *            区域
	 * @return
	 */
	@RequestMapping(value = "getTownByArea")
	public @ResponseBody List<Area> getTownByArea(String areaId) {
		// 根据区域查询镇
		List<Area> townList = new ArrayList<Area>();

		if (StringUtils.isNoneBlank(areaId)) {
			Area area = new Area();
			area.setId(areaId);
			townList = areaService.findAreaByParentId(area);
		}

		return townList;
	}
	
	@RequestMapping(value = "/checkMobile")
	@ResponseBody
	public String checkMobile(Shop shop, Model model) {
		List<Shop> list = shopService.phone(shop.getMobile());
		if(list.isEmpty()){
			return "success";
		}
		return  "error";
	}
}
