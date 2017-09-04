/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.service.CrudService;
import com.huojuit.hishop.common.utils.StringUtils;
import com.huojuit.hishop.modules.shop.entity.Hot;
import com.huojuit.hishop.modules.shop.entity.Mating;
import com.huojuit.hishop.modules.shop.entity.Shop;
import com.huojuit.hishop.modules.shop.utils.ShopConstants;
import com.huojuit.hishop.modules.shop.dao.HotDao;
import com.huojuit.hishop.modules.shop.dao.MatingDao;
import com.huojuit.hishop.modules.shop.dao.ShopDao;

/**
 * 店铺Service
 * 
 * @author daiyuxiang
 * @version 2017-06-19
 */
@Service
@Transactional(readOnly = true)
public class ShopService extends CrudService<ShopDao, Shop> {
	@Autowired
	private MatingDao matingDao;

	@Autowired
	private HotDao hotDao;

	public Shop get(String id) {
		return super.get(id);
	}

	public List<Shop> findList(Shop shop) {
		return super.findList(shop);
	}
	

	public Page<Shop> findPage(Page<Shop> page, Shop shop) {
		return super.findPage(page, shop);
	}

	@Transactional(readOnly = false)
	public void save(Shop shop) {
		super.save(shop);
	}

	@Transactional(readOnly = false)
	public void delete(Shop shop) {
		super.delete(shop);
	}

	public int countArea1() {
		return dao.countArea1();
	}
	
	public int countSaleSuccess() {
		return dao.countSaleSuccess();
	}
	
	public int countToday() {
		return dao.countToday();
	}

	public int countAll() {
		return dao.countAll();
	}

	public int countArea(String type,String shoppingMall) {
		return dao.countArea(type,shoppingMall);
	}

	
	public int countDynamic(Map paramMap) {
		return dao.countDynamic(paramMap);
	}

	@Transactional(readOnly = false)
	public void saveShop(Shop shop) {
		if (StringUtils.isBlank(shop.getId())) {
			shop.preInsert();
			dao.insert(shop);
		} else {
			shop.preUpdate();
			dao.update(shop);
		}
		if (StringUtils.isNotBlank(shop.getId())) {
			// 更新配套
			dao.deleteShopMating(shop);
			if (shop.getMatingList() != null && shop.getMatingList().size() > 0) {

				for (Mating mating : shop.getMatingList()) {
					mating.preInsert();
					mating.setSourceType(ShopConstants.SOURCE_TYPE_1);
					mating.setShopId(shop.getId());
					matingDao.insert(mating);
				}
			}

			// 更新热门推荐
			dao.deleteShopHot(shop);
			if (shop.getHotList() != null && shop.getHotList().size() > 0) {

				for (Hot hot : shop.getHotList()) {
					hot.preInsert();
					hot.setShopId(shop.getId());
					hotDao.insert(hot);
				}
			}
		}
	}

	public List<Shop> phone(String mobile) {
		return dao.phone(mobile);
	}

}