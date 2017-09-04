/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.service.CrudService;
import com.huojuit.hishop.modules.shop.entity.SliderImg;
import com.huojuit.hishop.modules.shop.dao.SliderImgDao;

/**
 * 轮播图Service
 * @author daiyuxiang
 * @version 2017-06-26
 */
@Service
@Transactional(readOnly = true)
public class SliderImgService extends CrudService<SliderImgDao, SliderImg> {

	public SliderImg get(String id) {
		return super.get(id);
	}
	
	public List<SliderImg> findList(SliderImg sliderImg) {
		return super.findList(sliderImg);
	}
	
	public Page<SliderImg> findPage(Page<SliderImg> page, SliderImg sliderImg) {
		return super.findPage(page, sliderImg);
	}
	
	@Transactional(readOnly = false)
	public void save(SliderImg sliderImg) {
		super.save(sliderImg);
	}
	
	@Transactional(readOnly = false)
	public void delete(SliderImg sliderImg) {
		super.delete(sliderImg);
	}
	
}