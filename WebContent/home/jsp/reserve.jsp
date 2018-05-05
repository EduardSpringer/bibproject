<!-- Eduard Springer -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Platzreservierung</title>
		<base href="${pageContext.request.requestURI}" />
		<link type="text/css" rel="stylesheet" href="../css/reserve.css" />
		<link type="text/css" rel="stylesheet" href="../css/header.css">
		<link type="text/css" rel="stylesheet" href="../css/footer.css">
		<script type="text/javascript" src="../js/init.js"></script>
		<script type="text/javascript" src="../js/listener.js"></script>
	</head>
	<body>
		<%@ include file="../jspf/header.jspf"%>
		<!-- Überprüfung, ob Benutzer eingeloggt ist, ansonsten Weiterleitung an die login.jsp -->
		<c:if test="${empty lb.username}">
			<jsp:forward page="login.jsp" />
		</c:if>

		<div id="flexarea">
		<main>
		<!-- Überprüfung, ob JavaScript aktiv ist -->
			<noscript>Um den vollen Funktionsumfang dieser Webseite zu
				erfahren, benötigen Sie aktiviertes JavaScript.</noscript>

			<p>
			<label for="zeitraum">Zeitraum:</label>
				<select id="zeitraum">
					<!-- <option value="choose">auswählen</option> -->
					<option value="08:00 - 10:00" selected>08:00 - 10:00</option>
					<option value="10:00 - 12:00">10:00 - 12:00</option>
					<option value="12:00 - 14:00">12:00 - 14:00</option>
					<option value="14:00 - 16:00">14:00 - 16:00</option>
					<option value="16:00 - 18:00">16:00 - 18:00</option>
					<option value="18:00 - 20:00">18:00 - 20:00</option>
					<option value="20:00 - 22:00">20:00 - 22:00</option>
					<option value="22:00 - 24:00">22:00 - 24:00</option>
				</select>
			</p>
			
			<h3 id="datum">Platzverteilung:</h3>
			<div id="platzverteilung">
			</div>
		</main>
		<aside>
			<h1>Bitte den Zeitraum auswählen:</h1>
			<p id="uhrzeit"></p>

			<h1>Bitte den Sitzplatz per Mausklick auswählen:</h1>
			<p>Ausgewählte Sitzplatz-Nr.: <div id="platznr">-</div></p>
			<button type="submit" name="send" value="submit">Bestätigen</button>
		</aside>
		</div>
		<%@ include file="../jspf/footer.jspf"%>
	</body>
</html>