/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.dao;

import java.util.List;

import com.huojuit.hishop.common.persistence.CrudDao;
import com.huojuit.hishop.common.persistence.annotation.MyBatisDao;
import com.huojuit.hishop.modules.shop.entity.Member;

/**
 * 会员DAO接口
 * 
 * @author daiyuxiang
 * @version 2017-06-20
 */
@MyBatisDao
public interface MemberDao extends CrudDao<Member> {

	/**
	 * 查询转铺会员数据
	 * 
	 * @param entity
	 * @return
	 */
	public List<Member> findShopList(Member entity);

	/**
	 * 查询找铺会员数据
	 * 
	 * @param entity
	 * @return
	 */
	public List<Member> findFindList(Member entity);

	/**
	 * 查询游客会员数据
	 * 
	 * @param entity
	 * @return
	 */
	public List<Member> findVisitorList(Member entity);
	
	/**
	 * 查询金牌店铺会员数据
	 * 
	 * @param member
	 * @return
	 */
	public List<Member> findGoldMemberList(Member member);
	
	/**
	 * 根据ID删除数据（物理删除）
	 * @param id
	 * @return
	 */
	public int deleteById(String id);

}