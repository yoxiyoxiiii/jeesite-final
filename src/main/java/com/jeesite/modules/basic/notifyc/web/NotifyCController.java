/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.notifyc.web;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.jeesite.modules.basic.notifyc.entity.NotifyC;
import com.jeesite.modules.basic.notifyc.service.NotifyCService;

/**
 * 通知管理Controller
 * @author longlou.d@foxmail.com
 * @version 2019-04-17
 */
@Controller
@RequestMapping(value = "${adminPath}/notifyc/notifyC")
public class NotifyCController extends BaseController {

	@Autowired
	private NotifyCService notifyCService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public NotifyC get(String id, boolean isNewRecord) {
		return notifyCService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("notifyc:notifyC:view")
	@RequestMapping(value = {"list", ""})
	public String list(NotifyC notifyC, Model model) {
		model.addAttribute("notifyC", notifyC);
		return "basic/notifyc/notifyCList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("notifyc:notifyC:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<NotifyC> listData(NotifyC notifyC, HttpServletRequest request, HttpServletResponse response) {
		notifyC.setPage(new Page<>(request, response));
		Page<NotifyC> page = notifyCService.findPage(notifyC);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("notifyc:notifyC:view")
	@RequestMapping(value = "form")
	public String form(NotifyC notifyC, Model model) {
		model.addAttribute("notifyC", notifyC);
		return "basic/notifyc/notifyCForm";
	}

	/**
	 * 保存通知
	 */
	@RequiresPermissions("notifyc:notifyC:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated NotifyC notifyC) {
		notifyC.setNotifyDate(new Date());
		notifyCService.save(notifyC);
		return renderResult(Global.TRUE, text("保存通知成功！"));
	}
	
	/**
	 * 停用通知
	 */
	@RequiresPermissions("notifyc:notifyC:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(NotifyC notifyC) {
		notifyC.setStatus(NotifyC.STATUS_DISABLE);
		notifyCService.updateStatus(notifyC);
		return renderResult(Global.TRUE, text("停用通知成功"));
	}
	
	/**
	 * 启用通知
	 */
	@RequiresPermissions("notifyc:notifyC:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(NotifyC notifyC) {
		notifyC.setStatus(NotifyC.STATUS_NORMAL);
		notifyCService.updateStatus(notifyC);
		return renderResult(Global.TRUE, text("启用通知成功"));
	}
	
	/**
	 * 删除通知
	 */
	@RequiresPermissions("notifyc:notifyC:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(NotifyC notifyC) {
		notifyCService.delete(notifyC);
		return renderResult(Global.TRUE, text("删除通知成功！"));
	}
	
}