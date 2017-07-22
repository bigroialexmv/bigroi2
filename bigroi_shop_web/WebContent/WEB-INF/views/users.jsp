<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${title}</title>
	<style type="text/css">
		td { background-color:#ffff00; }
  		td:hover { background-color:#000000; }
	</style>
</head>
<body>
	<h1>${title}</h1>
	<table align="center" width="100%">
	<tbody>		
	<c:forEach var="u" items="${users}">
		<tr>
			<td style="text-align: right;">
				<a href="user?userId=${u.id}">#${u.id}</a>
			</td>
			<td>
				${u.firstName}
			</td>
			<td>
			 	${u.lastName}
			</td>
			<td>
			 	${u.email}
			</td>
			<td>
			 	${u.phone}
			</td>
		</tr>
	</c:forEach>		
	</tbody>
	</table>	
</body>
</html>