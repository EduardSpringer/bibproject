<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Anmeldung</title>
<link rel="stylesheet" href="../css/login.css">
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
</head>
<body>
	<%@ include file="../jspf/header.jspf" %>
	<main>
	<div class=outsidediv>
		<div class="zentriert">
			
			<form id="loginform" action="/bibproject/loginservlet" method="post">
			<fieldset><legend>Anmeldung</legend>		
				<div>
					<label for="username">Name</label>
					<input id="username" name="username" value="${cookie.usernameCookie.value}" type="text" maxlength="7" required pattern="[A-Za-z]{3}[0-9]{4}|admin">
				</div>
				<div>
					<label for="password">Passwort</label>
					<input id="password" name="password" type="password" maxlength="10" required pattern="{5,10}">
				</div>			
				
				<div>
					<input type="checkbox" name="check" id="checkbox" value="merken">
					<label for="checkbox">Anmeldenamen merken</label>
				</div>
				
				<p>${lb.fehlermeldung}</p>
				 
							
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