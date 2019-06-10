package com.jeesite.modules.demo;

import com.jeesite.common.lang.StringUtils;
import com.jeesite.modules.test.entity.TestData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "${adminPath}/dataGrid")
public class DataGridController {

    /**
     * DataGrid
     */
    @RequestMapping(value = "{viewName}")
    public String dataGrid(@PathVariable String viewName, TestData testData, Model model) {
        return "modules/demo/demoDataGrid" + StringUtils.cap(viewName);
    }
}
