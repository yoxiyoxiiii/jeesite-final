/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.targets.entity.TargetDepartment;
import com.jeesite.modules.targets.entity.Targets;
import com.jeesite.modules.targets.service.TargetDepartmentService;
import com.jeesite.modules.targets.service.TargetsService;
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
 * 单位/部门目标分解Controller
 * @author 单位/部门目标分解
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/targets/targetDepartment")
public class TargetDepartmentController extends BaseController {

	@Autowired
	private TargetDepartmentService targetDepartmentService;

	//顶级目标
	@Autowired
	private TargetsService targetsService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TargetDepartment get(String id, boolean isNewRecord) {
		return targetDepartmentService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("targets:targetDepartment:view")
	@RequestMapping(value = {"list", ""})
	public String list(TargetDepartment targetDepartment, Model model) {
		model.addAttribute("targetDepartment", targetDepartment);
		return "modules/targets/targetDepartmentList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("targets:targetDepartment:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TargetDepartment> listData(TargetDepartment targetDepartment, HttpServletRequest request, HttpServletResponse response) {
		targetDepartment.setPage(new Page<>(request, response));
		Page<TargetDepartment> page = targetDepartmentService.findPage(targetDepartment);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("targets:targetDepartment:view")
	@RequestMapping(value = "form")
	public String form(TargetDepartment targetDepartment, Model model) {
		model.addAttribute("targetDepartment", targetDepartment);
		return "modules/targets/targetDepartmentForm";
	}
	/**
	 * 部门目标分解
	 */
	@RequiresPermissions("targets:targetDepartment:decompose")
	@RequestMapping(value = "decompose")
	public String decompose(String targetid, Model model) {
		TargetDepartment targetDepartment = new TargetDepartment();
		if (!"".equals(targetid) && targetid !=null) {
			Targets targets = targetsService.get(targetid);
			targetDepartment.setTarget(targets);
		}
		model.addAttribute("targetDepartment", targetDepartment);
		return "modules/targets/targetDepartmentFormDecompose";
	}

	/**
	 * 保存单位/部门目标分解
	 */
	@RequiresPermissions("targets:targetDepartment:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TargetDepartment targetDepartment) {
		targetDepartmentService.save(targetDepartment);
		return renderResult(Global.TRUE, text("保存单位/部门目标分解成功！"));
	}
	
	/**
	 * 删除单位/部门目标分解
	 */
	@RequiresPermissions("targets:targetDepartment:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TargetDepartment targetDepartment) {
		targetDepartmentService.delete(targetDepartment);
		return renderResult(Global.TRUE, text("删除单位/部门目标分解成功！"));
	}
	
}