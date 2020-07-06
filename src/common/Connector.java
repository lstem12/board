package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
	private static final String url = "jdbc:oracle:thin:@localhost:1521/xe";
	private static final String user = "c##test";
	private static final String password = "test";
	private static final String driver_name = "oracle.jdbc.driver.OracleDriver";
	
	private static Connection conn;
	
	private static void init() {
			try {
				Class.forName(driver_name);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

	}
	public static Connection getConnection() {
		if(conn==null) {
			init();
			try {
				conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	public static void close() {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		conn = null;
	}
	public static void main(String[] args) throws SQLException {
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		String sql = "select * from board";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			System.out.println("출력");
		}
	}

}
