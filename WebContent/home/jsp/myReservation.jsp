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
				<p>Sie haben zur Zeit keine Einzelreservierungen</p>
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
							<td><a href="/bibproject/deletereservationservlet?reservierungID=${termin.reservierungID}">X</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</section>
		
		<section>
			<h1>Wiederholungsreservierungen</h1>
			
			<c:if test="${empty wdhtermine}">
				<p>Sie haben zur Zeit keine Wiederholungsreservierungen</p>
			</c:if>
			<c:forEach var="terminBez" items="${terminBezSet}" varStatus="status">
				<p id="teminbez">${terminBez} <a href="/bibproject/deletereservationservlet?terminBez=${terminBez}&reservierungID=0">X</a></p>
				
			<table>
			<tr>
				<th> </th>
				<th>Datum</th>
				<th>Uhrzeit</th>
				<th>Platznummer</th>
				<th>Löschen</th>
			</tr>
			
			<c:forEach var="termin" items="${wdhtermine}" varStatus="status">
				<c:if test="${terminBez == termin.terminbezeichnung}">
							
				<tr>
					<td>${status.count}</td>
					<td>${termin.datumString}</td>
					<td>${termin.zeitraum}</td>
					<td>${termin.platzID}</td>
					<td><a href="/bibproject/deletereservationservlet?reservierungID=${termin.reservierungID}&terminBez=">X</a></td>
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