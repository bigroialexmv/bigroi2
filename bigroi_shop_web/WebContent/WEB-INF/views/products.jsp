<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<jsp:include page="../includes/header.jsp"/>
	<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>#</th>
			<th>Name</th>
			<th>Price</th>
			<th>Description</th>
			<th>Quantity</th>
			<th></th>
		</tr>
	</thead>
	<tbody>		
	<c:forEach var="p" items="${products}">
		<tr>
			<td>
				<a href="product?code=${p.code}">${p.code}</a>
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
			<td>
			 	<a href="add-item?pcode=${p.code}">
			 		<s:message code="label.addItem" />
			 	</a>
			</td>
		</tr>
	</c:forEach>		
	</tbody>
	</table>	
<jsp:include page="../includes/footer.jsp"/>