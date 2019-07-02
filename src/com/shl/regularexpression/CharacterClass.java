package com.shl.regularexpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**字符组
 * @author shenhl
 */
public class CharacterClass {
    private Pattern pattern = Pattern.compile("[0-9]");
    private Matcher matcher;
    public static void main(String[] args) {
        CharacterClass characterClass = new CharacterClass();
        // characterClass.characterClassRangeDesc();
        // 排除性 字符组
        characterClass.negatedCharacterClass();
        // 量词
        characterClass.regexQuantifier();
    }

    /**
     * 正则表达式  量词  介绍 和  用法
     */
    private void regexQuantifier() {
        // 所谓 量词  就是 正则的规则记法
        // 中国大陆 邮编匹配 六位数字
        String[] testStr = {"100806", "201203", "20A203", "20103", "2012036"};
        String regexExp = "^\\d{6}$";
        quantifierLoopTest(testStr, regexExp);
        hr();
        // 量词的一般 形式
        // {n} 前面的元素 必须 出现 n 次
        // {m,n} 前面的元素 必须 出现 m <=  <= n 次
        // {m,} 前面的 元素 必须 最少 出现 m次
        // {0,n} 前面的 元素 可以 不出现  最多 出现 m 次
        // 以上 说明  是 {m,n} 表示闭区间
        testStr = new String[]{"123","1234","12345","123456","1234567"," 123"};
        quantifierLoopTest(testStr, "^\\d{4,6}$");
        hr();
        quantifierLoopTest(testStr, "^\\d{4,}$");
        hr();
        quantifierLoopTest(testStr, "^\\d{0,6}$");
        hr();
        // 下面 这条语句 会 报错
        // Exception in thread "main" java.util.regex.PatternSyntaxException: Unclosed counted closure near index 6
        //^\d{0, 6}$  所以 ，后面不能有空格
        // quantifierLoopTest(testStr, "^\\d{0, 6}$");

        // 下面 介绍 三个  日常开发 中  使用 频率 较高 的 简记法
        // *  == {0,}
        // + == {1,}
        // ? == {0,1}
        // http 和 https 都是 协议名  下面来匹配一下
        testStr = new String[]{"http", "https"};

        quantifierLoopTest(testStr, "https?");
        hr();
        // 匹配 html open tag
        testStr = new String[]{"<b>"};
        quantifierLoopTest(testStr, "<[^/][^>]*>");
        hr();
        Pattern pattern = Pattern.compile("(\\d{6})");
        Matcher matcher = pattern.matcher("zipcode:123456 zipcode:442563");
        System.out.println(">>>>>>" + matcher.find());
    }
    private void quantifierLoopTest(String[] testStr, String regexExp) {
        for (String str : testStr) {
            System.out.println(">>>>>>>>>>" + str + " matches result : " + str.matches(regexExp));
        }
    }
    private void hr() {
        System.out.println("**************************************************************************");
    }
    /**
     * 排除型字符组   [在当前位置  “匹配” 一个 没有 列出的 字符] -- 即  排除型字符组“必须” 匹配一个字符
     */
    private void negatedCharacterClass() {
        System.out.println(">>>>>>>>>>>" + " ".matches("[\\d]?"));
        System.out.println(">>>>>>>>>" + "A8".matches("^[^\\d][\\d]$"));
        System.out.println(">>>>>>>>>" + "x8".matches("^[^\\d][\\d]$"));
        System.out.println(">>>>>>>>> 排除型字符组“必须” 匹配一个字符 示列1 " + "8".matches("^[^\\d][\\d]$"));
        System.out.println(">>>>>>>>> 排除型字符组“必须” 匹配一个字符 示列2 " + "龙8".matches("^[^\\d][\\d]$"));
    }
    /**
     * 普通字符组  【注意转义就OK】
     */
    private void characterClassRangeDesc() {
        // 正则中 普通字符组
        System.out.println(">>>>>>>>>" + "2345".matches("^[012]345]$"));
        System.out.println(">>>>>>>>>" + "2345]".matches("^[012]345]$"));
        System.out.println(">>>>>>>>>" + "2345".matches("^[012\\]345]$"));
        System.out.println(">>>>>>>>>" + "5".matches("^[012\\]345]$"));
        System.out.println(">>>>>>>>>" + "]".matches("^[012\\]345]$"));
        System.out.println(">>>>>>>>>" + "[012]".matches("\\[012]"));
    }
}
