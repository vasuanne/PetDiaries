<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Dashboard</title>
</head>
<body>
<p>Admin Dashboard</p>
<p>Hi ${firstName}!</p>
<c:if test="${!empty userId}">
<p>${userId}</p>

<c:url var="sendEmail" value="/admin/sendExpiryEmail"></c:url>
<c:url var="deleteExpired" value="/admin/deleteExpiredUsers"></c:url>

	<form:form action="${sendEmail}" commandName="admin">
	<form:input path="userId" value="${userId}" readonly="true" size="8" type="hidden"/>
			
	<form:input path="firstName" value="${firstName}" readonly="true" size="8" type="hidden"/>
	<form:input path="username" value="${username}" readonly="true" size="8" type="hidden"/>

	<form:input path="userType" value="" readonly="userType" size="8" type="hidden"/>
		
	<input type="submit" value="<spring:message text="Send Expiry Email"/>" />
	</form:form>
	
	<form:form action="${deleteExpired}" commandName="admin">
	<form:input path="userId" value="${userId}" readonly="true" size="8" type="hidden"/>
			
	<form:input path="firstName" value="${firstName}" readonly="true" size="8" type="hidden"/>
	<form:input path="username" value="${username}" readonly="true" size="8" type="hidden"/>

	<form:input path="userType" value="" readonly="userType" size="8" type="hidden"/>
		
	<input type="submit" value="<spring:message text="Delete Expired Users"/>" />
	</form:form>
	
	
</c:if>

<c:if test="${sentEmail}">
Expiry email sent to users!
</c:if>
</body>
</html>