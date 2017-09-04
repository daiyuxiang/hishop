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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.huojuit.hishop.common.beanvalidator.BeanValidators;
import com.huojuit.hishop.common.config.Global;
import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.utils.DateUtils;
import com.huojuit.hishop.common.utils.StringUtils;
import com.huojuit.hishop.common.utils.excel.ExportExcel;
import com.huojuit.hishop.common.utils.excel.ImportExcel;
import com.huojuit.hishop.common.web.BaseController;
import com.huojuit.hishop.modules.shop.entity.FindShop;
import com.huojuit.hishop.modules.shop.entity.Follow;
import com.huojuit.hishop.modules.shop.entity.Hobby;
import com.huojuit.hishop.modules.shop.entity.Hot;
import com.huojuit.hishop.modules.shop.entity.Mating;
import com.huojuit.hishop.modules.shop.entity.Member;
import com.huojuit.hishop.modules.shop.entity.Shop;
import com.huojuit.hishop.modules.shop.entity.ShoppingMall;
import com.huojuit.hishop.modules.shop.service.FindShopService;
import com.huojuit.hishop.modules.shop.service.FollowService;
import com.huojuit.hishop.modules.shop.service.HobbyService;
import com.huojuit.hishop.modules.shop.service.HotService;
import com.huojuit.hishop.modules.shop.service.MatingService;
import com.huojuit.hishop.modules.shop.service.MemberService;
import com.huojuit.hishop.modules.shop.service.ShopService;
import com.huojuit.hishop.modules.shop.service.ShoppingMallService;
import com.huojuit.hishop.modules.shop.utils.ShopConstants;

