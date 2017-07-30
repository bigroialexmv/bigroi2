<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<jsp:include page="../../includes/header.jsp"/>

<div class="panel panel-primary">
	<div class="panel-heading">User details</div>
	<p/>	
	<form action="user/edit" class="form-horizontal">
		<input type="hidden" name="userId" value="${user.id}">
  		<div class="form-group">
   			<label class="control-label col-sm-2" for="firstName">
   				<s:message code="label.firstName"/>
   			</label>
    		<div class="col-sm-4">
      			<p class="form-control-static">${user.firstName}</p>
    		</div>
  		</div>
  		<div class="form-group">
   			<label class="control-label col-sm-2" for="lastName">
   				<s:message code="label.lastName"/>
   			</label>
    		<div class="col-sm-4">
      			<p class="form-control-static">${user.lastName}</p>
    		</div>
  		</div>
  		<div class="form-group">
   			<label class="control-label col-sm-2" for="email">
				<s:message code="label.email"/>
			</label>
    		<div class="col-sm-4">
      			<p class="form-control-static">${user.email}</p>
    		</div>
  		</div>
  		<div class="form-group">
   			<label class="control-label col-sm-2" for="phone">
				<s:message code="label.phone"/>
			</label>
    		<div class="col-sm-4">
      			<p class="form-control-static">${user.phone}</p>
    		</div>
  		</div>  		
  		<div class="btn-group">
			<div class="col-sm-4">
				<input type="submit" value="<s:message code="label.edit"/>" class="btn btn-primary"/>
			</div>
		</div>
	</form>	
	<p/>
	<a href="orders?userId=${user.id}">See all orders</a>
</div>
<jsp:include page="../../includes/footer.jsp"/>