<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="cn.tech.tutorial.model.User"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	response.sendRedirect("index.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart Login Page</title>
<%@include file="include/head.jsp"%>
</head>
<body>
	<%@include file="include/navbar.jsp"%>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
<!-- 				<form action="user-login" method="post"> -->
				<form action="index.jsp" method="post">
					<div class="form-group">
						<label>Email address</label> <input type="email"
							name="login-email" class="form-control" placeholder="Enter email">
					</div>
					<div class="form-group">
						<label>Password</label> <input type="password"
							name="login-password" class="form-control" placeholder="Password">
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<%@include file="include/footer.jsp"%>
</body>
</html>