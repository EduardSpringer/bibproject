<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.requestURI}" />
<meta charset="UTF-8">
<title>Kontakt</title>
<link rel="stylesheet" type="text/css" href="../css/contact.css" />
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
</head>
<body>
	<%@ include file="../jspf/header.jspf"%>
	<div id="flexarea">
		<main>
		<form>
			<fieldset>
				<legend>Kontaktformular</legend>
				<div id="title">
					<h3>Fragen, Anregungen, Ideen?</h3>
				</div>
				<div>
					<label for="name">Name:</label> <input type="text" name="name"
						id="name" placeholder="Ihr Name" maxlength="20" autofocus required>
					<label for="name"></label>
				</div>
				<div>
					<label for="email">E-Mail-Adresse:</label> <input type="email"
						name="email" id="email" placeholder="Ihre E-Mail-Adresse" required>
					<label for="email"></label>
				</div>
				<div>
					<label for="betreff">Betreff:</label> <input type="text"
						list="betreffliste" name="betreff" id="betreff"
						placeholder="Ihr Betreff" maxlength="20" required> <label
						for="betreff"></label>
					<datalist id="betreffliste">
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
				<button type="reset" name="resetbutton">Zur&uuml;cksetzen</button>
			</div>
			<p>✘ = Eingabe erforderlich</p>
		</form>
		</main>
		<aside>
			<h3>Kontakt</h3>
			<p>
				Bibliothek THI <br>Geb&auml;ude A <br>Esplanade 10 <br>85049
				Ingolstadt
			</p>
			<p>Tel.:0841 - 9348 2160</p>
		</aside>
	</div>
	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>

<!-- Eduard Springer -->