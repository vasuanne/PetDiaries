<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Sign Up</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Sign Up
</h1>

<c:url var="signupAction" value="/user/signup" ></c:url>
<p> Enter the following details </p>
<form:form action="${signupAction}" commandName="user">
<table>
	<tr>
		<td>
			<form:label path="username">
				<spring:message text="Username"/>
			</form:label>
		</td>
		<td>
			<form:input path="username" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="password">
				<spring:message text="Password"/>
			</form:label>
		</td>
		<td>
			<form:input type="password" path="password" />
		</td>
	</tr>
	
	<tr>
		<td>
			<form:label path="firstName">
				<spring:message text="First Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="firstName" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="lastName">
				<spring:message text="Last Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="lastName"/>
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="userType">
				<spring:message text="Choose Role"/>
			</form:label>
		</td>
		<td>
			<select name="userType">
			<option> </option>
			<option>Owner</option>
			<option>Caretaker</option>
			</select>
		</td> 
	</tr>
	<tr>
		<td colspan="2">
		<button id="button1">submit</button>
			
		</td>
	</tr>
</table>	
</form:form>
<br><br><br>
<c:if test="${!empty invalidInput}">
	<p>${invalidInput}</p>
	</c:if>
</body>
</html>
