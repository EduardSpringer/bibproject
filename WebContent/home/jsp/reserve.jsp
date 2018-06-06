 <!-- Eduard Springer -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Platzreservierung</title>
		<base href="${pageContext.request.requestURI}" />
		<link type="text/css" rel="stylesheet" href="../css/reserve.css" />
		<link type="text/css" rel="stylesheet" href="../css/header.css">
		<link type="text/css" rel="stylesheet" href="../css/footer.css">
		<script type="text/javascript" src="../js/initPeriods.js"></script>
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
			<!-- Aktuelles Datum mittels JSP:fmt ermitteln, um min-Wert für Kalendar zu gewinnen-->
			<jsp:useBean id="aktuellesDatum" class="java.util.Date"/>    
			<fmt:formatDate value="${aktuellesDatum}" pattern="yyyy-MM-dd" var="aktuellesDatum"/>
			
			<!-- https://stackoverflow.com/questions/13661212/get-date-of-tomorrow-using-jstl/25058675 -->
			<jsp:useBean id="bisDatum" class="java.util.Date"/> 
			<jsp:setProperty name="bisDatum" property="time" value="${bisDatum.time + 604800000}"/>
			<fmt:formatDate value="${bisDatum}" pattern="yyyy-MM-dd" var="bisDatum"/> 
			
			<!-- Formular -->
			<form id="formular" action="/bibproject/reservationservlet" method="post"></form>
	
			<div id="auswahl">
				<label for="datum">Datum: </label>
				<input type="date" name="datum" id="datum" min="${aktuellesDatum}" max="2018-10-02" value="${aktuellesDatum}" required form="formular"> 
				<label for="zeitraum">Zeitraum:</label>
				<select name="zeitraum" id="zeitraum" form="formular"></select>
			</div>
			<!-- Überprüfung, ob JavaScript aktiv ist -->
			<noscript><br>Um den vollen Funktionsumfang dieser Webseite zu
				nutzen, benötigen Sie aktiviertes JavaScript!<a href="https://www.enable-javascript.com/de/" target="_blank">
				<br>Klicken Sie hier</a>, um mehr zu erfahren!</noscript>
			<div id="platzverteilung"></div>
		</main>
		<aside>
			<h1>Reservierungsdaten</h1>
			<p id="selectedNr">Ausgewählte Sitzplatz-Nr.:</p>
			<input type="number" id="platznr" name="platznr" form="formular" readonly/>
			<div id="termintyp" >
				<input type="radio" id="einzeltermin" name="termin" value="einzeltermin" form="formular" checked>
				<label for="einzeltermin">Einzeltermin</label>
				<input type="radio" id="wiederholtermin" name="termin" form="formular" value="wiederholtermin">
				<label for="wiederholtermin">Wiederholtermin</label>
			</div>
			<div id="terminname">
				<label for="terminbezeichnung">Terminbezeichnung:</label>
				<input type="text" id="terminbezeichnung" name="terminbezeichnung" placeholder="max. 30 Zeichen" maxlength="30" disabled form="formular">
			</div>
			<div id="termineingrenzung">
				<label for="vom">vom</label>
				<input type="date" id="vom" name="vom" min="${aktuellesDatum}" max="2018-10-02" value="${aktuellesDatum}" disabled form="formular">
				<label for="bis">bis</label>
				<input type="date" id="bis" name="bis" min="${bisDatum}" max="2018-10-02" step="7" value="${bisDatum}" disabled form="formular">
			</div>
			<p id="dauer" >Dauer: 1 Woche</p>
			<div id="actionbuttons">
				<!-- <button type="reset" name="reset" form="formular">Zurücksetzen</button> -->
				<button type="submit" id="submitbutton" name="reservieren" value="submit" form="formular">Reservieren</button>
			</div>
		</aside>
		</div>
		<%@ include file="../jspf/footer.jspf"%>
	</body>
</html>