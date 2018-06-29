<!-- Sara Viktoria Miller -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="errorPage.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="de">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<base href="${pageContext.request.requestURI}" />
		<link rel="stylesheet" href="../css/admin.css">
		<link rel="stylesheet" href="../css/center.css">
		<link rel="stylesheet" href="../css/header.css">
		<link rel="stylesheet" href="../css/footer.css">
		<script type="text/javascript" src="../js/myreservation.js"></script>
		<title>Reservierungsverwaltung</title>   
	</head> 
	<body>    
		<%@ include file="../jspf/header.jspf" %>       
		<div class="outsidediv">  
			<p></p>  
			<c:if test="${empty lb.username}">
				<jsp:forward page="login.jsp" />         
			</c:if>  
			<c:if test="${lb.adminrechte == false}">
				<p>Keine Berechtigung!</p>  
			</c:if>
			<c:if test="${empty reservierungen}">
				<p>Keine Reservierungen</p>
			</c:if>     
			<c:if test="${not empty reservierungen}">    
				<table> 
				<tr>			
					<th>ReservierungsID</th> 
					<th>Reservierungsdatum</th>    
					<th>Zeitraum</th>    
					<th>PlatznummerID</th> 
					<th>Nachname</th>
					<th>Vorname</th>
					<th>User</th>
					<th>Reservierung löschen?</th>
				</tr>
				<c:forEach var="reservierung" items="${reservierungen}">			
					<tr>
						<td>${reservierung.reservierungID}</td>
						<td>${reservierung.datumString}</td> 
						<td>${reservierung.zeitraum}</td>
						<td>${reservierung.platzID}</td>
						<td>${reservierung.nachname}</td>
						<td>${reservierung.vorname}</td>
						<td>${reservierung.username}</td>
						<td><a  href="/bibproject/deletereservation?ReservierungID=${reservierung.reservierungID}" class="deletelink">✘</a></td> 
					</tr>
				</c:forEach>
				</table>
			</c:if>
		</div> 
		<%@ include file="../jspf/footer.jspf" %>
	</body>
</html>