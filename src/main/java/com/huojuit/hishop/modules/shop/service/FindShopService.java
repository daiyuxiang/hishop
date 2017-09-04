/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.service.CrudService;
import com.huojuit.hishop.common.utils.StringUtils;
import com.huojuit.hishop.modules.shop.entity.FindShop;
import com.huojuit.hishop.modules.shop.entity.Hot;
import com.huojuit.hishop.modules.shop.entity.Mating;
import com.huojuit.hishop.modules.shop.entity.Shop;
import com.huojuit.hishop.modules.shop.utils.ShopConstants;
import com.huojuit.hishop.modules.shop.dao.FindShopDao;
import com.huojuit.hishop.modules.shop.dao.MatingDao;

/**
 * 找铺Service
 * @author daiyuxiang
 * @version 2017-06-24
 */
@Service
@Transactional(readOnly = true)
public class FindShopService extends CrudService<FindShopDao, FindShop> {
	@Autowired
	private MatingDao matingDao;

	public FindShop get(String id) {
		return super.get(id);
	}
	
	public List<FindShop> findList(FindShop findShop) {
		return super.findList(findShop);
	}
	
	public Page<FindShop> findPage(Page<FindShop> page, FindShop findShop) {
		return super.findPage(page, findShop);
	}
	
	@Transactional(readOnly = false)
	public void save(FindShop findShop) {
		super.save(findShop);
	}
	
	@Transactional(readOnly = false)
	public void delete(FindShop findShop) {
		super.delete(findShop);
	}
	
	public int countFind() {
		return dao.countFind();
	}

	
	@Transactional(readOnly = false)
	public void saveFindShop(FindShop findShop) {
		if (StringUtils.isBlank(findShop.getId())) {
			findShop.preInsert();
			dao.insert(findShop);
		} else {
			findShop.preUpdate();
			dao.update(findShop);
		}
		if (StringUtils.isNotBlank(findShop.getId())) {
			// 更新配套
			dao.deleteShopMating(findShop);
			if (findShop.getMatingList() != null && findShop.getMatingList().size() > 0) {

				for (Mating mating : findShop.getMatingList()) {
					mating.preInsert();
					mating.setSourceType(ShopConstants.SOURCE_TYPE_2);
					mating.setShopId(findShop.getId());
					matingDao.insert(mating);
				}
			}
		}
	}
}