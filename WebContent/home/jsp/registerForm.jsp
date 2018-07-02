<!-- Sara Viktoria Miller -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="de">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Registrierung</title>  
		<base href="${pageContext.request.requestURI}" />
		<link rel="stylesheet" href="../css/content.css">
		<link rel="stylesheet" href="../css/header.css">
		<link rel="stylesheet" href="../css/center.css">     
		<link rel="stylesheet" href="../css/footer.css">	
		<script src="../js/registry.js"></script> 
	</head>
	<body>  
		<%@ include file="../jspf/header.jspf" %>    
		<div class="outsidediv">                 
		<c:if test="${not empty lb.username}">
			<jsp:forward page="profileEditForm.jsp" />         
		</c:if>           
		<h1>Hast auch du das lange Suchen satt?</h1>     
		<h2>Registriere dich jetzt und finde deinen Sitzplatz!</h2>    
		<h2 id="fehlermeldung">${lb.fehlermeldung}</h2>
		<form action="/bibproject/registerservlet?username=${lb.username}" id="registrierung" method="post" enctype="multipart/form-data">                     
			<p>
				<label for="vorname">Vorname *</label>       
				<input name="vorname" id="vorname" type="text" size="30" maxlength="30" required placeholder="Vorname darf nicht länger als 30 Zeichen sein">
				<span></span>
			</p> 
			<p>             
				<label for="nachname">Nachname *</label>
				<input name="nachname" id="nachname" type="text" size="30" maxlength="30" required placeholder="Nachname darf nicht länger als 30 Zeichen sein">
				<span></span> 
			</p>     
			<p>
				<label for="email">E-Mail * </label>
				<input name="email" type="email" id="email" required pattern="[A-Za-z]{3}[0-9]{4}@thi.de" placeholder="E-Mail-Adresse muss eine THI-Adresse sein">
				<span></span>
			</p>	
			<p> 
				<label for="passwort">Passwort * </label>
				<input name="passwort" id="passwort" type="password" size="10" required pattern=".{5,10}" placeholder="Passwort darf zwischen 5 und 10 Zeichen lang sein">
				<span></span>
			</p>
			<p>
				<label for="passwortbestaetigen">Passwort bestätigen *</label>
				<input name="passwortbestaetigen" id="passwortbestaetigen" type="password" size="10" required pattern=".{5,10}" placeholder="Passwort darf zwischen 5 und 10 Zeichen lang sein">
				<span></span>
			</p>
			<p></p>
			<h5>(* Pflichtfeld)</h5>
			<div class="upload-button">
				Profilbild hochladen
			<input type="file" name="profilbild" id="profilbild" accept=".jpg, .jpeg, .png"/>		
			</div>
			<p></p>    
			<button type="submit" name = "registrieren" id="register">Registrieren</button>
		</form>  
		<p></p>
		</div>
		<%@ include file="../jspf/footer.jspf" %>
	</body>
</html>