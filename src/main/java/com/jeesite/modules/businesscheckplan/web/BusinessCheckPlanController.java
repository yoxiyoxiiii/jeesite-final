/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesscheckplan.web;

import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.mapper.JsonMapper;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.businesscheckplan.entity.BusinessCheckPlan;
import com.jeesite.modules.businesscheckplan.service.BusinessCheckPlanService;
import com.jeesite.modules.businesschecktemplat.entity.BusinessCheckTemplate;
import com.jeesite.modules.businesstarget2.entity.BusinessTarget2;
import com.jeesite.modules.businesstarget2.service.BusinessTarget2Service;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		//只要修改过,就重走流程
		businessCheckPlan.setStatus( BusinessCheckPlan.STATUS_DRAFT);
		businessCheckPlanService.updateStatus(businessCheckPlan);

		businessCheckPlan.setPlanStatus(1);//未启动
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
		businessCheckPlanService.updateStatus(businessCheckPlan);	
		businessCheckPlanService.start(businessCheckPlan);
		return renderResult(Global.TRUE, text("启用考核计划成功"));
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
	public String listSelect(BusinessCheckTemplate businessCheckTemplate, String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (JsonMapper.fromJson(selectDataJson, Map.class) != null) {
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("businessCheckTemplate", businessCheckTemplate);
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
	}


}