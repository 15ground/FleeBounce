package FB.STG.BLL;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import FB.STG.DAO.DAOProducts;
import FB.STG.DAO.DAOCategories;
import FB.STG.Entity.Products;
import FB.STG.Entity.Categories;

/**
 * Servlet implementation class CarAdminController
 */
@WebServlet(name = "product", urlPatterns = { "/admin/product", "/admin/product/*" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 50)
public class ProAdminController extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String method = request.getMethod();
		if (method.equals("GET")) {
			String id = request.getParameter("id");
			HttpSession session = request.getSession();
			if (id != null) {
				Products currentProd = DAOProducts.findProductsByID(Integer.parseInt(id));
				List<Categories> listCat = new ArrayList<Categories>();
				listCat = DAOCategories.findAll();
//				Set dữ liệu vào trong biến để truyền tới views
				request.setAttribute("listCat", listCat);
				listCat = DAOCategories.findAll();
				request.setAttribute("currentProd", currentProd);
				request.setAttribute("title", "Sửa sản phẩm");
				request.setAttribute("tabSelected", "sanpham");
				session.setAttribute("views", "includes/editProducts.jsp");
				request.getRequestDispatcher("../admin/dashboard.jsp").forward(request, response);
				return;
			}
			List<Products> listProd = new ArrayList<Products>();
			List<Categories> listCat = new ArrayList<Categories>();
			listProd = DAOProducts.findAll(1, 20);
			listCat = DAOCategories.findAll();
			request.setAttribute("listCat", listCat);
			request.setAttribute("listProd", listProd);
			request.setAttribute("title", "Danh sách sản phẩm");
			request.setAttribute("tabSelected", "sanpham");
			session.setAttribute("views", "includes/listproducts.jsp");
			request.getRequestDispatcher("../admin/dashboard.jsp").forward(request, response);
			return;
		} else {
			String action = request.getParameter("action");
			// handel sự kiện xảy ra khi user đã click vào form có thể là thêm , xoá , xửa
			if (action != null) {
				switch (action) {
				case "Add":
					AddProduct(request, response);
					break;
				case "Delete":
					DeleteProduct(request, response);
					break;
				case "Update":
					EditProduct(request, response);
					break;
				}
			}

		}

	}

	public void AddProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("title");
		int quantity = 10;
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String path = System.getProperty("user.dir") + "/upload";
		String cat = request.getParameter("category");
		String images = null;
		try {
			File dir = new File(request.getServletContext().getRealPath("/upload"));
			if (!dir.exists()) { // tạo nếu chưa tồn tại
				dir.mkdirs();
			}
			Part mainImage = request.getPart("image");
			if (mainImage != null) {
				File photoFile = new File(dir, mainImage.getSubmittedFileName());
				mainImage.write(photoFile.getAbsolutePath());
				images = photoFile.getName();
			}
		} catch (Exception e) {
		}
		/*
		 * String title, String description, String images, String contact, String url,
		 * double price, int categoryId
		 */
		Products pro = new Products();
		pro.setName(name);
		pro.setDescription(description);
		pro.setImages(images);
		pro.setQuantity(quantity);
		pro.setPrice(Double.parseDouble(price));
		pro.setCategoryID(Integer.parseInt(cat));
		boolean result = DAOProducts.create(pro);
		HttpSession session = request.getSession();
		session.setAttribute("message", "Thêm thành công");
		response.sendRedirect(request.getContextPath() + "/admin/category");
	}

	public void EditProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("title");
		int quantity = 10;
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String categoryID = request.getParameter("categoryCar");
		String path = System.getProperty("user.dir") + "/upload";

		String images = null;
		try {
			File dir = new File(request.getServletContext().getRealPath("/upload"));
			if (!dir.exists()) { // tạo nếu chưa tồn tại
				dir.mkdirs();
			}
			Part mainImage = request.getPart("image");
			if (mainImage != null) {
				File photoFile = new File(dir, mainImage.getSubmittedFileName());
				mainImage.write(photoFile.getAbsolutePath());
				images = photoFile.getName();
			}
		} catch (Exception e) {
		}

//        public Car(int id, String image, String title, String url, Double price, String description, String contact, int categoryId)
		Products car = new Products();
		car.setId(Integer.parseInt(id));
		car.setName(name);
		car.setDescription(description);
		car.setImages(images);
		car.setQuantity(quantity);
		car.setPrice(Double.parseDouble(price));
		car.setCategoryID(Integer.parseInt(categoryID));
		boolean result = DAOProducts.update(car);
		HttpSession session = request.getSession();
		if (result) {
			session.setAttribute("message", "Sửa thành công!");
		} else {
			session.setAttribute("message", "Sửa không thành công!");
		}
		response.sendRedirect(request.getContextPath() + "/admin/product");
	}

	public void DeleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			boolean result = DAOProducts.delete(Integer.parseInt(id));
			HttpSession session = request.getSession();
			if (result) {
				session.setAttribute("message", "Xoá thành công!");
			} else {
				session.setAttribute("message", "Xóa không thành công!");
			}
			response.sendRedirect(request.getContextPath() + "/admin/product");
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}