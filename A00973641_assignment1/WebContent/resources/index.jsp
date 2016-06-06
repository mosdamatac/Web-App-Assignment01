<%@ page import="java.util.List, a00973641.data.Member" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assignment 01</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css">
<script language="javascript" type="text/javascript">
	email_pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/
	phone_pattern = /^\(?(\d{3})\)?[\.\-\/ ]?(\d{3})[\.\-\/ ]?(\d{4})$/
	
	function submitIt(myForm)
	{
		errorMsg = ""
		if (myForm.firstName.value == "") {
			errorMsg = errorMsg + "First name can't be null or empty\n"
		}
		if (myForm.lastName.trim().value == "") {
			errorMsg = errorMsg + "Last name can't be null or empty\n"
		}
		if (myForm.address.trim().value == "") {
			errorMsg = errorMsg + "Address can't be null or empty\n"
		}
		if (myForm.city.trim().value == "") {
			errorMsg = errorMsg + "City can't be null or empty\n"
		}
		if (myForm.code.trim().value == "") {
			errorMsg = errorMsg + "Code can't be null or empty\n"
		}
		if (myForm.country.trim().value == "") {
			errorMsg = errorMsg + "Country can't be null or empty\n"
		}
		if (!phone_pattern.test(myForm.phoneNumber.value)) {
			errorMsg = errorMsg + "Invalid phone number (e.g. 111-111-1234)\n"
		}
		if (!email_pattern.test(myForm.email.value)) {
			errorMsg = errorMsg + "Invalid email (e.g. me@organization.com)\n"
		}
		
		if (errorMsg != "") {
			alert(errorMsg)
			myForm.focus()
			return false
		}
		return true
	}
</script>
</head>
<body>
<jsp:include page="/view"/>
<div id="wrapper">
	<header>
		<h2>${initParam.course}</h2>
		<h4>${initParam.author}</h4>
	</header>
	<div id="main">
		<form action="assignment01" name="headerForm" method="post" onSubmit="submitIt(this)">
			<table align="center">
				<thead>
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
						<th>Action</th>
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
		<form action="assignment01" name="memberForm" method="post" onSubmit="submitIt(this)"> 
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
					&nbsp;<input type="submit" name="delete" value="Delete" class="button"></td>
				
			</tr>
			</table>
		</form>
		<%} %>

		<form action="assignment01" name="addForm" method="post" onSubmit="submitIt(this)">
			<table>
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
				<td><input type="submit" name="insert" value="Insert" class="button"/>
					&nbsp;<input type="reset" name="clear" value="Clear" class="button"/></td>
			</tr>
			</table>
		</form>
	</div>
	<footer>&copy; ${initParam.footer}</footer>
</div>
</body>
</html>