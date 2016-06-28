<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/style.css"/>
<title>About Assingment 2</title>
</head>
<body>
<div id="wrapper">
	<header>
		<h2>${initParam.course}</h2>
		<h4>${initParam.author}</h4>
		<div id="langOption">
			<form action="i18n">
				<label style="display: inline;">Choose language:</label>
				<input type="submit" value="ENG" name="enBtn" class="buttonLink"/>
				<input type="submit" value="DUT" name="nlBtn" class="buttonLink"/>
			</form>
		</div>
	</header>
	<div id="main">
		<section>
			<h3 class="paragraphHeader">${requestScope.Intro_Head }</h3>
			<p>${requestScope.Intro_Body_1 }</p>
			<p>${requestScope.Intro_Body_2 }</p>
			<p>${requestScope.Intro_Body_Author_Details }</p>
		</section>
		<section>
			<h3 class="paragraphHeader">${requestScope.How_To_Use_App_Head }</h3>
			<p>${requestScope.How_To_Use_App_Body_1 }</p>
			<p>${requestScope.How_To_Use_App_Body_2 }</p>
			<p>${requestScope.How_To_Use_App_Body_3 }</p>
		</section>
		<section>
			<h3 class="paragraphHeader">${requestScope.How_To_Use_DB_Head }</h3>
			<p>${requestScope.How_To_Use_DB_Body_1 }</p>
			<p>${requestScope.How_To_Use_DB_Body_2 }</p>
			<p>${requestScope.How_To_Use_DB_Body_3 }</p>
			<p>${requestScope.How_To_Use_DB_Body_4 }</p>
			<p>${requestScope.How_To_Use_DB_Body_5 }</p>
			<p>${requestScope.How_To_Use_DB_Body_6 }</p>
			<p>${requestScope.How_To_Use_DB_Body_7 }</p>
		</section>
		<br/>
		<br/>
		<form action="assignment01">
			<input type="submit" value="Home" name="homeBtn" class="button"/>
		</form>
	</div>
	<footer>&copy; ${initParam.footer}</footer>
</div>
</body>
</html>