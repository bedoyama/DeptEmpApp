<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script>
	function drawTable(data) {
		var row = $("<tr />");
		//row.append($(""));
		row.append($("<td>First NAME</td>"));
		row.append($("<td>last NAME</td>"));
		row.append($("<td>Phone</td>"));
		row.append($("<td>Age</td>"));
		row.append($("<td>Id</td>"));
		
		$("#personDataTable").append(row);

		for (var i = 0; i < data.length; i++) {
			drawRow(data[i]);
		}
	}

	function drawRow(rowData) {
		var row = $("<tr />")
		$("#personDataTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
		row.append($("<td>" + rowData.firstName + "</td>"));
		row.append($("<td>" + rowData.lastName + "</td>"));
		row.append($("<td>" + rowData.phone + "</td>"));
		row.append($("<td>" + rowData.age + "</td>"));
		row.append($("<td><a href='/edit-"+ $("#empId").val() +"-employee' />"+ $("#empId").val() +"</a></td>"));
	}

	$(document).ready(
			function() {
				$("#next").click(
						function() {
							//alert("Value: " + $("#pagen").val());
							var pagno = parseInt($("#pagen").val());
							$("#pagen").val(pagno + 1);

							$.ajax({
								url : "employee?page=" + $("#pagen").val()
										+ "&size=" + $("#psize").val(),
								success : function(result) {
									$("#personDataTable").empty()
									drawTable(result);
								}
							});

						});

				$("#prev").click(
						function() {
							//alert("Value: " + $("#pagen").val());
							var pagno = parseInt($("#pagen").val());
							$("#pagen").val(Math.max(pagno - 1, 1));

							$.ajax({
								url : "employee?page=" + $("#pagen").val()
										+ "&size=" + $("#psize").val(),
								success : function(result) {
									$("#personDataTable").empty()
									drawTable(result);
								}
							});

						});
			});
</script>
</head>


<body>
	<h2>List of Employees</h2>
	<form method="GET">
		<input type="hidden" id="pagen" value="${page}" /> <input
			type="hidden" id="psize" value="${size}" /><input
			type="hidden" id="empId" value="${employee.empId}" />
	</form>

	<div id="someEmps">
		<table id="personDataTable">
			<tr>
				<td>First NAME</td>
				<td>last NAME</td>
				<td>Phone</td>
				<td>Age</td>
<!-- 				<td>Department</td>
 -->				<td>Id</td>
				<td></td>
			</tr>
			<c:forEach items="${someemployees}" var="employee">
				<tr>
					<td>${employee.firstName}</td>
					<td>${employee.lastName}</td>
					<td>${employee.phone}</td>
					<td>${employee.age}</td>
<%-- 					<td>${employee.department.deptName}</td>
 --%>					<td><a
						href="<c:url value='/edit-${employee.empId}-employee' />">${employee.empId}</a></td>
					<td><a
						href="<c:url value='/delete-${employee.empId}-employee' />">delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br />
	<div id="footerlinks">
		<a href="#" id="prev">Previous</a>-<a href="#" id="next">Next</a><br>
		<a href="<c:url value='/new' />">Add New Employee</a> <br> <a
			href="<c:url value='/newDept' />">Add New Department</a> <br> <a
			href="<c:url value='/deptList' />">List All Departments</a> <br>
		<jsp:directive.include file="/resources/include/logout.jsp" />

	</div>

</body>
</html>