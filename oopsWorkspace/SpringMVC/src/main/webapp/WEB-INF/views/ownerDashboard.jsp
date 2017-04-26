<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Owner Dashboard</title>
</head>
<body>
<p>Owner Dashboard</p>
<p>Hi ${firstName}! </p>


<c:if test="${petCount == 0 || !contactInfo}">
<h2>Profile setting not complete. You must complete your profile to raise and accept requests.</h2>
	<table class="tg"  border="1px">
	<tr>
		<th width="80" >Missing</th>
		<th width="120">Details</th>
		<th width="120">Action Required</th>
	</tr>
	
	<c:if test="${petCount == 0}">
	<tr>
		<td><p>Pet Information</p></td>
		<td><p>You have 0 pets available. Please add pets.</p></td>
		<td><p>Click here to add pet information</p></td>
	</tr>
	</c:if>
	
	<c:if test="${!contactInfo}">
	<tr>
		<td><p>Contact Information</p></td>
		<td><p>You have not added your contact information. Please add contact information.</p></td>
		<td><p>Click here to add contact information</p></td>
	</tr>
	</c:if>
	</table>
</c:if>



</body>
</html>