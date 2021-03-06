<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dashboard</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin.css">
<script src="https://cdn.ckeditor.com/4.15.1/standard/ckeditor.js"></script>
</head>
<body>
	<c:import url="adminheader.jsp"></c:import>
	<section>
		<div class="small-container">
			<div class="row">
				<div class="col col-md-2">
					<ul class="list-group">
						<li
							class="list-group-item ${tabSelected == 'danhmuc' ? 'active-tab' : ''}"><a
							href="${pageContext.request.contextPath}/admin/category">
								Danh mục</a></li>
						<li
							class="list-group-item ${tabSelected == 'sanpham' ? 'active-tab' : ''}"><a
							href="${pageContext.request.contextPath}/admin/product">Sản
								phẩm</a></li>
					</ul>
				</div>
				<div class="col col-md-8">
					<c:if test="${sessionScope.message != null}">
						<div class="alert alert-success alert-dismissible" id="myAlert">
							<button type="button" class="close">&times;</button>
							<c:out value="${sessionScope.message}" />
						</div>
					</c:if>
					<div class="tab-content" id="v-pills-tabContent">
						<div class="tab-pane fade show active" id="v-pills-home"
							role="tabpanel" aria-labelledby="v-pills-home-tab">
							<c:choose>
								<c:when test="${sessionScope.views != null}">
									<c:import url="${sessionScope.views}" />
								</c:when>
								<c:otherwise>
									<c:import url="includes/categories.jsp" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<c:import url="adminfooter.jsp"></c:import>
	<script src="https://cdn.ckeditor.com/4.15.1/standard/ckeditor.js"></script>
	<script>
		$(document).ready(function() {
			$(".close").click(function() {
				$("#myAlert").alert("close");
				<c:remove var="message"/>
			});
		});
	</script>
</body>
</html>