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

	<h2>Registration Form</h2>

	<form:form method="POST" modelAttribute="employee">
		<form:input type="hidden" path="empId" id="empId" />
		<table>
			<tr>
				<td><label for="firstName">First Name: </label></td>
				<td><form:input type="text" path="firstName" id="firstName" /></td>
				<td><form:errors path="firstName" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="lastName">Last Name: </label></td>
				<td><form:input type="text" path="lastName" id="lastName" /></td>
				<td><form:errors path="lastName" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="phone">Phone: </label></td>
				<td><form:input type="text" path="phone" id="phone" /></td>
				<td><form:errors path="phone" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="age">Age: </label></td>
				<td><form:input type="number" path="age" id="age" /></td>
				<td><form:errors path="age" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="department">Department: </label></td>
				<td><form:select path="department" items="${departments}" itemValue="deptId" itemLabel="deptName"/></td>
				<td><form:errors path="department" cssClass="error" /></td>
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
	<a href="<c:url value='/list' />">List of All Employees</a>
	<a href="<c:url value='/deptList' />">List of All Departments</a>
</body>
</html>