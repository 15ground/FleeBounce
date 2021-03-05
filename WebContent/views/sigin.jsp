<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%        
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", -1);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="../css/style.css">
</head>
<body>
	<div class="login">
		<form action="${pageContext.request.contextPath}/AuthController"
			method="POST">
			<i class="fa fa-empire"></i>
			<h2>ĐĂNG NHẬP</h2>
			<div class="group">
				<input type="text" name="username" placeholder="Tài khoản..." /> <i
					class="fa fa-user"> </i>
			</div>
			<div class="group">
				<input type="password" name="password" placeholder="Mật khẩu..." />
				<i class="fa fa-lock"> </i>
			</div>
			<p class="error">${message}</p>
			<%
			if (request.getParameter("redirect") != null) {
				String url = request.getParameter("redirect");
			%>
			<input type="hidden" id="redirect" name="redirect" value="<%=url%>" />
			<%
			}
			%>

			<input type="hidden" id="redirect" name="redirect" value="" />
			<button name="action" value="sigin">Đăng nhập</button>
			<p class="fs">
				Bạn đã quên <a href="#">Tài khoản</a> / <a href="#">Mật khẩu</a> ?
			</p>
			<p class="ss">
				Bạn chưa có tài khoản? <a href="sigup.jsp">Đăng ký</a>
			</p>
		</form>
	</div>
</body>
</html>