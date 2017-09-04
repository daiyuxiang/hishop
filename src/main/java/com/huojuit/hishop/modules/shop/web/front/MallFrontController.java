/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.web.front;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;
import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.utils.ConstantsUtil;
import com.huojuit.hishop.common.utils.StringUtils;
import com.huojuit.hishop.common.web.BaseController;
import com.huojuit.hishop.modules.shop.entity.Hobby;
import com.huojuit.hishop.modules.shop.entity.Hot;
import com.huojuit.hishop.modules.shop.entity.Shop;
import com.huojuit.hishop.modules.shop.entity.ShoppingMall;
import com.huojuit.hishop.modules.shop.service.HobbyService;
import com.huojuit.hishop.modules.shop.service.HotService;
import com.huojuit.hishop.modules.shop.service.ShopService;
import com.huojuit.hishop.modules.shop.service.ShoppingMallService;
import com.huojuit.hishop.modules.shop.utils.ShopConstants;
import com.huojuit.hishop.modules.shop.utils.ShopUtils;
import com.huojuit.hishop.modules.sys.entity.Area;
import com.huojuit.hishop.modules.sys.service.AreaService;
import com.huojuit.hishop.modules.sys.utils.DictUtils;

/**
 * 网站Controller
 * 
 * @author ThinkGem
 * @version 2013-5-29
 */
@Controller
@RequestMapping(value = "${frontPath}/wx/mall/")
public class MallFrontController extends BaseController {

	@Autowired
	private ShoppingMallService shoppingMallService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private HobbyService hobbyService;
	@Autowired
	private HotService hotService;

