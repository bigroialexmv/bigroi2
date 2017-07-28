<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../includes/header.jsp"/>
<div class="panel panel-primary">
	<div class="panel-heading">Change  product</div>
	<p/>
	<form action="save" method="POST" class="form-horizontal" style="width: 50%;">
		
		<s:bind path="product.name">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">		
			<label class="col-sm-2 control-label" for="name">name</label>
			<div class="col-sm-10">
				<input id="name" type="text" name="name" value="${product.name}" class="form-control"/>
				<c:if test="${status.error}">
					<span class="glyphicon glyphicon-remove form-control-feedback"></span>
					<span class="help-block">
						<sf:errors path="product.name"/>
					</span>
				</c:if>
			 </div>	
		</div>
		</s:bind>
		
		<s:bind path="product.price">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="col-sm-2 control-label" for="price">Price</label>
			<div class="col-sm-10">
				<input type="text" name="price" value="${product.price}" class="form-control">
				<c:if test="${status.error}">
					<span class="glyphicon glyphicon-remove form-control-feedback"></span>
					<span class="help-block">
						<sf:errors path="product.price"/>
					</span>
				</c:if>
			</div>
		</div>
		</s:bind>
		
		<s:bind path="product.description">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="col-sm-2 control-label" for="description">Description</label>			
			<div class="col-sm-10">				
				<input type="text" name="description" value="${product.description}" class="form-control">
				<c:if test="${status.error}">
					<span class="glyphicon glyphicon-remove form-control-feedback"></span>					
				</c:if>
			</div>
		</div>
		</s:bind>
		
		<s:bind path="product.quantity">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="col-sm-2 control-label" for="quantity">Quantity</label>
			<div class="col-sm-10">
				<input type="text" name="quantity" value="${product.quantity}" class="form-control">
				<c:if test="${status.error}">
					<span class="glyphicon glyphicon-remove form-control-feedback"></span>
					<span class="help-block">
						<sf:errors path="product.quantity"/>
					</span>
				</c:if>
			</div>
		</div>
		</s:bind>
		
		<input type="hidden" name="code" value="${product.code}"/>
		
		<div class="btn-group">
			<div class="col-sm-10">
				<input type="submit" value="Save" class="btn btn-primary"/>
			</div>
		</div>
		<p/>
	</form>
</div>
<jsp:include page="../includes/footer.jsp"/>