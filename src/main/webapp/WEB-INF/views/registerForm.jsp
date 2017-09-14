<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<spring:url value='/resources/css/style.css'/>"
      rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Register</h1>
	<form:form method="POST" commandName="spitter">
		<form:label path="firstname" cssErrorClass="error">First Name:</form:label>
		<form:input path="firstname" value="${spitter.firstname}" />
		<form:errors path="firstname" cssClass="error" />
		<br />
		<form:label path="lastname" cssErrorClass="error">Last Name:</form:label>
		<form:input path="lastname" value="${spitter.lastname}" />
		<form:errors path="lastname" cssClass="error" />
		<br />
		<form:label path="username" cssErrorClass="error">Username:</form:label>
		<form:input path="username" value="${spitter.username}" />
		<form:errors path="username" cssClass="error" />
		<br />
		<form:label path="password" cssErrorClass="error">Password:</form:label>
		<form:password path="password" value="${spitter.password}" />
		<form:errors path="password" cssClass="error" />
		<br />
		<input type="submit" value="Register" />
	</form:form>
</body>
</html>