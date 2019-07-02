package com.shl.test;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shl
 */
public class KKTest {

	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	private static List renderResultList(List list, int pageSize, int currPage)
	  {
	    List newList = null;
	    if ((list != null) && (list.size() > 0)) {
	      newList = new ArrayList();
	      Object[] obj = (Object[])null;
	      for (int i = 0; i < list.size(); i++) {
	        obj = (Object[])list.get(i);
	        obj = Arrays.copyOf(obj, obj.length + 1);
	        obj[(obj.length - 1)] = Integer.valueOf(i + 1 + (currPage - 1) * pageSize);
	        newList.add(obj);
	      }
	    }
	    return newList;
	  }

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://192.168.7.151:1433;DatabaseName=bd1247";
		Connection conn = DriverManager.getConnection(url, "ezoffice", "12345678.");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer("");

		/*
		 String  selectSql = " select unit.UnitName as names,sum(case when ( TaskStatus in ('5','6','7','8','9','10') )  then 1 else 0 end ) totalTask,  sum(case when ( TaskStatus = '6' ) then 1 else 0 end ) taskended,sum(case when ( TaskStatus = '7' ) then 1 else 0 end ) taskendover, sum(case when ( TaskStatus in('5','8')) then 1 else 0 end) taskdealing,sum(case when ( TaskStatus in('9','10'))  then 1 else 0 end ) taskdealingover ,unit.ORDERCODE ";

		    String fromSql = " from OA_SVISION_UNIT unit left join OA_SVISION_TASK po on unit.UnitId = po.HOSTUNITID  ";
		    String whereSql = " where 1=1 ";
		    whereSql = whereSql + " group by unit.UnitName,unit.ORDERCODE ";

		    String orderBySql = "  order by unit.ORDERCODE,names ";
		    pstmt = conn.prepareStatement(selectSql+fromSql+whereSql+orderBySql);
		    rs = pstmt.executeQuery();
		    while(rs.next()){
		    	System.out.println(">>>>>>"+rs.getString(1));
		    }
		*/
		/*
		String sql = "select opps.settingsxml from oa_portal_portlet_setting opps ";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		String result = "";
		String result2 = "";
		char[] buff = new char[32];
		int len = 0;
		if (rs.next()){
			Clob c = rs.getClob(1);
			result = c.getSubString(1, (int)c.length());
			 java.io.Reader r = c.getCharacterStream();
			while ((len = r.read(buff)) != -1){
				result2 += new String(buff,0,len);
			}
		}
		System.out.println("result = " + result);
		System.out.println("result2 = " + result2);
		if (result.equals(result2)){
			System.out.println("hello 康老师");
		}
		
		
		String sql2 = "SELECT SUM(1) kk,SUM(CASE WHEN TASKSTATUS = '5' OR TASKSTATUS = '8' THEN 1 ELSE 0 END) nn ,SUM(CASE WHEN TASKSTATUS = '6' OR TASKSTATUS = '7' THEN 1 ELSE 0 END) pp ,SUM(CASE WHEN TASKSTATUS = '9' OR TASKSTATUS = '10' THEN 1 ELSE 0 END) qq FROM OA_SVISION_TASK po WHERE 1=1 ";
		String sql3 = "SELECT SUM(1) ,SUM(CASE WHEN TASKSTATUS = '5' OR TASKSTATUS = '8' THEN 1 ELSE 0 END)  ,SUM(CASE WHEN TASKSTATUS = '6' OR TASKSTATUS = '7' THEN 1 ELSE 0 END)  ,SUM(CASE WHEN TASKSTATUS = '9' OR TASKSTATUS = '10' THEN 1 ELSE 0 END)  FROM OA_SVISION_TASK po WHERE 1=1 ";
		pstmt = conn.prepareStatement(sql2);
		rs = pstmt.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		List<Object[]> list = new ArrayList<>();
		Object[] obj;
		while (rs.next()){
			obj = new Object[rsmd.getColumnCount()];
			for (int i = 1; i <= rsmd.getColumnCount();i++){
				obj[i-1] = rsmd.getColumnName(i) + ":" + rs.getObject(rsmd.getColumnName(i));
			}
			list.add(obj);
		}
		
		for (Object[] ele : list){
			System.out.println(">>>>>" + java.util.Arrays.toString(ele));
		}*/
	}
}
