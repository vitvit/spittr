<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spittles</title>
</head>
<body>
	<h1>Recent Spittles</h1>
	<ul>
	<c:forEach items="${spittleList}" var="spittle">
		<li id='spittle_<c:out value="spittle.id"/>'>
			<div class="spittleMessage">	
				<spring:url value="/spittle/{spittleId}" var="spittleUrl">
					<spring:param name="spittleId" value="${spittle.id}" />
				</spring:url>
				<a href="${spittleUrl}"><c:out value="${spittle.message}"/></a>
			</div>
			<div>
				<span class="spittleTime"><c:out value="${spittle.time}"/></span>
			</div>
		</li>
	</c:forEach>
	</ul>
</body>
</html>