<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<base href="${pageContext.request.requestURI}" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
</head>
<body>
	<%@ include file="../jspf/header.jspf" %>
	<jsp:useBean id="jb" class="javabeans.ProfileBean" scope="session" ></jsp:useBean>
	
	<jsp:getProperty name="jb" property="vorname"/>, deine Änderungen wurden erfolgreich übernommen!
	
	<%@ include file="../jspf/footer.jspf" %>
</body>
</html>