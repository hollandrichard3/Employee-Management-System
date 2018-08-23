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
					<!-- put new button: Add Team -->
					<input type="button" value="Create New Team"
						onclick="window.location.href='showFormForAdd'; return false;"
						class="add-button" />
						
					<input type="button" value="Create New Employee"
						onclick="window.location.href='../employee/showFormForAdd'; return false;"
						class="add-button" />
				</div>
		
				<!-- add a search box -->
				<div id="search">
					<form:form action="search" method="POST">
						<input type="text" name="theSearchName" placeholder="Search Teams">
						<input type="submit" value="Search" class="add-button" id="long-button" />
					</form:form>
				</div>
				
			</div><!-- end div topmenu -->
			
			<div id="content">
					
					<!--  add our html table here -->
					<table>
						<tr>
							<th>Team Name</th>
							<th>Manager</th>
							<th></th>
							<th>Action</th>
						</tr>
		
						<c:forEach var="tempTeam" items="${teams}">
													
							<!-- construct an "update" link with team id -->
							<c:url var="updateLink" value="/team/showFormForUpdate">
								<c:param name="teamId" value="${tempTeam.id}" />
							</c:url>
		
							<!-- construct a "delete" link with team id -->
							<c:url var="deleteLink" value="/team/delete">
								<c:param name="teamId" value="${tempTeam.id}" />
							</c:url>
		
							<tr class="listselect">
								<td>${tempTeam.teamName}</td>
								<td>
									<c:set var="fullName">${tempTeam.manager.firstName}
										<c:if test="${not empty tempTeam.manager.middleName}">${fn:substring(tempTeam.manager.middleName, 0, 1)}.</c:if>
										${tempTeam.manager.lastName}<c:if test="${not empty tempTeam.manager.suffix}">, ${tempTeam.manager.suffix}</c:if>
									</c:set>
									
									<!-- DISPLAY MANAGER FULL NAME -->	
									<a href="${pageContext.request.contextPath}/employee/showFormForUpdate?employeeId=${tempTeam.manager.id}">${fullName}</a>
								</td>
								<td></td>
								<td>
									<!-- display the update link -->
									<a href="${updateLink}">Update</a>
									<c:choose>
										<c:when test="${tempTeam.id != 20}">
											| <a href="${deleteLink}"
											onclick="if (!(confirm('Are you sure you want to delete this team?'))) return false">Delete</a>
										</c:when>
										<c:otherwise>
											| Delete
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							
						</c:forEach>
		
					</table>
	
			</div><!-- END DIV "CONTENT" -->
		</div><!-- END DIV "CONTAINER" -->
	</div><!-- END DIV MAIN -->
</body>

</html>