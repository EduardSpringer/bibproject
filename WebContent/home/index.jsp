<!-- Helene Akulow -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<link rel="stylesheet" href="css/cookie.css">
		<script type="text/javascript" src="js/cookie.js"></script>
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
								<c:when test="${lb.bildexist == true}">
									<img id="profilbild" src="../bildservlet?username=${lb.username}" width="150" height="150" alt="Profilbild">		
								</c:when>
								<c:otherwise>
									<img id="profilbild" src="img/keinBild.jpg" width="150" height="150" alt="Profilbild1">
								</c:otherwise>
							</c:choose>
						</div>
							
						<div class="loginfeld">				
							<ul>
								<li><a>${lb.username} ▼</a> 
									<ul>
										<li><a href="jsp/profileEditForm.jsp">Profil bearbeiten</a></li>
										<li><a href="/bibproject/myreservationservlet">Meine Termine</a></li>
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
							<li><a href="https://www3.primuss.de/stpl/login.php?FH=fhin&Lang=de" target="_blank">Stundenplan</a></li>
							<li><a href="https://www.thi.de/" target="_blank">Technische Hochschule</a></li>
							<li><a href="https://moodle.thi.de/moodle/" target="_blank">Moodle</a></li>
							<li><a href="https://print.thi.de" target="_blank">Drucken</a></li>
							<li><a href="https://exchange.thi.de" target="_blank">Webmail</a></li>
						</ul>
					</li>
					 
					<c:if test="${lb.adminrechte == true}">
						<li id="admin"><a>Admin ▼</a>	
							<ul>
								<li><a href="jsp/adminEmail.jsp">Kontaktverwaltung</a></li>
								<li><a href="jsp/adminReserve.jsp">Platzverwaltung</a></li>
							</ul>
						</li>
					</c:if>	
					</ul>			
				</nav>			
			</div>
		</header>	
		  
		<div id="cookiebox" class="show">
			<p id="cookietext">Aktivieren Sie ihre Cookies 
				<button type="button" id="cookiebutton">Ok!</button>
			</p>
		</div>
		
		<main> 
			<section>
				<h1>AKTUELLE PLATZAUSLASTUNG</h1> 
				<div id=platzauslastung>
					<p>Reservierte Plätze: ##</p>
					<p>Freie Plätze: ##</p>
				</div>
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