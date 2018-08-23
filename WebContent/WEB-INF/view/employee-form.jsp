<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<head>
<title>KGa Employee Management System</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-employee-style.css">
</head>

<body>

	<jsp:include page="header.jsp" />
	
	<div id="main">
	
		<jsp:include page="left-menu.jsp" />
	
		<div id="container">
		
			<div id="topmenu">
				<h3>Add/Update Employee</h3>
			</div>
		
			<div id="employeeContent">
			
				<form:form action="saveEmployee" modelAttribute="employeeTeam"
					method="POST">
		
					<!-- need to associate this data with employee id -->
					<form:hidden path="employee.id" />
		
					<table>
						<tbody>
							<tr>
								<td><label>First name (*): <form:errors path="employee.firstName" cssClass="error" /></label></td>
								<td><label>Middle name:</label></td>
								<td><label>Last name (*): <form:errors path="employee.lastName" cssClass="error"/></label></td>
								<td><label>, Suffix:</label></td>
							</tr>
							
							<tr>
								<script type="text/javascript">
								    function generateEmail()
								    {
								        document.getElementById('email').innerText = 
								            document.getElementById('firstName').value[0].toLowerCase() + 
								            document.getElementById('lastName').value.toLowerCase() +
								            '@kensingtonglass.com';
								    }
								</script>
								<td><form:input path="employee.firstName" id="firstName" onkeyup="generateEmail()" maxlength="45"/></td>
								<td><form:input path="employee.middleName" /></td>
								<td><form:input path="employee.lastName" id="lastName" onkeyup="generateEmail()" maxlength="45" /></td>
								<td class="alignRight"><form:input path="employee.suffix" maxlength="45" /></td>
							</tr>
							
							<tr>
								<td colspan="2"><label>Email:</label></td>
								<td><label>Desk Ext:</label></td>
								<td><label>Cell Phone:</label></td>
							</tr>
							
							<tr>
								<td colspan="2"><form:input path="employee.email" id="email" maxlength="45" /></td>
								<td><form:input path="employee.deskExt" maxlength="5"/></td>
								<td>
								  ( <form:input placeholder="###" path="employee.cellNumber.areaCode" id="areaCode" maxlength="3"/>) 
									<form:input placeholder="###" path="employee.cellNumber.officeCode" id="centralOfficeCode" maxlength="3"/>-
									<form:input placeholder="####" path="employee.cellNumber.stationCode" id="stationNumber" maxlength="4"/>
								</td>
							</tr>
							
							<tr>
								<td><label class="long">Start Date: <form:errors path="employee.startDate" cssClass="error" /></label></td>
								<td><label>Active?</label></td>
								<td><label>Title:</label></td>
								<td><label>Team:</label></td>
							</tr>
							
							<tr>
								<td><form:input path="employee.startDate" placeholder="mm/dd/yyyy"/></td>
								<td><form:checkbox path="employee.active" /></td>
								<td><form:input path="employee.title" maxlength="45"/></td>
								<td>
									<form:select path="team.id">
										<form:option value="20" label="------- Select -------"/>
										<c:forEach var="team" items="${teamList}">
											<form:option value="${team.id}" label="${team.teamName}" />
										</c:forEach>
									</form:select>
								</td>
							</tr>
							
							<tr>
								<td colspan="2"><label>Shirt Size:</label></td>
								<td><label>Role: </label></td>
								<td><label>Employee/Manager?</label></td>
							</tr>
							
							<tr>
								<td colspan="3">
									<form:select path="employee.shirtSize">
										<form:option value="" label="------- Select -------"/>
										<form:option value="XS" label="XS"/>
										<form:option value="S" label="S"/>
										<form:option value="M" label="M"/>
										<form:option value="L" label="L"/>
										<form:option value="XL" label="XL"/>
										<form:option value="XXL" label="XXL"/>
									</form:select>
								</td>
								<td>
									<form:select path="role.id">
										<c:forEach var="roleItem" items="${roleList}">
											<form:option value="${roleItem.id}" label="${roleItem.roleName}" />
										</c:forEach>
									</form:select>
								</td>
							</tr>
							
							<tr id="testing">
								<td></td>
								<td></td>
								<td colspan="2"><input type="submit" value="Save" class="save" /></td>
							</tr>
		
						</tbody>
					</table>
		
				</form:form>
			</div><!-- END DIV EMPLOYEECONTENT -->
	
			<p>
				<button type="button" name="back" id="back-button" onclick="history.back()">Back</button>
			</p>
	
		</div> <!-- END DIV CONTAINER -->
	</div> <!-- END DIV MAIN -->
</body>

</html>