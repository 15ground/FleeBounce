<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Chi tiết sản phẩm</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/products.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body>
	<c:import url="headerdetails.jsp"></c:import>
	<section>
		<!-- Single Page Detail -->
		<div class="small-container single-product">
			<div class="content">
				<div class="text-content">
					<img src="${proDetails.getImages()}" />
					<div class="small-container-img">
						<div class="small-img">
							<img src="${proDetails.getImages()}" width="100%">
						</div>
						<div class="small-img">
							<img src="${proDetails.getImages()}" width="100%">
						</div>
						<div class="small-img">
							<img src="${proDetails.getImages()}" width="100%">
						</div>
						<div class="small-img">
							<img src="${proDetails.getImages()}" width="100%">
						</div>
					</div>
				</div>
				<div class="text-content">
					<p>Sản phẩm / Chi tiết</p>
					<h1>${proDetails.getName()}</h1>
					<h4>
						<fmt:formatNumber type="number" maxFractionDigits="1"
							value="${proDetails.getPrice()}" />
					</h4>
					<select>
						<option>Chọn size</option>
						<option>S</option>
						<option>M</option>
						<option>L</option>
						<option>XL</option>
					</select> <input type="number" value="1" />
					<button class="btn-content">Mua ngay</button>
					<h3>
						Chi tiết sản phẩm <i class="fa fa-indent"></i>
					</h3>
					<br />
					<p>Sản phẩm mới đầy sự mới mẻ và sáng tạo, chất liệu tốt giúp
						bạn thoải mái nhất</p>
				</div>
			</div>
		</div>
		<!-- Products -->
		<div class="small-container">
			<div class="content" id="detailProducts">
				<c:forEach items="${listPro}" var="pro">
					<div class="pro-content">
						<a href="products?id=${pro.getId()}"> <img
							src="${pro.getImages()}" />
						</a>
						<h4>${pro.getName()}</h4>
						<div class="rating">
							<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
								class="fa fa-star"></i> <i class="fa fa-star"></i> <i
								class="fa fa-star-half-o"></i>
						</div>
						<p>
							<fmt:formatNumber type="number" maxFractionDigits="1"
								value="${pro.getPrice()}" />
						</p>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<c:import url="footer.jsp"></c:import>
</body>
</html>
