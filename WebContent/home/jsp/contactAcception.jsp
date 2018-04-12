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
		<base href="${pageContext.request.requestURI}" />
		<link rel="stylesheet" href="../css/header.css">
		<link rel="stylesheet" href="../css/footer.css">
		<link rel="stylesheet" href="../css/contactAcception.css">
	</head>
	<body>
		<%@ include file="../jspf/header.jspf"%>
		<c:choose>
			<c:when test="${empty cb.name}"><%-- Falls name-Bean nicht leer ist, dann ... --%>
				<div id="message">
					<h2>${cb.name},<%--JSP-EL: direkter Zugriff auf Beans--%>
						vielen Dank für Ihre Nachricht!<br /> Sie werden nun auf die
						Startseite weitergeleitet.
					</h2>
					<p>Falls Ihr Browser keine automatische Weiterleitung unterstützt, 
						<a href="../index.jsp">klicken Sie hier</a>!
					</p>
				</div>
			</c:when>
			<c:otherwise><jsp:forward page="contact.jsp" /></c:otherwise><%-- ...ansonsten weiterleiten --%>
		</c:choose>
		<%@ include file="../jspf/footer.jspf"%>
	</body>
</html>