	@ModelAttribute
	public ShoppingMall get(@RequestParam(required = false) String id) {
		ShoppingMall entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = shoppingMallService.get(id);
		}
		if (entity == null) {
			entity = new ShoppingMall();
		}
		return entity;
	}

	/**
	 * shoppingMall分区域首页
	 */
	@RequestMapping(value = "mallIndex")
	public String mallIndex(Model model) {
		// 根据城市查找所有区域和区域商场数量
		Area city = new Area();
		city.setId(ShopConstants.CITY_ID);
		List<Area> areaList = areaService.findMallNumByCityId(city);

		// 根据区域查找置顶商场
		for (Area area : areaList) {
			ShoppingMall mallParam = new ShoppingMall();
			mallParam.setArea(area);
			mallParam.setAreaTop(ConstantsUtil.YES_FLAG);
			List<ShoppingMall> mallList = shoppingMallService
					.findList(mallParam);
			area.setMallList(mallList);
		}

		model.addAttribute("areaList", areaList);

		return "modules/shop/front/themes/weixin/mallIndex";
	}

	/**
	 * shoppingMall分析页面
	 */
	@RequestMapping(value = "mallAnalysis")
	public String mallAnalysis(Model model, String id) {		
		// 根据区域查询商场
		ShoppingMall mallParam = new ShoppingMall();
		Area a = new Area();
		a.setId(id);
		mallParam.setArea(a);

		List<ShoppingMall> mallList = shoppingMallService.findList(mallParam);

//		for (ShoppingMall shoppingMall : mallList) {
//			// 统计商场数据
//			countMall(shoppingMall);
//		}
		
		List oneMallList = Lists.newArrayList();
		List twoMallList = Lists.newArrayList();
		List tempList = Lists.newArrayList();
		// 前端美工需要特殊结构，无语
		for(int i=0;i<mallList.size();i++) {
			if(i<2) {
				oneMallList.add(mallList.get(i));
			} else {
				tempList.add(mallList.get(i));
			}
		}
		
		//如果是单数，最后一个单独处理
		List lastList = Lists.newArrayList();
		if(tempList.size()%2==1) {
			lastList.add(tempList.get(tempList.size()-1));
			tempList.remove(tempList.size()-1);
		}
		
		List tempList2 = Lists.newArrayList();
		for(int j=0;j<tempList.size();j++) {
			tempList2.add(tempList.get(j));
			if(j%2==1) {
				twoMallList.add(tempList2);
				tempList2 = Lists.newArrayList();
			} 
		}		
		
		if(lastList.size()>0) {
			twoMallList.add(lastList);
		}
		
		model.addAttribute("oneMallList", oneMallList);
		model.addAttribute("twoMallList", twoMallList);
		
		return "modules/shop/front/themes/weixin/mallAnalysis";
	}

	/**
	 * shoppingMall详情页
	 */
	@RequestMapping(value = "mallInfo")
	public String mallInfo(Shop shop, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 统计商场数据
		ShoppingMall shoppingMall = shoppingMallService.get(shop.getShoppingMall());
		//countMall(shoppingMall);
		
		// 查找区域
		Area city = new Area();
		city.setId(ShopConstants.CITY_ID);
		List<Area> areaList = areaService.findAreaByParentId(city);
		
		// 查询条件处理
		ShopUtils.preQuery(shop);
		// 默认设置上架
		shop.setShowFlag(ShopConstants.SHOW_FLAG_1);
		
		// 查找商场下的店铺
		Page<Shop> shopPage = shopService.findPage(new Page<Shop>(0, 20),
				shop);

		// 查找是否被收藏,热门推荐
		Hobby hobbyParam = new Hobby();
		Hot hotParam = new Hot();
		for (Shop s : shopPage.getList()) {
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
		}

		model.addAttribute("areaList", areaList);
		model.addAttribute("shopPage", shopPage);
		model.addAttribute("shoppingMall", shoppingMall);

		return "modules/shop/front/themes/weixin/mallInfo";
	}

	public void countMall(ShoppingMall shoppingMall) {
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(2);
		
		Map paramMap = new HashMap();
		paramMap.put("shoppingMall", shoppingMall.getId());
		List<String> typeList = Lists.newArrayList();

		// 购物面积
		String type1 = DictUtils.getDictValue("百货/购物中心",
				ConstantsUtil.SHOP_SHOP_TYPE, "");
		int shoppingArea = shopService.countArea(type1, shoppingMall.getId());
		String shoppingAreaString = numberFormat.format(shoppingArea / 10000);
		shoppingMall.setShoppingArea(shoppingAreaString);

		// 写字楼面积
		String type2 = DictUtils.getDictValue("写字楼配套",
				ConstantsUtil.SHOP_SHOP_TYPE, "");
		int officeArea = shopService.countArea(type2, shoppingMall.getId());
		String officeAreaString = numberFormat.format(officeArea / 10000);
		shoppingMall.setOfficeArea(officeAreaString);

		// 餐饮
		String value1 = DictUtils.getDictValue("餐饮美食",
				ConstantsUtil.SHOP_MANAGEMENT_FORMAT, "");
		typeList.add(value1);

		paramMap.put("types", typeList);
		int num1 = shopService.countDynamic(paramMap);
		

		// 购物
		typeList.clear();
		String value2 = DictUtils.getDictValue("服饰鞋包",
				ConstantsUtil.SHOP_MANAGEMENT_FORMAT, "");
		String value3 = DictUtils.getDictValue("百货超市",
				ConstantsUtil.SHOP_MANAGEMENT_FORMAT, "");
		typeList.add(value2);
		typeList.add(value3);

		paramMap.put("types", typeList);
		int num2 = shopService.countDynamic(paramMap);

		// 休闲娱乐
		typeList.clear();
		String value4 = DictUtils.getDictValue("休闲娱乐",
				ConstantsUtil.SHOP_MANAGEMENT_FORMAT, "");
		String value5 = DictUtils.getDictValue("美容美发",
				ConstantsUtil.SHOP_MANAGEMENT_FORMAT, "");
		typeList.add(value4);
		typeList.add(value5);

		paramMap.put("types", typeList);
		int num3 = shopService.countDynamic(paramMap);
		
		// 教育培训
		typeList.clear();
		String value6 = DictUtils.getDictValue("教育培训",
				ConstantsUtil.SHOP_MANAGEMENT_FORMAT, "");
		typeList.add(value6);

		paramMap.put("types", typeList);
		int num4 = shopService.countDynamic(paramMap);
		
		// 生活服务
		typeList.clear();
		String value7 = DictUtils.getDictValue("生活服务",
				ConstantsUtil.SHOP_MANAGEMENT_FORMAT, "");
		String value8 = DictUtils.getDictValue("家居建材",
				ConstantsUtil.SHOP_MANAGEMENT_FORMAT, "");
		String value9 = DictUtils.getDictValue("电器通讯",
				ConstantsUtil.SHOP_MANAGEMENT_FORMAT, "");
		String value10 = DictUtils.getDictValue("汽修美容",
				ConstantsUtil.SHOP_MANAGEMENT_FORMAT, "");
		String value11 = DictUtils.getDictValue("医药保健",
				ConstantsUtil.SHOP_MANAGEMENT_FORMAT, "");
		String value12 = DictUtils.getDictValue("旅馆宾馆",
				ConstantsUtil.SHOP_MANAGEMENT_FORMAT, "");
		String value13 = DictUtils.getDictValue("其它业态",
				ConstantsUtil.SHOP_MANAGEMENT_FORMAT, "");
		typeList.add(value7);
		typeList.add(value8);
		typeList.add(value9);
		typeList.add(value10);
		typeList.add(value11);
		typeList.add(value12);
		typeList.add(value13);

		paramMap.put("types", typeList);
		int num5 = shopService.countDynamic(paramMap);

		// 动态客流
		// 动态人流= 餐饮+购物+休闲娱乐+教育培训+生活服务 人流
		// 餐饮=300人* 餐饮商铺数量* 3
		// 购物=500*购物店铺数量*4
		// 休闲娱乐=200*店铺数量*3
		// 教育培训=100*店铺数量*2
		// 生活服务=50*店铺数量*2
		double dynamicFlow = 300 * num1 * 3 + 500 * num2 * 4 + 200 * num3 * 3
				+ 100 * num4 * 2 + 50 * num5 * 2;
		String dynamicFlowString = numberFormat.format(dynamicFlow / 10000);
		shoppingMall.setDynamicFlow(dynamicFlowString);

		// 静态客流
		// 静态人流量=动态人流*0.34
		double staticFlow = dynamicFlow * 0.34;
		String staticFlowString = numberFormat.format(staticFlow / 10000);
		shoppingMall.setStaticFlow(staticFlowString);

		//总的店铺数
		int total = num1+num2+num3+num4+num5;
		
		
		if(total!=0) {
			// 创建一个数值格式化对象
			String percent1 = numberFormat.format((float) num1 / (float) total * 100);
			shoppingMall.setPercent1(percent1);
			String percent2 = numberFormat.format((float) num2 / (float) total * 100);
			shoppingMall.setPercent2(percent2);
			String percent3 = numberFormat.format((float) num3 / (float) total * 100);
			shoppingMall.setPercent3(percent3);
			String percent4 = numberFormat.format((float) num4 / (float) total * 100);
			shoppingMall.setPercent4(percent4);
		}		
	}
}
