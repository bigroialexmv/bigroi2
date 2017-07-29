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
	<br/>
		First name: ${user.firstName}
	<br/>
		Last name: ${user.lastName}
	<br/>
		Email: ${user.email}
	<br/>
		Phone: ${user.phone}
	<br/>
	
	<form action="user/edit">
		<input type="hidden" name="userId" value="${user.id}">
		<input type="submit" value="Edit"/>
	</form>
	
	<a href="orders?userId=${user.id}">See all orders</a>
</body>
</html>