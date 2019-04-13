/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.check.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.check.entity.CheckScore;
import com.jeesite.modules.check.service.CheckScoreService;
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
 * 考核评分名单Controller
 * @author 考核评分名单
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/check/checkScore")
public class CheckScoreController extends BaseController {

	@Autowired
	private CheckScoreService checkScoreService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public CheckScore get(String id, boolean isNewRecord) {
		return checkScoreService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("check:checkScore:view")
	@RequestMapping(value = {"list", ""})
	public String list(CheckScore checkScore, Model model) {
		model.addAttribute("checkScore", checkScore);
		return "modules/check/checkScoreList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("check:checkScore:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<CheckScore> listData(CheckScore checkScore, HttpServletRequest request, HttpServletResponse response) {
		checkScore.setPage(new Page<>(request, response));
		Page<CheckScore> page = checkScoreService.findPage(checkScore);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("check:checkScore:view")
	@RequestMapping(value = "form")
	public String form(CheckScore checkScore, Model model) {
		model.addAttribute("checkScore", checkScore);
		return "modules/check/checkScoreForm";
	}

	/**
	 * 保存考核评分名单
	 */
	@RequiresPermissions("check:checkScore:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated CheckScore checkScore) {
		checkScoreService.save(checkScore);
		return renderResult(Global.TRUE, text("保存考核评分名单成功！"));
	}
	
	/**
	 * 删除考核评分名单
	 */
	@RequiresPermissions("check:checkScore:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(CheckScore checkScore) {
		checkScoreService.delete(checkScore);
		return renderResult(Global.TRUE, text("删除考核评分名单成功！"));
	}
	
}