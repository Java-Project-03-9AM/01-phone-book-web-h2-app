<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
	
</script>

<script>
	$(document).ready(function(e) {
		$("#contactEmail").blur(function(event) {
			$("#dupEmail").html("");
			var emailId = $("#contactEmail").val();
			$.ajax({
				url : 'validateEmail?email=' + emailId,
				success : function(abc) {
					if (abc == 'Duplicate') {
						$("#dupEmail").html("Email already registered");
						$("#contactEmail").focus();
					}
				}
			});
		});
	});
</script>

</head>
<body>

	<h2>Contact Info Form</h2>

	<font color='green'>${succMsg}</font>
	<font color='red'>${errMsg}</font>

	<form:form action="saveContact" method="POST" modelAttribute="contact">
		<table>
			<form:hidden path="contactId" />
			<tr>
				<td>Contact Name:</td>
				<td><form:input path="contactName" /></td>
			</tr>
			<tr>
				<td>Contact Email:</td>
				<td><form:input path="contactEmail" />
					<font color='red'>
						<div id="dupEmail"></div>
					</font>		
				</td>
			</tr>
			<tr>
				<td>Contact Number:</td>
				<td><form:input path="phNo" /></td>
			</tr>
			<tr>
				<td><input type="reset" value="Reset" /></td>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>

	<a href="viewContacts">View All Contacts</a>
</body>
</html>