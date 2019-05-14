/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstargettypetree.web;

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
import com.jeesite.modules.businesstargettypetree.entity.BusinessTargetTypeTree;
import com.jeesite.modules.businesstargettypetree.service.BusinessTargetTypeTreeService;

/**
 * 目标分类Controller
 * @author BusinessTargetTypeTree
 * @version 2019-05-05
 */
@Controller
@RequestMapping(value = "${adminPath}/businesstargettypetree/businessTargetTypeTree")
public class BusinessTargetTypeTreeController extends BaseController {

	@Autowired
	private BusinessTargetTypeTreeService businessTargetTypeTreeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessTargetTypeTree get(String targetTypeCode, boolean isNewRecord) {
		return businessTargetTypeTreeService.get(targetTypeCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businesstargettypetree:businessTargetTypeTree:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessTargetTypeTree businessTargetTypeTree, Model model) {
		model.addAttribute("businessTargetTypeTree", businessTargetTypeTree);
		return "modules/businesstargettypetree/businessTargetTypeTreeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businesstargettypetree:businessTargetTypeTree:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<BusinessTargetTypeTree> listData(BusinessTargetTypeTree businessTargetTypeTree) {
		if (StringUtils.isBlank(businessTargetTypeTree.getParentCode())) {
			businessTargetTypeTree.setParentCode(BusinessTargetTypeTree.ROOT_CODE);
		}
		if (StringUtils.isNotBlank(businessTargetTypeTree.getTargetTypeName())){
			businessTargetTypeTree.setParentCode(null);
		}
		if (StringUtils.isNotBlank(businessTargetTypeTree.getRemarks())){
			businessTargetTypeTree.setParentCode(null);
		}
		List<BusinessTargetTypeTree> list = businessTargetTypeTreeService.findList(businessTargetTypeTree);
		return list;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesstargettypetree:businessTargetTypeTree:view")
	@RequestMapping(value = "form")
	public String form(BusinessTargetTypeTree businessTargetTypeTree, Model model) {
		// 创建并初始化下一个节点信息
		businessTargetTypeTree = createNextNode(businessTargetTypeTree);
		model.addAttribute("businessTargetTypeTree", businessTargetTypeTree);
		return "modules/businesstargettypetree/businessTargetTypeTreeForm";
	}
	
	/**
	 * 创建并初始化下一个节点信息，如：排序号、默认值
	 */
	@RequiresPermissions("businesstargettypetree:businessTargetTypeTree:edit")
	@RequestMapping(value = "createNextNode")
	@ResponseBody
	public BusinessTargetTypeTree createNextNode(BusinessTargetTypeTree businessTargetTypeTree) {
		if (StringUtils.isNotBlank(businessTargetTypeTree.getParentCode())){
			businessTargetTypeTree.setParent(businessTargetTypeTreeService.get(businessTargetTypeTree.getParentCode()));
		}
		if (businessTargetTypeTree.getIsNewRecord()) {
			BusinessTargetTypeTree where = new BusinessTargetTypeTree();
			where.setParentCode(businessTargetTypeTree.getParentCode());
			BusinessTargetTypeTree last = businessTargetTypeTreeService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null){
				businessTargetTypeTree.setTreeSort(last.getTreeSort() + 30);
				businessTargetTypeTree.setTargetTypeCode(IdGen.nextCode(last.getTargetTypeCode()));
			}else if (businessTargetTypeTree.getParent() != null){
				businessTargetTypeTree.setTargetTypeCode(businessTargetTypeTree.getParent().getTargetTypeCode() + "001");
			}
		}
		// 以下设置表单默认数据
		if (businessTargetTypeTree.getTreeSort() == null){
			businessTargetTypeTree.setTreeSort(BusinessTargetTypeTree.DEFAULT_TREE_SORT);
		}
		return businessTargetTypeTree;
	}

	/**
	 * 保存目标分类
	 */
	@RequiresPermissions("businesstargettypetree:businessTargetTypeTree:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessTargetTypeTree businessTargetTypeTree) {
		businessTargetTypeTreeService.save(businessTargetTypeTree);
		return renderResult(Global.TRUE, text("保存目标分类成功！"));
	}
	
	/**
	 * 删除目标分类
	 */
	@RequiresPermissions("businesstargettypetree:businessTargetTypeTree:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessTargetTypeTree businessTargetTypeTree) {
		businessTargetTypeTreeService.delete(businessTargetTypeTree);
		return renderResult(Global.TRUE, text("删除目标分类成功！"));
	}
	
	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("businesstargettypetree:businessTargetTypeTree:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<BusinessTargetTypeTree> list = businessTargetTypeTreeService.findList(new BusinessTargetTypeTree());
		for (int i=0; i<list.size(); i++){
			BusinessTargetTypeTree e = list.get(i);
			// 过滤非正常的数据
			if (!BusinessTargetTypeTree.STATUS_NORMAL.equals(e.getStatus())){
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
			map.put("name", StringUtils.getTreeNodeName(isShowCode, e.getTargetTypeCode(), e.getTargetTypeName()));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 修复表结构相关数据
	 */
	@RequiresPermissions("businesstargettypetree:businessTargetTypeTree:edit")
	@RequestMapping(value = "fixTreeData")
	@ResponseBody
	public String fixTreeData(BusinessTargetTypeTree businessTargetTypeTree){
		if (!UserUtils.getUser().isAdmin()){
			return renderResult(Global.FALSE, "操作失败，只有管理员才能进行修复！");
		}
		businessTargetTypeTreeService.fixTreeData();
		return renderResult(Global.TRUE, "数据修复成功");
	}
	
}