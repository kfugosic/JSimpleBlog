<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<html>

<head>
<meta charset="utf-8">
<title>Registration</title>
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
	<br>
	
	Registration successful, you are automatically logged in!
	
<footer>
  <br><br><a href="/blog/">Return to main.</a>
</footer>
	
</body>
</html>