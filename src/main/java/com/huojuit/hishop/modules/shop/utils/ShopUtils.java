package com.huojuit.hishop.modules.shop.utils;

import org.apache.commons.lang3.StringUtils;

import com.huojuit.hishop.common.utils.ConstantsUtil;
import com.huojuit.hishop.common.utils.DateUtils;
import com.huojuit.hishop.modules.shop.entity.Shop;
import com.huojuit.hishop.modules.sys.entity.Area;
import com.huojuit.hishop.modules.sys.service.AreaService;
import com.huojuit.hishop.modules.sys.utils.DictUtils;

public class ShopUtils {
	public static String createShopNo(String provinceCode, String cityCode,
			String areaCode) {
		// 编号生成规则 省+市+区+年+月+日+时+秒

		StringBuffer shopNo = new StringBuffer();
		String dateStr = DateUtils.getDate("yyyyMMddHHmmss");
		shopNo.append(provinceCode).append(cityCode).append(areaCode)
				.append(dateStr);

		return shopNo.toString();
	}
	
	
	public static void preImportSave(Shop shop, AreaService areaService) {
		// 设置省份和城市
		Area area = areaService.get(shop.getArea());
		String parentIds = area.getParentIds();

		String[] idArray = parentIds.split(",");
		shop.setProvince(idArray[2]);
		shop.setCity(idArray[3]);

		// 生成店铺编号
		if (StringUtils.isBlank(shop.getShopNo())) {
			Area province = areaService.get(shop.getProvince());
			String provinceCode = province.getCode();
			Area city = areaService.get(shop.getCity());
			String cityCode = city.getCode();
			String areaCode = area.getCode();

			String shopNo = ShopUtils.createShopNo(provinceCode, cityCode,
					areaCode);
			shop.setShopNo(shopNo);
		}

		// 设置店铺名称
		// 区域+面积+商铺类型+当前经营+转让/出租
		if (StringUtils.isBlank(shop.getShopName())) {
			StringBuffer nameBuffer = new StringBuffer();
			String shopTypeName = DictUtils.getDictLabel(shop.getShopType(),
					ConstantsUtil.SHOP_SHOP_TYPE, "");
			String sellTypeName = DictUtils.getDictLabel(shop.getSellType(),
					ConstantsUtil.SHOP_SELL_TYPE, "");
			
			nameBuffer.append(area.getName()).append(shop.getUseArea())
					.append("㎡").append(shopTypeName)
					.append(shop.getCurrentManagement()).append(sellTypeName);
			shop.setShopName(nameBuffer.toString());
		}

		// 默认设置上架
		if (StringUtils.isBlank(shop.getShowFlag())) {
			shop.setShowFlag(ShopConstants.SHOW_FLAG_1);
		}

		// 默认设置金牌店铺标志为否
		if (StringUtils.isBlank(shop.getGoldFlag())) {
			shop.setGoldFlag(ConstantsUtil.NO_FLAG);
		}

		// 默认是委托发布
		if (StringUtils.isBlank(shop.getPublishType())) {
			shop.setPublishType(ShopConstants.PUBLISH_TYPE_1);
		}
	}
	

	public static void preSave(Shop shop, AreaService areaService) {
		// 设置省份和城市和区域
		Area town = areaService.get(shop.getTown());
		String parentIds = town.getParentIds();
		Area area = areaService.get(town.getParentId());
		
		String[] idArray = parentIds.split(",");
		shop.setProvince(idArray[2]);
		shop.setCity(idArray[3]);
		shop.setArea(area);

		// 生成店铺编号
		if (StringUtils.isBlank(shop.getShopNo())) {
			Area province = areaService.get(shop.getProvince());
			String provinceCode = province.getCode();
			Area city = areaService.get(shop.getCity());
			String cityCode = city.getCode();
			String areaCode = area.getCode();

			String shopNo = ShopUtils.createShopNo(provinceCode, cityCode,
					areaCode);
			shop.setShopNo(shopNo);
		}

		// 设置店铺名称
		// 区域+面积+商铺类型+当前经营+转让/出租
		if (StringUtils.isBlank(shop.getShopName())) {
			StringBuffer nameBuffer = new StringBuffer();
			String shopTypeName = DictUtils.getDictLabel(shop.getShopType(),
					ConstantsUtil.SHOP_SHOP_TYPE, "");
			String sellTypeName = DictUtils.getDictLabel(shop.getSellType(),
					ConstantsUtil.SHOP_SELL_TYPE, "");
			
			nameBuffer.append(area.getName()).append(shop.getUseArea())
					.append("㎡").append(shopTypeName)
					.append(shop.getCurrentManagement()).append(sellTypeName);
			shop.setShopName(nameBuffer.toString());
		}

		// 默认设置上架
		if (StringUtils.isBlank(shop.getShowFlag())) {
			shop.setShowFlag(ShopConstants.SHOW_FLAG_1);
		}

		// 默认设置金牌店铺标志为否
		if (StringUtils.isBlank(shop.getGoldFlag())) {
			shop.setGoldFlag(ConstantsUtil.NO_FLAG);
		}

		// 默认是委托发布
		if (StringUtils.isBlank(shop.getPublishType())) {
			shop.setPublishType(ShopConstants.PUBLISH_TYPE_1);
		}
	}

	public static void preQuery(Shop shop) {
		// 设置月租金查询条件
		if (StringUtils.isNotBlank(shop.getFeeString())) {
			String[] array = shop.getFeeString().split("-");
			if (array.length == 2) {
				shop.setFeeBegin(array[0]);
				shop.setFeeEnd(array[1]);
			} else {
				shop.setFeeBegin(array[0]);
			}
		}

		// 设置转让费查询条件
		if (StringUtils.isNotBlank(shop.getTransferFeeString())) {
			String[] array = shop.getTransferFeeString().split("-");
			if (array.length == 2) {
				shop.setTransferFeeBegin(array[0]);
				shop.setTransferFeeEnd(array[1]);
			} else {
				shop.setTransferFeeBegin(array[0]);
			}
		}

		// 设置使用面积查询条件
		if (StringUtils.isNotBlank(shop.getUseAreaString())) {
			String[] array = shop.getUseAreaString().split("-");
			if (array.length == 2) {
				shop.setUseAreaBegin(array[0]);
				shop.setUseAreaEnd(array[1]);
			} else {
				shop.setUseAreaBegin(array[0]);
			}
		}

		// 设置多选商铺类型查询
		if (StringUtils.isNotBlank(shop.getShopTypeString())) {
			String[] array = shop.getShopTypeString().split(",");
			shop.setShopTypeArray(array);
		}

		// 设置多选推荐查询
		if (StringUtils.isNotBlank(shop.getHotString())) {
			String[] array = shop.getHotString().split(",");
			shop.setHotArray(array);
		}

	}
}
