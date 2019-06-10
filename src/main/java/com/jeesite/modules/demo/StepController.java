package com.jeesite.modules.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 分步表单
 */
@Controller
@RequestMapping(value = "${adminPath}/demo/step")
public class StepController {

    public String list(Demo demo, Model model) {
        model.addAttribute("demo",demo);
        return "demo/stepList";
    }
}
