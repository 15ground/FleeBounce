<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/layout.css">
</head>
<body>
	<div class="login">
		<form action="${pageContext.request.contextPath}/AuthController"
			method="POST">
			<c:if test="${not empty message}">
				<div class="alert alert-danger">${message}</div>
			</c:if>
			<h2>ĐĂNG KÝ</h2>
			<div class="group">
				<input type="email" name="email" required autocomplete="off"
					placeholder="Địa chỉ email" /> <input type="text"
					autocomplete="off" name="username" placeholder="Tài khoản..." /> <input
					type="password" name="password" autocomplete="off"
					placeholder="Mật khẩu..." /> <input type="password"
					name="repeat-password" autocomplete="off"
					placeholder="Nhập lại mật khẩu..." />
			</div>
			<button name="action" value="sigup">Đăng ký</button>
			<a href="sigin.jsp" class="sig-up">Đăng nhập</a> / <a
				href="#" class="forgot-password">Quên mật khẩu</a>
		</form>
	</div>
</body>
</html>