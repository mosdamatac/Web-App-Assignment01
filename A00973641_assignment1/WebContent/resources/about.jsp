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
			Choose language:
			<form action="i18n">
				<input type="submit" value="ENG" name="enBtn" class="buttonLink"/>
				<input type="submit" value="DUT" name="nlBtn" class="buttonLink"/>
			</form>
		</div>
	</header>
	<div id="main">
		<section>
			<h4>${requestScope.Intro_Head }</h4>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam nec nisl magna. Nulla feugiat, ante sed euismod convallis, quam enim facilisis massa, et rhoncus nibh tortor vitae elit. Nam sed convallis mauris. Phasellus condimentum rutrum turpis sit amet dapibus. Donec fermentum tincidunt porta. Quisque sagittis neque in mauris consequat, ac dictum eros tincidunt. Proin ut eros eros. Pellentesque interdum felis non tellus aliquam, in posuere magna finibus. Vivamus ac ante eu elit porta scelerisque. Sed a semper justo, nec eleifend nulla. Sed eleifend velit dolor, sed blandit purus vulputate at. Fusce efficitur, erat eu mollis porta, quam nisl auctor ante, sit amet hendrerit purus tellus eget enim. Vivamus non laoreet elit, et mollis ante.</p>
		</section>
		<section>
			<h4>${requestScope.How_To_Use_App_Head }</h4>
			<p>Ut tincidunt rhoncus cursus. Duis nec felis tellus. Maecenas consequat tincidunt justo quis sollicitudin. Vivamus rutrum, nibh consectetur faucibus faucibus, nisi turpis viverra felis, eget convallis eros elit nec augue. Fusce tempor ac lacus sed vestibulum. Praesent maximus vestibulum odio at scelerisque. Quisque sit amet ex est. In consequat volutpat cursus. Aliquam porta eu justo a interdum. Aliquam venenatis eget augue nec bibendum. Integer gravida purus risus, a sodales diam tincidunt ut.</p>
		</section>
		<section>
			<h4>${requestScope.How_To_Use_DB_Head }</h4>
			<p>Sed dignissim ornare enim non ultrices. Vestibulum auctor at nulla non euismod. Nulla nec pretium diam. Integer vehicula venenatis ornare. Praesent ut congue magna, a dapibus mi. Fusce porttitor hendrerit sem id sagittis. Donec malesuada quis augue non semper. Fusce turpis odio, ullamcorper et leo et, elementum sagittis erat. Cras placerat augue dui, ut varius tellus scelerisque et.</p>
		</section>
		<form action="assignment01">
			<input type="submit" value="Home" name="homeBtn" class="button"/>
		</form>
	</div>
	<footer>&copy; ${initParam.footer}</footer>
</div>
</body>
</html>