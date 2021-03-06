<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<ul class="nav nav-pills" role="tablist">
	<li class="nav-item"><a class="nav-link" href="../admin/category">Danh
			sách</a></li>
	<li class="nav-item "><a class="nav-link active"
		data-toggle="pill" href="#menu1">Sửa sản phẩm</a></li>
</ul>

<form action="product" method="post" enctype='multipart/form-data'>
	<div class="form-group">
		<label for="name">Tên sản phẩm:</label> <input name="title"
			type="text" value="${currentProd.getName()}" required
			class="form-control" placeholder="Tên sản phẩm..." id="email">
	</div>
	<div class="form-group">
		<label for="name">Gía sản phẩm:</label> <input name="price"
			type="number" value="${currentProd.getPrice()}" required
			class="form-control" placeholder="Gía sản phẩm..." id="email">
	</div>
	<div class="form-group">
		<label for="name">Danh mục :</label> <select name="categoryCar"
			class="custom-select" required>
			<option>Chọn danh mục sản phẩm</option>
			<c:forEach items="${listCat}" var="cat">
				<option value="${cat.getId()}"
					${cat.getId() == currentProd.getCategoryID() ? 'selected="selected"' : ''}>${cat.getName()}</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group">
		<label for="pwd">Mô tả</label>
		<textarea name="description" id="description" class="form-control"
			placeholder="mô tả danh mục">
            <c:out value="${currentProd.getDescription()}" />
        </textarea>
	</div>
	<label for="pwd">Hình ảnh</label>
	<c:if test="${not empty currentProd.getImages()}">
		<img
			src="${pageContext.request.contextPath}/upload/${currentProd.getImages()}"
			width="200" height="100" />
	</c:if>
	<input type="file" name="image" class="form-control"
		placeholder="Mô tả sản phẩm...">
	<input type="hidden" name="id" value="${currentProd.getId()}" />
	<button type="submit" name="action" value="Update"
		class="btn btn-success">Cập nhật</button>
</form>
<script>
	CKEDITOR.replace('description');
	CKEDITOR.replace('contact');
</script>