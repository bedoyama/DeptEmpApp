<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Registration Form</title>

<style>
.error {
	color: #ff0000;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("input").focus(function() {
			$(this).css("background-color", "#cccccc");
		});
		$("input").blur(function() {
			$(this).css("background-color", "#ffffff");
		});
	});
</script>
</head>

<body>

	<h2>Department Registration Form</h2>

	<form:form method="POST" modelAttribute="department">
		<form:input type="hidden" path="deptId" id="deptId" />
		<table>
			<tr>
				<td><label for="deptName">Department Name: </label></td>
				<td><form:input type="text" path="deptName" id="deptName" /></td>
				<td><form:errors path="deptName" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="deptEmail">Department Email: </label></td>
				<td><form:input type="text" path="deptEmail" id="deptEmail" /></td>
				<td><form:errors path="deptEmail" cssClass="error" /></td>
			</tr>

			<tr>
				<td colspan="3"><c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update" />
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register" />
						</c:otherwise>
					</c:choose></td>
			</tr>
		</table>
	</form:form>
	<br />
	<br /> Go back to
	<a href="<c:url value='/list' />">List of All Employees</a><br>
	<a href="<c:url value='/deptList' />">List of All Departments</a>
</body>
</html>