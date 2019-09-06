package com.shl.test;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static String UID() {
        return UUID.randomUUID().toString();
    }

    public static String UUID() {
        return UID().replaceAll("-", "");
    }

    public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
        System.out.println(">>>>>>>>>>>" + File.pathSeparator);
        System.out.println(">>>>>>>>>>>" + File.pathSeparatorChar);
        System.out.println(">>>>>>>>>>" + File.separator);
        System.out.println(">>>>>>>>>>" + File.separatorChar);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();
        setChinaSunday(c);
        System.out.println(">>>>>>>>c = " + sdf.format(c.getTime()));
        int weekNum = 1;
        System.out.println(">>>>>>>>.周num = " + weekNum);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Timestamp t = new Timestamp(System.currentTimeMillis());
        Date d = (Date) t;
        System.out.println(">>>>>>>>>>>>>>>d = " + d);

        Map<String, String> testMap = new HashMap<>();
        List<Map<String, String>> testList = new LinkedList<>();
        Type[] types = testList.getClass().getGenericInterfaces();
        //Type types = testList.getClass().getGenericSuperclass()
        System.out.println(">>>>>>>>>>types.length = " + types.length);
        for (Type tt : types) {
            if (tt instanceof ParameterizedType) {
                ParameterizedType ntt = (ParameterizedType) tt;
                Type[] nntt = ntt.getActualTypeArguments();
                for (Type tttt : nntt) {
                    System.out.println(">>>>>>>>>>>>ParameterizedType = " + tttt.getTypeName());
                }
            }
        }

        Type newType = testList.getClass().getGenericSuperclass();
        if (newType instanceof ParameterizedType) {
            ParameterizedType newTT = (ParameterizedType) newType;
            Type[] ttArr = newTT.getActualTypeArguments();
            for (Type tttt : ttArr) {
                System.out.println(">>>>>>>>>>>>>>>2222:" + tttt.getTypeName());

            }
        }

        Map.Entry entry ;

        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            r.setSeed(System.currentTimeMillis() + i);
            System.out.print("random = " + r.nextInt(10) + " \t");
        }

        listAddAllOtherList();

        System.out.println(">>>>>>>>>>div个数" + render());
        percentOut(3, 11);
        String str = "$2c90abb66c9d6c35016c9d876401001a$$2c90abb66c9d6c35016c9d8952d90024$";
        str = str.substring(1, str.length() - 1);
        System.out.println(">>>>>>>>" + str.split("\\$\\$").length);
        NumberFormat nbf = NumberFormat.getPercentInstance();
        nbf.setMaximumFractionDigits(2);
        System.out.println(">>>>>>>>>>" + nbf.format(0.2333333));

        String dateStr = "2019-08-31";
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(">>>>>>>>>>>" + (sdf.parse(dateStr)));

    }

    public static int render() {
        String s = "<div class=\"view\"><a href=\"javascript:;\" onclick=\"deleteGrid(this)\" class=\"layout-btn layout-item-close\"><i class=\"fa fa-remove\"></i><em>删除</em></a>\n" +
                "                        <span class=\"layout-btn layout-item-move\"><i class=\"fa fa-drag-handle\"></i><em>移动</em></span>\n" +
                "                        <div class=\"row\"><div class=\"col-xs-4 layout-item\" id=\"15663559229950\"></div><div class=\"col-xs-8 layout-item\" id=\"15663559229951\"></div></div></div>\f <div class=\"row\"><div class=\"col-xs-4 layout-item\" id=\"15663559229950\"></div><div class=\"col-xs-8 layout-item\" id=\"15663559229951\"></div></div></div> \n" +
                "<div class=\"row\"><div class=\"col-xs-4 layout-item\" id=\"15663559229950\"></div><div class=\"col-xs-8 layout-item\" id=\"15663559229951\"></div><div>hello</div></div></div>";
        Pattern p = Pattern.compile("<div\\s*class=\"row\">\\s*");
        Matcher m = p.matcher(s);
        int n = 0;
        int start = 0;
        int end = 0;
        while (m.find()) {
            n++;
            System.out.println(m.group());

            if (start > 0) {
                System.out.println(s.substring(start, m.start()));
            }
            start = m.start();
        }
        System.out.println(s.substring(start));
        return n;
    }

    public static void listAddAllOtherList() {
        List<String> list = new LinkedList<>();
        List<String> list2 = Arrays.asList("1", "3", "3");
        list.add("shenhl");
        list.addAll(list2);
        list.remove(1);
        list.stream().forEach(System.out::println);
    }

    private static void setChinaSunday(Calendar c) {
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        if (c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            c.add(Calendar.DATE, 7);
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        }
        System.out.println(">>>>>>>>>>>>>>>>ccc" + c.getTime());

    }

    static void percentOut(double d1, double d2) {
        NumberFormat nbf = NumberFormat.getPercentInstance();
        nbf.setMaximumFractionDigits(2);
        double result = d1 / d2;
        System.out.println(nbf.format(result));
    }
}



class A {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
