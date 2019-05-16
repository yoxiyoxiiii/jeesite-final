/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.evalu.web;

import java.util.List;
import java.util.Map;

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
import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.idgen.IdGen;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.evalu.entity.BizEvaluLib;
import com.jeesite.modules.evalu.service.BizEvaluLibService;

/**
 * 民主测评明细树表Controller
 * @author sanye
 * @version 2019-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/evalu/bizEvaluLib")
public class BizEvaluLibController extends BaseController {

	@Autowired
	private BizEvaluLibService bizEvaluLibService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BizEvaluLib get(String treeCode, boolean isNewRecord) {
		return bizEvaluLibService.get(treeCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("evalu:bizEvaluLib:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizEvaluLib bizEvaluLib, Model model) {
		model.addAttribute("bizEvaluLib", bizEvaluLib);
		return "modules/evalu/bizEvaluLibList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("evalu:bizEvaluLib:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<BizEvaluLib> listData(BizEvaluLib bizEvaluLib) {
		if (StringUtils.isBlank(bizEvaluLib.getParentCode())) {
			bizEvaluLib.setParentCode(BizEvaluLib.ROOT_CODE);
		}
		if (StringUtils.isNotBlank(bizEvaluLib.getTreeName())){
			bizEvaluLib.setParentCode(null);
		}
		if (StringUtils.isNotBlank(bizEvaluLib.getEvaluId())){
			bizEvaluLib.setParentCode(null);
		}
		List<BizEvaluLib> list = bizEvaluLibService.findList(bizEvaluLib);
		return list;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("evalu:bizEvaluLib:view")
	@RequestMapping(value = "form")
	public String form(BizEvaluLib bizEvaluLib, Model model) {
		// 创建并初始化下一个节点信息
		bizEvaluLib = createNextNode(bizEvaluLib);
		model.addAttribute("bizEvaluLib", bizEvaluLib);
		return "modules/evalu/bizEvaluLibForm";
	}
	
	/**
	 * 创建并初始化下一个节点信息，如：排序号、默认值
	 */
	@RequiresPermissions("evalu:bizEvaluLib:edit")
	@RequestMapping(value = "createNextNode")
	@ResponseBody
	public BizEvaluLib createNextNode(BizEvaluLib bizEvaluLib) {
		if (StringUtils.isNotBlank(bizEvaluLib.getParentCode())){
			bizEvaluLib.setParent(bizEvaluLibService.get(bizEvaluLib.getParentCode()));
		}
		if (bizEvaluLib.getIsNewRecord()) {
			BizEvaluLib where = new BizEvaluLib();
			where.setParentCode(bizEvaluLib.getParentCode());
			BizEvaluLib last = bizEvaluLibService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null){
				bizEvaluLib.setTreeSort(last.getTreeSort() + 30);
				bizEvaluLib.setTreeCode(IdGen.nextCode(last.getTreeCode()));
			}else if (bizEvaluLib.getParent() != null){
				bizEvaluLib.setTreeCode(bizEvaluLib.getParent().getTreeCode() + "001");
			}
		}
		// 以下设置表单默认数据
		if (bizEvaluLib.getTreeSort() == null){
			bizEvaluLib.setTreeSort(BizEvaluLib.DEFAULT_TREE_SORT);
		}
		return bizEvaluLib;
	}

	/**
	 * 保存民主测评明细树表
	 */
	@RequiresPermissions("evalu:bizEvaluLib:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BizEvaluLib bizEvaluLib) {
		bizEvaluLibService.save(bizEvaluLib);
		return renderResult(Global.TRUE, text("保存民主测评明细树表成功！"));
	}
	
	/**
	 * 删除民主测评明细树表
	 */
	@RequiresPermissions("evalu:bizEvaluLib:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BizEvaluLib bizEvaluLib) {
		bizEvaluLibService.delete(bizEvaluLib);
		return renderResult(Global.TRUE, text("删除民主测评明细树表成功！"));
	}
	
	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("evalu:bizEvaluLib:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<BizEvaluLib> list = bizEvaluLibService.findList(new BizEvaluLib());
		for (int i=0; i<list.size(); i++){
			BizEvaluLib e = list.get(i);
			// 过滤非正常的数据
			if (!BizEvaluLib.STATUS_NORMAL.equals(e.getStatus())){
				continue;
			}
			// 过滤被排除的编码（包括所有子级）
			if (StringUtils.isNotBlank(excludeCode)){
				if (e.getId().equals(excludeCode)){
					continue;
				}
				if (e.getParentCodes().contains("," + excludeCode + ",")){
					continue;
				}
			}
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentCode());
			map.put("name", StringUtils.getTreeNodeName(isShowCode, e.getTreeCode(), e.getTreeName()));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 修复表结构相关数据
	 */
	@RequiresPermissions("evalu:bizEvaluLib:edit")
	@RequestMapping(value = "fixTreeData")
	@ResponseBody
	public String fixTreeData(BizEvaluLib bizEvaluLib){
		if (!UserUtils.getUser().isAdmin()){
			return renderResult(Global.FALSE, "操作失败，只有管理员才能进行修复！");
		}
		bizEvaluLibService.fixTreeData();
		return renderResult(Global.TRUE, "数据修复成功");
	}
	
}