<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css"/>
</head>
<body>
<div id="wrapper">
	<header>
		<h2>${initParam.course}</h2>
		<h4>${initParam.author}</h4>
	</header>
	<div id="main">
		<p>
		<span id="error">ERROR!!</span><br/><br/>
		${requestScope.errorMsg }
		<br/>
		<form action="assignment01"><input type="submit" value="Back to Home" name="backBtn" class="button"/></form>
		</p>
	</div>
	<footer>&copy; ${initParam.footer}</footer>
</div>
</body>
</html>
