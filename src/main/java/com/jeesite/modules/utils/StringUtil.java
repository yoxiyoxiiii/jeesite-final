package com.jeesite.modules.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {


    public static String replaceChinese(String source, String replacement) {
        if (replacement == null) {
            replacement = "#";//占位符
        }
        String reg = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(source);
        if (!mat.find()) {return source;}//不包含中文，直接返回
        String repickStr = mat.replaceAll(replacement);

        String substring = repickStr.substring(repickStr.indexOf(replacement),repickStr.lastIndexOf(replacement));
        String result = repickStr.replaceAll(substring, "").replaceAll(replacement,"1");
        return result;
    }

    public static List<String> getChiness(String str) {
        List<String> result = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = str.toCharArray();
        for (int i = 0;i<chars.length; i++) {
            int x = chars[i];
            if (x!=65310 && x!=65308) {//处理> <
                stringBuffer.append(chars[i]);
            }else {
                x = x - 65248;
                stringBuffer.append((char)x);
            }
        }
        String regEx = "[/>=/,/<=/,`,~,!,@,#,$%^&*()+,=|{}':;',\\[\\]./>/,/</,/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？>= <= - \"\\\\\",\"$\",\"(\",\")\",\"*\",\"+\",\".\",\"[\", \"]\",\"?\",\"^\",\"{\",\"}\",\"|\",\"'\",\"%\"]";
        String[] split = stringBuffer.toString().split(regEx);
        //是否是中文
        String reg = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(reg);
        for (String item : split) {
            boolean match = pat.matcher(item).find();
            if (match) {
                result.add(item.trim());//添加中文到结果
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        String item = "(100-数据次啊及)*数据";
////        List<String> chiness = getChiness(item);
////        System.out.println(chiness);
////

        String text = "if(鸟>0)".replaceAll(">","");
        System.out.println(text);
    }
}
