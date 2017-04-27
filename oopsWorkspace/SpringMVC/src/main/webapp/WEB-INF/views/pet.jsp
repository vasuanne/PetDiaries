<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Add Pet Info</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add a Pet ${userId}
</h1>

<c:url var="addAction" value="/user/pet/add" ></c:url>
<form:form action="${addAction}" commandName="pet">
<table>
	<c:if test="${!empty pet.petName}">
	
	</c:if>
	<tr>
		<td>
			<form:label path="userId">
				<spring:message text=""/>
			</form:label>
		</td>
		<td>
			<form:input path="userId" value="${userId}" readonly="true" size="8" type="hidden" />
			<form:hidden path="userId" />
		</td> 
	</tr>
	
	<tr>
		<td>
			<form:label path="petName">
				<spring:message text="Pet Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="petName" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="petType">
				<spring:message text="Pet Type"/>
			</form:label>
		</td>
		<td>
			<select name="petType">
			<option> </option>
			<option>Cat</option>
			<option>Dog</option>
			</select>
		</td> 
	</tr>
	<tr>
	<tr>
		<td>
			<form:label path="petBreed">
				<spring:message text="Pet Breed"/>
			</form:label>
		</td>
		<td>
			<form:input path="petBreed" />
		</td>
	</tr>
	<tr>
	<tr>
		<td>
			<form:label path="petSize">
				<spring:message text="Pet Size"/>
			</form:label>
		</td>
		<td>
			<select name="petSize">
			<option> </option>
			<option>Small</option>
			<option>Medium</option>
			<option>Large</option>
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
<c:if test="${!empty invalidInput}">
	<p>${invalidInput}</p>
</c:if>	
<br>
<h3>Pet List</h3>
<c:if test="${!empty listPets}">
	<table class="tg">
	<tr>
		<th width="120">Pet Name</th>
		<th width="120">Pet Type</th>
		<th width="120">Pet Breed</th>
		<th width="120">Pet Size</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listPets}" var="pet">
		<tr>
			<td>${pet.petName}</td>
			<td>${pet.petType}</td>
			<td>${pet.petBreed}</td>
			<td>${pet.petSize}</td>
			<td><a href="<c:url value='/user/pet/edit/${pet.petId}' />" >Edit</a></td>
			<td><a href="<c:url value='/user/pet/remove/${pet.petId}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>