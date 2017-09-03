<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<jsp:include page="../../includes/header.jsp"/>

	<form action="users" method="GET" class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-2 control-label" for="lastName"><s:message code="label.lastName"/></label>
			<div class="col-sm-4">
				<input type="text" name="lastName" value="${usersPage.filter.lastName}" class="form-control">				
			</div>
			<label class="col-sm-2 control-label" for="email"><s:message code="label.email"/></label>
			<div class="col-sm-4">
				<input type="text" name="email" value="${usersPage.filter.email}" class="form-control">				
			</div>
		</div>	
		<div class="btn-group">
				<input type="submit" value="<s:message code='label.search'/>" class="btn btn-primary"/>
		</div>
		<p/>
	</form>
	
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
	<c:forEach var="u" items="${usersPage.items}">
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
	
	<c:set var="page" value="${usersPage}"/>
		
	<nav aria-label="Page navigation">
  <ul class="pagination">
    <li class="${page.filter.start == 0 ? 'disabled' : ''}">
      <a onclick="javascript: return ${page.filter.start != 0};" href="users?start=${page.filter.start - page.filter.count}&count=${page.filter.count}${page.filter.params}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <c:forEach begin="1" end="${page.totalPagesCount}" varStatus="loop">
    	<li class="${page.filter.count * (loop.index-1) == page.filter.start ? 'active' : ''}">
    		<a href="users?start=${page.filter.count * (loop.index-1)}&count=${page.filter.count}${page.filter.params}">${loop.index}
    		</a>
    	</li>
    </c:forEach>
    <li class="${page.filter.start + page.filter.count >= page.totalItemsCount ? 'disabled' : ''}">
      <a onclick="javascript: return ${page.filter.start + page.filter.count < page.totalItemsCount};" 
      	href="users?start=${page.filter.start + page.filter.count}&count=${page.filter.count}${page.filter.params}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>	
<jsp:include page="../../includes/footer.jsp"/>