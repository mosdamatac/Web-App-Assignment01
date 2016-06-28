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
<jsp:include page="/i18n"/>
<div id="wrapper">
	<header>
		<h2>${initParam.course}</h2>
		<h4>${initParam.author}</h4>
		<div id="langOption">
			<form action="i18n">
				<label style="display: inline;">${requestScope.Choose_Language }:</label>
				<input type="submit" value="ENG" name="enBtn" class="buttonLink"/>
				<input type="submit" value="DUT" name="nlBtn" class="buttonLink"/>
				<input type="submit" value="FRA" name="frBtn" class="buttonLink"/>
			</form>
		</div>
	</header>
	<div id="main">
		<p>
		<span id="error">${requestScope.Error }!!</span><br/><br/>
		${requestScope.errorMsg }
		<br/>
		<form action="assignment01">
			<input type="submit" value="Back" name="backBtn" class="button"/>
			<input type="submit" value="Home" name="homeBtn" class="button"/>
		</form>
		</p>
	</div>
	<footer>&copy; ${initParam.footer}</footer>
</div>
</body>
</html>
