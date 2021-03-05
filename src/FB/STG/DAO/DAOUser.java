package FB.STG.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import FB.STG.Entity.User;
import FB.STG.Until.DBHelper;

public class DAOUser {
	private static Connection conn = DBHelper.getConnection();
// Kiểm tra đăng nhập
	public boolean isLogin(User user) {
		try {
			String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
			if (conn != null) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, user.getUsername());
				pst.setString(2, user.getPassword());
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
// Tạo mới Users
	public static boolean createUser(User user) {
		boolean check = false;
		try {
			String sql = "INSERT INTO users (email, username, password, role) VALUES(?,?,?,?)";
			if (conn != null) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, user.getEmail());
				pst.setString(2, user.getUsername());
				pst.setString(3, user.getPassword());
				pst.setString(4, user.getRole());
				int rs = pst.executeUpdate();
				if (rs > 0) {
					check = true;
				} else {
					check = false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return check;
	}
// Kiểm tra Users đã tồn tại hay chưa
	public static boolean isExistUser(String email) {
		boolean check = false;
		try {
			String sql = "SELECT * FROM users WHERE email = ?";
			if (conn != null) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, email);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					check = true;
				} else {
					check = false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return check;
	}

	public static User findUserByUserName(String email) {
		User user = new User();
		try {
			String sql = "SELECT * FROM users where username= ?";
			if (conn != null) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, email);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					user.setId(rs.getInt("id"));
					user.setEmail(rs.getString("email"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setRole(rs.getString("role"));
				}
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}
}
