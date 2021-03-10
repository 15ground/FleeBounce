package FB.STG.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import FB.STG.Entity.Products;
import FB.STG.Until.DBHelper;

public class DAOProducts {
	public static Connection conn = DBHelper.getConnection();

	public static ArrayList<Products> findAll(int start, int total) {
		ArrayList<Products> list = new ArrayList<Products>();
		try {
			String sql = "Select * From products limit ?, ?";
			if (conn != null) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, start - 1);
				pst.setInt(2, total);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					Products pro = new Products();
					pro.setId(rs.getInt("id"));
					pro.setName(rs.getString("name"));
					pro.setImages(rs.getString("images"));
					pro.setQuantity(rs.getInt("quantity"));
					pro.setPrice(rs.getDouble("price"));
					pro.setDescription(rs.getString("description"));
					pro.setCategoryID(rs.getInt("categoryID"));
					list.add(pro);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<Products> findAllDesc(int start, int total) {
		ArrayList<Products> list = new ArrayList<Products>();
		try {
			String sql = "Select * From products order by id desc limit ?, ?;";
			if (conn != null) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, start - 1);
				pst.setInt(2, total);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					Products pro = new Products();
					pro.setId(rs.getInt("id"));
					pro.setName(rs.getString("name"));
					pro.setImages(rs.getString("images"));
					pro.setQuantity(rs.getInt("quantity"));
					pro.setPrice(rs.getDouble("price"));
					pro.setDescription(rs.getString("description"));
					pro.setCategoryID(rs.getInt("categoryID"));
					list.add(pro);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static boolean create(Products product) {
		try {
			String sql = "INSERT INTO products (name, images, quantity, price, description, categoryID)"
					+ " VALUES(?, ?, ?, ?, ?, ?)";
			if (conn != null) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, product.getName());
				pst.setString(2, product.getImages());
				pst.setInt(3, product.getQuantity());
				pst.setDouble(4, product.getPrice());
				pst.setString(5, product.getDescription());
				pst.setInt(6, product.getCategoryID());
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

	public static Products findProductsByID(int id) {
		Products pro = new Products();
		try {
			String sql = "Select * From products Where id = ?";
			if (conn != null) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					pro.setId(rs.getInt("id"));
					pro.setName(rs.getString("name"));
					pro.setImages(rs.getString("images"));
					pro.setQuantity(rs.getInt("quantity"));
					pro.setPrice(rs.getDouble("price"));
					pro.setDescription(rs.getString("description"));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pro;
	}

	public static List<Products> findProductByCategory(int catID) {
		List<Products> list = new ArrayList<Products>();
		try {
			String sql = "select * from products inner join categories on products.categoryID = categories.id where products.categoryID = ?";
			if (conn != null) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, catID);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					Products pro = new Products();
					System.out.println("info" + rs.getInt("products.id"));
					pro.setId(rs.getInt("id"));
					pro.setName(rs.getString("name"));
					pro.setImages(rs.getString("images"));
					pro.setQuantity(rs.getInt("quantity"));
					pro.setPrice(rs.getDouble("price"));
					pro.setDescription(rs.getString("description"));
					list.add(pro);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static boolean delete(int id) {
		try {
			String sql = "delete from products where id= ?";
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

	public static boolean update(Products car) {
		try {
			String sql = "update products set name = ?, images = ? , quantity = ?, "
					+ "price = ?, description = ?, categoryID = ? where id = ?";
			if (conn != null) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, car.getName());
				pst.setString(2, car.getImages());
				pst.setInt(3, car.getQuantity());
				pst.setDouble(4, car.getPrice());
				pst.setString(5, car.getDescription());
				pst.setInt(6, car.getCategoryID());
				pst.setInt(7, car.getId());
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
