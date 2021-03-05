package FB.STG.Until;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
	private static final String dbURL = "jdbc:mysql://localhost:3306/fleebounce";
	private static final String userName = "admin";
	private static final String password = "admin";
	public static Connection conn = null;

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, userName, password);
			System.out.println("Connect successfully!");
		} catch (Exception e) {
			System.out.println("Connect failed!");
			e.printStackTrace();
		}
		return conn;
	}
}
