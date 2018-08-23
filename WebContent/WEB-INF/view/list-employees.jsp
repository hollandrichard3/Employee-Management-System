<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>

<head>
<title>KGa Employee Management System</title>

<!-- reference our style sheet -->

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<jsp:include page="header.jsp" />
	
	<div id="main">
	
		<jsp:include page="left-menu.jsp" />
	
		<div id="container">
			
			<div id="topmenu">
				<div id="create">
					<!-- put new button: Add Employee -->
						
					<input type="button" value="Create New Employee"
						onclick="window.location.href='../employee/showFormForAdd'; return false;"
						class="add-button" />			
				</div>
				
				<!-- add a search box -->
				<div id="search">
		
					<!-- add a search box -->
					<form:form action="search" method="POST">
						<input type="text" name="theSearchName" placeholder="Search Employees">
		
						<input type="submit" value="Search" class="add-button"  id="long-button" />
					</form:form>
				</div>
				
			</div><!-- end div topmenu -->
		
			<div id="content">
				
				<!--  add our html table here -->
	
				<table>
					<tr>
						<th>Last Name</th>
						<th>First Name</th>
						<th>Title</th>
						<th>Team</th>
						<th>Active?</th>
						<th>Action</th>
					</tr>
	
					<c:forEach var="tempEmployee" items="${employees}">
	
						<!-- construct an "update" link with employee id -->
						<c:url var="updateLink" value="/employee/showFormForUpdate">
							<c:param name="employeeId" value="${tempEmployee.id}" />
						</c:url>
	
						<!-- construct a "delete" link with employee id -->
						<c:url var="deleteLink" value="/employee/delete">
							<c:param name="employeeId" value="${tempEmployee.id}" />
						</c:url>
	
						<c:set var="lastsuffixName">			
							${tempEmployee.lastName}<c:if test="${not empty tempEmployee.suffix}">, ${tempEmployee.suffix}</c:if>
						</c:set>
	
						<tr class="listselect">
							<td>${lastsuffixName}</td>
							<td>${tempEmployee.firstName}</td>
							<td>${tempEmployee.title}</td>
							<td>${tempEmployee.employeeTeam.team.teamName}</td>
							<td>
								<c:choose>
									<c:when test="${tempEmployee.active=='0'}">
										N
									</c:when>
									<c:otherwise>
										Y
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<!-- display the update link --> <a href="${updateLink}">Update</a>
								| <a href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this employee?'))) return false">Delete</a>
							</td>
						</tr>
	
					</c:forEach>
	
				</table>
	
			</div> <!-- END DIV CONTENT -->
		</div> <!-- END DIV CONTAINER -->
	</div> <!-- END DIV MAIN -->
</body>

</html>