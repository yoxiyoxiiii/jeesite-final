/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.check.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.check.entity.CheckList;
import com.jeesite.modules.check.service.CheckListService;
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
 * 考核名单Controller
 * @author 考核名单
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/check/checkList")
public class CheckListController extends BaseController {

	@Autowired
	private CheckListService checkListService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public CheckList get(String id, boolean isNewRecord) {
		return checkListService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("check:checkList:view")
	@RequestMapping(value = {"list", ""})
	public String list(CheckList checkList, Model model) {
		model.addAttribute("checkList", checkList);
		return "modules/check/checkListList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("check:checkList:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<CheckList> listData(CheckList checkList, HttpServletRequest request, HttpServletResponse response) {
		checkList.setPage(new Page<>(request, response));
		Page<CheckList> page = checkListService.findPage(checkList);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("check:checkList:view")
	@RequestMapping(value = "form")
	public String form(CheckList checkList, Model model) {
		model.addAttribute("checkList", checkList);
		return "modules/check/checkListForm";
	}

	/**
	 * 保存考核名单
	 */
	@RequiresPermissions("check:checkList:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated CheckList checkList) {
		checkListService.save(checkList);
		return renderResult(Global.TRUE, text("保存考核名单成功！"));
	}
	
	/**
	 * 删除考核名单
	 */
	@RequiresPermissions("check:checkList:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(CheckList checkList) {
		checkListService.delete(checkList);
		return renderResult(Global.TRUE, text("删除考核名单成功！"));
	}
	
}