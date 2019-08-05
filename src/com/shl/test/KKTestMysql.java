package com.shl.test;

import com.mysql.fabric.xmlrpc.base.Param;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.*;
import java.util.Date;

public class KKTestMysql {

    public static String UUID() {
        return UID().replaceAll("-", "");
    }

    public static String UID() {
        return UUID.randomUUID().toString();
    }

    public static int randomBound(int bound) {
        int offset = ((int) Math.random() * 100) + 1;
        return new Random(offset + System.currentTimeMillis()).nextInt(bound);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");//找到oracle驱动器所在的类
        /*String url="jdbc:mysql://localhost:3306/xiaocoffice?characterEncoding=UTF-8"; //URL地址
        Connection conn= DriverManager.getConnection(url, "root", "shenshao");*/

        String url = "jdbc:mysql://192.168.20.63:3306/xiaocoffice?characterEncoding=UTF-8"; //URL地址
        Connection conn = DriverManager.getConnection(url, "root", "12345");
        createProData(conn);
        createTrainingCourseData(conn);
        createCheckSetData(conn);
        createCheckRecord(conn);

    }

    static void createCheckRecord(Connection conn) throws SQLException {
        String sql = "INSERT INTO training_checkrecord (id, isdelete, optlock, domain_id, checkinnstatus, checkinntime, checkuserid, checkoutstatus, checkouttime, courseid, proid," +
                "check_num," +
                " course_point,course_real_point,total_pro_num) " +
                "VALUES (?,0,0,0,?,?,?,?,?,?,?,?,?,?,?)";
        Map<String, String> proRefCourseMap = porRefCourse(conn);
        Map<String, ParamVO> porIdMap = porIdMap(conn);
        List<String> userList = userInfo(conn);
        String[] courseIdArr = null;
        String courseIds;
        Calendar c = Calendar.getInstance();
        int n;
        ParamVO vo;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (String key : proRefCourseMap.keySet()) {
            courseIds = proRefCourseMap.get(key);
            vo = porIdMap.get(key);
            int totalProNum = randomBound(100);
            int totalPoint = randomBound(200);
            int realPoint = randomBound(180);
            int checkNum = randomBound(20);
            int realCheckNum = randomBound(20);
            if (courseIds != null && courseIds.contains(",")) {
                courseIdArr = courseIds.split(",");
                for (String courseId : courseIdArr) {
                    for (int i = 0; i < 5; i++) {
                        n = 0;
                        pstmt.setObject(++n, UUID());
                        pstmt.setObject(++n, randomBound(1));
                        pstmt.setObject(++n, c.getTime());
                        pstmt.setObject(++n, userList.get(randomBound(userList.size())));
                        pstmt.setObject(++n, randomBound(1));
                        pstmt.setObject(++n, c.getTime());
                        pstmt.setObject(++n, courseId);
                        pstmt.setObject(++n, key);
                        pstmt.setObject(++n, 1);
                        pstmt.setObject(++n, 25);
                        pstmt.setObject(++n, 4);
                        pstmt.setObject(++n, 1);
                        pstmt.executeUpdate();
                    }
                }
            }
        }
    }

    // 签到设置
    static void createCheckSetData(Connection conn) throws SQLException {
        String sql = "INSERT INTO training_check_in (id,  isdelete, optlock, domain_id, check_in_end_minutes, check_in_start_minutes, check_in_type, come_late_minutes, course_id, except_check_out, need_check_out, normal_check_out_end, normal_check_out_start, pro_id) " +
                "VALUES (?,0,0,0,10,-10,0,10,?,1,1,-10,10,?)";
        Map<String, String> proRefCourseMap = porRefCourse(conn);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (String key : proRefCourseMap.keySet()) {
            pstmt.setObject(1, UUID());
            pstmt.setObject(2, proRefCourseMap.get(key));
            pstmt.setObject(3, key);
            pstmt.executeUpdate();
        }
    }


    static Map<String, String> porRefCourse(Connection conn) throws SQLException {
        String sql = "select bbb.id , aaa.id from training_evaluationpro bbb LEFT JOIN training_course aaa ON aaa.pro_id = bbb.id ";
        Map<String, String> resultMap = new HashMap<>(32);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        String courseId;
        String proId;
        while (rs.next()) {
            proId = rs.getString(1);
            courseId = rs.getString(2);
            if (resultMap.containsKey(proId)) {
                courseId = resultMap.get(proId) + "," + courseId;
                resultMap.put(proId, courseId);
            } else {
                resultMap.put(proId, courseId);
            }
        }

        return resultMap;
    }

