<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../includes/header.jsp"/>
	<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>#</th>
			<th>Name</th>
			<th>Price</th>
			<th>Description</th>
			<th>Quantity</th>
		</tr>
	</thead>
	<tbody>		
	<c:forEach var="u" items="${products}">
		<tr>
			<td>
				<a href="product?code=${p.code}">${u.code}</a>
			</td>
			<td>
				${p.name}
			</td>
			<td>
			 	${p.price}
			</td>
			<td>
			 	${p.description}
			</td>
			<td>
			 	${p.quantity}
			</td>
		</tr>
	</c:forEach>		
	</tbody>
	</table>	
<jsp:include page="../includes/footer.jsp"/>