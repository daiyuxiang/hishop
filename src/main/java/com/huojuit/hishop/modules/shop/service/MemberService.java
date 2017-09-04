/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huojuit.hishop.common.persistence.Page;
import com.huojuit.hishop.common.service.CrudService;
import com.huojuit.hishop.modules.shop.entity.Member;
import com.huojuit.hishop.modules.shop.dao.MemberDao;

/**
 * 会员Service
 * @author daiyuxiang
 * @version 2017-06-20
 */
@Service
@Transactional(readOnly = true)
public class MemberService extends CrudService<MemberDao, Member> {

	public Member get(String id) {
		return super.get(id);
	}
	
	public List<Member> findList(Member member) {
		return super.findList(member);
	}
	
	public Page<Member> findPage(Page<Member> page, Member member) {
		return super.findPage(page, member);
	}
	
	public Page<Member> findGoldMemberPage(Page<Member> page, Member member) {
		member.setPage(page);
		page.setList(dao.findGoldMemberList(member));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(Member member) {
		super.save(member);
	}
	
	@Transactional(readOnly = false)
	public void delete(Member member) {
		super.delete(member);
	}
	
	public Page<Member> findShopPage(Page<Member> page, Member member) {
		member.setPage(page);
		page.setList(dao.findShopList(member));
		return page;
	}
	
	public Page<Member> findFindPage(Page<Member> page, Member member) {
		member.setPage(page);
		page.setList(dao.findFindList(member));
		return page;
	}
	

	public Page<Member> findVisitorPage(Page<Member> page, Member member) {
		member.setPage(page);
		page.setList(dao.findVisitorList(member));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void deleteVistor(String openId) {
		Member memberParam = new Member();
		memberParam.setOpenId(openId);
		List<Member> vistorList = dao.findVisitorList(memberParam);
		if(vistorList!=null&&vistorList.size()>0) {
			Member m  = vistorList.get(0);
			dao.deleteById(m.getId());
		}
	}
	
}