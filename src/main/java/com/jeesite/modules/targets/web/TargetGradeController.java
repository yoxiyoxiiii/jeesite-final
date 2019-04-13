/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.targets.entity.TargetGrade;
import com.jeesite.modules.targets.service.TargetGradeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 目标分档Controller
 * @author 目标分档
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/targets/targetGrade")
public class TargetGradeController extends BaseController {

	@Autowired
	private TargetGradeService targetGradeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TargetGrade get(String id, boolean isNewRecord) {
		return targetGradeService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("targets:targetGrade:view")
	@RequestMapping(value = {"list", ""})
	public String list(TargetGrade targetGrade, Model model) {
		model.addAttribute("targetGrade", targetGrade);
		return "modules/targets/targetGradeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("targets:targetGrade:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TargetGrade> listData(TargetGrade targetGrade, HttpServletRequest request, HttpServletResponse response) {
		targetGrade.setPage(new Page<>(request, response));
		Page<TargetGrade> page = targetGradeService.findPage(targetGrade);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("targets:targetGrade:view")
	@RequestMapping(value = "form")
	public String form(TargetGrade targetGrade, Model model) {
		model.addAttribute("targetGrade", targetGrade);
		return "modules/targets/targetGradeForm";
	}

	/**
	 * 保存目标分档
	 */
	@RequiresPermissions("targets:targetGrade:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TargetGrade targetGrade) {
		targetGradeService.save(targetGrade);
		return renderResult(Global.TRUE, text("保存目标分档成功！"));
	}
	
	/**
	 * 删除目标分档
	 */
	@RequiresPermissions("targets:targetGrade:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TargetGrade targetGrade) {
		targetGradeService.delete(targetGrade);
		return renderResult(Global.TRUE, text("删除目标分档成功！"));
	}
	
}