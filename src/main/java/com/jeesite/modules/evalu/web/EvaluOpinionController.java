/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.evalu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jeesite.modules.evalu.entity.EvaluOpinion;
import com.jeesite.modules.evalu.service.EvaluOpinionService;

/**
 * 民主测评意见Controller
 * @author sanye
 * @version 2019-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/evalu/evaluOpinion")
public class EvaluOpinionController extends BaseController {

	@Autowired
	private EvaluOpinionService evaluOpinionService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public EvaluOpinion get(String id, boolean isNewRecord) {
		return evaluOpinionService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(EvaluOpinion evaluOpinion, Model model) {
		model.addAttribute("evaluOpinion", evaluOpinion);
		return "modules/evalu/evaluOpinionList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<EvaluOpinion> listData(EvaluOpinion evaluOpinion, HttpServletRequest request, HttpServletResponse response) {
		evaluOpinion.setPage(new Page<>(request, response));
		if( evaluOpinion.getCreateBy() == null  || evaluOpinion.getCreateBy().equals("")){
			evaluOpinion.setCreateBy(UserUtils.getUser().getUserCode());
		}
		Page<EvaluOpinion> page = evaluOpinionService.findPage(evaluOpinion);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequestMapping(value = "form")
	public String form(EvaluOpinion evaluOpinion, Model model) {
		model.addAttribute("evaluOpinion", evaluOpinion);
		return "modules/evalu/evaluOpinionForm";
	}

	/**
	 * 保存民主测评意见
	 */
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated EvaluOpinion evaluOpinion) {
		evaluOpinionService.save(evaluOpinion);
		return renderResult(Global.TRUE, text("保存民主测评意见成功！"));
	}
	
}