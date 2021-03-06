package FB.STG.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import FB.STG.Entity.Categories;
import FB.STG.Until.DBHelper;

public class DAOCategories {
	private static Connection conn = DBHelper.getConnection();

	public static boolean create(Categories product) {
		try {
			Connection conn = DBHelper.getConnection();
			String sql = "INSERT INTO categories (name, description) VALUES(?, ?)";
			if (conn != null) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, product.getName());
				pst.setString(2, product.getDescription());
				int result = pst.executeUpdate();
				if (result > 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static List<Categories> findAll() {
		List<Categories> list = new ArrayList<Categories>();
		try {
			String sql = "select * from categories";
			if (conn != null) {
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet resultSet = pst.executeQuery();
				while (resultSet.next()) {
					Categories categories = new Categories();
					categories.setId(resultSet.getInt("id"));
					categories.setName(resultSet.getString("name"));
					categories.setDescription(resultSet.getString("description"));
					list.add(categories);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static List<Categories> findProductById(int id) {
		List<Categories> list = new ArrayList<Categories>();
		try {
			String sql = "select * from categories where id= ?";
			if (conn != null) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, id);
				ResultSet resultSet = pst.executeQuery();
				while (resultSet.next()) {
					Categories categories = new Categories();
					categories.setId(resultSet.getInt("id"));
					categories.setName(resultSet.getString("name"));
					categories.setDescription(resultSet.getString("description"));
					list.add(categories);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static Categories findCategoryById(int id) {
		Categories categories = new Categories();
		try {
			String sql = "select * from categories where id= ?";
			if (conn != null) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, id);
				ResultSet resultSet = pst.executeQuery();
				while (resultSet.next()) {
					categories.setId(resultSet.getInt("id"));
					categories.setName(resultSet.getString("name"));
					categories.setDescription(resultSet.getString("description"));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return categories;
	}

	public static boolean delete(int id) {
		try {
			String sql = "delete from categories where id= ?";
			if (conn != null) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, id);
				int result = pst.executeUpdate();
				if (result > 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean update(Categories categories) {
		try {
			String sql = "UPDATE categories set name = ?, description = ? where id= ?";
			if (conn != null) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, categories.getName());
				pst.setString(2, categories.getDescription());
				pst.setInt(3, categories.getId());
				int result = pst.executeUpdate();
				if (result > 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
