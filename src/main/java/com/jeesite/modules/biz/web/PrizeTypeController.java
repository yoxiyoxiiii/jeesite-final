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
import com.jeesite.modules.biz.entity.PrizeType;
import com.jeesite.modules.biz.service.PrizeTypeService;

import java.util.Map;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;

import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.common.mapper.JsonMapper;

/**
 * 奖扣类型Controller
 *
 * @author sanye
 * @version 2019-05-02
 */
@Controller
@RequestMapping(value = "${adminPath}/biz/prizeType")
public class PrizeTypeController extends BaseController {

    @Autowired
    private PrizeTypeService prizeTypeService;

    /**
     * 获取数据
     */
    @ModelAttribute
    public PrizeType get(String id, boolean isNewRecord) {
        return prizeTypeService.get(id, isNewRecord);
    }

    /**
     * 查询列表
     */
    @RequiresPermissions("biz:prizeType:view")
    @RequestMapping(value = {"list", ""})
    public String list(PrizeType prizeType, Model model) {
        model.addAttribute("prizeType", prizeType);
        return "modules/biz/prizeTypeList";
    }

    /**
     * 查询列表数据
     */
    @RequiresPermissions("biz:prizeType:view")
    @RequestMapping(value = "listData")
    @ResponseBody
    public Page<PrizeType> listData(PrizeType prizeType, HttpServletRequest request, HttpServletResponse response) {
        prizeType.setPage(new Page<>(request, response));
        Page<PrizeType> page = prizeTypeService.findPage(prizeType);
        return page;
    }

    /**
     * 查看编辑表单
     */
    @RequiresPermissions("biz:prizeType:view")
    @RequestMapping(value = "form")
    public String form(PrizeType prizeType, Model model) {
        model.addAttribute("prizeType", prizeType);
        return "modules/biz/prizeTypeForm";
    }

    /**
     * 保存奖扣类型
     */
    @RequiresPermissions("biz:prizeType:edit")
    @PostMapping(value = "save")
    @ResponseBody
    public String save(@Validated PrizeType prizeType) {
        prizeTypeService.save(prizeType);
        return renderResult(Global.TRUE, text("保存奖扣类型成功！"));
    }

    /**
     * 停用奖扣类型
     */
    @RequiresPermissions("biz:prizeType:edit")
    @RequestMapping(value = "disable")
    @ResponseBody
    public String disable(PrizeType prizeType) {
        prizeType.setStatus(PrizeType.STATUS_DISABLE);
        prizeTypeService.updateStatus(prizeType);
        return renderResult(Global.TRUE, text("停用奖扣类型成功"));
    }

    /**
     * 启用奖扣类型
     */
    @RequiresPermissions("biz:prizeType:edit")
    @RequestMapping(value = "enable")
    @ResponseBody
    public String enable(PrizeType prizeType) {
        prizeType.setStatus(PrizeType.STATUS_NORMAL);
        prizeTypeService.updateStatus(prizeType);
        return renderResult(Global.TRUE, text("启用奖扣类型成功"));
    }

    /**
     * 删除奖扣类型
     */
    @RequiresPermissions("biz:prizeType:edit")
    @RequestMapping(value = "delete")
    @ResponseBody
    public String delete(PrizeType prizeType) {
        prizeTypeService.delete(prizeType);
        return renderResult(Global.TRUE, text("删除奖扣类型成功！"));
    }


    @RequiresPermissions("biz:prizeType:edit")
    @RequestMapping({"listSelect"})
    public String listSelect(PrizeType prizeType, String selectData, Model model) {
        String selectDataJson = EncodeUtils.decodeUrl(selectData);
        if (JsonMapper.fromJson(selectDataJson, Map.class) != null) {
            model.addAttribute("selectData", selectDataJson);
        }
        model.addAttribute("prizeType", prizeType);
        return "modules/biz/prizeTypelistSelect";
    }


    /**
     * 获取树结构数据
     *
     * @param excludeCode 排除的Code
     * @param isShowCode  是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
     * @return
     */
    @RequiresPermissions("biz:prizeType:edit")
    @RequestMapping(value = "treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        PrizeType prizeType = new PrizeType();
        List<PrizeType> prizeTypeList = prizeTypeService.findList(prizeType);
        // 处理转换数据，并返回ztree支持的格式
        for (int i = 0; i < prizeTypeList.size(); i++) {
            PrizeType e = prizeTypeList.get(i);
            Map<String, Object> map = MapUtils.newHashMap();
            map.put("id", e.getId());
            map.put("pId", 0);
            map.put("name", e.getName());
            map.put("title", e.getName() + " ["+ e.getLed() +"]");
            map.put("led", e.getLed());
            map.put("isAdd", e.getIsAdd());
            // 是否仅获取可管理的栏目，指定 true 或 false
            mapList.add(map);
        }
        return mapList;
    }

}