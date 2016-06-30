<%@ page import="java.util.List, a00973641.data.Member" %>
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
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<title>Assignment 01</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css"/>
	<SCRIPT language="javascript" type="text/javascript">

		re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/
		regexp = /^\(?(\d{3})\)?[\.\-\/ ]?(\d{3})[\.\-\/ ]?(\d{4})$/


		function submitIt(myForm)
		{
			errMsg = ""
			if(myForm.firstName.value == ""){
				errMsg = "First name can't empty\n"
			}
			if(myForm.lastName.value == ""){
				errMsg = errMsg + "Last name can't be empty\n"
			}
			if(myForm.address.value == ""){
				errMsg = errMsg + "Address can't be empty\n"
			}
			if(myForm.city.value == "") {				
				errMsg = errMsg + "City can't be empty\n"
			}
			if(myForm.code.value == "" || myForm.code.value.length > 7) {
				errMsg = errMsg + "Code can't be empty and less than 7 characters long\n"
			}
			if(myForm.country.value == "") {
				errMsg = errMsg + "Country can't be empty\n"
			}			
			if (!regexp.test(myForm.phoneNumber.value)) {
				errMsg = errMsg + "Invalid phone number (e.g. 111-111-1234)\n"
			}
			if (!re.test(myForm.email.value)) {
				errMsg = errMsg + "Invalid email (e.g. me@organization.com)\n"
			}

			if(errMsg != ""){
				alert(errMsg)
				myForm.focus()
				return false
			}
			return true
		}
		
	 </SCRIPT>
</head>
<body>
<jsp:include page="/view"/>
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
		<form action="assignment01" name="headerForm" method="post" onSubmit="return submitIt(this)">
			<table align="center">
				<thead>
					<tr>
						<th>${requestScope.Member_Id}</th>
						<th>${requestScope.First_Name}</th>
						<th>${requestScope.Last_Name}</th>
						<th>${requestScope.Address}</th>
						<th>${requestScope.City}</th>
						<th>${requestScope.Code}</th>
						<th>${requestScope.Country}</th>
						<th>${requestScope.Phone_Number}</th>
						<th>${requestScope.Email}</th>
						<th>${requestScope.Action}</th>
					</tr>
				</thead>
			</table>
		</form>
				
		<%
		List<Member> members = (List<Member>) request.getAttribute("members");
		int i = 0;
		for (Member member : members) {
			request.setAttribute("memberID", member.getMemberID());
		%>
		<form action="assignment01" name="memberForm" method="post" onSubmit="return submitIt(this)"> 
			<table>
			<tr>
				<td><input type="text" name="memberID" value="<%= member.getMemberID() %>" class="idField" readonly/></td>
				<td><input type="text" name="firstName" value="<%= member.getFirstName() %>"/></td>
				<td><input type="text" name="lastName" value="<%= member.getLastName() %>"/></td>
				<td><input type="text" name="address" value="<%= member.getAddress() %>"/></td>
				<td><input type="text" name="city" value="<%= member.getCity() %>"/></td>
				<td><input type="text" name="code" value="<%= member.getCode() %>"/></td>
				<td><input type="text" name="country" value="<%= member.getCountry() %>"/></td>
				<td><input type="text" name="phoneNumber" value="<%= member.getPhoneNumber() %>"/></td>
				<td><input type="text" name="email" value="<%= member.getEmail() %>"/></td>
				<td><input type="submit" name="update" value="Update" class="button"/>
					&nbsp;<input type="submit" name="delete" value="Delete" class="button" onClick="return confirm('Are you sure you want to delete member?')"></td>
				
			</tr>
			</table>
		</form>
		<%} %>

		<form action="assignment01" name="addForm" method="post" onSubmit="submitIt(this)">
			<table>
			<tr>
				<td>${requestScope.Auto_Fill }</td>
				<td><input type="text" name="firstName"/></td>
				<td><input type="text" name="lastName"/></td>
				<td><input type="text" name="address"/></td>
				<td><input type="text" name="city"/></td>
				<td><input type="text" name="code"/></td>
				<td><input type="text" name="country"/></td>
				<td><input type="text" name="phoneNumber"/></td>
				<td><input type="text" name="email"/></td>
				<td><input type="submit" name="insert" value="Insert" class="button"/>
					&nbsp;<input type="reset" name="clear" value="Clear" class="button"/></td>
			</tr>
			</table>
		</form>
		<br/><br/>
		<form action="assignment01" name="buttons" method="post">
			<input type="submit" value="Home" name="homeBtn" class="button"/>
			<input type="submit" value="Summary" name="summaryBtn" class="button"/>
			<input type="submit" value="About" name="aboutBtn" class="button"/>
		</form>
		<br/>
	</div>
	<footer>&copy; ${initParam.footer}</footer>
</div>
</body>
</html>