<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
	<p>
		Hello <b><c:out value="${pageContext.request.remoteUser }"/></b>
		Roles: <b><sec:authentication property="principal.authorities"/></b>
	</p>
	
	<form action="logout" method="post">
		<input type="submit" value="Logout" />
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
	</form>

</body>
</html>