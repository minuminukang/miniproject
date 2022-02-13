package common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {
	
	public static final int ERROR_CODE_USER_DUPLE = -100;
	public static final int ERROR_CODE_SCHEDULE_DUPLE = -200;
	public static final int ERROR_CODE_RESERVATION_DUPLE = -300;
	public static final int ERROR_CODE_PAYMENT_DUPLE = -400;
	
	// 무조건 쓰는 구간
	public static String driverClass 	= null;
	public static String url 			= null;
	public static String user 			= null;
	public static String password 		= null;

	static {
		Properties prop = new Properties();
				
		try {
			FileReader fr = new FileReader("./resources/data-source.properties");
			FileReader fr2 = new FileReader("./resources/query.properties");
			prop.load(fr);
			prop.load(fr2);
			fr.close();
			fr2.close();

			driverClass = prop.getProperty("driverClass");
			url = prop.getProperty("url");
			user  = prop.getProperty("user");
			password = prop.getProperty("password");
			
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return conn;
	}
	//
	
	public static void close(Connection conn) {
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void commit(Connection conn) {
		try {
			if(conn != null)
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		try {
			if(conn != null)
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
