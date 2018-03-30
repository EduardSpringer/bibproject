<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<base href="${pageContext.request.requestURI}" />
		<meta charset="UTF-8">
		<title>Registrierung erfolgt</title>
	</head>
	<body>
		<%@ include file="../jspf/header.jspf" %>
		<section>
			<h1>Registrierung erfolgt</h1>
		</section>
		
		<jsp:useBean id="javabean" class="javabeans.ProfileBean" scope="session"></jsp:useBean>
		Hallo <jsp:getProperty name="javabean" property="vorname"/>, Sie haben sich erfolgreich registriert! Sie können sich jetzt nun einloggen.
		<%@ include file="../jspf/footer.jspf" %>
	</body>
</html>