    // 生成课程id sql
    static void createTrainingCourseData(Connection connection) throws SQLException {
        String inserCourseSql = "INSERT INTO training_course (id,isdelete,optlock, domain_id, course_content, course_start_date, course_name, course_period, course_place, course_end_date,  course_teacher_name, course_worth, pro_id, course_time_seq) " +
                "VALUES (?,0,0,0,?,?,?,?,?,?,'郭老师',?,?,?)";
        List<ParamVO> proIdCollection = porIdCollection(connection);
        PreparedStatement pstmt = connection.prepareStatement(inserCourseSql);
        int n;
        ParamVO vo = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String timeSeq = "";
        for (int i = 0; i < 20; i++) {
            n = 0;
            vo = proIdCollection.get(randomBound(proIdCollection.size()));
            pstmt.setObject(++n, UUID());
            pstmt.setObject(++n, "郭老师人造数据" + UUID());
            pstmt.setObject(++n, vo.getStartDate());
            timeSeq = sdf.format(vo.getStartDate());
            pstmt.setObject(++n, "郭老师的课程" + randomBound(20));
            pstmt.setObject(++n, randomBound(20));
            pstmt.setObject(++n, "郭老师的地方" + randomBound(20));
            pstmt.setObject(++n, vo.getEndDate());
            pstmt.setObject(++n, randomBound(50));
            pstmt.setObject(++n, vo.getId());
            pstmt.setObject(++n, timeSeq + "~" + sdf.format(vo.getEndDate()));
            pstmt.executeUpdate();
        }


    }

    // 获取项目 id 集合
    static Map<String, ParamVO> porIdMap(Connection connection) throws SQLException {
        String sql = "select id,pro_start_date,pro_end_date from training_evaluationpro ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        Map<String, ParamVO> result = new HashMap(16);
        ParamVO vo;
        Date startDate, endDate;
        String id;
        while (rs.next()) {
            id = rs.getString(1);
            startDate = rs.getDate(2);
            endDate = rs.getDate(3);
            vo = new ParamVO(id, startDate, endDate);
            result.put(id, vo);
        }
        return result;
    }

    // 获取项目 id 集合
    static List<ParamVO> porIdCollection(Connection connection) throws SQLException {
        String sql = "select id,pro_start_date,pro_end_date from training_evaluationpro ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<ParamVO> result = new ArrayList<>(16);
        ParamVO vo;
        Date startDate, endDate;
        String id;
        while (rs.next()) {
            id = rs.getString(1);
            startDate = rs.getDate(2);
            endDate = rs.getDate(3);
            vo = new ParamVO(id, startDate, endDate);
            result.add(vo);
        }
        return result;
    }

    private static List<String> userInfo(Connection conn) throws SQLException {
        String sql = "select id from sys_core_user";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<String> list = new ArrayList<>(32);
        while (rs.next()) {
            list.add(rs.getString(1));
        }
        return list;
    }

    // 构造 项目数据
    private static void createProData(Connection conn) throws SQLException {
        String insertProSql = "INSERT INTO training_evaluationpro (id, isdelete, optlock, domain_id, attend_emp_num,  pro_start_date , pro_name,pro_end_date,  pro_introduction,pro_time_seq,send_status) " +
                "VALUES (?,0,0,0,100,?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(insertProSql);
        int n;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar c = Calendar.getInstance();
        String timeSeq = "";
        for (int i = 0; i < 5; i++) {
            n = 0;
            pst.setString(++n, UUID());
            pst.setObject(++n, c.getTime());
            timeSeq = sdf.format(c.getTime());
            pst.setObject(++n, "郭老师人造数据" + i * 2);
            c.add(Calendar.DATE, (i + 1) * 2);
            pst.setObject(++n, c.getTime());
            pst.setObject(++n, "郭老师人造数据" + i * 30);
            pst.setObject(++n, timeSeq + "~" + sdf.format(c.getTime()));
            pst.setObject(++n, 1);
            pst.executeUpdate();
        }
    }


}

class ParamVO {
    private String id;
    private Date startDate;

    private Date endDate;

    public ParamVO() {
    }

    public ParamVO(String id, Date startDate, Date endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}