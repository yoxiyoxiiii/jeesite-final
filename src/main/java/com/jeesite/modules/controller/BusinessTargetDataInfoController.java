/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.controller;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.dto.BusinessTargetDataInfoDto;
import com.jeesite.modules.entity.*;
import com.jeesite.modules.service.*;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.service.UserService;
import com.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 上报的数据Controller
 * @author 上报的数据
 * @version 2019-06-07
 */
@Controller
@RequestMapping(value = "${adminPath}/businesstargetdatainfo/businessTargetDataInfo")
public class BusinessTargetDataInfoController extends BaseController {

	@Autowired
	private BusinessTargetDataInfoService businessTargetDataInfoService;

	@Autowired
	private BusinessTarget2Service businessTargetService;

	@Autowired
	private BusinessTargetDataItemService dataItemService;

	@Autowired
	private UserService userService;
	@Autowired
	private BusinessPlanUserTaskService businessPlanUserTaskService;

	@Autowired
	private BusinessTargetTaskMonitorService businessTargetTaskMonitorService;


	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessTargetDataInfo get(String id, boolean isNewRecord) {
		return businessTargetDataInfoService.get(id, isNewRecord);
	}
	
//	/**
//	 * 查询列表
//	 */
//	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:view")
//	@RequestMapping(value = {"list", ""})
//	public String list(BusinessTargetDataInfo businessTargetDataInfo,String userCode,Model model) {
//		model.addAttribute("userCode", userCode);
//		model.addAttribute("businessTargetDataInfo", businessTargetDataInfo);
//		return "modules/businesstargetdatainfo/businessTargetDataInfoList";
//	}
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessPlanUserTask businessPlanUserTask, Model model) {
		model.addAttribute("businessPlanUserTask", businessPlanUserTask);
		return "modules/businesstargetdatainfo/businessPlanUserTaskList";
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BusinessPlanUserTask> listData(BusinessPlanUserTask businessPlanUserTask,HttpServletRequest request, HttpServletResponse response) {

//		businessTargetDataInfo.setPage(new Page<>(request, response));
//		if (StringUtils.isEmpty(userCode)) {
//			Page<BusinessTargetDataInfo> page = businessTargetDataInfoService.findPage(businessTargetDataInfo);
//			return page;
//		}
//		//更具用户过滤
//		User user = new User();
//		user.setUserCode(userCode);
//		businessTargetDataInfo.setUser(user);
//		return businessTargetDataInfoService.findPage(businessTargetDataInfo);
		businessPlanUserTask.setPage(new Page<>(request, response));
		Page<BusinessPlanUserTask> page = businessPlanUserTaskService.findPage(businessPlanUserTask);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:view")
	@RequestMapping(value = "form")
	public String form(BusinessTargetDataInfo businessTargetDataInfo, Model model) {
		model.addAttribute("businessTargetDataInfo", businessTargetDataInfo);
		return "modules/businesstargetdatainfo/businessTargetDataInfoForm";
	}
	/**
	 * 重新填报
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:view")
	@RequestMapping(value = "report")
	public String report(BusinessTargetDataInfo businessTargetDataInfo, Model model) {
		model.addAttribute("businessTargetDataInfo", businessTargetDataInfo);
		return "modules/businesstargetdatainfo/report";
	}

	/**
	 * 重新填报
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:view")
	@RequestMapping(value = "saveReport")
	@ResponseBody
	public String saveReport(BusinessTargetDataInfo businessTargetDataInfo, Model model) {
		businessTargetDataInfo.setDataStatus("1");//待审核
		businessTargetDataInfo.setIsNewRecord(false);
//		businessTargetDataInfoService.saveReport(businessTargetDataInfo);
		model.addAttribute("businessTargetDataInfo", businessTargetDataInfo);
		return renderResult(Global.TRUE, text("保存上报的数据成功！"));
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:view")
	@RequestMapping(value = "viewAndUpdate")
	public String viewAndUpdate(BusinessTargetDataInfo businessTargetDataInfo, Model model) {
		model.addAttribute("businessTargetDataInfo", businessTargetDataInfo);
		return "modules/businesstargetdatainfo/businessTargetDataInfoFormAndUpdate";
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:view")
	@RequestMapping(value = "formNew")
	public String formNew(BusinessTargetDataInfo businessTargetDataInfo,
						  String businessTargetId,
						  String userCode,
						  String userTaskId,
						  String stageId,
						  @RequestParam(required = false) boolean view,
						  @RequestParam(required = false) boolean report,
						  Model model) {

		BusinessTarget2 businessTarget2 = new BusinessTarget2();
		businessTarget2.setId(businessTargetId);
		BusinessTarget2 target2 = businessTargetService.get(businessTarget2);
		businessTargetDataInfo.setBusinessTarget(target2);
		User user = new User();
		user.setUserCode(userCode);
		User userModel = userService.get(user);
		businessTargetDataInfo.setUser(userModel);
		List<BusinessTargetDataItem2> businessTargetDataItem2List = target2.getBusinessTargetDataItem2List();
		List<BusinessTargetDataInfoDto> collect=null;
		if (view) {
			List<BusinessTargetDataInfoDto> dataInfoDtos = businessTargetDataInfoService.findByUserCode(userCode);
			Map<String, String> map = dataInfoDtos.stream().collect(Collectors.toMap(BusinessTargetDataInfoDto::getId, BusinessTargetDataInfoDto::getDataInfo));
			collect = businessTargetDataItem2List.stream().map(item -> BusinessTargetDataInfoDto.builder().id(item.getId()).itemName(item.getItemName()).itemDescription(item.getItemDescription()).dataInfo(map.get(item.getId())).build()).collect(Collectors.toList());
		}else {
			collect = businessTargetDataItem2List.stream().map(item -> BusinessTargetDataInfoDto.builder().id(item.getId()).itemName(item.getItemName()).itemDescription(item.getItemDescription()).build()).collect(Collectors.toList());
		}
		businessTargetDataInfo.setDataInfoDtoList(collect);
		model.addAttribute("businessTargetDataInfo", businessTargetDataInfo);
		model.addAttribute("userTaskId", userTaskId);
		model.addAttribute("userCode", userCode);
		model.addAttribute("businessTargetId", businessTargetId);
		model.addAttribute("stageId", stageId);
		model.addAttribute("view", view);
		model.addAttribute("report", report);
		if (view) {
			return "modules/businesstargetdatainfo/businessTargetDataInfoFormView";
		}
		return "modules/businesstargetdatainfo/businessTargetDataInfoFormNew";
	}


	/**
	 * 保存上报的数据
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(BusinessTargetDataInfo dataInfoDtos, String userTaskId,
					   @RequestParam(required = false) boolean report, String stageId) {
		String dtosId = dataInfoDtos.getId();
		//上报的数据
		String dataInfo = dataInfoDtos.getDataInfo();
		String[] dataList = dataInfo.split(",");
		String[] split = dtosId.split(",");
		if (report) {
			String targetId = dataInfoDtos.getBusinessTarget().getId();
			businessPlanUserTaskService.updateStatusByItems(targetId,stageId,dataInfoDtos.getUser().getUserCode(),"5");//重新填报
		}

		//数据项ID
		List<String> collect = Arrays.asList(split).stream().filter(item ->!StringUtils.isEmpty(item)).collect(Collectors.toList());
		List<String> dataCollect = Arrays.asList(dataList).stream().filter(item ->!StringUtils.isEmpty(item)).collect(Collectors.toList());
        for (int i = 0;i<collect.size(); i++) {
			BusinessTargetDataInfo businessTargetDataInfo = new BusinessTargetDataInfo();
			businessTargetDataInfo.setUser(dataInfoDtos.getUser());
			businessTargetDataInfo.setBusinessTarget(dataInfoDtos.getBusinessTarget());
			BusinessTargetDataItem dataItem = new BusinessTargetDataItem();
			dataItem.setId(collect.get(i));
			businessTargetDataInfo.setBusinessTargetDataItem(dataItem);
			businessTargetDataInfo.setDataInfo(dataCollect.get(i));
			businessTargetDataInfo.setCreteDate(new Date());
			businessTargetDataInfo.setUpdateDate(new Date());
			if (report) {//重新上报
				businessTargetDataInfo.setDataStatus("1");//待审核
				businessTargetDataInfo.setIsNewRecord(false);
				BusinessStageTarget2 businessStageTarget2 = new BusinessStageTarget2();
				businessStageTarget2.setId(stageId);
				businessTargetDataInfo.setBusinessStageTarget2(businessStageTarget2);
				businessTargetDataInfoService.saveReport(businessTargetDataInfo,stageId);
			} else {
				businessTargetDataInfoService.save(businessTargetDataInfo,userTaskId);
			}
		}
		return renderResult(Global.TRUE, text("保存上报的数据成功！"));
	}

	/**
	 * 停用上报的数据
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(BusinessTargetDataInfo businessTargetDataInfo) {
		businessTargetDataInfo.setStatus(BusinessTargetDataInfo.STATUS_DISABLE);
		businessTargetDataInfoService.updateStatus(businessTargetDataInfo);
		return renderResult(Global.TRUE, text("停用上报的数据成功"));
	}
	
	/**
	 * 数据项状态变化
	 * 通过
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(BusinessTargetDataInfo businessTargetDataInfo, String status) {
		String dtosId = businessTargetDataInfo.getId();
		businessTargetDataInfoService.updateStatusBy(UserUtils.getUser().getUserCode(), dtosId,status );
		return renderResult(Global.TRUE, text("操作成功!"));
	}

	/**
	 *
	 * 数据填报驳回
	 * @param
	 * @return
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:edit")
	@RequestMapping(value = "back")
	public String back(String userTaskId,
					   String userCode,
					   String businessTargetId,
					   String stageId,
					   Model model) {
		model.addAttribute("userTaskId",userTaskId);
		model.addAttribute("userCode",userCode);
		model.addAttribute("stageId",stageId);
		model.addAttribute("businessTargetId",businessTargetId);
		return "modules/businesstargetdatainfo/back";
	}


	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:edit")
	@RequestMapping(value = "saveBack")
	public String saveBack(String stageId,
						   String userCode,
						   String businessTargetId,
						   String msg) {

		businessTargetDataInfoService.saveBack(stageId,userCode,businessTargetId, msg);
		//MsgPushUtils.push()
		return "redirect:list";
//		return renderResult(Global.TRUE, text("操作成功!"));
	}

	/**
	 * 删除上报的数据
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessTargetDataInfo businessTargetDataInfo) {
		businessTargetDataInfoService.delete(businessTargetDataInfo);
		return renderResult(Global.TRUE, text("删除上报的数据成功！"));
	}
}