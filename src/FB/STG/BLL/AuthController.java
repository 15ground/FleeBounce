package FB.STG.BLL;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import FB.STG.DAO.DAOUser;
import FB.STG.Entity.User;

@WebServlet(urlPatterns = "/AuthController")
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			System.out.print("action: " + action);
			switch (action) {
			case "sigin":
				SigIn(request, response);
				break;
			case "sigup":
				SigUp(request, response);
				break;
			case "logout":
				LogOut(request, response);
				break;
			}
		}
	}

	public void SigIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		DAOUser dao = new DAOUser();
		User user = new User(username, password);
		boolean isAuth = dao.isLogin(user);
		HttpSession session = request.getSession();
		if (isAuth) {
			User currentUser = DAOUser.findUserByUserName(username);
			session.setAttribute("username", username);
			session.setAttribute("currentUser", currentUser);
			System.out.println(currentUser.getEmail());
			String redirect = request.getParameter("redirect");
			if (redirect != null && !redirect.isEmpty()) {
				response.sendRedirect(redirect);
			}
			request.getRequestDispatcher("/products").forward(request, response);
		} else {
			request.setAttribute("message", "Tài khoản hoặc mật khẩu không chính xác!");
			request.getRequestDispatcher("views/sigin.jsp").forward(request, response);
		}
	}

	public void SigUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repeat_password = request.getParameter("repeat-password");
		if (email.isEmpty() || username.isEmpty() || password.isEmpty() || repeat_password.isEmpty()) {
			request.setAttribute("message", "Tài khoản hoặc mật khẩu không được để trống!");
			request.getRequestDispatcher("views/sigup.jsp").forward(request, response);
		}
		if (password.equals(repeat_password) == false) {
			request.setAttribute("message", "Nhập lại mật khẩu không chính xác!");
			request.getRequestDispatcher("views/sigup.jsp").forward(request, response);
		} else {
			User user = new User(email, username, password, "admin");
			boolean isExist = DAOUser.isExistUser(username);
			System.out.println("isExist" + isExist);
			if (isExist) {
				request.setAttribute("message", "Tài khoản đã được đăng ký, vui lòng đăng ký tài khoản khác!");
				request.getRequestDispatcher("views/sigup.jsp").forward(request, response);
			} else {
				boolean isCreate = DAOUser.createUser(user);
				System.out.println("isCreate" + isCreate);
				if (isCreate) {
					System.out.println("Success" + isCreate);
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					session.setAttribute("password", password);
					request.setAttribute("message", "Đăng ký thành công!");
					sendMail(request, response);
					request.getRequestDispatcher("views/sigup.jsp").forward(request, response);

				} else {
					System.out.println("Failed!" + isCreate);
					request.setAttribute("message", "Lỗi đăng ký!");
					request.getRequestDispatcher("views/sigup.jsp").forward(request, response);
				}
			}
		}
	}

	public void sendMail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String userName = "lvhungbk@gmail.com";
		final String passWord = "01653481049";
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		javax.mail.Session session = javax.mail.Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, passWord);
			}
		});
		String emailTo = request.getParameter("email");
		String Subject = "Xác nhận đăng ký!";
		String Content = "Từ FleeBounce, chúc mừng bạn đã đăng ký thành công tài khoản!";
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			message.setSubject(Subject);
			message.setText(Content);
			Transport.send(message);
			System.out.println("Send mail successfully!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void LogOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("Logout: ");
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.removeAttribute("currentUser");
		response.sendRedirect("views/sigin.jsp");
	}
}
