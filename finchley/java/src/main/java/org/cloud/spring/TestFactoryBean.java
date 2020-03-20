package org.cloud.spring;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLSyntaxErrorException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author liangcai_zhu
 * @Description TODO
 * @Date 2019/11/13 9:47
 */
public class TestFactoryBean {

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

        String str = "LED筒灯 CEA12501E  4W 3000K 24 DWDW";
        String[] s = str.split(" ");
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
//        testSpec();

    }

    private static void testSpec() {
        String[] str = {"LED筒灯 CEA12501E Ⅱ 4W 3000K 24° DW/DW","/_!、\"商$特 @殊 % /品'|^","!@   #$%   ^&*())_+=-09 8{}|【】、[]\\","! *","n!@#$  %^&*x"};
        for (int i = 0; i < str.length; i++) {
            String filtration = filtration0(str[i]);
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
        String regEx = "[`_~!@#$%^&*()+=|{}:;.<>[\\\\]/\\[\\]?~！@#￥%……&*（）——+\\-|{}【】‘；：”“’。，、？'\"]";
        pattern = Pattern.compile(regEx);
    }
    public static String filtration(String str) {
        str = pattern.matcher(str).replaceAll("").trim();
        return str;
    }

    private static void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        ABean bean = context.getBean(ABean.class);
        String sql = bean.getSql();
        sql = sql.toLowerCase();

        String oldLikeSQL = sql;
        String headLike = oldLikeSQL.substring(0, oldLikeSQL.indexOf("like"));
        getSubStr(headLike, "?");

        System.out.println();


        String midleLike = oldLikeSQL.substring(oldLikeSQL.indexOf("like"), oldLikeSQL.lastIndexOf("like"));
        String tailLike = oldLikeSQL.substring(oldLikeSQL.lastIndexOf("like"));
        System.out.println(headLike);
        System.out.println(midleLike);
        System.out.println(tailLike);
        //截取sql 成为收尾两部分
        String tempSQL = sql;
        String[] head_tail = null;
        int likecount = 1; //去除的like ? 个数
        while (tempSQL.contains("like")) {
            head_tail = doSqlSub(tempSQL);
            tempSQL = head_tail[0] + head_tail[1];
            likecount++;
        }

        System.out.println(head_tail[0] + "（****）" + head_tail[1]);
    }

    /**
     * 获取字符串出现的个数
     *
     * @param str 传进来的原字符串
     * @param chs 传进来的要查找的字符串
     * @return 统计字符串出现的个数
     */
    public static int getSubStr(String str, String chs) {
        int count = 0;
        char[] ch = str.toCharArray();
        char c = chs.charAt(0);
        for (int i = 0; i < ch.length; i++) {
            if (c == ch[i]) {
                count++;
            }
        }

        return count;
    }

    private static String[] doSqlSub(String sql) {
        String head = sql.substring(0, sql.indexOf("like"));
        String tail = sql.substring(sql.indexOf("like"));
        return new String[]{head.substring(0, head.lastIndexOf("(")), tail.substring(tail.indexOf(")") + 1)};
    }

    //
    public static SQLStatement parser(String sql, String dbType) throws SQLSyntaxErrorException {
        List<SQLStatement> list = SQLUtils.parseStatements(sql, dbType);
        if (list.size() > 1) {
            throw new SQLSyntaxErrorException("MultiQueries is not supported,use single query instead ");
        }
        return list.get(0);
    }

    private static void parserSql(String sql) {

        try {
            SQLSelectStatement statement = (SQLSelectStatement) parser(sql, "mysql");

            SQLSelect select = statement.getSelect();
            SQLSelectQueryBlock query = (SQLSelectQueryBlock) select.getQuery();
            SQLJoinTableSource tableSource = (SQLJoinTableSource) query.getFrom();
//            tableSource.getParent().getParent().get
            MySqlSelectQueryBlock queryBlock = (MySqlSelectQueryBlock) tableSource.getParent();
            if (queryBlock.getWhere() instanceof SQLBinaryOpExpr) {
                SQLBinaryOpExpr sqlBinaryOpExpr = (SQLBinaryOpExpr) queryBlock.getWhere();
                doSqlBinary(sqlBinaryOpExpr);
            }


        } catch (SQLSyntaxErrorException e) {
            e.printStackTrace();
        }


    }

    private static void doSqlBinary(SQLBinaryOpExpr sqlBinaryOpExpr) {

        SQLExpr left = sqlBinaryOpExpr.getLeft();
        if (left instanceof SQLBinaryOpExpr) {
            doSqlBinary((SQLBinaryOpExpr) left);
        }

        SQLExpr right = sqlBinaryOpExpr.getRight();
        if (right instanceof SQLBinaryOpExpr) {
            doSqlBinary((SQLBinaryOpExpr) right);
        }
    }
//
//    private static String[] doSqlSub(String sql) {
//        String head = sql.substring(0, sql.indexOf("or"));
//        String tail = sql.substring(sql.indexOf("or"));
//        return new String[]{head.substring(0, head.lastIndexOf("(")),tail.substring(tail.indexOf(")") + 1)};
//    }

    /**
     * 是否包含汉字<br>
     * 根据汉字编码范围进行判断<br>
     * CJK统一汉字（不包含中文的，。《》（）“‘'”、！￥等符号）<br>
     *
     * @param str
     * @return
     */
    public static boolean hasChineseByReg(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
        return pattern.matcher(str).find();
    }
}
