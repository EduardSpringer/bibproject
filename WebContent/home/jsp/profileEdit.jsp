<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.requestURI}" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../jspf/header.jspf" %>
	<jsp:useBean id="jb" class="javabeans.ProfileBean" scope="session" ></jsp:useBean>
	
	<jsp:getProperty name="jb" property="vorname"/>, deine Änderungen wurden erfolgreich übernommen!
	
	<%@ include file="../jspf/footer.jspf" %>
</body>
</html>