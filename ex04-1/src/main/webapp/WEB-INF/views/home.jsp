<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
	<form action="/security/logout" method="post">
		<input type="hidden" name="{$_csrf.parameterName}
			value="${_csrf.token }" />
		<input type="submit" value="로그아웃" />
	</form>

</body>
</html>
