<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<jsp:include page="../includes/header.jsp"/>
	
	
	
	<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th></th>
			<th>Product</th>
			<th>Price Per Unit</th>
			<th>Quantity</th>
			<th>Discount</th>
			<th>Status</th>
			<th>Total Price</th>
			</tr>
	</thead>
	<tbody>		
	<c:forEach var="orderProduct" items="${products}">
		<tr>
			<td>
				<a href="product?code=${orderProduct.product.code}">${orderProduct.product.name}</a>
			</td>
			<td>
			 	${orderProduct.product.price}
			</td>
			<td>
			 	${orderProduct.quantity}
			</td>
			<td>
			 	${orderProduct.discount}
			</td>
			<td>
			 	<c:if test ="${order.statusCode == 0}">
			 	    <s:message code="label.new"/>
			 	</c:if>
			 	<c:if test ="${order.statusCode == 1}">
			 	    <s:message code="label.approved"/>
			 	</c:if>
			 	<c:if test ="${order.statusCode == 2}">
			 	    <s:message code="label.delivered"/>
			 	</c:if>
			 	<c:if test ="${order.statusCode == 3}">
			 	    <s:message code="label.cancelled"/>
			 	</c:if>
			</td>
			<td>
			 	${orderProduct.totalPrice}
			</td>
		</tr>
		<hr/>
	</c:forEach>		
	</tbody>
	</table>	
<jsp:include page="../includes/footer.jsp"/>