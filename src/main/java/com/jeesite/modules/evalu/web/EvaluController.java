/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.evalu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.evalu.entity.EvaluData;
import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.evalu.entity.Evalu;
import com.jeesite.modules.evalu.service.EvaluService;

import java.util.List;
import java.util.Map;

/**
 * 民主测评Controller
 * @author sanye
 * @version 2019-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/evalu/evalu")
public class EvaluController extends BaseController {

	@Autowired
	private EvaluService evaluService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Evalu get(String id, boolean isNewRecord) {
		return evaluService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("evalu:evalu:view")
	@RequestMapping(value = {"list", ""})
	public String list(Evalu evalu, Model model) {
		model.addAttribute("evalu", evalu);
		return "modules/evalu/evaluList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("evalu:evalu:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Evalu> listData(Evalu evalu, HttpServletRequest request, HttpServletResponse response) {
		evalu.setPage(new Page<>(request, response));
		Page<Evalu> page = evaluService.findPage(evalu);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("evalu:evalu:view")
	@RequestMapping(value = "form")
	public String form(Evalu evalu, Model model) {
		model.addAttribute("evalu", evalu);
		return "modules/evalu/evaluForm";
	}

	/**
	 * 保存民主测评
	 */
	@RequiresPermissions("evalu:evalu:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Evalu evalu) {
		evaluService.save(evalu);
		return renderResult(Global.TRUE, text("保存民主测评成功！"));
	}
	
	/**
	 * 停用民主测评
	 */
	@RequiresPermissions("evalu:evalu:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Evalu evalu) {
		evalu.setStatus(Evalu.STATUS_DISABLE);
		evaluService.updateStatus(evalu);
		return renderResult(Global.TRUE, text("停用民主测评成功"));
	}
	
	/**
	 * 启用民主测评
	 */
	@RequiresPermissions("evalu:evalu:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Evalu evalu) {
		evalu.setStatus(Evalu.STATUS_NORMAL);
		evaluService.updateStatus(evalu);
		return renderResult(Global.TRUE, text("启用民主测评成功"));
	}
	
	/**
	 * 删除民主测评
	 */
	@RequiresPermissions("evalu:evalu:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Evalu evalu) {
		evaluService.delete(evalu);
		return renderResult(Global.TRUE, text("删除民主测评成功！"));
	}


	/**
	 * 获取单位列表
	 */
	@RequiresPermissions("evalu:evalu:edit")
	@RequestMapping(value = "offices")
	@ResponseBody
	public List<Office> offices(Office office, String inParam) {
		return evaluService.findOfficeIn(office, inParam);
		//return renderResult(Global.TRUE, text("删除民主测评成功！"));
	}


	/**
	 * 获取单位列表
	 */
	@RequestMapping(value = "evaluData")
	@ResponseBody
	public List<EvaluData> offices(String evaluId, String deptId, String createBy) {
		if(createBy == null || createBy.equals("")  || createBy.equals("0")){
			createBy = UserUtils.getUser().getUserCode();
		}
		return evaluService.findGrid(evaluId, deptId, createBy);
	}

	/**
	 * 获取单位列表
	 */
	@RequestMapping(value = "evaluReport")
	@ResponseBody
	public List<Map<String, Object>> evaluReport(String evaluId, String createBy, String deptId) {
		return evaluService.findReport(evaluId, createBy, deptId);
	}
}