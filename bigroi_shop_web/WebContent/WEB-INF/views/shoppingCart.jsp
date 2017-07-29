<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../includes/header.jsp"/>
shopping cart: ${shoppingCart}
	<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>#</th>
			<th>Name</th>
			<th>Quantity</th>			
		</tr>
	</thead>
	<tbody>		
	<c:forEach var="i" items="${shoppingCart.items}">
		<tr>
			<td>
				<a href="product?code=${i.productCode}">${i.productCode}</a>
			</td>
			<td>
				
			</td>
			<td>
			 	${i.quantity}
			</td>			
		</tr>
	</c:forEach>		
	</tbody>
	</table>	
<jsp:include page="../includes/footer.jsp"/>