/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.common.service.ServiceException;
import com.jeesite.common.utils.excel.ExcelImport;
import com.jeesite.common.validator.ValidatorUtils;
import com.jeesite.modules.dao.BusinessTargetDataInfoDao;
import com.jeesite.modules.dto.BusinessTargetDataInfoDto;
import com.jeesite.modules.entity.*;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.sys.entity.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 上报的数据Service
 * @author 上报的数据
 * @version 2019-06-07
 */
@Service
@Transactional(readOnly=true)
public class BusinessTargetDataInfoService extends CrudService<BusinessTargetDataInfoDao, BusinessTargetDataInfo> {

	@Autowired
	private BusinessPlanUserTaskService planUserTaskService;
	@Autowired
	private BusinessCheckPlanUserService checkPlanUserService;
	@Autowired
	private BusinessCheckPlanService businessCheckPlanService;
	@Autowired
	private BusinessTargetTaskMonitorService businessTargetTaskMonitorService;
	@Autowired
	private BusinessPlanUserTaskService businessPlanUserTaskService;
	/**
	 * 获取单条数据
	 * @param businessTargetDataInfo
	 * @return
	 */
	@Override
	public BusinessTargetDataInfo get(BusinessTargetDataInfo businessTargetDataInfo) {
		return super.get(businessTargetDataInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param businessTargetDataInfo 查询条件
	 * @return
	 */
	@Override
	public Page<BusinessTargetDataInfo> findPage(BusinessTargetDataInfo businessTargetDataInfo) {
		return super.findPage(businessTargetDataInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param businessTargetDataInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BusinessTargetDataInfo businessTargetDataInfo) {
		super.save(businessTargetDataInfo);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(businessTargetDataInfo.getId(), "businessTargetDataInfo_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(businessTargetDataInfo.getId(), "businessTargetDataInfo_file");
		//更新个人任务状态

	}
	/**
	 * 保存数据（插入或更新）
	 * @param businessTargetDataInfo
	 */
	@Transactional(readOnly=false)
	public void save(BusinessTargetDataInfo businessTargetDataInfo,String userTaskId) {
		businessTargetDataInfo.setDataStatus("1");//待审
		//更新个人任务状态
		BusinessPlanUserTask businessPlanUserTask =planUserTaskService.get(userTaskId);
		businessPlanUserTask.setTaskStatus(3);
		businessPlanUserTask.setId(userTaskId);
		businessPlanUserTask.setIsNewRecord(false);
		planUserTaskService.save(businessPlanUserTask);
		Office office = businessPlanUserTask.getOffice();
		BusinessCheckPlanUser businessCheckPlanUser = new BusinessCheckPlanUser();
		businessCheckPlanUser.setIsNewRecord(false);
		businessCheckPlanUser.setPlanStatus("2");//设置考核名单中，上报数据后部门的数据上报状态
		businessCheckPlanUser.setOffice(office);
		String businessCheckPlanId = businessPlanUserTask.getBusinessCheckPlanId();
		BusinessCheckPlan businessCheckPlan = new BusinessCheckPlan();
		businessCheckPlan.setId(businessCheckPlanId);
		businessCheckPlanUser.setBusinessCheckPlan(businessCheckPlan);
		businessCheckPlanUser.setId(businessCheckPlanId);
		checkPlanUserService.save(businessCheckPlanUser);//更新部门任务上报状态
		//关联期数
		businessTargetDataInfo.setBusinessStageTarget2(businessPlanUserTask.getBusinessStageTarget2());
		//BusinessTargetTaskMonitor taskMonitor = businessTargetTaskMonitorService.findByIds(businessPlanUserTask.getBusinessTarget().getId(), office.getOfficeCode(), businessCheckPlanId);
		this.save(businessTargetDataInfo);
		BusinessTargetTaskMonitor taskMonitor = businessTargetTaskMonitorService.get(businessPlanUserTask.getMonitorId());
		Integer dataItemCount = taskMonitor.getDataItemCount();
		taskMonitor.addUpItemCount();//+1
		Integer upItemCount = taskMonitor.getUpItemCount();
		if (dataItemCount<= upItemCount) {
			taskMonitor.setStatus("3");//设置考核细则数据填报完整。
			upItemCount = dataItemCount;
		}
		 String status = taskMonitor.getStatus();
		//businessTargetTaskMonitorService.save(taskMonitor);//更新
		//设置数据上报的数量 + 1
		businessTargetTaskMonitorService.updateByIds(
				businessPlanUserTask.getBusinessTarget().getId(),
				office.getOfficeCode(),
				businessCheckPlanId,
				upItemCount,
				status
		);


	}

	/**
	 * 更新状态
	 * @param businessTargetDataInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BusinessTargetDataInfo businessTargetDataInfo) {
		super.updateStatus(businessTargetDataInfo);
	}
	
	/**
	 * 删除数据
	 * @param businessTargetDataInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BusinessTargetDataInfo businessTargetDataInfo) {
		super.delete(businessTargetDataInfo);
	}

	@Transactional(readOnly=false)
	public void saveReport(BusinessTargetDataInfo businessTargetDataInfo) {
		businessTargetDataInfo.setDataStatus("1");//待审核
		businessTargetDataInfo.setIsNewRecord(false);
		String userCode = businessTargetDataInfo.getUser().getUserCode();
		String targetId = businessTargetDataInfo.getBusinessTarget().getId();
		String dataItemId = businessTargetDataInfo.getBusinessTargetDataItem().getId();
		businessPlanUserTaskService.updateStatus(targetId,dataItemId, userCode, "5");//重报
		businessTargetTaskMonitorService.updateBy(userCode, targetId,businessTargetDataInfo.getBusinessStageTarget2().getId(),"5");//将监控状态修改未 已重报
		this.save(businessTargetDataInfo);
	}

	public BusinessTargetDataInfo findByItemName(String dataItemName, String targetId) {
		return 	super.dao.findByItemName(targetId, dataItemName);
	}


	/**
	 * 导入采集数据
	 * @param file 导入的用户数据文件
	 * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
	 */
	@Transactional(readOnly=false)
	public String importData(MultipartFile file, Boolean isUpdateSupport) {
		if (file == null){
			throw new ServiceException("请选择导入的数据文件！");
		}
		int successNum = 0;
		int failureNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		try(ExcelImport ei = new ExcelImport(file, 2, 0)){
			List<BusinessTargetDataInfo> list = ei.getDataList(BusinessTargetDataInfo.class);
			for (BusinessTargetDataInfo businessTargetDataInfo : list) {
				try{
					// 验证数据文件
					ValidatorUtils.validateWithException(businessTargetDataInfo);
					// 验证是否存在这个用户
					BusinessTargetDataInfo u = this.get(businessTargetDataInfo.getId());
					if (u == null){
						this.save(businessTargetDataInfo);
						successNum++;
						successMsg.append("<br/>" + successNum + "、数据 " + businessTargetDataInfo.getId() + " 导入成功");
					} else if (isUpdateSupport){
						businessTargetDataInfo.setId(u.getId());
						this.save(businessTargetDataInfo);
						successNum++;
						successMsg.append("<br/>" + successNum + "、数据 " + businessTargetDataInfo.getId() + " 更新成功");
					} else {
						failureNum++;
						failureMsg.append("<br/>" + failureNum + "、数据 " + businessTargetDataInfo.getId() + " 已存在");
					}
				} catch (Exception e) {
					failureNum++;
					String msg = "<br/>" + failureNum + "、数据 " + businessTargetDataInfo.getId() + " 导入失败：";
					if (e instanceof ConstraintViolationException){
						List<String> messageList = ValidatorUtils.extractPropertyAndMessageAsList((ConstraintViolationException)e, ": ");
						for (String message : messageList) {
							msg += message + "; ";
						}
					}else{
						msg += e.getMessage();
					}
					failureMsg.append(msg);
					logger.error(msg, e);
				}
			}
		} catch (Exception e) {
			failureMsg.append(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		if (failureNum > 0) {
			failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
			throw new ServiceException(failureMsg.toString());
		}else{
			successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
		}
		return successMsg.toString();
	}

	public List<BusinessTargetDataInfoDto> findByUserCode(String userCode) {
		return super.dao.findByUserCode(userCode);
	}
}