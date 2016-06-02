<%@ page import="java.util.List, a00973641.data.Member" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assignment 01</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css">
</head>
<body>

<div id="wrapper">
	<header>
		<h2>${initParam.course}</h2>
		<h4>${initParam.author}</h4>
	</header>
	<div id="main">
		<form action="/member.jsp" name="memberForm" method="post">
			<table>
				<tr>
					<th>Member ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Address</th>
					<th>City</th>
					<th>Code</th>
					<th>Country</th>
					<th>Phone Number</th>
					<th>Email</th>
				</tr>

				<tr>
					<td>Autofill</td>
					<td><input type="text" name="firstName"/></td>
					<td><input type="text" name="lastName"/></td>
					<td><input type="text" name="address"/></td>
					<td><input type="text" name="city"/></td>
					<td><input type="text" name="code"/></td>
					<td><input type="text" name="country"/></td>
					<td><input type="text" name="phoneNumber"/></td>
					<td><input type="text" name="email"/></td>
				</tr>
			</table>
		</form>
	</div>
	<footer>&copy; ${initParam.footer}</footer>
</div>
</body>
</html>