<!-- Eduard Springer -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%-- page-Direktive für die Kommunikation mit dem Browser --%>
<!DOCTYPE html><!-- Für die Festlegung der Syntax/ Elemente -->
<html>
	<head>
		<meta charset="utf-8">
		<title>Kontakt</title>
		<base href="${pageContext.request.requestURI}"/><!-- Basis für relative Verweise bei MVC-Pattern -->
		<link rel="stylesheet" type="text/css" href="../css/contact.css" /><!-- Einbindung der CSS-Dateien -->
		<link rel="stylesheet" href="../css/header.css">
		<link rel="stylesheet" href="../css/footer.css">
	</head>
	<body>
		<%@ include file="../jspf/header.jspf"%><!-- include-Direktive für die Einbindung des headers -->
		<div id="flexarea">
		<main>
		<form>
			<fieldset>
				<legend>Kontaktformular</legend>
				<div id="title">
					<h3>Fragen, Anregungen, Ideen?</h3>
				</div>
				<div>
					<!-- Attribut "name" zum Einlesen vom Servlet & "id/class" für CSS -->
					<label for="name">Name:</label> <input type="text" name="name"
						id="name" placeholder="Ihr Name" maxlength="40" autofocus required> 
					<label for="name"></label><!-- Label für Prüfzeichen -->
				</div>
				<div>
					<label for="email">E-Mail-Adresse:</label> <input type="email"
						name="email" id="email" placeholder="Ihre E-Mail-Adresse" maxlength="45"
						required pattern="[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+\.[a-z]{2,3}"><!-- \. als Literalzeichen sonst ist . ein Platzhalter, 
						[] Menge der erlaubten Zeichen, {} Einschränkung der Zeichenanzahl -->
					<label for="email"></label>
				</div>
				<div>
					<label for="betreff">Betreff:</label> <input type="text"
						list="betreffListe" name="betreff" id="betreff"
						placeholder="Ihr Betreff" maxlength="40" required> 
						<label for="betreff"></label>
						<!-- Vorschläge für Input-Feld id="betreffliste" -->
					<datalist id="betreffListe">
						<option value="Vorschlag">
						<option value="Idee">
						<option value="Feedback">
						<option value="Profil">
					</datalist>
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
				<button type="submit" name="sendbutton"
					formaction="/bibproject/contactformservlet" formmethod="post">Abschicken</button>
				<button type="reset" name="resetbutton">Zurücksetzen</button>
			</div>
			<p>✘ = Eingabe erforderlich</p>
		</form>
		</main>
		<aside>
			<h3>Kontakt</h3>
			<p> Bibliothek THI <br>Gebäude A <br>Esplanade 10 <br>85049 Ingolstadt
			</p>
			<p>Tel.: 0841 - 9348 2160</p>
		</aside>
		</div>
		<%@ include file="../jspf/footer.jspf"%><!-- include-direktive für die Einbindung des footers -->
	</body>
</html>