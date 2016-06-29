<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/style.css"/>
</head>
<body>
<jsp:include page="/i18n"/>
<div id="wrapper">
	<header>
		<h2>${initParam.course}</h2>
		<h4>${initParam.author}</h4>
		<div id="langOption">
			<form action="i18n">
				<input type="hidden" name="langPreference" value="langPreference"/>
				<label style="display: inline;">${requestScope.Choose_Language }:</label>
				<input type="submit" value="ENG" name="enBtn" class="buttonLink"/>
				<input type="submit" value="DUT" name="nlBtn" class="buttonLink"/>
				<input type="submit" value="FRA" name="frBtn" class="buttonLink"/>
			</form>
		</div>
	</header>
	<div id="main">
		<p>
		<span class="spanHead">${requestScope.Summary }</span><br/>
		<br/>
		<section>${sessionScope.SQLSummary }</section>
		<form action="assignment01">
			<input type="submit" value="Home" name="homeBtn" class="button"/>
			<input type="submit" value="Back" name="backBtn" class="button"/>
			<input type="submit" value="About" name="aboutBtn" class="button"/>
		</form>
		<br/>
		</p>
	</div>
	<footer>&copy; ${initParam.footer}</footer>
</div>
</body>
</html>