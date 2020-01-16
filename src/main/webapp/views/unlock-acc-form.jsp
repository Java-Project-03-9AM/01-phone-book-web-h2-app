<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function checkPwds() {
		var pwd = document.getElementById("newPwd").value;
		var confirmPwd = document.getElementById("newConfirmPwd").value;
		if (pwd == confirmPwd) {
			return true;
		} else {
			alert("Password and Confirm Password Should be same.");
			return false;
		}
	}
</script>
</head>
<body>

	<h2>Unlock Account Form</h2>

	<font color='green'>${succMsg}</font>
	<font color='red'>${errMsg}</font>

	<form:form action="unlockAccount" method="POST"
		modelAttribute="unlockAcc">
		<table>
			<form:hidden path="email" />
			<tr>
				<td>Email:</td>
				<td>${unlockAcc.email}</td>
			</tr>
			<tr>
				<td>Temporary Pwd:</td>
				<td><form:input path="tempPwd" /></td>
			</tr>
			<tr>
				<td>New Password:</td>
				<td><form:input path="newPwd" /></td>
			</tr>
			<tr>
				<td>Confirm Password:</td>
				<td><form:input path="newConfirmPwd" /></td>
			</tr>
			<tr>
				<td><input type="reset" value="Reset" /></td>
				<td><input type="submit" value="Un Lock"
					onclick="return checkPwds()"></td>
			</tr>
		</table>
	</form:form>

	<a href="viewContacts">View All Contacts</a>
</body>
</html>