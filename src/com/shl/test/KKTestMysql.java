package com.shl.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class KKTestMysql {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");//找到oracle驱动器所在的类
        /*String url="jdbc:mysql://localhost:3306/xiaocoffice?characterEncoding=UTF-8"; //URL地址
        Connection conn= DriverManager.getConnection(url, "root", "shenshao");*/

        String url="jdbc:mysql://192.168.20.63:3306/xiaocoffice?characterEncoding=UTF-8"; //URL地址
        Connection conn= DriverManager.getConnection(url, "root", "12345");
        String sql1 = "INSERT INTO mark_record VALUES (?, '402894ab6a91c07c016a91cfc8900000', '2019-6-15 16:15:18', '402894a76a911b98016a911cae950000', 0, NULL, NULL, NULL, 0, '40289e886b02443f016b02477b6f0002', '0', '40289e886b068f2a016b06917d420009', '2019-6-15 16:15:18', '考勤备注：发到我给', '402894f56b259297016b27e929de0016', 1, NULL, '402894f56b259297016b27e170df0012', NULL, -1.0, NULL, '402894426b45611f016b457ab2de0016', '40289e886b0bf880016b0bfa420e0000', NULL, NULL, 0, 0, 0, NULL)";
        String sql2 = "INSERT INTO mark_record VALUES (?, '402894ab6a91c07c016a91cfc8900000', '2019-6-15 15:16:32', '402894a76a911b98016a911cae950000', 0, NULL, NULL, NULL, 0, '40289e886b02443f016b0246f8f00001', '0', '402894426b1bf1c0016b1bf2bbc10001', '2019-6-15 15:16:32', NULL, NULL, 0, NULL, '402894f56b259297016b27e862c10014', NULL, -32.0, NULL, NULL, '402894426b4f755e016b50c754110007', NULL, NULL, 0, 0, 1, NULL)";
        System.out.println(conn == null);
        PreparedStatement ps = conn.prepareStatement(sql1);
        for (int i = 0; i < 10000; i++) {
            ps.setString(1, UUID());
            ps.executeUpdate();
        }

        ps = conn.prepareStatement(sql2);
        for (int i = 0; i < 10000; i++) {
            ps.setString(1, UUID());
            ps.executeUpdate();
        }


    }

    public static String UUID() {
        return UID().replaceAll("-", "");
    }
    public static String UID() {
        return UUID.randomUUID().toString();
    }
}
