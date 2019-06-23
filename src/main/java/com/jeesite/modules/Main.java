package com.jeesite.modules;

import bsh.EvalError;
import bsh.Interpreter;
import com.jeesite.modules.utils.StringUtil;

public class Main {

    public static void main(String[] args) throws EvalError {
        Interpreter bsh = new Interpreter();
        Object eval = bsh.eval("(1+2)-1");
        bsh.set("a", 2);
        bsh.set("b", 1);
        Object eval1 = bsh.eval("a+b");
        System.out.println(bsh.eval("if(a>b) return a+b"));

        String oldString = "(100-车辆查处)*0.1";
        String chinese = StringUtil.replaceChinese(oldString, "#");
        System.out.println(chinese);
        Object eval2 = bsh.eval(chinese);
        System.out.println(eval2);
    }



    /**
     * 把字符串去重，并升序排序
     * @param str
     * @return
     */
    public static String sort2(String str) {


        return str;
    }
}
