package com.shl.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KKTestOracle {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("oracle.jdbc.driver.OracleDriver");//找到oracle驱动器所在的类
		String url="jdbc:oracle:thin:@10.0.12.180:1521:oa"; //URL地址
		Connection conn=DriverManager.getConnection(url, "ezoffice", "oa1800");
		
		
		
		File file = new File("D:/countFile.txt");
		PreparedStatement pstmt = conn.prepareCall("select ody.decision_name from oa_decision_ytys ody order by ody.create_time desc ");
		ResultSet rs = pstmt.executeQuery();
		int i = 0;
		FileWriter write = new FileWriter(file);
		while (rs.next()) {



			i++;
			write.write(rs.getString(1) + "\n");
		}
		write.write(">>>>总数 = " + i);
		write.flush();
		write.close();
	}
}
