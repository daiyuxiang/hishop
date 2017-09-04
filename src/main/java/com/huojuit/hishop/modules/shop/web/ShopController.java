/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.huojuit.hishop.common.beanvalidator.BeanValidators;
import com.huojuit.hishop.common.config.Global;
import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.utils.ConstantsUtil;
import com.huojuit.hishop.common.utils.DateUtils;
import com.huojuit.hishop.common.utils.StringUtils;
import com.huojuit.hishop.common.utils.excel.ExportExcel;
import com.huojuit.hishop.common.utils.excel.ImportExcel;
import com.huojuit.hishop.common.web.BaseController;
import com.huojuit.hishop.modules.shop.entity.Follow;
import com.huojuit.hishop.modules.shop.entity.Hot;
import com.huojuit.hishop.modules.shop.entity.Mating;
import com.huojuit.hishop.modules.shop.entity.Member;
import com.huojuit.hishop.modules.shop.entity.Shop;
import com.huojuit.hishop.modules.shop.entity.ShoppingMall;
import com.huojuit.hishop.modules.shop.service.FollowService;
import com.huojuit.hishop.modules.shop.service.HotService;
import com.huojuit.hishop.modules.shop.service.MatingService;
import com.huojuit.hishop.modules.shop.service.MemberService;
import com.huojuit.hishop.modules.shop.service.ShopService;
import com.huojuit.hishop.modules.shop.service.ShoppingMallService;
import com.huojuit.hishop.modules.shop.utils.ShopConstants;
import com.huojuit.hishop.modules.shop.utils.ShopUtils;
import com.huojuit.hishop.modules.sys.entity.Area;
import com.huojuit.hishop.modules.sys.service.AreaService;

/**
 * 店铺Controller
 * 
 * @author daiyuxiang
 * @version 2017-06-19
 */
@Controller
@RequestMapping(value = "${adminPath}/shop/shop")
public class ShopController extends BaseController {

	@Autowired
	private ShopService shopService;
	@Autowired
	private ShoppingMallService shoppingMallService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private MatingService matingService;
	@Autowired
	private HotService hotService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private FollowService followService;
	

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

	@RequestMapping(value = { "list", "" })
	public String list(Shop shop, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 查询条件处理
		ShopUtils.preQuery(shop);

		Page<Shop> page = shopService.findPage(new Page<Shop>(request, response), shop);
		model.addAttribute("page", page);
		return "modules/shop/shopList";
	}

	@RequestMapping(value = "form")
	public String form(Shop shop, Model model) {
		// 根据区域查询商场
		List<ShoppingMall> shoppingMallList = new ArrayList<ShoppingMall>();

		if (shop.getArea() != null) {
			ShoppingMall paramModel = new ShoppingMall();
			paramModel.setArea(shop.getArea());
			shoppingMallList = shoppingMallService.findList(paramModel);
		}

		// 查询商铺配套
		if (StringUtils.isNotBlank(shop.getId())) {
			Mating matingParam = new Mating();
			matingParam.setShopId(shop.getId());
			List<Mating> matingList = matingService.findList(matingParam);
			shop.setMatingList(matingList);
		}

		// 查询商铺热门推荐
		if (StringUtils.isNotBlank(shop.getId())) {
			Hot hotParam = new Hot();
			hotParam.setShopId(shop.getId());
			List<Hot> hotList = hotService.findList(hotParam);
			shop.setHotList(hotList);
		}

		model.addAttribute("shop", shop);
		System.out.println(shop.getSellType());
		model.addAttribute("shoppingMallList", shoppingMallList);

		return "modules/shop/shopForm";
	}

