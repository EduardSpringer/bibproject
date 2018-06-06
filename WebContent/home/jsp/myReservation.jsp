<%--Helene Akulow --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Meine Termine</title>
		<base href="${pageContext.request.requestURI}" />
		<link rel="stylesheet" href="../css/myReservation.css">
		<link rel="stylesheet" href="../css/header.css">
		<link rel="stylesheet" href="../css/footer.css">

		<script type="text/javascript" src="../js/myreservation.js"></script>

	</head>
	<body>
		<%@ include file="../jspf/header.jspf" %>
		<!-- Überprüfung, ob Benutzer eingeloggt ist, ansonsten Weiterleitung an die login.jsp -->
		<c:if test="${empty lb.username}">
			<jsp:forward page="login.jsp" />
		</c:if>
	
	<div id="flex">
		<section>
			<h1>Einzelreservierungen</h1>
			
			<c:if test="${empty einzeltermine}">
				<p class="keineTermine">Sie haben zur Zeit keine Einzelreservierungen</p>
			</c:if>
			
			<c:if test="${not empty einzeltermine}">
				<table>
					<tr>
						<th></th>
						<th>Datum</th>
						<th>Uhrzeit</th>
						<th>Platznummer</th>
						<th>Löschen</th>
					</tr>
					<c:forEach var="termin" items="${einzeltermine}" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>${termin.datumString}</td>
							<td>${termin.zeitraum}</td>
							<td>${termin.platzID}</td>
							<td><a href="/bibproject/deletereservationservlet?reservierungID=${termin.reservierungID}"
									class ="deletelink">✘</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</section>
		
		<section>
			<h1>Wiederholungsreservierungen</h1>
			
			<c:if test="${empty wdhtermine}">
				<p class="keineTermine">Sie haben zur Zeit keine Wiederholungsreservierungen</p>
			</c:if>
			<c:forEach var="terminBez" items="${terminBezSet}" varStatus="status">
				<p><span class="terminbez"> ${terminBez} </span>
					<a href="/bibproject/deletereservationservlet?terminBez=${terminBez}&reservierungID=0"
						class ="deletewdhtermin">✘</a>
				</p>
				
			<table class="hiddentable">
			<tr>			
				<th>Datum</th>
				<th>Uhrzeit</th>
				<th>Platznummer</th>
				<th>Löschen</th>
			</tr>
			
			<c:forEach var="termin" items="${wdhtermine}">
				<c:if test="${terminBez eq termin.terminbezeichnung}">
							
				<tr>
					<td>${termin.datumString}</td>
					<td>${termin.zeitraum}</td>
					<td>${termin.platzID}</td>
					<td><a href="/bibproject/deletereservationservlet?reservierungID=${termin.reservierungID}&terminBez="
							class="deletelink">✘</a>
					</td>
				</tr>
				</c:if>
			</c:forEach>
			

		</table>
		</c:forEach>
		
		</section>
	</div>
		<%@ include file="../jspf/footer.jspf" %>
	</body>
</html>