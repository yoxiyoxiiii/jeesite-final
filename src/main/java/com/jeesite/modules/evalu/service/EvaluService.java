/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.evalu.service;

import java.util.List;
import java.util.Map;

import com.jeesite.common.collect.MapUtils;
import com.jeesite.modules.evalu.entity.EvaluData;
import com.jeesite.modules.sys.entity.Office;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.evalu.entity.Evalu;
import com.jeesite.modules.evalu.dao.EvaluDao;

/**
 * 民主测评Service
 * @author sanye
 * @version 2019-05-16
 */
@Service
@Transactional(readOnly=true)
public class EvaluService extends CrudService<EvaluDao, Evalu> {

	/**
	 * 获取单条数据
	 *
	 * @param evalu
	 * @return
	 */
	@Override
	public Evalu get(Evalu evalu) {
		return super.get(evalu);
	}

	/**
	 * 查询分页数据
	 *
	 * @param evalu 查询条件
	 * @return
	 */
	@Override
	public Page<Evalu> findPage(Evalu evalu) {
		return super.findPage(evalu);
	}

	/**
	 * 保存数据（插入或更新）
	 *
	 * @param evalu
	 */
	@Override
	@Transactional(readOnly = false)
	public void save(Evalu evalu) {
		super.save(evalu);
	}

	/**
	 * 更新状态
	 *
	 * @param evalu
	 */
	@Override
	@Transactional(readOnly = false)
	public void updateStatus(Evalu evalu) {
		super.updateStatus(evalu);
	}

	/**
	 * 删除数据
	 *
	 * @param evalu
	 */
	@Override
	@Transactional(readOnly = false)
	public void delete(Evalu evalu) {
		super.delete(evalu);
	}


	public List<Office> findOfficeIn(Office office, String params) {
		String[] inStrings = params.split(",");
		Map<String, Object> ps = MapUtils.newHashMap();
		ps.put("officeCode", inStrings);
		List<Office> result = dao.findByIn(ps);
		return result;

		//		// 演示Map参数和返回值，支持分页
		/*Page<Map<String, Object>> pageMap = new Page<>();
		Map<String, Object> ps = MapUtils.newHashMap();
		ps.put("officeCode", inStrings);
		ps.put("page", pageMap);
		pageMap.setList(dao.findListForMap(ps));
		System.out.println(pageMap.getList());
		System.out.println(pageMap.getCount());
		return null;*/
	}

	/**
	 * 获取制定测评表中所有单位各项指标或特定部门各项指标值
	 *
	 * @param evaluId
	 * @return
	 */
	public List<EvaluData> findGrid(String evaluId, String deptId, String createBy) {
		Map<String, Object> ps = MapUtils.newHashMap();
		ps.put("evaluId", evaluId);
		ps.put("deptId", deptId);
		ps.put("createBy",createBy);
		List<EvaluData> result = dao.findGrid(ps);
		return result;
	}

	/**
	 * 测评报告
	 * @param evaluId 测评ID
	 * @param deptId  参评部门ID
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<Map<String, Object>> findReport(String evaluId, String createBy, String deptId) {
		Map<String, Object> ps = MapUtils.newHashMap();
		ps.put("evaluId", evaluId);
		ps.put("createBy", createBy);
		ps.put("deptId", deptId);
		List<Map<String, Object>> result = dao.findReport(ps);
		return result;
	}

	@Transactional(readOnly = false)
	public List<Map<String, Object>> findUsers(String userCodes){
		String[] inStrings = userCodes.split(",");
		Map<String, Object> ps = MapUtils.newHashMap();
		ps.put("userCodes", inStrings);
		List<Map<String, Object>> result = dao.findUsers(ps);
		return result;
	}
}