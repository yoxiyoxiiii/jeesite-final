package com.jeesite.modules;

import bsh.EvalError;
import bsh.Interpreter;

public class Main {

    public static void main(String[] args) throws EvalError {
        Interpreter bsh = new Interpreter();
        Object eval = bsh.eval("(1+2)-1");
        bsh.set("a",2);
        bsh.set("b",1);
        Object eval1 = bsh.eval("a+b");
        System.out.println(bsh.eval("if(a>b) return a+b"));

//        System.out.println(eval1);
    }

}