/**
 * 会员Controller
 * @author daiyuxiang
 * @version 2017-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/shop/member")
public class MemberController extends BaseController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private FindShopService findShopService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShoppingMallService shoppingMallService;
	@Autowired
	private MatingService matingService;
	@Autowired
	private HotService hotService;
	@Autowired
	private HobbyService hobbyService;
	@Autowired
	private FollowService followService;
	
	@ModelAttribute
	public Member get(@RequestParam(required=false) String id) {
		Member entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memberService.get(id);
		}
		if (entity == null){
			entity = new Member();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Member member, HttpServletRequest request, HttpServletResponse response, Model model,String sourceType) {
		String result = "";
		Page<Member> page = new Page<Member>();
		
		// 查看转铺会员信息
		if(ShopConstants.SOURCE_TYPE_1.equals(sourceType)) {			
			page = memberService.findShopPage(new Page<Member>(request, response), member); 
			
			result =  "modules/shop/memberList1";
		// 查看找铺会员信息
		} else if(ShopConstants.SOURCE_TYPE_2.equals(sourceType)) {
			page = memberService.findFindPage(new Page<Member>(request, response), member); 

			result =  "modules/shop/memberList2";
		// 查看游客信息
		} else if(ShopConstants.SOURCE_TYPE_3.equals(sourceType)) {
			page = memberService.findVisitorPage(new Page<Member>(request, response), member);
		
			result =  "modules/shop/memberList3";
		// 查看金牌店铺会员信息
		} else if(ShopConstants.SOURCE_TYPE_4.equals(sourceType)) {
			page = memberService.findGoldMemberPage(new Page<Member>(request, response), member); 

			result =  "modules/shop/memberList4";
		}
		
			
		model.addAttribute("page", page);
		return result;
	}

	@RequestMapping(value = "form")
	public String form(Member member, Model model) {
		// 查询找铺信息
		if(StringUtils.isNotBlank(member.getFindId())) {
			FindShop findShop = findShopService.get(member.getFindId());
			List<FindShop> findShopList = new ArrayList<FindShop>();
			findShopList.add(findShop);
			model.addAttribute("findShopList", findShopList);
		}		
		
		// 查询转铺信息
		if(StringUtils.isNotBlank(member.getShopId())) {
			Shop shop = shopService.get(member.getShopId());
			List<Shop> shopList = new ArrayList<Shop>();
			shopList.add(shop);
			model.addAttribute("shopList", shopList);
		}
		
		// 查询收藏信息
		Hobby hobbyParam = new Hobby();
		hobbyParam.setOpenId(member.getOpenId());
		List<Shop> hobbyList = hobbyService.findShopList(hobbyParam);
		model.addAttribute("hobbyList", hobbyList);
		
		// 查询跟进信息
		Follow followParam = new Follow();
		followParam.setMemberId(member.getId());
		List<Follow> followList = followService.findList(followParam);
		model.addAttribute("followList", followList);
		
		model.addAttribute("member", member);
		return "modules/shop/memberForm";
	}
	
	@RequestMapping(value = "auditFindForm")
	public String auditFindForm(Member member, Model model) {
		// 查询找铺信息
		FindShop findShop = findShopService.get(member.getFindId());
		List<FindShop> findShopList = new ArrayList<FindShop>();
		findShopList.add(findShop);
		
		model.addAttribute("findShopList", findShopList);
		model.addAttribute("member", member);
		return "modules/shop/auditFindForm";
	}
	
	@RequestMapping(value = "auditSaleForm")
	public String auditSaleForm(Member member, Model model) {
		// 根据区域查询商场
		List<ShoppingMall> shoppingMallList = new ArrayList<ShoppingMall>();
		
		// 查找店铺数据
		Shop shop = shopService.get(member.getShopId());
		
		if (shop.getArea() != null) {
			ShoppingMall paramModel = new ShoppingMall();
			paramModel.setArea(shop.getArea());
			shoppingMallList = shoppingMallService.findList(paramModel);
			
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
		}

		model.addAttribute("shop", shop);
		model.addAttribute("shoppingMallList", shoppingMallList);
		model.addAttribute("memberId", member.getId());
		
		return "modules/shop/auditSaleForm";
	}
	
	
	@RequestMapping(value = "updateSaleForm")
	public String updateSaleForm(Shop shop, Model model) {
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

		return "modules/shop/updateSaleForm";
	}
	

	@RequestMapping(value = "save")
	public String save(Member member, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, member)){
			return form(member, model);
		}
		memberService.save(member);
		addMessage(redirectAttributes, "保存会员成功");
		return "redirect:"+Global.getAdminPath()+"/shop/member/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(Member member, RedirectAttributes redirectAttributes) {
		memberService.delete(member);
		addMessage(redirectAttributes, "删除会员成功");
		return "redirect:"+Global.getAdminPath()+"/shop/member/?repage";
	}

	/**
	 * 导出会员数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(Member member, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "会员数据" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			Page<Member> page = memberService.findPage(new Page<Member>(request,
					response), member);
			new ExportExcel("会员数据", Member.class).setDataList(page.getList())
					.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出会员失败！失败信息：" + e.getMessage());
		}
		
		return "redirect:" + adminPath + "/shop/member/list?repage";
	}

	/**
	 * 导入会员数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file,
			RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Member> list = ei.getDataList(Member.class);
			for (Member member : list) {
				try {
					if ("true".equals(checkOpenId(member.getOpenId()))) {
						BeanValidators.validateWithException(validator, member);
						memberService.save(member);
						successNum++;
					} else {
						failureMsg.append("<br/>会员"+member.getOpenId()+" 已存在; ");
						failureNum++;
					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("导入失败：");
					List<String> messageList = BeanValidators
							.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>会员"+member.getOpenId()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条会员，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条会员"
					+ failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入会员失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/shop/member/list?repage";
	}
	
	/**
	 * 下载导入会员数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "会员数据导入模板.xlsx";
    		List<Member> list = Lists.newArrayList(); 
			list.add(new Member());
    		new ExportExcel("会员数据", Member.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/shop/member/list?repage";
    }
    
	public String checkOpenId(String openId) {
		Member memberParam = new Member();
		memberParam.setOpenId(openId);
		List<Member> memberList = memberService.findList(memberParam);
		
		if (StringUtils.isNotBlank(openId) && memberList.size()==0) {
			return "true";
		}
		return "false";
	}

}