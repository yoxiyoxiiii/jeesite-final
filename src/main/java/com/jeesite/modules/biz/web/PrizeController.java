/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.biz.web;

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
import com.jeesite.modules.biz.entity.Prize;
import com.jeesite.modules.biz.service.PrizeService;

/**
 * 奖扣记录Controller
 * @author sanye
 * @version 2019-05-03
 */
@Controller
@RequestMapping(value = "${adminPath}/biz/prize")
public class PrizeController extends BaseController {

	@Autowired
	private PrizeService prizeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Prize get(String id, boolean isNewRecord) {
		return prizeService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("biz:prize:view")
	@RequestMapping(value = {"list", ""})
	public String list(Prize prize, Model model) {
		model.addAttribute("prize", prize);
		return "modules/biz/prizeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("biz:prize:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Prize> listData(Prize prize, HttpServletRequest request, HttpServletResponse response) {
		prize.setPage(new Page<>(request, response));
		Page<Prize> page = prizeService.findPage(prize);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("biz:prize:view")
	@RequestMapping(value = "form")
	public String form(Prize prize, Model model) {
		model.addAttribute("prize", prize);
		return "modules/biz/prizeForm";
	}

	/**
	 * 保存奖扣记录
	 */
	@RequiresPermissions("biz:prize:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Prize prize) {
		prizeService.save(prize);
		return renderResult(Global.TRUE, text("保存奖扣记录成功！"));
	}
	
	/**
	 * 停用奖扣记录
	 */
	@RequiresPermissions("biz:prize:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Prize prize) {
		prize.setStatus(Prize.STATUS_DISABLE);
		prizeService.updateStatus(prize);
		return renderResult(Global.TRUE, text("停用奖扣记录成功"));
	}
	
	/**
	 * 启用奖扣记录
	 */
	@RequiresPermissions("biz:prize:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Prize prize) {
		prize.setStatus(Prize.STATUS_NORMAL);
		prizeService.updateStatus(prize);
		return renderResult(Global.TRUE, text("启用奖扣记录成功"));
	}
	
	/**
	 * 删除奖扣记录
	 */
	@RequiresPermissions("biz:prize:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Prize prize) {
		prizeService.delete(prize);
		return renderResult(Global.TRUE, text("删除奖扣记录成功！"));
	}
	
}