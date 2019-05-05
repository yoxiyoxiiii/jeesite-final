/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.demo.web;

import java.io.Console;
import java.util.Date;
import java.util.List;

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
import com.jeesite.common.lang.DateUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.demo.entity.DemoCustomer;
import com.jeesite.modules.demo.service.DemoCustomerService;
import com.jeesite.modules.msg.entity.MsgPush;
import com.jeesite.modules.msg.entity.MsgTemplate;
import com.jeesite.modules.msg.entity.content.AppMsgContent;
import com.jeesite.modules.msg.entity.content.EmailMsgContent;
import com.jeesite.modules.msg.entity.content.PcMsgContent;
import com.jeesite.modules.msg.entity.content.SmsMsgContent;
import com.jeesite.modules.msg.service.MsgPushService;
import com.jeesite.modules.msg.service.MsgTemplateService;
import com.jeesite.modules.msg.task.impl.MsgLocalMergePushTask;
import com.jeesite.modules.msg.task.impl.MsgLocalPushTask;
import com.jeesite.modules.msg.utils.MsgPushUtils;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.service.UserService;
import com.jeesite.modules.sys.utils.UserUtils;

import com.jeesite.common.cache.CacheUtils;

/**
 * demo_customerController
 * 
 * @author sanye
 * @version 2019-04-23
 */
@Controller
@RequestMapping(value = "${adminPath}/demo/demoCustomer")
public class DemoCustomerController extends BaseController {

	@Autowired
	private DemoCustomerService demoCustomerService;
	@Autowired
	private MsgPushService msgPushService;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DemoCustomer get(String id, boolean isNewRecord) {
		return demoCustomerService.get(id, isNewRecord);
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("demo:demoCustomer:view")
	@RequestMapping(value = { "list", "" })
	public String list(DemoCustomer demoCustomer, Model model) {
		model.addAttribute("demoCustomer", demoCustomer);
		return "modules/demo/demoCustomerList";
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("demo:demoCustomer:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DemoCustomer> listData(DemoCustomer demoCustomer, HttpServletRequest request,
			HttpServletResponse response) {
		demoCustomer.setPage(new Page<>(request, response));
		Page<DemoCustomer> page = demoCustomerService.findPage(demoCustomer);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("demo:demoCustomer:view")
	@RequestMapping(value = "form")
	public String form(DemoCustomer demoCustomer, Model model) {
		model.addAttribute("demoCustomer", demoCustomer);
		return "modules/demo/demoCustomerForm";
	}

	/**
	 * 保存demo_customer
	 */
	@RequiresPermissions("demo:demoCustomer:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DemoCustomer demoCustomer) {
		demoCustomerService.save(demoCustomer);

		// sanye： 发送消息

		PcMsgContent msgContent = new PcMsgContent();
		msgContent.setTitle("提示信息");
		msgContent.setContent("您有1条新的任务");
		msgContent.addButton("办理", "/a/demo/demoCustomer/form?id=1120518619533426688");
		// 即时推送消息
		MsgPushUtils.push(msgContent, "BizKey", "BizType", "system");
		// 定时推送消息
		MsgPushUtils.push(msgContent, "BizKey", "BizType", "system", DateUtils.parseDate("2019-04-23 10:58"));
		// // 延迟推送消息
		// MsgPushUtils.push(msgContent, "BizKey", "BizType", "system", new Date(),
		// Global.YES);		

		/*
		//sanye: 保存实体
		MsgPush mp = new MsgPush();
		mp.setMsgTitle("Hxxxxxx");
		mp.setMsg
		mp.setBizKey("bizKey");
		mp.setMsgContent(msgContent.toString());
		mp.setSendUserCode("system");
		mp.setReceiveCode("system");
		mp.setId("1121532045941748972");

		// MsgPushService msgPusService;
		msgPushService.save(mp);

		
		String usercode = UserUtils.getUser().getUserCode();
		CacheUtils.put("msgPcPoolCache", usercode, Global.isTestProfileActive());
		*/
		return renderResult(Global.TRUE, text("保存demo_customer成功！"));
	}

	/**
	 * 删除demo_customer
	 */
	@RequiresPermissions("demo:demoCustomer:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DemoCustomer demoCustomer) {
		demoCustomerService.delete(demoCustomer);
		return renderResult(Global.TRUE, text("删除demo_customer成功！"));
	}

}