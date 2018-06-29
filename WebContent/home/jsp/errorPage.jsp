<!-- Eduard Springer -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Fehlerseite</title>
		<link rel="stylesheet" href="../css/header.css">
		<link rel="stylesheet" href="../css/footer.css">
		<link type="text/css" rel="stylesheet" href="../css/center.css">
	</head>
	<body>
	<%@ include file="../jspf/header.jspf"%>
		<div class="outsidediv">
			<div class="zentriert">
				<h1>Es ist ein Fehler aufgetreten! Bitte setzen Sie sich mit Ihrem Administrator in Verbindung: admin@thibib.de</h1>
				<h2>Die Fehlermeldung lautet: ${pageContext.exception.stackTrace}</h2>
				<h3>Stacktrace:
					<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
						<p>${trace}</p>
					</c:forEach>
				</h3>
			</div>
		</div>
	<%@ include file="../jspf/footer.jspf"%>	
	<%-- Beispiel für einen Fehler:
	<%String irgendwas = null;out.println(irgendwas.toString());%> --%>
	<%-- <%@ page errorPage="errorPage.jsp" %> --%>
	</body>
</html>