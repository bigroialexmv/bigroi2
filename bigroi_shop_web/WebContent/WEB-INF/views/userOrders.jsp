<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<jsp:include page="../includes/header.jsp"/>


	<h3>User: ${user.firstName} ${user.lastName}</h3>
	<hr/>
	<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>#</th>
			<th>Created</th>
			<th>Delivery Date</th>
			<th>Delivery Address</th>
			<th>Status</th>
			</tr>
	</thead>
	<tbody>		
	<c:forEach var="order" items="${ordersPage.items}">
		<tr>
			<td>
				<a href="order?orderId=${order.id}">${order.id}</a>
			</td>
			<td>
			 	${order.created}
			</td>
			<td>
			 	${order.deliveryDate}
			</td>
			<td>
			 	${order.deliveryAddress.city}, ${order.deliveryAddress.streetAddr}
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
		</tr>
		<hr/>
	</c:forEach>		
	</tbody>
	</table>
	
	<c:set var="page" value="${ordersPage}"/>
		
	<nav aria-label="Page navigation">
  <ul class="pagination">
    <li class="${page.filter.start == 0 ? 'disabled' : ''}">
      <a onclick="javascript: return ${page.filter.start != 0};" href="orders?start=${page.filter.start - page.filter.count}&count=${page.filter.count}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <c:forEach begin="1" end="${page.totalPagesCount}" varStatus="loop">
    	<li class="${page.filter.count * (loop.index-1) == page.filter.start ? 'active' : ''}">
    		<a href="orders?start=${page.filter.count * (loop.index-1)}&count=${page.filter.count}">${loop.index}
    		</a>
    	</li>
    </c:forEach>
    <li class="${page.filter.start + page.filter.count >= page.totalItemsCount ? 'disabled' : ''}">
      <a onclick="javascript: return ${page.filter.start + page.filter.count < page.totalItemsCount};" 
      	href="orders?start=${page.filter.start + page.filter.count}&count=${page.filter.count}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>		
<jsp:include page="../includes/footer.jsp"/>