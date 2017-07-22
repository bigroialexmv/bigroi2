<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../includes/header.jsp"/>
	<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>#</th>
			<th>First name</th>
			<th>Last name</th>
			<th>Email</th>
			<th>Phone</th>
		</tr>
	</thead>
	<tbody>		
	<c:forEach var="u" items="${users}">
		<tr>
			<td>
				<a href="user?userId=${u.id}">${u.id}</a>
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
<jsp:include page="../includes/footer.jsp"/>