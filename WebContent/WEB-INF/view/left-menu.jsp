<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:set var="pageUrl">
	<%= request.getRequestURI() %>
</c:set>

<c:choose>
	<c:when test="${fn:endsWith(pageUrl, '/list-employees.jsp')}">
	    <!-- LEFT MENU DIV -->
		<div id="leftmenu">
			<input type="button" value="View All Teams"
			onclick="window.location.href='../team/list'; return false;"
			class="add-button" id="long-button"/>
		</div>
	</c:when>
	
	<c:when test="${fn:endsWith(pageUrl, '/list-teams.jsp')}">
		<!-- LEFT MENU DIV -->
		<div id="leftmenu">
			<input type="button" value="View All Employees"
			onclick="window.location.href='../employee/list'; return false;"
			class="add-button" id="long-button"/>
		</div>
	</c:when>
	
	<c:otherwise>
		<!-- LEFT MENU DIV -->
		<div id="leftmenu">
			<input type="button" value="View All Teams"
			onclick="window.location.href='../team/list'; return false;"
			class="add-button" id="long-button"/>
			
			<input type="button" value="View All Employees"
			onclick="window.location.href='../employee/list'; return false;"
			class="add-button" id="long-button"/>
		</div>
	</c:otherwise>
</c:choose>