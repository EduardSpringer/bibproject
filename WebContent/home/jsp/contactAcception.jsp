<!-- Eduard Springer -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="refresh" content="8; URL=../index.jsp"><!-- Weiterleitung in 8 Sekunden -->
		<title>Nachricht erfolgreich versandt!</title>
		<base href="${pageContext.request.requestURI}"/>
		<link rel="stylesheet" href="../css/header.css">
		<link rel="stylesheet" href="../css/footer.css">
		<link rel="stylesheet" href="../css/contactAcception.css">
		<link rel="stylesheet" href="../css/cookie.css">
		<script type="text/javascript" src="../js/cookie.js"></script>
	</head>
	<body>
		<%@ include file="../jspf/header.jspf"%>
		<div id="message">
			<h2>${cb.name},<%--JSP-EL: direkter Zugriff auf Beans--%>
				vielen Dank für Ihre Nachricht!<br /> Sie werden nun auf die
				Startseite weitergeleitet.
			</h2>
			<p>Falls Ihr Browser keine automatische Weiterleitung unterstützt,<br>
				<a href="../index.jsp">klicken Sie hier</a>!
			</p>
		</div>
		<%@ include file="../jspf/footer.jspf"%>
		<%-- <c:choose>
				<c:when test="${not empty cb.name}">Falls name-Bean nicht leer ist, dann ...
				</c:when>
				<c:otherwise><jsp:forward page="contact.jsp" /></c:otherwise>...ansonsten weiterleiten
			</c:choose> --%>
	</body>
</html>