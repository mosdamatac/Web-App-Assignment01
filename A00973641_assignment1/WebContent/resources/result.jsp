<%@ page import="a00973641.data.Member" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css"/>
<title>Result</title>
</head>
<body>
<div id="wrapper">
	<header>
		<h2>${initParam.course}</h2>
		<h4>${initParam.author}</h4>
	</header>
	<div id="main">
		<p>
			<span id="welcome">Successfully ${requestScope.operation } member:</span><br/>
			<table>
				<tr>
					<td>Member ID</td>
					<td>${requestScope.member.memberID }</td>
				</tr>
				<tr>
					<td>First Name</td>
					<td>${requestScope.member.firstName }</td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td>${requestScope.member.lastName }</td>
				</tr>
				<tr>
					<td>address</td>
					<td>${requestScope.member.address }</td>
				</tr>
				<tr>
					<td>City</td>
					<td>${requestScope.member.city }</td>
				</tr>
				<tr>
					<td>Code</td>
					<td>${requestScope.member.code }</td>
				</tr>
				<tr>
					<td>Country</td>
					<td>${requestScope.member.country }</td>
				</tr>
				<tr>
					<td>Phone Number</td>
					<td>${requestScope.member.phoneNumber }</td>
				</tr>
				<tr>
					<td>Email</td>
					<td>${requestScope.member.email }</td>
				</tr>
			</table>
			<br/>
			<form action="assignment01">
				<input type="submit" value="Continue" name="continue" class="button">
			</form>
			<br/>
		</p>
	</div>
	<footer>&copy; ${initParam.footer}</footer>
</div>
</body>
</html>