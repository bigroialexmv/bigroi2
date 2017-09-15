<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<jsp:include page="../includes/header.jsp"/>
	
	
	
	<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>Product</th>
			<th>Price Per Unit</th>
			<th>Quantity</th>
			<th>Discount</th>
			<th>Status</th>
			<th>Total Price</th>
			</tr>
	</thead>
	<tbody>		
	<c:forEach var="orderProduct" items="${orderPage.items}">
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
	
	<c:set var="page" value="${orderPage}"/>
		
	<nav aria-label="Page navigation">
  <ul class="pagination">
    <li class="${page.filter.start == 0 ? 'disabled' : ''}">
      <a onclick="javascript: return ${page.filter.start != 0};" href="order?start=${page.filter.start - page.filter.count}&count=${page.filter.count}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <c:forEach begin="1" end="${page.totalPagesCount}" varStatus="loop">
    	<li class="${page.filter.count * (loop.index-1) == page.filter.start ? 'active' : ''}">
    		<a href="order?start=${page.filter.count * (loop.index-1)}&count=${page.filter.count}">${loop.index}
    		</a>
    	</li>
    </c:forEach>
    <li class="${page.filter.start + page.filter.count >= page.totalItemsCount ? 'disabled' : ''}">
      <a onclick="javascript: return ${page.filter.start + page.filter.count < page.totalItemsCount};" 
      	href="order?start=${page.filter.start + page.filter.count}&count=${page.filter.count}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>		
<jsp:include page="../includes/footer.jsp"/>