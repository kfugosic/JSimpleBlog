<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>


<html>
<head>
<meta charset="utf-8">
<title>Home</title>
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

	<c:if test="${CurrentUser==null}">

			<%
				String loginError = (String) session.getAttribute("loginError");
					if (loginError != null) {
			%>
						<font color="red">Nickname doesn't exist or wrong password!</font><br>
			<%
						session.setAttribute("loginError", Integer.valueOf(0));
					}
			%>
		
		<form action="/blog/servleti/login" method="POST">
			Nick:<br> <input required type="Text" name="nick" value="${chosenusr}"><br> 
			Password:<br> <input type="Password" name="password" required><br> 
			<input type="submit" onclick="submitform()" value="Login">
			<input type="reset" value="Reset">
		</form>
		<br>
		<hr>
		<a href="/blog/servleti/registerNew">Register</a>
		<hr>
		<br>

	</c:if>

	<c:choose>
		<c:when test="${nicknames==null}">
      	No registered users!
    	</c:when>
		<c:otherwise>

			<c:forEach var="nm" items="${nicknames}">
				<li><a href="/blog/servleti/author/${nm}">${nm}</a></li>
			</c:forEach>

			<hr>
		</c:otherwise>
	</c:choose>

</body>
</html>