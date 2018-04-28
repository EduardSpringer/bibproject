<!-- Helene Akulow -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html> <!-- Document Type Definition (legt die verwendbaren Elemente und die Syntax fest) -->
<html> <!-- umfasst das gesamte HTML-Dokument -->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Startseite</title>
		<meta name="Webseite der Bibliothek an der THI" content="Platzreservierung"/>
		<base href="${pageContext.request.requestURI}" />
		<link rel="stylesheet" href="css/header.css">
		<link rel="stylesheet" href="css/homepage.css">
		<link rel="stylesheet" href="css/footer.css">
		<script type="text/javascript" src="javascript/cookie.js"></script>
	</head>
	<body>
		<header>
			<div class="center">
				<div>
					<c:choose>
						<c:when test="${empty lb.username}">
							<div class="loginfeld">
								<a href="jsp/login.jsp">Anmelden</a> 
							</div>						
						</c:when>
						<c:otherwise>
						<div class="loginfeld">	
						
							<c:choose>
								<c:when test="${empty lb.profilbild}">
									<img id="profilbild" src="bildservlet?username=${lb.username}" width="150" height="150" alt="Profilbild">		
								</c:when>
								<c:otherwise>
									<img id="profilbild" src="img/keinBild.jpg" width="150" height="150" alt="Profilbild1">
									<!-- src="img/keinBild.jpg" src="de.login/bildservlet?username=${lb.username}"-->
								</c:otherwise>
							</c:choose>
						</div>
							
						<div class="loginfeld">				
							<ul>
								<li><a>${lb.username} ▼</a> 
									<ul>
										<li><a href="jsp/profileEditForm.jsp">Profil bearbeiten</a></li>
										<li><a href="jsp/myReservation.jsp">Meine Termine</a></li>
										<li><a href="/bibproject/logoutservlet">Logout</a></li>
									</ul>
								</li>
							</ul>
						</div>	
						</c:otherwise>
					</c:choose>
				</div>
	
				<a href="index.jsp">
					<img src="img/logo.jpg" width="179" height="173" alt="Logo">
				</a>
				
				<hr>
				
				<nav>			
					<ul>
					<li><a href="index.jsp">Startseite</a></li>
					<li><a href="jsp/reserve.jsp">Reservieren</a></li>
					<li><a>Quicklinks ▼</a>
						<ul>
							<li><a href="https://www3.primuss.de/stpl/login.php?FH=fhin&Lang=de">Stundenplan</a></li>
							<li><a href="https://www.thi.de/">Technische Hochschule</a></li>
							<li><a href="https://moodle.thi.de/moodle/">Moodle</a></li>
							<li><a href="https://print.thi.de">Drucken</a></li>
							<li><a href="https://exchange.thi.de">Webmail</a></li>
							<li><a href="https://idp1.thi.de/idp/profile/SAML2/Redirect/SSO;jsessionid=935F04F10D206BDCB915E1C9D954D28E?execution=e1s1">Primuss (Studierenden Portal)</a></li>
						</ul>
					</li>		
					</ul>			
				</nav>			
			</div>
		</header>	
		<!--  
		<div id="cookiebox">
			<p id="cookietext">Aktivieren Sie ihre Cookies 
				<button type="button" id="cookiebutton" onclick="cookieButton()">Ok!</button>
			</p>
		</div>
		-->
		<main> 
			<section>
				<h1>AKTUELLE PLATZAUSLASTUNG: ##</h1> 
			</section>
			<img id="bild" src="img/startseite.jpg" width="1280" height="450" alt="Bild">	
		</main>
				
		<footer class="foot">
		<p>
			<a href="jsp/openingHours.jsp"> Öffnungszeiten</a> |
			<a href="jsp/contact.jsp">Kontakt</a>	|
			<a href="jsp/legalNotice.jsp">Impressum</a>
		</p>
		</footer>
	</body>
</html>