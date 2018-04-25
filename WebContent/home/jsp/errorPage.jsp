<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Fehlerseite</title>
		<link rel="stylesheet" href="../css/header.css">
		<link rel="stylesheet" href="../css/footer.css">
	</head>
	<body>
	<%@ include file="../jspf/header.jspf"%>
		<h1>Es ist ein Fehler aufgetreten! Bitte setzen Sie sich mit Ihrem Administrator in Verbindung: admin@thibib.de</h2>
		<h2>Die Fehlermeldung lautet: ${pageContext.exception.stackTrace}</h1>
		<h3>Stacktrace:
			<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
				<p>${trace}</p>
			</c:forEach>
		</h3>
	<%@ include file="../jspf/footer.jspf"%>
	</body>
</html>