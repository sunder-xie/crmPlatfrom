package com.kintiger.platform.sales.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Test {
	private static final String DRIVER = "com.sap.db.jdbc.Driver";  //jdbc 4.0
	private static final String URL = "jdbc:sap://10.0.3.20:30015?Catalog=_SYS_BIC&reconnect=true";

	public static void main(String[] args) {
		try {
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, "system", "Sap123456");
			PreparedStatement pstmt = con.prepareStatement("SELECT MSEHI,MSEH3 FROM SLTPRD.T006A WHERE SPRAS = '1'");
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int colNum = rsmd.getColumnCount();
				for (int i = 1; i <= colNum; i++) {
					System.out.println(rsmd.getColumnName(i));
				}
			}
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
