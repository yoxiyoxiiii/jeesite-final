/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.biz.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.common.mapper.JsonMapper;
import com.jeesite.modules.biz.entity.PrizeType;
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
import com.jeesite.modules.biz.entity.PrizeLib;
import com.jeesite.modules.biz.service.PrizeLibService;

import java.util.Map;

/**
 * 奖扣分类Controller
 * @author sanye
 * @version 2019-05-02
 */
@Controller
@RequestMapping(value = "${adminPath}/biz/prizeLib")
public class PrizeLibController extends BaseController {

	@Autowired
	private PrizeLibService prizeLibService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public PrizeLib get(String id, boolean isNewRecord) {
		return prizeLibService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("biz:prizeLib:view")
	@RequestMapping(value = {"list", ""})
	public String list(PrizeLib prizeLib, Model model) {
		model.addAttribute("prizeLib", prizeLib);
		return "modules/biz/prizeLibList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("biz:prizeLib:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<PrizeLib> listData(PrizeLib prizeLib, HttpServletRequest request, HttpServletResponse response) {
//        prizeLib.setStatus("0");
		prizeLib.setPage(new Page<>(request, response));
		Page<PrizeLib> page = prizeLibService.findPage(prizeLib);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("biz:prizeLib:view")
	@RequestMapping(value = "form")
	public String form(PrizeLib prizeLib, Model model) {
		model.addAttribute("prizeLib", prizeLib);
		return "modules/biz/prizeLibForm";
	}

	/**
	 * 保存奖扣分类
	 */
	@RequiresPermissions("biz:prizeLib:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated PrizeLib prizeLib) {
		prizeLibService.save(prizeLib);
		return renderResult(Global.TRUE, text("保存奖扣分类成功！"));
	}
	
	/**
	 * 删除奖扣分类
	 */
	@RequiresPermissions("biz:prizeLib:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(PrizeLib prizeLib) {
		prizeLibService.delete(prizeLib);
		return renderResult(Global.TRUE, text("删除奖扣分类成功！"));
	}


//
//	//	@RequiresPermissions("biz:prizeLib:edit")
//	@RequestMapping({"listSelect"})
//	public String listSelect(PrizeLib prizeLib, String selectData, Model model) {
//		String selectDataJson = EncodeUtils.decodeUrl(selectData);
//		if (JsonMapper.fromJson(selectDataJson, Map.class) != null) {
//			model.addAttribute("selectData", selectDataJson);
//		}
//		model.addAttribute("prizeLib", prizeLib);
//		return "modules/biz/prizeLiblistSelect";
//	}


//	@RequiresPermissions("biz:prizeType:edit")
	@RequestMapping({"listSelect"})
	public String listSelect(PrizeLib prizeLib, String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (JsonMapper.fromJson(selectDataJson, Map.class) != null) {
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("prizeLib", prizeLib);
		return "modules/biz/prizeLiblistSelect";
	}
}