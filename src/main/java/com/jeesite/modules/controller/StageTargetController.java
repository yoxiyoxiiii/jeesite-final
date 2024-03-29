/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.controller;

import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.mapper.JsonMapper;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.entity.BusinessTarget2;
import com.jeesite.modules.service.BusinessTarget2Service;
import com.jeesite.modules.entity.StageTarget;
import com.jeesite.modules.service.StageTargetService;
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
import java.util.List;
import java.util.Map;

/**
 * 阶段目标Controller
 * @author StageTarget
 * @version 2019-04-22
 */
@Controller
@RequestMapping(value = "${adminPath}/stagetarget/stagetarget/stageTarget")
public class StageTargetController extends BaseController {

	@Autowired
	private StageTargetService stageTargetService;

	@Autowired
	private BusinessTarget2Service businessTargetService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public StageTarget get(String id, boolean isNewRecord) {
		return stageTargetService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("stagetarget:stagetarget:stageTarget:view")
	@RequestMapping(value = {"list", ""})
	public String list(StageTarget stageTarget, Model model) {
		model.addAttribute("stageTarget", stageTarget);
		return "modules/stagetarget/stagetarget/stageTargetList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("stagetarget:stagetarget:stageTarget:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<StageTarget> listData(StageTarget stageTarget, HttpServletRequest request, HttpServletResponse response) {
		stageTarget.setPage(new Page<>(request, response));
		Page<StageTarget> page = stageTargetService.findPage(stageTarget);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("stagetarget:stagetarget:stageTarget:view")
	@RequestMapping(value = "form")
	public String form(StageTarget stageTarget, BusinessTarget2 businessTarget, Model model) {


		List<BusinessTarget2> businessTargetList = businessTargetService.findList(businessTarget);
		if (businessTargetList.size() == 0) {
			businessTargetService.findList();
		}

		model.addAttribute("stageTarget", stageTarget);
		model.addAttribute("businessTargetList", businessTargetList);
		return "modules/stagetarget/stagetarget/stageTargetForm";
	}

	/**
	 * 保存阶段目标
	 */
	@RequiresPermissions("stagetarget:stagetarget:stageTarget:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated StageTarget stageTarget) {
		stageTargetService.save(stageTarget);
		return renderResult(Global.TRUE, text("保存阶段目标成功！"));
	}
	
	/**
	 * 删除阶段目标
	 */
	@RequiresPermissions("stagetarget:stagetarget:stageTarget:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(StageTarget stageTarget) {
		stageTargetService.delete(stageTarget);
		return renderResult(Global.TRUE, text("删除阶段目标成功！"));
	}

	@RequestMapping({"listSelects"})
	public String listSelect(StageTarget stageTarget, String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (JsonMapper.fromJson(selectDataJson, Map.class) != null) {
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("stageTarget", stageTarget);
		return "modules/stagetarget/stagetarget/listSelect";
	}
	
}