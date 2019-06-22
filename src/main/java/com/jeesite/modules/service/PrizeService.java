/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.service;

import java.util.List;
import java.util.Map;

import com.jeesite.common.collect.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.entity.Prize;
import com.jeesite.modules.dao.PrizeDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 奖扣记录Service
 * @author sanye
 * @version 2019-05-03
 */
@Service
@Transactional(readOnly=true)
public class PrizeService extends CrudService<PrizeDao, Prize> {
	
	/**
	 * 获取单条数据
	 * @param prize
	 * @return
	 */
	@Override
	public Prize get(Prize prize) {
		return super.get(prize);
	}
	
	/**
	 * 查询分页数据
	 * @param prize 查询条件
	 * @param prize.page 分页对象
	 * @return
	 */
	@Override
	public Page<Prize> findPage(Prize prize) {
		return super.findPage(prize);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param prize
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Prize prize) {
		super.save(prize);
		// 保存上传附件
		FileUploadUtils.saveFileUpload(prize.getId(), "prize_file");
	}
	
	/**
	 * 更新状态
	 * @param prize
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Prize prize) {
		super.updateStatus(prize);
	}
	
	/**
	 * 删除数据
	 * @param prize
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Prize prize) {
		super.delete(prize);
	}

	@Transactional(readOnly=false)
    public List<Map<String, Object>> prizeReport(String startDate, String endDate, Integer isDepart,String orderBy) {
		Map<String, Object> ps = MapUtils.newHashMap();
		ps.put("startDate", startDate);
		ps.put("endDate", endDate);
		ps.put("isDepart", isDepart);
		ps.put("orderBy", orderBy);
		List<Map<String, Object>> result = dao.prizeReport(ps);
		return result;
	}
}