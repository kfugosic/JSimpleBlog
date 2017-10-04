<%@page import="hr.fer.zemris.java.hw15.model.CurrentUser"%>
<%@page import="hr.fer.zemris.java.hw15.model.BlogUser"%>
<%@page import="hr.fer.zemris.java.hw15.model.BlogEntry"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>


<html>
<head>
<meta charset="utf-8">
<title>Entries</title>
</head>
<body>

	<c:choose>
		<c:when test="${CurrentUser==null}">
			<p>
				<c:out value="Guest" />
			</p>
		</c:when>
		<c:otherwise>
			<p>
				<c:out value="Logged in as ${CurrentUser.nick}   " />
				<a href="/blog/servleti/logout">(Logout)</a>
			</p>
		</c:otherwise>
	</c:choose>
	<hr><br>
	
	<c:choose>
		<c:when test="${entriesList==null}">
    	  No entries!
    	</c:when>
		<c:otherwise>
			<c:forEach var="entry" items="${entriesList}">
				<li><a href="/blog/servleti/author/${pathNick}/${entry.id}">${entry.title}</a></li>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<br>
	
	<%
		CurrentUser user = (CurrentUser) session.getAttribute("CurrentUser");
		if (user != null && user.getNick().equals((String) session.getAttribute("pathNick"))) {
	%>
	<p>
		<a href="/blog/servleti/author/${pathNick}/new">New Entry</a>
	</p>
	<%
		}
	%>
	<hr>

<footer>
  <br><br><a href="/blog/">Return to main.</a>
</footer>

</body>
</html>