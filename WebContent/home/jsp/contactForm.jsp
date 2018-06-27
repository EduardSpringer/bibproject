<!-- Eduard Springer -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Kontakt</title>
		<base href="${pageContext.request.requestURI}"/>
		<link rel="stylesheet" type="text/css" href="../css/contact.css"/>
		<link rel="stylesheet" href="../css/header.css">
		<link rel="stylesheet" href="../css/footer.css">
		<script type="text/javascript" src="../js/contactFormListener.js"></script>
	</head>
	<body>
		<%@ include file="../jspf/header.jspf"%>
		<div id="flexarea">
		<main>
		<form id="contactForm" action="/bibproject/contactformservlet" method="post">
			<fieldset>
				<legend>Kontaktformular</legend>
				<div id="title">
					<h3>Fragen, Anregungen, Ideen?</h3>
				</div>
				<div>
					<label for="name">Name:</label> <input type="text" name="name"
						id="name" placeholder="Ihr Name" maxlength="25" autofocus required> 
					<label for="name"></label>
					<!-- 2.Label für das Prüfzeichen! -->
				</div>
				<div>
					<label for="email">E-Mail-Adresse:</label> <input type="email"
						name="email" id="email" placeholder="Ihre E-Mail-Adresse" maxlength="30"
						required pattern="[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+\.[a-z]{2,3}">
					<label for="email"></label>
					<!-- Aufbau von required pattern:
					[] Menge der erlaubten Zeichen; {min, max} Einschränkung der Zeichenanzahl; 
					\. Literalzeichen für Punkt -->
				</div>
				<div>
					<label for="betreff">Betreff:</label> <input type="text"
						list="betreffListe" name="betreff" id="betreff"
						placeholder="Ihr Betreff" maxlength="40" required> 
						<label for="betreff"></label>
					<datalist id="betreffListe">
						<option value="Vorschlag">
						<option value="Idee">
						<option value="Feedback">
						<option value="Profil">
					</datalist>
					<!-- datalist: Gibt Vorschläge für das Input-Feld "Betreff" aus! -->
				</div>
				<div>
					<label for="nachricht">Nachricht:</label>
					<textarea name="nachricht" id="nachricht"
						placeholder="Hier Nachricht eingeben..." rows="10" maxlength="500"
						required></textarea>
					<label for="nachricht"></label>
				</div>
			</fieldset>
			<div class="buttons">
				<button type="submit" name="sendbutton">Abschicken</button>
				<button type="reset" id="contactFormReset" name="resetbutton">Zurücksetzen</button>
			</div>
			<p>✘ = Eingabe erforderlich</p>
		</form>
		</main>
		<aside>
			<h3>Kontakt</h3>
			<p>Bibliothek THI <br>Gebäude A <br>Esplanade 10 <br>85049 Ingolstadt</p>
			<p>Tel.: 0841 - 9348 2160</p>
		</aside>
		</div>
		<%@ include file="../jspf/footer.jspf"%>
	</body>
</html>