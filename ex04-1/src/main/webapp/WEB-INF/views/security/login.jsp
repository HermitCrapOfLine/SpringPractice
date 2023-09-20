<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<body>
	<h1>login</h1>
	<form action='/security/login' method='POST'>
		<input type="hidden" name="${_csrf.parameterName }"
			value="${_csrf.token }" />
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>

			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Login" /></td>
			</tr>
		</table>
	</form>
</body>
</html>