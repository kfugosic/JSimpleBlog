<%@page import="hr.fer.zemris.java.hw15.model.CurrentUser"%>
<%@page import="hr.fer.zemris.java.hw15.model.BlogUser"%>
<%@page import="hr.fer.zemris.java.hw15.model.BlogEntry"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>


<html>
<head>
<meta charset="utf-8">
<title>Entry</title>
</head>
<body>

	<c:choose>
		<c:when test="${CurrentUser==null}">
			<p>
				<c:out value="Guest" />
		</c:when>
		<c:otherwise>
			<p>
				<c:out value="Logged in as ${CurrentUser.nick}   " />
				<a href="/blog/servleti/logout">(Logout)</a>
			</p>
		</c:otherwise>
	</c:choose>
	<hr>

	<c:choose>
		<c:when test="${eid==null}">
			<h1>New entry:</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit entry:</h1>
		</c:otherwise>
	</c:choose>

	<form action="/blog/servleti/updateentry" id="entryform" method="GET">
		Title:<br> <input type="Text" name="title" required maxlength=200
			value="${formTitle}">
	</form>
	<textarea name="text" form="entryform" required maxlength=4096>${formText}</textarea>
	<br>
	<input form="entryform" type="submit" onclick="submitform()" value="Save">
	<input form="entryform" type="reset" value="Reset">

<footer>
  <br><br><a href="/blog/">Return to main.</a>
</footer>
</body>
</html>