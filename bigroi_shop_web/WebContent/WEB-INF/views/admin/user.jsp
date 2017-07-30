<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../includes/header.jsp"/>

<div class="panel panel-primary">
	<div class="panel-heading">User details</div>
	<p/>	
	<form action="user/edit" class="form-horizontal">
		<input type="hidden" name="userId" value="${user.id}">
  		<div class="form-group">
   			<label class="control-label col-sm-2" for="firstName">First name</label>
    		<div class="col-sm-4">
      			<p class="form-control-static">${user.firstName}</p>
    		</div>
  		</div>
  		<div class="form-group">
   			<label class="control-label col-sm-2" for="lastName">Last name</label>
    		<div class="col-sm-4">
      			<p class="form-control-static">${user.lastName}</p>
    		</div>
  		</div>
  		<div class="form-group">
   			<label class="control-label col-sm-2" for="email">Email</label>
    		<div class="col-sm-4">
      			<p class="form-control-static">${user.email}</p>
    		</div>
  		</div>
  		<div class="form-group">
   			<label class="control-label col-sm-2" for="phone">Phone</label>
    		<div class="col-sm-4">
      			<p class="form-control-static">${user.phone}</p>
    		</div>
  		</div>  		
  		<div class="btn-group">
			<div class="col-sm-4">
				<input type="submit" value="Edit" class="btn btn-primary"/>
			</div>
		</div>
	</form>	
	<p/>
	<a href="orders?userId=${user.id}">See all orders</a>
</div>
<jsp:include page="../../includes/footer.jsp"/>