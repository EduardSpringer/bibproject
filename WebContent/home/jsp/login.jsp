<!-- Helene Akulow -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html> <!-- Document Type Definition (legt die verwendbaren Elemente und die Syntax fest) -->
<html> <!-- Wurzelelement-> umfasst das gesamte HTML-Dokument -->
	<head> <!-- Kopf: Enthält Titel der Seite, Meta-Angaben zum Dokument (z.B. Zeichenkodierung) sowie
				Angaben zum Nachladen von JavaScript- und Stylesheet-Dateien -->
		<meta charset="UTF-8">
		<title>Anmeldung</title>
		<base href="${pageContext.request.requestURI}" />
		<link rel="stylesheet" href="../css/login.css">
		<link rel="stylesheet" href="../css/header.css">
		<link rel="stylesheet" href="../css/center.css">
		<link rel="stylesheet" href="../css/footer.css">
	</head>
	
	<body> <!-- eigentlicher Inhalt der Seite, der vom Browser angezeigt wird -->
		<%@ include file="../jspf/header.jspf" %>
		<main>
			<div class=outsidediv>
				<div class="zentriert">
					
					<form id="loginform" action="/bibproject/loginservlet" method="post">
						<fieldset>
							<legend>Anmeldung</legend>		
							<p id="fehlermeldung">${lb.fehlermeldung}</p>
							<div>
								<label for="username">Name:</label>
								<input type ="text" id="username" name="username" value="${cookie.usernameCookie.value}" 
								type="text" maxlength="7" size="15" required pattern="[A-Za-z]{3}[0-9]{4}|admin" title="abc1234">
							</div>
							<div>
								<label for="password">Passwort:</label>
								<input id="password" name="password" type="password" maxlength="10" size="15" required pattern="{5,10}">
							</div>			
							
							<div>
								<input type="checkbox" name="check" id="checkbox" value="merken">
								<label for="checkbox">Anmeldenamen merken</label>
							</div>
														
							<button type="submit" name="loginbutton" value="login">Anmelden</button>
							<p id="register">Noch nicht registriert?<br>
							<a href="registerForm.jsp">Jetzt registrieren!</a>
							</p>
						</fieldset>	
					</form>
				
					
				</div>
			</div>
		</main>
		<%@ include file="../jspf/footer.jspf" %>
	</body>
</html>