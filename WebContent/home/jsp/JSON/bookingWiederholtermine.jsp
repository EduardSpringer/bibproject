<%-- Eduard Springer --%>

<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%>
[
<c:forEach var="platz" items="${besetztePlaetze}" varStatus="status">
	{
		"datumBesetzt":"${platz.datumString}",
		"zeitraumBesetzt":"${platz.zeitraum}",
		"platznrBesetzt":"${platz.platzID}",
		"terminbezeichnungBesetzt":"${platz.terminbezeichnung}"
	},
</c:forEach>
<c:forEach var="platz" items="${freiePlaetze}" varStatus="status">
	{
		"datum":"${platz.datumString}",
		"zeitraum":"${platz.zeitraum}",
		"platznr":"${platz.platzID}",
		"terminbezeichnung":"${platz.terminbezeichnung}"
	}<c:if test="${not status.last}">,</c:if>
</c:forEach>
]