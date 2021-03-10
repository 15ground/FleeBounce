package FB.STG.BLL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import FB.STG.DAO.DAOCategories;
import FB.STG.DAO.DAOProducts;
import FB.STG.Entity.Categories;
import FB.STG.Entity.Products;

import javax.servlet.annotation.MultipartConfig;

/**
 * Servlet implementation class ProController
 */
@WebServlet(urlPatterns = { "/home", "/products", "/danhmuc", "/ProController" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 50)
public class ProController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String path = request.getServletPath();
		try {
			List<Products> listPro = new ArrayList<>();
			List<Categories> catPro = new ArrayList<Categories>();
			String id = request.getParameter("id");
			if (path.contains("danhmuc")) {
				listPro = DAOProducts.findProductByCategory(Integer.parseInt(id));
				request.setAttribute("listPro", listPro);
				catPro = DAOCategories.findAll();
				request.setAttribute("catPro", catPro);
				request.getRequestDispatcher("views/products.jsp").forward(request, response);
			}
			// load chi tiet san pham
			if (id != null && !id.isEmpty()) {
				// load chi tiet san pham
				int idPro = Integer.parseInt(id);
				// lay chiet san phan du vao id cua products
				listPro = DAOProducts.findAll(1, 4);
				request.setAttribute("listPro", listPro);
				Products proDetails = DAOProducts.findProductsByID(idPro);
				request.setAttribute("proDetails", proDetails);
				request.getRequestDispatcher("views/prodetails.jsp").forward(request, response);
			}
			// load tat cac san pham
			String page = request.getParameter("page");
			int ItemOfPage = 1;
			if (page != null) {
				ItemOfPage = Integer.parseInt(page);
				listPro = DAOProducts.findAll(ItemOfPage, 20);

			} else {
				listPro = DAOProducts.findAll(1, 20);
			}
			catPro = DAOCategories.findAll();
			request.setAttribute("listPro", listPro);
			request.setAttribute("active", ItemOfPage);
			request.setAttribute("catPro", catPro);
			request.getRequestDispatcher("views/products.jsp").forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
