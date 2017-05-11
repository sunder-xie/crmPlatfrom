package com.kintiger.platform.kunnrManager.pojo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HanaConnection {
	private static final String DRIVER = "com.sap.db.jdbc.Driver";  //jdbc 4.0
	private static final String URL = "jdbc:sap://10.0.3.20:30015?Catalog=_SYS_BIC&reconnect=true";
    
	public static List<String[]> connection(String sql) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String[]> results = new ArrayList<String[]>();
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, "system", "Sap123456");
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int colNum = rsmd.getColumnCount();
				String[] result = new String[colNum];
				for (int i = 1; i <= colNum; i++) {
					result[i-1]=rs.getString(i);
					System.out.println(rs.getString(i));
				}
				results.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
				if(rs!=null){rs.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return results;
	}
}
