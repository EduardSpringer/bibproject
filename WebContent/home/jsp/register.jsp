<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${pageContext.request.requestURI}" />
		<meta charset="UTF-8">
		<title>Registrierung erfolgt</title>
		<link rel="stylesheet" href="../css/content.css" type="text/css">
		<link rel="stylesheet" href="../css/center.css">
		<link rel="stylesheet" href="../css/header.css">
		<link rel="stylesheet" href="../css/footer.css">
	</head>
	<body>
		<%@ include file="../jspf/header.jspf" %>
		<div class="outsidediv"> 
		<section class="zentriert"> 
		<h1>Registrierung erfolgt</h1>
		
		<h2>
		<jsp:useBean id="javabean" class="javabeans.ProfileBean" scope="session"></jsp:useBean>
		Hallo <jsp:getProperty name="javabean" property="vorname"/>, wir wünschen dir viel Erfolg bei den Prüfungen! 
		</h2>
		
		<p><a href="login.jsp">Hier geht es zum Login</a></p>
		</section>
		</div>
		<%@ include file="../jspf/footer.jspf" %>
	</body>
</html>