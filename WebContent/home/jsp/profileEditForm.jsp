<!-- Sara Viktoria Miller -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="de">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Profil bearbeiten</title>
		<link rel="stylesheet" href="../css/content.css" type="text/css">
		<link rel="stylesheet" href="../css/header.css">
		<link rel="stylesheet" href="../css/center.css">
		<link rel="stylesheet" href="../css/footer.css">   
		<script src="../js/edit.js"></script> 
	</head> 
	<body>          
		<%@ include file="../jspf/header.jspf" %>     
		<div class="outsidediv">     
		<section class="zentriert">   
			<c:if test="${empty lb.username}">
				<jsp:forward page="login.jsp" />             
			</c:if>   
			<h1>Bearbeite jetzt dein Profil!</h1>                 
			<form action="/bibproject/profileeditservlet?username=${lb.username}" id="profileedit" method="post" enctype="multipart/form-data">
				<p>
					<label for="bearbeitevorname">Vorname</label>
					<input name="vorname" id="bearbeitevorname" type="text" size="30" maxlength="30" placeholder="Vorname darf nicht länger als 30 Zeichen sein">
					<span></span>
				</p>
				<p>
					<label for="bearbeitenachname">Nachname</label>
					<input name="nachname" id="bearbeitenachname" type="text" size="30" maxlength="30" placeholder="Nachname darf nicht länger als 30 Zeichen sein">
					<span></span>
				</p>
				<p>
					<label for="bearbeitepasswort">Neues Passwort</label>
					<input name="passwort" type="password" id="bearbeitepasswort" size="10" pattern=".{5,10}" placeholder="Passwort darf zwischen 5 und 10 Zeichen lang sein">
					<span></span>
				</p>
				<p>
					<label for="bearbeitepasswortbestaetigen">Passwort bestätigen</label>
					<input name="passwortbestaetigen" type="password" id="bearbeitepasswortbestaetigen" size="10" pattern=".{5,10}" placeholder="Passwort darf zwischen 5 und 10 Zeichen lang sein">
					<span></span>
				</p>
				<div>
				  <input type="checkbox" name="bild" id="bild" value="bild">
				  <label for="bild">Ich will kein Profilbild mehr!</label>
				</div>
				<p></p>
				<div class="upload-button">
					Profilbild hochladen
					<input type="file" name="profilbild" id="bildaendern" accept=".jpg, .jpeg, .png"/>
				</div>
				<p></p>
				<button type="submit" id="bearbeiten">Änderungen übernehmen</button>
			</form>
		</section>
		</div>
		<%@ include file="../jspf/footer.jspf" %>
	</body>
</html>