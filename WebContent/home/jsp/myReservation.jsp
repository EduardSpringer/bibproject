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
	
	<div id="flex">
		<section>
			<h1>Einzelreservierungen</h1>
			
			<table>
			<tr>
				<th>Nummer</th>
				<th>Datum</th>
				<th>Uhrzeit</th>
				<th>Platznummer</th>
				<th>Löschen</th>
			</tr>
			<c:forEach var="termin" items="${einzeltermine}">
				<tr>
					<td>NR</td>
					<td>${termin.datumString}</td>
					<td>${termin.zeitraum}</td>
					<td>${termin.platzID}</td>
					<td><a href="">X</a></td>
				</tr>
			</c:forEach>
		</table>
		
		</section>
		
		<section>
			<h1>Wiederholungsreservierungen</h1>
		
		</section>
	</div>
		<%@ include file="../jspf/footer.jspf" %>
	</body>
</html>