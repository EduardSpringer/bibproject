<!-- Sara Viktoria Miller -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="de">
	<head>
		<base href="${pageContext.request.requestURI}" />
		<meta charset="UTF-8">
		<title>Profilbearbeitung erfolgt</title>
		<link rel="stylesheet" href="../css/content.css" type="text/css">
		<link rel="stylesheet" href="../css/center.css"> 
		<link rel="stylesheet" href="../css/header.css">
		<link rel="stylesheet" href="../css/footer.css">
	</head>
	<body>
		<%@ include file="../jspf/header.jspf" %>
		<div class="outsidediv"> 
		<section class="zentriert"> 
			<h1>Profilbearbeitung erfolgt</h1>
			<h2>
				<jsp:useBean id="jb" class="javabeans.ProfileBean" scope="session" ></jsp:useBean>
				<jsp:getProperty name="jb" property="username"/>, deine Änderungen wurden erfolgreich übernommen!
			</h2>
		</section>
		</div>
		<%@ include file="../jspf/footer.jspf" %>
	</body>
</html>