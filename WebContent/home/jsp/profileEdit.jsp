<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<base href="${pageContext.request.requestURI}" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	<h2>
	<jsp:useBean id="jb" class="javabeans.ProfileBean" scope="session" ></jsp:useBean>
	
	<jsp:getProperty name="jb" property="username"/>, deine Änderungen wurden erfolgreich übernommen!
	</h2>
	</section>
	</div>
	<%@ include file="../jspf/footer.jspf" %>
</body>
</html>