/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test1.entity.Test1;
import com.jeesite.modules.test1.dao.Test1Dao;
import com.jeesite.modules.test1.entity.Test3;
import com.jeesite.modules.test1.dao.Test3Dao;
import com.jeesite.modules.test1.entity.Test2;
import com.jeesite.modules.test1.dao.Test2Dao;

/**
 * test1Service
 * @author Test1
 * @version 2019-05-03
 */
@Service
@Transactional(readOnly=true)
public class Test1Service extends CrudService<Test1Dao, Test1> {
	
	@Autowired
	private Test3Dao test3Dao;
	
	@Autowired
	private Test2Dao test2Dao;
	
	/**
	 * 获取单条数据
	 * @param test1
	 * @return
	 */
	@Override
	public Test1 get(Test1 test1) {
		Test1 entity = super.get(test1);
		if (entity != null){
			Test3 test3 = new Test3(entity);
			test3.setStatus(Test3.STATUS_NORMAL);
			entity.setTest3List(test3Dao.findList(test3));
			Test2 test2 = new Test2(entity);
			test2.setStatus(Test2.STATUS_NORMAL);
			entity.setTest2List(test2Dao.findList(test2));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param test1 查询条件
	 * @param test1.page 分页对象
	 * @return
	 */
	@Override
	public Page<Test1> findPage(Test1 test1) {
		return super.findPage(test1);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param test1
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Test1 test1) {
		super.save(test1);
		// 保存 Test1子表
		for (Test3 test3 : test1.getTest3List()){
			if (!Test3.STATUS_DELETE.equals(test3.getStatus())){
				test3.setTest1Id(test1);
				if (test3.getIsNewRecord()){
					test3.preInsert();
					test3Dao.insert(test3);
				}else{
					test3.preUpdate();
					test3Dao.update(test3);
				}
			}else{
				test3Dao.delete(test3);
			}
		}
		// 保存 Test1子表
		for (Test2 test2 : test1.getTest2List()){
			if (!Test2.STATUS_DELETE.equals(test2.getStatus())){
				test2.setTest1Id(test1);
				test2.setTest1Id(test1);
				if (test2.getIsNewRecord()){
					test2.preInsert();
					test2Dao.insert(test2);
				}else{
					test2.preUpdate();
					test2Dao.update(test2);
				}
			}else{
				test2Dao.delete(test2);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param test1
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Test1 test1) {
		super.updateStatus(test1);
	}
	
	/**
	 * 删除数据
	 * @param test1
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Test1 test1) {
		super.delete(test1);
		Test3 test3 = new Test3();
		test3.setTest1Id(test1);
		test3Dao.deleteByEntity(test3);
		Test2 test2 = new Test2();
		test2.setTest1Id(test1);
		test2.setTest1Id(test1);
		test2Dao.deleteByEntity(test2);
	}
	
}