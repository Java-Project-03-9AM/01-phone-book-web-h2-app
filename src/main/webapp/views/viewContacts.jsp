<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
	function confirmDelete(){
		return confirm("Are you sure, want to delete?");
	}

</script>
</head>
<body>
	<h2>All Contacts</h2>


    <a href="/">+Add New Contact</a>
    <br/>
	<table border="1">
		<thead>
			<tr>
				<td>S.No</td>
				<td>Contact Name</td>
				<td>Contact Email</td>
				<td>Contact Num</td>
				<td>Action</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${contacts}" 
					   var="c" 
					   varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${c.contactName}</td>
					<td>${c.contactEmail}</td>
					<td>${c.phNo}</td>
					<td>
						<a href="editContact?contactId=${c.contactId}">Edit</a> &nbsp;
						<a href="deleteContact?contactId=${c.contactId}" onclick="return confirmDelete()">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>