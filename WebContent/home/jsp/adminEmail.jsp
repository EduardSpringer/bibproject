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
		<title>Kontaktverwaltung</title>    
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
			<c:if test="${empty kontakte}"> 
				<p>Keine Kontaktanfragen</p> 
			</c:if>      
				<c:if test="${not empty kontakte}">   
					<table> 
					<tr>			 
						<th>NachrichtID</th>
						<th>Betreff</th>              
						<th>Nachricht</th>    
						<th>Name</th> 
						<th>Email</th> 
						<th>Gelesen?</th>
						<th>Als "Gelesen" markieren?</th> 
						<th>Nachricht löschen?</th>                      
					</tr>
					<c:forEach var="kontakt" items="${kontakte}">			
					<tr> 
						<td>${kontakt.id}</td>  
						<td>${kontakt.betreff}</td>
						<td>${kontakt.nachricht}</td> 
						<td>${kontakt.name}</td>
						<td>${kontakt.email}</td>
						<c:choose>
							<c:when test="${kontakt.status == true}"> 
								<td>Ja</td>
								<td></td>
							</c:when>
							<c:otherwise>
								<td>Nein</td>
								<td><a  href="/bibproject/nachrichtgelesen?KontaktID=${kontakt.id}" class="statusaendern">✔</a></td> 
							</c:otherwise>
						</c:choose>
						<td><a  href="/bibproject/deletemessage?KontaktID=${kontakt.id}" class="deletelink">✘</a></td> 
					</tr>
					</c:forEach>
					</table>
				</c:if>
				<p></p> 
		</div>
		<%@ include file="../jspf/footer.jspf" %>
	</body>
</html>