<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.requestURI}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="8; URL=../index.jsp">
<title>Nachricht erfolgreich versandt!</title>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/contactAcception.css">
</head>
<body>
	<%@ include file="../jspf/header.jspf"%>

	<!--<jsp:useBean id="cb" class="javabeans.ContactBean" scope="session"></jsp:useBean> Bekanntmachung der JavaBean -->
	<!--  <h2><jsp:getProperty name="cb" property="name" />, vielen Dank-->
	<!-- Besser mit JSP-EL -->

	<c:choose>
		<c:when test="${empty lb.name}">
			<!-- Falls name-Bean nicht leer ist, dann ... ansonsten zum Kontaktformular weiterleiten -->
			<div id="message">
				<h2>${cb.name},
					<!-- ${cb["name"]} ist dasselbe -->
					vielen Dank für Ihre Nachricht!<br /> Sie werden nun auf die
					Startseite weitergeleitet.
				</h2>
				<p>
					Falls Ihr Browser keine automatische Weiterleitung unterstützt, <a
						href="../index.jsp">klicken Sie hier</a>!
				</p>
			</div>
		</c:when>
		<c:otherwise><jsp:forward page="contact.jsp" /></c:otherwise>
	</c:choose>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>

<!-- Eduard Springer -->