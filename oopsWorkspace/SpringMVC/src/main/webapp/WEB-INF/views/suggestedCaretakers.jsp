<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Suggested Caretakers</title>
</head>
<body>
<c:url var="submitAction" value="/user/listCaretakers" ></c:url>
<c:if test="${!empty listCaretakers}">
	<table class="tg">
	<tr>
		<th width="120">User Name</th>
		<th width="120"></th>
		<th width="120"></th>
	</tr>
	<c:forEach items="${listCaretakers}" var="user">
		<tr>
			<td>
			 <form:form action="${submitAction}" commandName="user">
		
			<form:input path="userId" value="${user.userId}" readonly="true" size="20" type="hidden"/>
			
			<form:input path="username" value="${user.username}" readonly="true" size="20"/>
			
			<form:input path="firstName" value="${user.firstName}" readonly="true" size="8" type="hidden"/>
			<form:input path="userType" value="${user.userType}" readonly="true" size="8" type="hidden"/>
			<input type="submit"	
			value="<spring:message text="View Profile"/>" />
			</form:form>
			</td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>