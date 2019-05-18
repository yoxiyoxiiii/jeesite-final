/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.evalu.web;

import java.util.List;
import java.util.Map;

import com.jeesite.modules.evalu.entity.Evalu;
import com.jeesite.modules.evalu.service.EvaluService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jeesite.common.config.Global;
import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.idgen.IdGen;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.evalu.entity.EvaluLib;
import com.jeesite.modules.evalu.service.EvaluLibService;

/**
 * 民主测评明细树表Controller
 * @author sanye
 * @version 2019-05-17
 */
@Controller
@RequestMapping(value = "${adminPath}/evalu/evaluLib")
public class EvaluLibController extends BaseController {

	@Autowired
	private EvaluLibService evaluLibService;
	@Autowired
	private EvaluService evaluService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public EvaluLib get(String treeCode, boolean isNewRecord) {
		return evaluLibService.get(treeCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("evalu:evaluLib:view")
	@RequestMapping(value = {"list/{evaluId}", ""})
	public String list(@PathVariable String evaluId, EvaluLib evaluLib, Model model) {
		Evalu evalu = evaluService.get(evaluId);
		model.addAttribute("evaluLib", evaluLib);
		model.addAttribute("evalu",evalu);
		return "modules/evalu/evaluLibList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("evalu:evaluLib:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<EvaluLib> listData(EvaluLib evaluLib) {
		if (StringUtils.isBlank(evaluLib.getParentCode())) {
			evaluLib.setParentCode(EvaluLib.ROOT_CODE);
		}
		if (StringUtils.isNotBlank(evaluLib.getTreeName())){
			evaluLib.setParentCode(null);
		}
		if (StringUtils.isNotBlank(evaluLib.getEvaluId())){
			evaluLib.setParentCode(null);
		}
		if (StringUtils.isNotBlank(evaluLib.getRemarks())){
			evaluLib.setParentCode(null);
		}
		List<EvaluLib> list = evaluLibService.findList(evaluLib);
		return list;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("evalu:evaluLib:view")
	@RequestMapping(value = "form")
	public String form(EvaluLib evaluLib, Model model) {
		// 创建并初始化下一个节点信息
		evaluLib = createNextNode(evaluLib);
		model.addAttribute("evaluLib", evaluLib);
		return "modules/evalu/evaluLibForm";
	}
	
	/**
	 * 创建并初始化下一个节点信息，如：排序号、默认值
	 */
	@RequiresPermissions("evalu:evaluLib:edit")
	@RequestMapping(value = "createNextNode")
	@ResponseBody
	public EvaluLib createNextNode(EvaluLib evaluLib) {
		if (StringUtils.isNotBlank(evaluLib.getParentCode())){
			evaluLib.setParent(evaluLibService.get(evaluLib.getParentCode()));
		}
		if (evaluLib.getIsNewRecord()) {
			EvaluLib where = new EvaluLib();
			where.setParentCode(evaluLib.getParentCode());
			EvaluLib last = evaluLibService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null){
				evaluLib.setTreeSort(last.getTreeSort() + 30);
				evaluLib.setTreeCode(IdGen.nextCode(last.getTreeCode()));
			}else if (evaluLib.getParent() != null){
				evaluLib.setTreeCode(evaluLib.getParent().getTreeCode() + "001");
			}
		}
		// 以下设置表单默认数据
		if (evaluLib.getTreeSort() == null){
			evaluLib.setTreeSort(EvaluLib.DEFAULT_TREE_SORT);
		}
		return evaluLib;
	}

	/**
	 * 保存民主测评明细树表
	 */
	@RequiresPermissions("evalu:evaluLib:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated EvaluLib evaluLib) {
		evaluLibService.save(evaluLib);
		return renderResult(Global.TRUE, text("保存民主测评明细树表成功！"));
	}
	
	/**
	 * 删除民主测评明细树表
	 */
	@RequiresPermissions("evalu:evaluLib:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(EvaluLib evaluLib) {
		evaluLibService.delete(evaluLib);
		return renderResult(Global.TRUE, text("删除民主测评明细树表成功！"));
	}
	
	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("evalu:evaluLib:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<EvaluLib> list = evaluLibService.findList(new EvaluLib());
		for (int i=0; i<list.size(); i++){
			EvaluLib e = list.get(i);
			// 过滤非正常的数据
			if (!EvaluLib.STATUS_NORMAL.equals(e.getStatus())){
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
	@RequiresPermissions("evalu:evaluLib:edit")
	@RequestMapping(value = "fixTreeData")
	@ResponseBody
	public String fixTreeData(EvaluLib evaluLib){
		if (!UserUtils.getUser().isAdmin()){
			return renderResult(Global.FALSE, "操作失败，只有管理员才能进行修复！");
		}
		evaluLibService.fixTreeData();
		return renderResult(Global.TRUE, "数据修复成功");
	}
	
}