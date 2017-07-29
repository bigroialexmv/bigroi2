<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../includes/header.jsp"/>
	<h1>User: ${user.firstName} ${user.lastName}</h1>
	<hr/>
	<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>#</th>
			<th>Product</th>
			<th>Created</th>
			<th>Delivery Date</th>
			<th>Status</th>
			<th>Quantity</th>
			<th>Price</th>
		</tr>
	</thead>
	<tbody>		
	<c:forEach var="order" items="${orders}">
		<tr>
			<td>
				<a href="order?orderId=${order.id}">${order.id}</a>
			</td>
			<td>
				${order.product.name}
			</td>
			<td>
			 	${order.created}
			</td>
			<td>
			 	${order.deliveryDate}
			</td>
			<td>
			 	${order.productQuantity}
			</td>
			<td>
			 	${order.product.getPrice()}
			</td>
		</tr>
		<hr/>
	</c:forEach>		
	</tbody>
	</table>	
<jsp:include page="../includes/footer.jsp"/>