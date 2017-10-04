<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

			  <form action="register" method="POST">
  				First name:<br><input type="Text" name="fname" maxlength=20 value="${form.firstName}" required><br>
  				Last name:<br><input type="Text" name="lname" maxlength=20 value="${form.lastName}" required><br>
  				E-Mail:<br><input type="Text" name="email" maxlength=30 value="${form.email}" required><br>
				 <%
					String nickExists = (String) session.getAttribute("nickExists");
					if (nickExists != null) {
					%>
						<font color="red">This nickname is taken, please choose another!</font><br>
						<%	session.setAttribute("nickExists", Integer.valueOf(0));
					}
				%>
  				Nickname:<br><input type="Text" name="nick" maxlength=20 value="${form.nick}" required><br>
  				Password:<br><input type="Password" name="password" required><br>
			  	<input type="submit" value="Register"><input type="reset" value="Reset">
			  	<br>
			  	<a href="/blog/">Return to main.</a>
			  	
 			 </form>

		</c:when>
		<c:otherwise>
			    You are already logged in!<br>
			    <a href="/blog/">Return to main.</a>
		</c:otherwise>
	</c:choose>

<footer>
  <br><br><a href="/blog/">Return to main.</a>
</footer>

</body>
</html>