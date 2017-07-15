<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${title}</title>
</head>
<body>
	<h1>${title}</h1>
	<form action="save" method="post">
		<br/>
			First name: 
			<input type="text" name="firstName" value="${user.firstName}">
		<br/>
			Last name:
			<input type="text" name="lastName" value="${user.lastName}">
		<br/>
			Email: 
			<input type="text" name="email" value="${user.email}">
		<br/>
			Phone:
			<input type="text" name="phone" value="${user.phone}">
		<br/>
		<input type="hidden" name="userId" value="${user.id}">
		<input type="submit" value="Save"/>
	</form>
</body>
</html>