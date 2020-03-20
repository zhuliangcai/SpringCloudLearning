package org.cloud;

import java.util.regex.Pattern;

/**
 * @author liangcai_zhu
 * @Description TODO
 * @Date 2019/11/25 15:50
 */
public class RegexTest {
    public static void main(String[] args) {
//        Object[] objects = new Object[]{"a","b","1","0",0,'a',12,123};
//        int head=2;
//        int index=3;
//
//        Object[] objects1 = Arrays.copyOfRange(objects, 0,head);
//
//        Object[] objects2 = Arrays.copyOfRange(objects, head+index,objects.length);
//
//        Object[] objects3 = ArrayUtils.addAll(objects1, objects2);
//
//        for(Object i:objects3) {
//            System.out.print(i + " ");
//        }

//        String str = "LED筒灯 CEA12501E  4W 3000K 24 DWDW";
//        String[] s = str.split(" ");
//        for (int i = 0; i < s.length; i++) {
//            System.out.println(s[i]);
//        }
        testSpec();

    }

    //黑名单，排除掉特殊字符
    private static void testSpec() {
        String[] str = {"LED筒灯 CEA12501E Ⅱ 4W 3000K 24° DW/DW","/_!、\"商$特 @殊 % /品'|^","!@   #$%   ^&*())_+=-09 8{}|【】、[]\\","! *","n!@#$  %^&*x"};
        for (int i = 0; i < str.length; i++) {
            String filtration = filtration(str[i]);
            System.out.println("==>"+ filtration+"<==");
            System.out.println("==>"+ isBlank(filtration)+"<==");
            System.out.println(hasBlank(filtration));
        }

    }

    /**
     * 判断是否是空格
     * @param filtration
     * @return
     */
    private static boolean isBlank(String filtration) {
        return filtration==null||filtration.trim().length()==0;

    }
    /**
     * 判断是否包含空格
     * @param filtration
     * @return
     */
    private static boolean hasBlank(String filtration) {
        if(filtration==null||filtration.length()<1){
            return false;
        }
        String[] s = filtration.split(" ");
        return s.length>1;
    }


    static final Pattern pattern0;
    static {
        // ((?!X).)* 多个字符取非X
        // [\u4e00-\u9fa5a-zA-Z0-9_\s] 任意的中文，字母数字 下划线
        String regEx0 = "((?![\\u4e00-\\u9fa5a-zA-Z0-9\\s]).)*";
        pattern0 = Pattern.compile(regEx0);
    }
    /**
     * 常见特殊字符过滤
     * 留下中文字母数字空格
     * @param str
     * @return
     */
    public static String filtration0(String str) {
        str = pattern0.matcher(str).replaceAll("").trim();
        return str;
    }




    static final Pattern pattern;
    static {
//        所有要排除的字符都写在这里
        //[`_~!@#$%^&*()+=|{}:;.<>[\\]/\[\]?~！@#￥%……&*（）——+\-|{}【】‘；：”“’。，、？'"]
//        String regEx = "[`_~!@#$%^&*()+=|{}:;.<>[\\\\]/\\[\\]?~！@#￥%……&*（）——+\\-|{}【】‘；：”“’。，、？'\"]";
        String regEx = "[`_~!@#$%^&*()+=|{}:;.<>[\\\\]/\\[\\]?~！@#￥%……&*（）——+\\-|{}【】‘；：”“’。，、？'\"]";
        pattern = Pattern.compile(regEx);
    }
    /**
     *     黑名单，排除掉特殊字符
     */

    public static String filtration(String str) {
        str = pattern.matcher(str).replaceAll("").trim();
        return str;
    }

}
