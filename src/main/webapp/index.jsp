<%@page import="cn.tech.tutorial.connection.DBConnection"%>
<%@page import="cn.tech.tutorial.model.User"%>
<%@page import="cn.tech.tutorial.dao.ProductDao"%>
<%@page import="cn.tech.tutorial.model.Product"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}
ProductDao pd = new ProductDao(DBConnection.getConnection());
List<Product> products = pd.getAllProducts();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Wel-Come to Shopping Cart</title>
<%@include file="include/head.jsp"%>
</head>
<body>
	<%@include file="include/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100">
					<img class="card-img-top" src="product-images/<%=p.getImage()%>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName()%></h5>
						<h6 class="price">
							Price: $<%=p.getPrice()%></h6>
						<h6 class="category">
							Category: s<%=p.getCategory()%></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a class="btn btn-dark" href="add-to-cart?id=<%=p.getId()%>">Add
								to Cart</a> <a class="btn btn-primary"
								href="order-now?quantity=1&id=<%=p.getId()%>">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			}
			%>

		</div>
	</div>
	<%@include file="include/footer.jsp"%>
</body>
</html>