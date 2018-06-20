<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registrierung</title> 
<base href="${pageContext.request.requestURI}" />  
<link rel="stylesheet" href="../css/content.css" type="text/css">
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/center.css"> 
<link rel="stylesheet" href="../css/footer.css">
		<!-- https://stackoverflow.com/questions/16990378/javascript-form-validation-with-password-confirming -->
		<!-- https://stackoverflow.com/questions/24391078/how-to-change-the-default-message-of-the-required-field-in-the-popover-of-form-c/24392931 -->
		
		<script src="../js/registry.js"></script> 
</head>
<body> 
	<%@ include file="../jspf/header.jspf" %>    
	<div class="outsidediv"> 
	<section class="zentriert">                   
	<h1>Hast auch du das lange Suchen satt?</h1>     
	<h2>Registriere dich jetzt und finde deinen Sitzplatz!</h2>
			<form action="/bibproject/registerservlet" id="registrierung" method="post" enctype="multipart/form-data">
			 <p id="fehlermeldung">${lb.fehlermeldung}</p>   
				<p>
					<label for="vorname">Vorname *</label>
					<input name="vorname" type="text" size="30" maxlength="30" required oninvalid="this.setCustomValidity('Pflichtfeld: Vorname darf nicht länger als 30 Zeichen sein!')">
				</p> 
				<p>             
					<label for="nachname">Nachname *</label>
					<input name="nachname" type="text" size="30" maxlength="30" required oninvalid="this.setCustomValidity('Pflichtfeld: Nachname darf nicht länger als 30 Zeichen sein!')">
				</p>     
				<p>
					<label for="email">E-Mail * </label>
					<input name="email" type="email" id="email" required pattern="[A-Za-z]{3}[0-9]{4}@thi.de" oninvalid="this.setCustomValidity('Pflichtfeld: E-Mail-Adresse muss eine THI-Adresse sein!')">
				</p>
				
				<p> 
					<label for="passwort">Passwort * </label>
					<input name="passwort" id="passwort" type="password" size="10" required pattern=".{5,10}" oninvalid="this.setCustomValidity('Pflichtfeld: Passwort darf zwischen 5 und 10 Zeichen lang sein!')">
				</p>
				<p>
					<label for="passwortbestaetigen">Passwort bestätigen *</label>
					<input name="passwortbestaetigen" id="passwortbestaetigen" type="password" size="10" required pattern=".{5,10}" oninvalid="this.setCustomValidity('Pflichtfeld: Passwort darf zwischen 5 und 10 Zeichen lang sein!')">
				</p>
				<p></p>
				<h5>(* Pflichtfeld)</h5>
				<div class="upload-button"> 
					Profilbild hochladen 
					<input type="file" name="profilbild" id="profilbild" accept=".jpg, .jpeg, .png"/>
				</div>
				<p></p>    
				<button type="submit" name = "registrieren" id="absenden">Registrieren</button>
			</form>  
	</section>
	</div>
	<%@ include file="../jspf/footer.jspf" %>
</body>
</html>