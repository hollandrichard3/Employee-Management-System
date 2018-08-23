<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>

<head>
<title>KGa Employee Management System</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-team-style.css">
</head>

<body>

	<jsp:include page="header.jsp" />
	
	<div id="main">
	
		<jsp:include page="left-menu.jsp" />
	
		<div id="container">
			
			<div id="topmenu">
				<h3>Add/Update Team</h3>
			</div>
			
			<form:form action="saveTeam" modelAttribute="team"
				method="POST">
				
				<!-- need to associate this data with team id -->
				<form:hidden path="id" />
				
				<table>
					<tbody>
						<!-- TABLE HEADER INFORMATION -->
						<tr>
							<td><label>Team name (*): <form:errors path="teamName" cssClass="error"/></label></td>
							<td><label>Manager:</label></td>
							<td colspan="2"><label>Assistant Manager(s):</label></td>
						</tr>
	
						<tr>
							<!-- TEAM NAME VALUE -->
							<td><form:input path="teamName" /></td>
							
							<!-- MANAGER DISPLAY CONDITION -->
							<td>
								<!-- PIECE TOGETHER MANAGER FULL NAME -->
								<c:set var="fullName">${team.manager.firstName}
									<c:if test="${not empty team.manager.middleName}">${fn:substring(team.manager.middleName, 0, 1)}.</c:if>
									${team.manager.lastName}<c:if test="${not empty team.manager.suffix}">, ${team.manager.suffix}</c:if>
								</c:set>
								
								<!-- DISPLAY MANAGER FULL NAME -->	
								<a href="${pageContext.request.contextPath}/employee/showFormForUpdate?employeeId=${team.manager.id}">${fullName}</a>
							</td>
							
							<c:set var="count" value="0" />
							<c:forEach var="asstManager" items="${asstManagers}">
								<c:if test="${count == 0}">
									<td colspan="2">
								<!-- ASSTMANAGER DISPLAY TABLE -->
								
									<div class="innerDiv">
										<c:set var="fullName">${asstManager.firstName}
											<c:if test="${not empty asstManager.middleName}">${fn:substring(asstManager.middleName, 0, 1)}.</c:if>
											${asstManager.lastName}<c:if test="${not empty asstManager.suffix}">, ${asstManager.suffix}</c:if>
										</c:set>
										
										<!-- DISPLAY ASSTMANAGER FULL NAME -->	
										<a href="${pageContext.request.contextPath}/employee/showFormForUpdate?employeeId=${asstManager.id}">${fullName}</a>
									</div>
							</td>
						</tr>
								</c:if>
								<c:if test="${count > 0}">
									<tr class="asstManagers">
										<td colspan="2"></td>
										<td colspan="2">
											<div class="innerDiv">
												<c:set var="fullName">${asstManager.firstName}
													<c:if test="${not empty asstManager.middleName}">${fn:substring(asstManager.middleName, 0, 1)}.</c:if>
													${asstManager.lastName}<c:if test="${not empty asstManager.suffix}">, ${asstManager.suffix}</c:if>
												</c:set>
												
												<!-- DISPLAY ASSTMANAGER FULL NAME -->	
												<a href="${pageContext.request.contextPath}/employee/showFormForUpdate?employeeId=${asstManager.id}">${fullName}</a>
											</div>
										</td>
									</tr>
								</c:if>
								<c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
						
						<tr>
							<td class="line"><label>Employees:</label></td>
							<td class="line"><label>Title:</label></td>
							<td class="line"><label></label></td>
							<td class="line"><label>Active?</label></td>
						</tr>
						<!-- LIST OF EMPLOYEES -->
						<c:forEach var="employee" items="${team.employees}">
							
								<tr>
									<!-- FORM: EMPLOYEE FULL NAME -->
									<td>
										<c:set var="fullName">${employee.firstName}
										<c:if test="${not empty employee.middleName}">${fn:substring(employee.middleName, 0, 1)}.</c:if>
										${employee.lastName}<c:if test="${not empty employee.suffix}">, ${employee.suffix}</c:if>
										</c:set>
										<a href="${pageContext.request.contextPath}/employee/showFormForUpdate?employeeId=${employee.id}">${fullName}</a>
									</td>
									<!-- FORM: EMPLOYEE TITLE -->
									<td>${employee.title}</td>
									<!-- FORM: ASST MANAGER? CHECKBOX -->
									<td></td>
									<!-- FORM: ACTIVE? -->
									<td>
										<c:choose>
											<c:when test="${employee.active=='0'}">
												N
											</c:when>
											<c:otherwise>
												Y
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							
						</c:forEach>
						<tr id="saveTR">
							<td colspan="4"><input type="submit" value="Save" class="save" /></td>
						</tr>
	
					</tbody>
				</table>
	
			</form:form>
	
			<p>
				<button type="button" name="back" id="back-button" onclick="history.back()">Back</button>
			</p>
	
		</div> <!-- END DIV CONTAINER -->
	</div> <!-- END DIV MAIN -->
</body>

</html>