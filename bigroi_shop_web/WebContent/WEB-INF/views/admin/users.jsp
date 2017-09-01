<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../includes/header.jsp"/>
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
	<nav aria-label="Users navigation">
  		<ul class="pagination">
    		<li class="${usersPage.hasPreviousPage() ? 'page-item' : 'page-item disabled'}">
      			<a class="page-link" onclick="javascript:return ${usersPage.hasPreviousPage()};" href="?start=${usersPage.filter.count * (usersPage.pageIndex - 2)}&count=${usersPage.filter.count}" tabindex="-1">Previous</a>
   			</li>
   		<c:forEach begin="1" end="${usersPage.allPagesCount}" step="1" varStatus="loop">
   			<li class="${loop.count == usersPage.pageIndex ? 'page-item active' : 'page-item'}">
      			<a class="page-link" href="?start=${usersPage.filter.count * (loop.count -1)}&count=${usersPage.filter.count}">
      				${loop.count}
      				<c:if test="${loop.count == usersPage.pageIndex}">
      					<span class="sr-only">(current)</span>
      				</c:if>
      			</a>
    		</li>    	
   		</c:forEach>    		
    		<li class="${usersPage.hasNextPage() ? 'page-item' : 'page-item disabled'}">
      			<a class="page-link" onclick="javascript:return ${usersPage.hasNextPage()};" href="?start=${usersPage.filter.count * (usersPage.pageIndex)}&count=${usersPage.filter.count}">Next</a>
    		</li>
  		</ul>
	</nav>
	(Shown ${usersPage.filter.start + 1} - ${usersPage.end} of ${usersPage.totalItemsCount} records)
<jsp:include page="../../includes/footer.jsp"/>