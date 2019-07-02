package com.shl.test;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

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
            weekNum = (int)time + 1;
            System.out.println(time);
        }
        System.out.println(">>>>>>>>.周num = " + weekNum);

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
