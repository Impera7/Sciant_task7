<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.dao.EmployeeDao,
	com.bean.Employee, java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="script.js"></script>
<link rel="stylesheet" href="style.css">
<title>Web Application index page</title>
</head>
<body onload="disableButtons();addRowHandlers()">
	
	<h1>Employee List</h1>
	
	<%
	List<Employee> list = EmployeeDao.getAllEmployees();
	request.setAttribute("list", list);
	%>
	
	<div class="divForm">
		<form name="myForm" onsubmit="return validateForm();"
			method="post" autocomplete="off">
			<div class="names" style="float: left">
				First name: <input type="text" name="fname" id="fnameId"><br>
				Last name: <input type="text" name="lname" id="lnameId">
			</div>
			
			<div class="salaryAndDate" style="float: right">
				Salary: <input type="text" name="salary" id="salaryId"><br>
				Start date: <input type="text" name="startDate" id="startDateId">
			</div>
			
			<div class="buttons">
				<input id="insertButton" name="insertButton" type="submit" formaction="DbActionsServlet" value="Insert">
				<input id="updateButton" name="updateButton" onclick="return confirm('Are you sure you want to update?')" type="submit"
					formaction="DbActionsServlet" value="Update">
				<input id="deleteButton" name="deleteButton" onclick="return confirm('Are you sure you want to delete?');" type="submit"
					formaction="DbActionsServlet" value="Delete">
				<input id="clearButton" name ="clearButton" type="button" onclick="clearAll()" value="Clear">
				<input type="hidden" id="empId" name="empId">
			</div>
			
			<div class="table">
				<table id="myTable" border="1" width="88%">
					<tr>
						<th style="display: none">ID</th>
						<th>First name</th>
						<th>Last name</th>
						<th>Salary</th>
						<th>Start date</th>
					</tr>
					<c:forEach items="${list}" var="emp">
						<tr onmouseover="changeColor(this, true);"
							onmouseout="changeColor(this, false);">
							<td style="display: none"><c:out value="${emp.getEmpId()}" /></td>
							<td><c:out value="${emp.getFname()}" /></td>
							<td><c:out value="${emp.getLname()}" /></td>
							<td><c:out value="${emp.getSalary()}" /></td>
							<td><c:out value="${emp.getStartDate()}" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</form>
	</div>
		
</body>
</html>
