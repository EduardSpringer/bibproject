<!-- Sara Viktoria Miller -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="errorPage.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="de">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<base href="${pageContext.request.requestURI}" />
		<link rel="stylesheet" href="../css/content.css">
		<link rel="stylesheet" href="../css/admin.css">
		<link rel="stylesheet" href="../css/header.css">
		<link rel="stylesheet" href="../css/footer.css">
	<title>User-Verwaltung</title>      
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
			<c:if test="${empty users}">
				<p>Keine User vorhanden</p>
			</c:if>       
			<c:if test="${not empty users}">    
				<table> 
				<tr>			  
					<th>Vorname</th> 
					<th>Nachname</th>    
					<th>EMail</th>    
					<th>Username</th> 
					<th>User hat Adminrechte?</th>
					<th>Adminrechte vergeben?</th>
					<th>User löschen?</th>    
				</tr>
				<c:forEach var="user" items="${users}">			
					<tr>
						<td>${user.vorname}</td>
						<td>${user.nachname}</td> 
						<td>${user.email}</td>
						<td>${user.username}</td>
						<c:choose>
						<c:when test="${user.adminrechte == true}">
							<td>Ja</td>
							<td></td> 
						</c:when>
						<c:otherwise>
							<td>Nein</td>
							<td><a  href="/bibproject/makeuseradmin?User=${user.username}">✔</a></td> 
						</c:otherwise>
						</c:choose>
						<td><a  href="/bibproject/deleteuser?User=${user.username}">✘</a></td> 	
					</tr>
				</c:forEach>
				</table>
			</c:if>
		</div>
		<%@ include file="../jspf/footer.jspf" %>
	</body>
</html>