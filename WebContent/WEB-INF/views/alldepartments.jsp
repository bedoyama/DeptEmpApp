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
</style>

</head>


<body>
	<h2>List of Departments</h2>
	<table>
		<tr>
			<td>Department Name</td>
			<td>Department e-mail</td>
			<td>Id</td>
			<td></td>
		</tr>
		<c:forEach items="${departments}" var="department">
			<tr>
				<td>${department.deptName}</td>
				<td>${department.deptEmail}</td>
				<td><a
					href="<c:url value='/edit-${department.deptId}-department' />">${department.deptId}</a></td>
				<td><a
					href="<c:url value='/delete-${department.deptId}-department' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="<c:url value='/new' />">Add New Employee</a>
	<br>
	<a href="<c:url value='/newDept' />">Add New Department</a>
	<br>
	<a href="<c:url value='/list' />">List All Employees</a>
	<br>
	<jsp:directive.include file="/resources/include/logout.jsp" />

</body>
</html>