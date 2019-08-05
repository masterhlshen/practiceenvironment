package com.shl.test;

import javax.xml.crypto.Data;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static String UID() {
        return UUID.randomUUID().toString();
    }

    public static String UUID() {
        return UID().replaceAll("-", "");
    }

    public static void main(String[] args) throws ParseException, UnsupportedEncodingException {

        String startDateStr = "2019-12-29";

        String endDateStr = "2020-01-06";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date start = sdf.parse(startDateStr);
        Date end = sdf.parse(endDateStr);

        Calendar c = Calendar.getInstance();
        c.setTime(end);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(start);
        setChinaSunday(c);
        setChinaSunday(c2);
        System.out.println(">>>>>>>>c = " + sdf.format(c.getTime()));
        System.out.println(">>>>>>>>c2 = " + sdf.format(c2.getTime()));
        int weekNum = 1;
        if (c.after(c2)) {
            long time = c.getTimeInMillis() - c2.getTimeInMillis();
            time /= (1000 * 3600 * 24 * 7);
            weekNum = (int) time + 1;
            System.out.println(time);
        }
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


        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            r.setSeed(System.currentTimeMillis() + i);
            System.out.print("random = " + r.nextInt(10) + " \t");
        }

        listAddAllOtherList();

        try {
            System.out.println(">>>>>>>>" + (2 / 0));
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>");
            e.printStackTrace();
        }

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