	@RequestMapping(value = "save")
	public String save(Shop shop, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shop)) {
			return form(shop, model);
		}

		ShopUtils.preSave(shop, areaService);

		shopService.saveShop(shop);
		addMessage(redirectAttributes, "保存店铺成功");
		return "redirect:" + Global.getAdminPath() + "/shop/shop/?repage";
	}

	@RequestMapping(value = "sale")
	public String sale(Shop shop, Model model, RedirectAttributes redirectAttributes) {
		shop.setShowFlag(ShopConstants.SHOW_FLAG_1);
		shopService.save(shop);
		addMessage(redirectAttributes, "上架店铺成功");
		return "redirect:" + Global.getAdminPath() + "/shop/shop/?repage";
	}

	@RequestMapping(value = "unSale")
	public String unSale(Shop shop, Model model, RedirectAttributes redirectAttributes) {
		shop.setShowFlag(ShopConstants.SHOW_FLAG_2);
		shopService.save(shop);
		addMessage(redirectAttributes, "下架店铺成功");
		return "redirect:" + Global.getAdminPath() + "/shop/shop/?repage";
	}

	@RequestMapping(value = "auditShop")
	public String auditShop(Shop shop, Model model, RedirectAttributes redirectAttributes, String memberId,
			String auditType) {
		if (!beanValidator(model, shop)) {
			return form(shop, model);
		}

		ShopUtils.preSave(shop, areaService);

		// 通过时间
		shop.setPassDate(new Date());
		// 通过金牌时间
		if(ConstantsUtil.YES_FLAG.equals(shop.getGoldFlag())) {
			shop.setGoldDate(new Date());
		}
		shopService.saveShop(shop);

		// 修改会员信息
		if (StringUtils.isNotBlank(memberId)) {
			Member member = memberService.get(memberId);
			
			// 是自行转铺
			if (!ConstantsUtil.NO_FLAG.equals(member.getSaleFlag())) {
				member.setSaleFlag(ShopConstants.APPLICATION_FLAG_2);
			}
			// 是一键转铺
			if (!ConstantsUtil.NO_FLAG.equals(member.getPublishSaleFlag())) {
				member.setPublishSaleFlag(ShopConstants.APPLICATION_FLAG_2);
			}
		
			memberService.save(member);

			// 添加跟进记录
			Follow follow = new Follow();
			follow.setMemberId(memberId);
			follow.setFollowNote(shop.getFollowNote());
			followService.save(follow);
		}
		
		addMessage(redirectAttributes, "审核完成");
		return "redirect:" + Global.getAdminPath() + "/shop/member/list?sourceType=1";
	}

	@RequestMapping(value = "rejectShop")
	public String rejectShop(Shop shop, Model model, RedirectAttributes redirectAttributes, String memberId,
			String auditType) {
		shopService.saveShop(shop);

		// 修改会员信息
		
		if (StringUtils.isNotBlank(memberId)) {
			Member member =  memberService.get(memberId);
			
			// 是自行转铺
			if (!ConstantsUtil.NO_FLAG.equals(member.getSaleFlag())) {
				member.setSaleFlag(ShopConstants.APPLICATION_FLAG_3);
			}
			// 是一键转铺
			if (!ConstantsUtil.NO_FLAG.equals(member.getPublishSaleFlag())) {
				member.setPublishSaleFlag(ShopConstants.APPLICATION_FLAG_3);
			}
			
			memberService.save(member);

			// 添加跟进记录
			Follow follow = new Follow();
			follow.setMemberId(memberId);
			follow.setFollowNote(shop.getFollowNote());
			followService.save(follow);
		}
		
		addMessage(redirectAttributes, "审核完成");
		return "redirect:" + Global.getAdminPath() + "/shop/member/list?sourceType=1";
	}

	@RequestMapping(value = "delete")
	public String delete(Shop shop, RedirectAttributes redirectAttributes) {
		shopService.delete(shop);
		addMessage(redirectAttributes, "删除店铺成功");
		return "redirect:" + Global.getAdminPath() + "/shop/shop/?repage";
	}

	/**
	 * 导出商铺数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(Shop shop, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "商铺数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<Shop> page = shopService.findPage(new Page<Shop>(request, response), shop);
			new ExportExcel("商铺数据", Shop.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出商铺失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/shop/shop/list?repage";
	}

	/**
	 * 导入商铺数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Shop> list = ei.getDataList(Shop.class);
			for (Shop shop : list) {
				try {
					BeanValidators.validateWithException(validator, shop);

					ShopUtils.preImportSave(shop, areaService);

					shopService.save(shop);

					successNum++;
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>店铺导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>店铺导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条商铺，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条商铺" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入商铺失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/shop/shop/list?repage";
	}

	/**
	 * 下载导入商铺数据模板
	 * 
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "商铺数据导入模板.xlsx";
			List<Shop> list = Lists.newArrayList();
			list.add(new Shop());
			new ExportExcel("商铺数据", Shop.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/shop/shop/list?repage";
	}

	/**
	 * 根据区域查询商场
	 * 
	 * @param area
	 *            区域
	 * @return
	 */
	@RequestMapping(value = "getShoppingMallByArea")
	public @ResponseBody List<ShoppingMall> getShoppingMallByArea(String townId) {
		// 根据区域查询商场
		List<ShoppingMall> shoppingMallList = new ArrayList<ShoppingMall>();

		if (StringUtils.isNoneBlank(townId)) {
			Area town = areaService.get(townId);

			ShoppingMall paramModel = new ShoppingMall();
			Area area = new Area();
			area.setId(town.getParentId());
			paramModel.setArea(area);
			shoppingMallList = shoppingMallService.findList(paramModel);
		}

		return shoppingMallList;
	}

	@RequestMapping(value = "/checkMobile")
	@ResponseBody
	public String checkMobile(Shop shop, Model model) {
		List<Shop> list = shopService.phone(shop.getMobile());
		if (list.isEmpty()) {
			return "success";
		}
		return "error";
	}

}