package FB.STG.BLL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import FB.STG.DAO.DAOCategories;
import FB.STG.Entity.Categories;

/**
 *
 * @author tomnyson
 */
@WebServlet(name = "category", urlPatterns = { "/admin/category", "/admin/category/*" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 50)
public class CategoriesController extends HttpServlet {

	/*
	 * category list danh sach them category xoa category update category
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// chia hai trường hợp get/post
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String method = request.getMethod();
		if (method.equals("GET")) {
			String id = request.getParameter("id");
			HttpSession session = request.getSession();
			if (id != null && !id.isEmpty()) {
				Categories currentCat = DAOCategories.findCategoryById(Integer.parseInt(id));
				request.setAttribute("currentCat", currentCat);
				session.setAttribute("views", "includes/editCategories.jsp");
				request.getRequestDispatcher("../admin/dashboard.jsp").forward(request, response);
				return;
			}
			List<Categories> listCat = new ArrayList<Categories>();
			listCat = DAOCategories.findAll();
			request.setAttribute("listCat", listCat);
			request.setAttribute("title", "Dashboard");
			request.setAttribute("tabSelected", "danhmuc");
			session.setAttribute("views", "includes/categories.jsp");
			request.getRequestDispatcher("../admin/dashboard.jsp").forward(request, response);
			return;
		} else {
			String action = request.getParameter("action");
			// handel sự kiện xảy ra khi user đã click vào form có thể là thêm , xoá , xửa
			if (action != null) {
				switch (action) {
				case "Add":
					AddCategory(request, response);
					break;
				case "Delete":
					DeleteCategory(request, response);
					break;
				case "Update":
					EditCategory(request, response);
					break;
				}
			}
		}
	}

	public void AddCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Categories cat = new Categories(name, description);
		boolean result = DAOCategories.create(cat);
		HttpSession session = request.getSession();
		if (result) {
			session.setAttribute("message", "Thêm thành công");
		} else {
			session.setAttribute("message", "Thêm không thành công");
		}
		response.sendRedirect(request.getContextPath() + "/admin/category");
	}

	public void EditCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Categories cat = new Categories(Integer.parseInt(id), name, description);
		boolean result = DAOCategories.update(cat);
		HttpSession session = request.getSession();
		if (result) {
			session.setAttribute("message", "Cập nhật thành công!");
		} else {
			session.setAttribute("message", "Cập nhật không thành công!");
		}

		response.sendRedirect(request.getContextPath() + "/admin/category");
	}

	public void DeleteCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			String id = request.getParameter("id");
			boolean result = DAOCategories.delete(Integer.parseInt(id));
			HttpSession session = request.getSession();
			if (result) {
				session.setAttribute("message", "Xoá thành công!");
			} else {
				session.setAttribute("message", "Xoá không thành công!");
			}
			response.sendRedirect(request.getContextPath() + "/admin/category");
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
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