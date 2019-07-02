package com.shl.common.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DbBase {

	private static final String url;
	private static final String pwd;
	private static final String user;
	private static final String driver;
	private static Properties dbconfig = new Properties();
	
	protected Connection conn;
	protected PreparedStatement pstmt;
	static {
		try {
			dbconfig.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dbconfig.properties"));
		} catch (IOException e) {
			System.out.println(">>>读取配置错误");
			e.printStackTrace();
		}
		url = dbconfig.getProperty("dburl");
		user = dbconfig.getProperty("dbuser");
		pwd = dbconfig.getProperty("dbpwd");
		driver = dbconfig.getProperty("dbdriver");
	}
	
	protected void begin() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		conn = DriverManager.getConnection(url,user,pwd);
	}
	
}
