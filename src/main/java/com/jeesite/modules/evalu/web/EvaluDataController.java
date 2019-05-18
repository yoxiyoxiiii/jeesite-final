/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.evalu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jeesite.modules.evalu.entity.EvaluData;
import com.jeesite.modules.evalu.service.EvaluDataService;

/**
 * 民主测评记录Controller
 * @author sanye
 * @version 2019-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/evalu/evaluData")
public class EvaluDataController extends BaseController {

	@Autowired
	private EvaluDataService evaluDataService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public EvaluData get(String id, boolean isNewRecord) {
		return evaluDataService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("evalu:evaluData:view")
	@RequestMapping(value = {"list", ""})
	public String list(EvaluData evaluData, Model model) {
		model.addAttribute("evaluData", evaluData);
		return "modules/evalu/evaluDataList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("evalu:evaluData:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<EvaluData> listData(EvaluData evaluData, HttpServletRequest request, HttpServletResponse response) {
		evaluData.setPage(new Page<>(request, response));
		Page<EvaluData> page = evaluDataService.findPage(evaluData);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("evalu:evaluData:view")
	@RequestMapping(value = "form")
	public String form(EvaluData evaluData, Model model) {
		model.addAttribute("evaluData", evaluData);
		return "modules/evalu/evaluDataForm";
	}

	/**
	 * 保存民主测评记录
	 */
	@RequiresPermissions("evalu:evaluData:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated EvaluData evaluData) {
		evaluDataService.save(evaluData);
		return renderResult(Global.TRUE, text("保存民主测评记录成功！"));
	}



	/**
	 * 评测表单
	 */
	@RequestMapping(value = "reportTable")
	public String reportTable(EvaluData evaluData, Model model) {
		model.addAttribute("evaluData", evaluData);
		return "modules/evalu/evaluDataTable";
	}

	/**
	 * 对比评测表格
	 */
	@RequestMapping(value = "reportGrid")
	public String reportGrid(EvaluData evaluData, Model model) {
		model.addAttribute("evaluData", evaluData);
		return "modules/evalu/evaluDataGrid";
	}
}