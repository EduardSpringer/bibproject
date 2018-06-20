<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!-- https://stackoverflow.com/questions/24391078/how-to-change-the-default-message-of-the-required-field-in-the-popover-of-form-c/24392931 -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profil bearbeiten</title>
<link rel="stylesheet" href="../css/content.css" type="text/css">
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/center.css">
<link rel="stylesheet" href="../css/footer.css">
<script src="../js/registry.js"></script> 
</head> 
<body> 
	<%@ include file="../jspf/header.jspf" %>     
	<div class="outsidediv"> 
	<section class="zentriert">  
		<h1>Bearbeite jetzt dein Profil!</h1>              
			<form action="/bibproject/profileeditservlet?username=${lb.username}" id="profileedit" method="post" enctype="multipart/form-data">
				<p>
					<label for="vorname">Vorname</label>
					<input name="vorname" type="text" size="30" maxlength="30" oninvalid="this.setCustomValidity('Vorname darf nicht länger als 30 Zeichen sein!')">
				</p>
				<p>
					<label for="nachname">Nachname</label>
					<input name="nachname" type="text" size="30" maxlength="30" oninvalid="this.setCustomValidity('Nachname darf nicht länger als 30 Zeichen sein!')">
				</p>
				<p>
					<label for="passwort">Neues Passwort</label>
					<input name="passwort" type="password" id="passwort" size="10" pattern=".{5,10}" oninvalid="this.setCustomValidity('Passwort darf zwischen 5 und 10 Zeichen lang sein!')">
				</p>
				<p>
					<label for="passwortbestaetigen">Passwort bestätigen</label>
					<input name="passwortbestaetigen" type="password" id="passwortbestaetigen" size="10" pattern=".{5,10}" oninvalid="this.setCustomValidity('Passwort darf zwischen 5 und 10 Zeichen lang sein!')">
				</p>
				<h5>(* Pflichtfeld)</h5>
				<div class="upload-button">
					Profilbild ändern
					<input type="file" name="profilbild" id="profilbild" accept=".jpg, .jpeg, .png"/>
				</div>
				<p></p>
				<button type="submit" id="absenden">Änderungen übernehmen</button>
			</form>
	</section>
	</div>
	<%@ include file="../jspf/footer.jspf" %>
</body>
</html>