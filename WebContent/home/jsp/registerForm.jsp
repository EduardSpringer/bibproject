<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registrierung</title>
<link rel="stylesheet" href="../css/content.css" type="text/css">
<link rel="stylesheet" href="../css/header.css" type="text/css">
</head>
<body>
	<%@ include file="../jspf/header.jspf" %>
	<section class="zentriert">
		<h1>Registrieren</h1>
			<form action="/bibproject/registerservlet" method="post">
				<p>
					<label for="vorname">Vorname *</label>
					<input name="vorname" type="text" size="30" maxlength="30" required>
				</p>
				<p>             
					<label for="nachname">Nachname *</label>
					<input name="nachname" type="text" size="30" maxlength="30" required>
				</p>     
				<p>
					<label for="email">E-Mail * </label>
					<input name="email" type="email" required pattern="[A-Za-z]{3}[0-9]{4}@thi.de">
				</p>
				
				<p>
					<label for="passwort">Passwort * </label>
					<input name="passwort" type="password" size="10" required pattern="{5,10}">
				</p>
				<p>
					<label for="passwortbestaetigen">Passwort bestätigen *</label>
					<input name="passwortbestaetigen" type="password" size="10" required pattern="{5,10}">
					<!--< Passwortbestätigung mit JavaScript? >-->
				</p>
				<h5>(* Pflichtfeld)</h5>
				<div class="upload-button"> 
					Profilbild hochladen 
					<input type="file" name="profilbild" accept="image/*">
				</div>
				<p></p>
				<button type="submit" name="registrierung" value="submit">Registrieren</button>
			</form>
	</section>
	<%@ include file="../jspf/footer.jspf" %>
</body>
</html>