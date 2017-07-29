<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../includes/header.jsp"/>
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
	<form method="post" action="makeOrder">
		<div class="btn-group">
			<div class="col-sm-4">
				<input type="submit" value="Make order" class="btn btn-primary"/>
			</div>
		</div>
	</form>
<jsp:include page="../includes/footer.jsp"/>