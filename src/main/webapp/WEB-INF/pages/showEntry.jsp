<%@page import="hr.fer.zemris.java.hw15.model.CurrentUser"%>
<%@page import="hr.fer.zemris.java.hw15.model.BlogUser"%>
<%@page import="hr.fer.zemris.java.hw15.model.BlogEntry"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>


<html>
<head>
<meta charset="utf-8">
<title>Blog Entry</title>
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
	<br>
	<hr>
	
	<h1>${currentEntry.title}</h1>
	<p>${currentEntry.text}</p>
	<p></p>
	<div align="right">
		<font size = "2">Author: ${currentEntry.creator.nick},   Created: ${currentEntry.createdAt}, Last Modified: ${currentEntry.lastModifiedAt}</font>
	</div>
	
	
	<hr>
	<%
		CurrentUser user = (CurrentUser) session.getAttribute("CurrentUser");
		if (user != null && user.getNick().equals((String) session.getAttribute("pathNick"))) {
	%>
	<p>
		<a href="/blog/servleti/author/${pathNick}/edit">Edit entry</a>
	</p>
	<%
		}
	%>
	
	<c:choose>
    <c:when test="${comments==null}">
      No comments!
    </c:when>
    <c:otherwise>
      <c:forEach var="e" items="${comments}">
      	<hr>
        <li><b>${e.usersEMail} commented: </b>
        <div style="padding-left: 10px;"><c:out value="${e.message}"/></div></li>
		<div align="right"><font size = "1"> ${e.postedOn}</font></div>
		<hr>     
	</c:forEach>
    </c:otherwise>
    </c:choose>
  	
  	<hr>
  	
  	<form action="/blog/servleti/newcomment" id="cmnt" method="GET">
		E-Mail:<br> <input type="Text" name="email" required maxlength=200>
	</form>
		Message:<br> 	<textarea name="msg" form="cmnt" required maxlength=4096></textarea>
	<br>s
	<input form="cmnt" type="submit" onclick="submitform()" value="Add comment">
	<input form="cmnt" type="reset" value="Reset">

<footer>
  <br><br><a href="/blog/">Return to main.</a>
</footer>

</body>
</html>