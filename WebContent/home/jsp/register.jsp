<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${pageContext.request.requestURI}" />
		<meta charset="UTF-8">
		<title>Registrierung erfolgt</title>
		<link rel="stylesheet" href="../css/header.css">
		<link rel="stylesheet" href="../css/footer.css">
		<link rel="stylesheet" href="../css/cookie.css">
		<script type="text/javascript" src="../js/cookie.js"></script>
	</head>
	<body>
		<%@ include file="../jspf/header.jspf" %>
		<section>
			<h1>Registrierung erfolgt</h1>
		</section>
		
		<jsp:useBean id="javabean" class="javabeans.ProfileBean" scope="session"></jsp:useBean>
		Hallo <jsp:getProperty name="javabean" property="vorname"/>, Sie haben sich erfolgreich registriert! Sie können sich jetzt nun einloggen.
		<%@ include file="../jspf/footer.jspf" %>
	</body>
</html>