package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://127.0.0.1:3306/doublez"
			+ "?useUnicode=true&characterEncoding=utf8";
	private static final String DBUSER = "root";
	private static final String DBPASSWORD = "admin";
	private Connection conn;

	public DBConn() throws Exception {
		try {
			Class.forName(DBDRIVER); 
			this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		} catch (Exception e) {
			throw e;
		}
	}
	public Connection getConnection() {
		return this.conn;
	}
	public void close() throws Exception {
		if (this.conn != null) {		
			try {					
				this.conn.close();		
			} catch (Exception e) {
				throw e;
			}
		}
	}

}
