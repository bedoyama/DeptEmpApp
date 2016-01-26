<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>University Enrollments</title>

<style>
tr:first-child {
	font-weight: bold;
	background-color: #C6C9C4;
}

table {
	margin: auto;
}

#footerlinks {
	text-align: center;
}

h2 {
	text-align: center;
}
</style>

</head>


<body>
	<h2>List of Employees</h2>
	<table>
		<tr>
			<td>First NAME</td>
			<td>last NAME</td>
			<td>Phone</td>
			<td>Age</td>
			<td>Department</td>
			<td>Id</td>
			<td></td>
		</tr>
		<c:forEach items="${employees}" var="employee">
			<tr>
				<td>${employee.firstName}</td>
				<td>${employee.lastName}</td>
				<td>${employee.phone}</td>
				<td>${employee.age}</td>
				<td>${employee.department.deptName}</td>
				<td><a
					href="<c:url value='/edit-${employee.empId}-employee' />">${employee.empId}</a></td>
				<td><a
					href="<c:url value='/delete-${employee.empId}-employee' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<div id="footerlinks">
		<a href="<c:url value='/new' />">Add New Employee</a> <br> <a
			href="<c:url value='/newDept' />">Add New Department</a> <br> <a
			href="<c:url value='/deptList' />">List All Departments</a> <br>
		<jsp:directive.include file="/resources/include/logout.jsp" />

	</div>

</body>
</html>