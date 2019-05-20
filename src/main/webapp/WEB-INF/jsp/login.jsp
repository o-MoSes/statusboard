<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="common/header.jspf"%>
<title>Login</title>
</head>
<body>
	<div class="container">
		<form action="/authenticate" method="post">


			<div>
				<c:choose>
					<c:when test="${param.error != null}">
						<p>Oops, seems like the credentials are wrong!</p>
					</c:when>
					<c:when test="${param.logout != null}">
						<p>Nice, you have been logged out successfully!</p>
					</c:when>
				</c:choose>
			</div>
			<div>
				<label for="username">Username : </label><input type="text"
					name="username" />
			</div>
			<div>
				<label for="password">Password: </label><input type="password"
					name="password" />
			</div>
			<div>
				<input type="submit" value="login" />
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>
</body>
</html>