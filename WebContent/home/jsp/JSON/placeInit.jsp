<%-- Eduard Springer --%>

<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%>
[
<c:forEach var="sitzplatz" items="${reserviertePlaetze}" varStatus="status">
	{
		"reservedPlace":${sitzplatz.platzID}
	}<c:if test="${not status.last}">,</c:if>
</c:forEach>
]