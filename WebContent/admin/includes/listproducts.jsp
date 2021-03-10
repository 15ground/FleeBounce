
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<ul class="nav nav-pills" role="tablist">
	<li class="nav-item"><a class="nav-link active" data-toggle="pill"
		href="#home">Danh sách</a></li>
	<li class="nav-item"><a class="nav-link" data-toggle="pill"
		href="#menu1">Thêm sản phẩm</a></li>
</ul>
<div class="tab-content">
	<div id="home" class="container tab-pane active">
		<br>
		<div class="table-responsive">
			<table class="table ">
				<thead class="thead-dark">
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Name</th>
						<th scope="col">Images</th>
						<th scope="col">Price</th>
						<th scope="col">Description</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listProd}" var="prod">
						<tr>
							<td><c:out value="${prod.getId()}" /></td>
							<td>${prod.getName()}</td>
							<td><c:if test="${not empty prod.getImages()}">
									<c:choose>
										<c:when test="${fn:contains(prod.getImages(), 'http')}">
											<img src="${prod.getImages()}" width="200" height="100" />
										</c:when>
										<c:otherwise>
											<img
												src="${pageContext.request.contextPath}/upload/${prod.getImages()}"
												width="200" height="100" />
										</c:otherwise>
									</c:choose>
								</c:if></td>
							<td><fmt:formatNumber type="number" maxFractionDigits="1"
									value="${prod.getPrice()}" /></td>
							<td class="short-text">${prod.getDescription()}</td>
							<td>
								<form action="product" method="post">
									<input type="hidden" name="id" value="${prod.getId()}" /> <a
										href="?id=${prod.getId()}">Sửa</a>
									<button type="submit" name="action" value="Delete"
										class="btn btn-danger">X</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div id="menu1" class="container tab-pane fade">
		<br>
		<form action="product" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="name">Tên sản phẩm :</label> <input name="title"
					type="text" required class="form-control"
					placeholder="Tên sản phẩm..." id="email">
			</div>
			<div class="form-group">
				<label for="name">Giá sản phẩm :</label> <input name="price"
					type="number" required class="form-control"
					placeholder="Giá sản phẩm..." id="email">
			</div>
			<div class="form-group">
				<label for="name">Danh mục :</label> <select name="category"
					class="custom-select" required>
					<option selected>Chọn danh mục sản phẩm</option>
					<c:forEach items="${listCat}" var="cat">
						<option value="${cat.getId()}">${cat.getName()}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="pwd">Mô tả</label>
				<textarea name="description" class="form-control"
					placeholder="Mô tả sản phẩm..." id="pwd"></textarea>
			</div>
			<label for="pwd">Hình ảnh</label> <input type="file" name="image" t
				class="form-control" placeholder="Mô tả sản phẩm" id="pwd">
			<button type="submit" name="action" value="Add"
				class="btn btn-success btn-submit">Thêm</button>
		</form>
	</div>
</div>
<script>
	CKEDITOR.replace('description');
	CKEDITOR.replace('contact');
</script>
