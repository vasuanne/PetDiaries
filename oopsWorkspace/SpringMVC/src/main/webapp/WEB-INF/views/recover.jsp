<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Recover username password</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>Recover username password</h1>

<c:url var="recoverAction" value="/user/recover" ></c:url>

<form:form action="${recoverAction}" commandName="user">
<table>
	
	<tr>
		<td>
			<form:label path="emailAddr">
				<spring:message text="Enter Recovery Email Address"/>
			</form:label>
		</td>
		<td>
			<form:input path="emailAddr" />
		</td> 
	</tr>
	<tr>

		<td colspan="2">

				<input type="submit"
					value="<spring:message text="Submit"/>" />

	</tr>
	
</table>	
</form:form>
<c:if test="${emailSent}">
	<p>Recovery email sent to registered email address. Email will contain username that was registered </p>
	
	<p>Use links provided in email to reset password if required</p>
</c:if>
<br>

</body>
</html>