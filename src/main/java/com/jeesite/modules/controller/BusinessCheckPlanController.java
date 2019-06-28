/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.controller;

import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.mapper.JsonMapper;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.entity.BusinessCheckPlan;
import com.jeesite.modules.service.BusinessCheckPlanService;
import com.jeesite.modules.service.BusinessTarget2Service;
import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.service.OfficeService;
import com.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 考核计划Controller
 * @author BusinessCheckPlan
 * @version 2019-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/businesscheckplan/businessCheckPlan")
public class BusinessCheckPlanController extends BaseController {

	@Autowired
	private BusinessCheckPlanService businessCheckPlanService;

	@Autowired
	private OfficeService officeService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessCheckPlan get(String id, boolean isNewRecord) {
		return businessCheckPlanService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businesscheckplan:businessCheckPlan:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessCheckPlan businessCheckPlan, Model model) {
		model.addAttribute("businessCheckPlan", businessCheckPlan);
		return "modules/businesscheckplan/businessCheckPlanList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businesscheckplan:businessCheckPlan:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BusinessCheckPlan> listData(BusinessCheckPlan businessCheckPlan, HttpServletRequest request, HttpServletResponse response) {
		businessCheckPlan.setPage(new Page<>(request, response));
		Page<BusinessCheckPlan> page = businessCheckPlanService.findPage(businessCheckPlan);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesscheckplan:businessCheckPlan:view")
	@RequestMapping(value = "form")
	public String form(BusinessCheckPlan businessCheckPlan, Model model) {
		model.addAttribute("businessCheckPlan", businessCheckPlan);
		return "modules/businesscheckplan/businessCheckPlanForm";
	}

	/**
	 * 保存考核计划
	 */
	@Autowired
	private BusinessTarget2Service businessTarget2Service;
	@RequiresPermissions("businesscheckplan:businessCheckPlan:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessCheckPlan businessCheckPlan) {
		boolean isNewRecord = businessCheckPlan.getIsNewRecord();
		if (!isNewRecord) {
			//只要修改过,就重走流程
			businessCheckPlan.setStatus( BusinessCheckPlan.STATUS_DRAFT);
			businessCheckPlanService.updateStatus(businessCheckPlan);
		}

		if(businessCheckPlan.getPlanEndTime().getTime()<=businessCheckPlan.getPlanStartTime().getTime()) {return renderResult(Global.FALSE, text("计划时间设置不合理!"));};
		if(businessCheckPlan.getPlanScoringEndTime().getTime()<=businessCheckPlan.getPlanScoringStartTime().getTime()) {return renderResult(Global.FALSE, text("评分时间设置不合理!"));};
//		businessCheckPlan.setPlanStatus(1);//未启动
		businessCheckPlan.setStatus("9");//未启动
		/*  sanye: 必填项应该在实体里面约束
		String targetTypeCode = businessCheckPlan.getBusinessTargetType().getTargetTypeCode();
		List<BusinessTarget2> businessTarget2List = businessTarget2Service.findByTypeCode(targetTypeCode);
		if (StringUtils.isEmpty(businessTarget2List) || businessTarget2List.size() == 0) {return renderResult(Global.FALSE, text("该考核模板下不存在考核细则或模板层级错误!")); }
		if (StringUtils.isEmpty(targetTypeCode)) {return renderResult(Global.FALSE, text("考核模板必填!"));}
		if (StringUtils.isEmpty(businessCheckPlan.getPlanCheckUser().getUserCode())) {return renderResult(Global.FALSE, text("负责人必填!"));}
		if (StringUtils.isEmpty(businessCheckPlan.getPlanDutyUser().getUserCode())) {return renderResult(Global.FALSE, text("责任人必填!"));}
		*/
		businessCheckPlanService.save(businessCheckPlan);
		return renderResult(Global.TRUE, text("保存考核计划成功！"));
	}
	
	/**
	 * 停用考核计划
	 */
	@RequiresPermissions("businesscheckplan:businessCheckPlan:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(BusinessCheckPlan businessCheckPlan) {
		businessCheckPlan.setStatus(BusinessCheckPlan.STATUS_DISABLE);
		businessCheckPlanService.updateStatus(businessCheckPlan);
		businessCheckPlanService.stop(businessCheckPlan);
		return renderResult(Global.TRUE, text("停用考核计划成功"));
	}
	
	/**
	 * 启用考核计划
	 * 生成一个定时任务，并启动
	 */

	@RequiresPermissions("businesscheckplan:businessCheckPlan:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(BusinessCheckPlan businessCheckPlan) throws ClassNotFoundException, SchedulerException {
		businessCheckPlan.setStatus(BusinessCheckPlan.STATUS_NORMAL);
		Map<String, Object> objectMap = businessCheckPlanService.start(businessCheckPlan);
		return renderResult((String) objectMap.get("result"), text((String) objectMap.get("msg")));
	}
	
	/**
	 * 删除考核计划
	 */
	@RequiresPermissions("businesscheckplan:businessCheckPlan:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessCheckPlan businessCheckPlan) {
		businessCheckPlanService.delete(businessCheckPlan);
		return renderResult(Global.TRUE, text("删除考核计划成功！"));
	}

	@RequestMapping({"listSelect"})
	public String listSelect(BusinessCheckPlan businessCheckPlan, String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (JsonMapper.fromJson(selectDataJson, Map.class) != null) {
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("businessCheckPlan", businessCheckPlan);
		return "modules/businesscheckplan/listSelect";
	}

	/**
	 * 审批奖扣类型
	 */
	@RequiresPermissions("businesscheckplan:businessCheckPlan:edit")
	@RequestMapping(value = "audit")
	@ResponseBody
	public String audit(BusinessCheckPlan businessCheckPlan, String status) {
		businessCheckPlan.setStatus(status);
		businessCheckPlanService.updateStatus(businessCheckPlan);
		return renderResult(Global.TRUE, text("考核计划流程操作成功"));
		//todo:流程状态需要内部控制
		//todo: 当前120时,必须先判断是否关联了奖扣和测评
	}



	/**
	 * 独立评价表
	 */
	@RequestMapping(value = {"report/{checkPlanId}/{depatId}", ""})
	public String report(@PathVariable String checkPlanId, @PathVariable String depatId, BusinessCheckPlan businessCheckPlan, Model model) {
		//todo:需要权限判断
		BusinessCheckPlan temp = businessCheckPlanService.get(checkPlanId);
		//参评单位, 前端在指定单位的情况下,关注各项测评指标
		if( depatId != null  && depatId != "0"){
			Office office = officeService.get(depatId);
			model.addAttribute("office", office);
		}

//		List<Map<String, Object>>  users = businessCheckPlanService.findUsers(businessCheckPlan.getPlanDutyUser());
//		model.addAttribute("listEvaluLib", listEvaluLib);
		model.addAttribute("businessCheckPlan",temp);
		model.addAttribute("user", UserUtils.getUser());
//		model.addAttribute("users",users);
		return "modules/businesscheckplan/businessCheckPlanDataReport";
	}

	/**
	 * 获取绩效报告
	 */
	@RequestMapping(value = "evaluReport")
	@ResponseBody
	public List<Map<String, Object>> checkReport(String checkPlanId, String createBy, String deptId) {
		return businessCheckPlanService.findReport(checkPlanId, createBy, deptId);
	}


	/**
	 * 独立评价表
	 */
	@RequestMapping(value = {"reportTable/{checkPlanId}/{depatId}", ""})
	public String reportTable(@PathVariable String checkPlanId, @PathVariable String depatId, BusinessCheckPlan businessCheckPlan, Model model) {
		//todo:需要权限判断
		BusinessCheckPlan temp = businessCheckPlanService.get(checkPlanId);
//		List<EvaluLib> listEvaluLib = this.listData(evaluLib);
//		for(int i=0; i<listEvaluLib.size(); i++){
//			EvaluLib lib = listEvaluLib.get(i);
//			if( listEvaluLib.get(i).getIsTreeLeaf()){
//				String temType[] = lib.getEvalSelectType().split(",");
//				listEvaluLib.get(i).setEvalSelectType(temType[temType.length-1]);
//			}
//		}
		//评测数据
		//测评单位
		Office office = officeService.get(depatId);
//		model.addAttribute("listEvaluLib", listEvaluLib);
		model.addAttribute("businessCheckPlan",temp);
		model.addAttribute("user",UserUtils.getUser());
		model.addAttribute("office", office);
		model.addAttribute("createBy","");
		return "modules/businesscheckplan/businessCheckPlanDataTable";
	}

	/**
	 * 独立考核表,带创建人
	 */
	@RequestMapping(value = {"reportTableView/{checkPlanId}/{depatId}/{createBy}", ""})
	public String reportTableView(@PathVariable String checkPlanId, @PathVariable String depatId, @PathVariable String createBy, BusinessCheckPlan businessCheckPlan, Model model) {
		//todo:需要权限判断
		BusinessCheckPlan temp = businessCheckPlanService.get(checkPlanId);
		//防止createBy字段错误传递给lib
//		evaluLib.setCreateBy(null);
//		List<EvaluLib> listEvaluLib = this.listData(evaluLib);
//		for(int i=0; i<listEvaluLib.size(); i++){
//			EvaluLib lib = listEvaluLib.get(i);
//			if( listEvaluLib.get(i).getIsTreeLeaf()){
//				String temType[] = lib.getEvalSelectType().split(",");
//				listEvaluLib.get(i).setEvalSelectType(temType[temType.length-1]);
//			}
//		}
		//评测数据
		//测评单位
		Office office = officeService.get(depatId);
//		model.addAttribute("listEvaluLib", listEvaluLib);
		model.addAttribute("businessCheckPlan",temp);
		model.addAttribute("user",UserUtils.getUser());
		model.addAttribute("office", office);
		model.addAttribute("createBy",createBy);
		return "modules/businesscheckplan/businessCheckPlanDataTable";
	}


	/**
	 * 考核成绩汇总表
	 */
	@RequestMapping(value = "reportGrid/{checkPlanId}")
	public String reportGrid(@PathVariable String checkPlanId, Model model) {
		BusinessCheckPlan businessCheckPlan = businessCheckPlanService.get(checkPlanId);
//		List<EvaluLib> listEvaluLib = this.listData(evaluLib);
//		for(int i=0; i<listEvaluLib.size(); i++){
//			EvaluLib lib = listEvaluLib.get(i);
//			if( listEvaluLib.get(i).getIsTreeLeaf()){
//				String temType[] = lib.getEvalSelectType().split(",");
//				listEvaluLib.get(i).setEvalSelectType(temType[temType.length-1]);
//			}
//		}

//		List<User> users =
		//参评领导
//		List<Map<String, Object>>  users = evaluService.findUsers(evalu.getExeUser());
		model.addAttribute("businessCheckPlan", businessCheckPlan);
//		model.addAttribute("listEvaluLib",listEvaluLib);
//		model.addAttribute("evalu",evalu);
//		model.addAttribute("users",users);
		return "modules/businesscheckplan/businessCheckPlanDataGrid";
	}


	/**
	 * 业务工作完成数据统计表
	 */
	@RequestMapping(value = "reportTargetData/{checkPlanId}")
	public String reportTarget(@PathVariable String checkPlanId, Model model) {
		BusinessCheckPlan businessCheckPlan = businessCheckPlanService.get(checkPlanId);
//		List<EvaluLib> listEvaluLib = this.listData(businessCheckPlan);
//		for(int i=0; i<listEvaluLib.size(); i++){
//			EvaluLib lib = listEvaluLib.get(i);
//			if( listEvaluLib.get(i).getIsTreeLeaf()){
//				String temType[] = lib.getEvalSelectType().split(",");
//				listEvaluLib.get(i).setEvalSelectType(temType[temType.length-1]);
//			}
//		}

//		List<User> users =
		//参评领导
//		List<Map<String, Object>>  users = evaluService.findUsers(evalu.getExeUser());
		model.addAttribute("businessCheckPlan", businessCheckPlan);
//		model.addAttribute("listEvaluLib",listEvaluLib);
//		model.addAttribute("evalu",evalu);
//		model.addAttribute("users",users);
		return "modules/businesscheckplan/businessCheckPlanDataTarget";
	}

}