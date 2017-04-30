<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Caretaker Dashboard</title>
</head>
<body>
<p>Caretaker Dashboard</p>
<p>Hi ${firstName}! </p>
<p>${userId}</p>
<c:url var="addPets" value="/user/pet" ></c:url>
<c:url var="addContactInfo" value="/user/contactInfo" ></c:url>
<c:url var="viewProfile" value="/user/viewProfile" ></c:url>


<c:if test="${petCount == 0 || !contactInfo}">
<h2>Profile setting not complete. You must complete your profile to accept requests.</h2>
	<table class="tg"  border="1px">
	<tr>
		<th width="80" >Missing</th>
		<th width="120">Details</th>
		<th width="120">Action Required</th>
	</tr>
	
	<c:if test="${petCount == 0}">
	<tr>
		<td><p>Pet Information</p></td>
		<td><p>You have not set Pet Preferences. Please add Pet Preferences.</p></td>
		   
       	<td><form:form method = "GET" action="${addPets}" commandName="pet">
       	<input type="hidden" name="userId" value="${userId}" />
     	 <input type="hidden" name="userType" value="${userType}" />
        <input type="submit" value="Add pet info"/>
      	</form:form>  </td>
      
       

	</tr>
	</c:if>
	
	<c:if test="${!contactInfo}">
	<tr>
		<td><p>Contact Information</p></td>
		<td><p>You have not added your contact information. Please add contact information.</p></td>
	   
       	<td><form:form method = "GET" action="${addContactInfo}" commandName="contactInfo"><input type="hidden" name="userId" value="${userId}" />
        <input type="submit" value="Add contact info"/>
      	</form:form>  </td>
	</tr>
	</c:if>
	</table>
</c:if>


<c:if test="${contactInfo && petCount>0}">
	

	<c:url var="addAction" value="/user/login/request/${userId}/${username}/${userType}" ></c:url>
	<form:form action="${addAction}" commandName="request">
	<table class="tg">
	<tr>
		<td colspan="2">
			
				<input type="submit" value="Create Request" />
			
		</td>
	</tr>
	</table>
	</form:form>
	
	<c:if test="${!empty listRequest}">
	<table class="tg">
	<tr>
		<th width="80">Request ID</th>
		<th width="120">Pet Type</th>
		<th width="120">Pet Name</th>
		<th width="120">Pet Breed</th>
		<th width="120">Owner Name</th>
		<th width="120">From Date</th>
		<th width="120">To Date</th>
	</tr>
	<c:forEach items="${listRequest}" var="user">
		<tr>
			 <td>${user.requestId}</td>
			<td>${user.petType}</td>
			<td>${user.petName}</td>
			<td>${user.petBreed}</td>
			<td>${user.fromDate}</td>
			<td>${user.toDate}</td>
			 
		</tr>
	</c:forEach>
	</table>
</c:if>
</c:if>



</body>
</html>