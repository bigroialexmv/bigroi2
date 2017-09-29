<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" tagdir="/WEB-INF/tags/form" %>

<jsp:include page="../includes/header.jsp"/>
<div class="panel panel-primary">
	<div class="panel-heading">Registration</div>
	<p/>
	<div class="row">
		<div class="col-md-8">
	<form action="registration" method="POST" class="form-horizontal">
		
		<f:input name="user.firstName" value="${registration.user.firstName}" path="registration.user.firstName" type="text" id="firstName" labelCode="label.firstName"/>
		<f:input name="user.lastName" value="${registration.user.lastName}" path="registration.user.lastName" type="text" id="lastName" labelCode="label.lastName"/>
		<f:input name="user.email" value="${registration.user.email}" path="registration.user.email" id="email" type="text"  labelCode="label.email"/>
		<f:input name="user.phone" value="${registration.user.phone}" path="registration.user.phone" id="phone" type="text" labelCode="label.phone"/>
		<f:input name="password" value="${registration.password}" path="registration.password" id="password" type="text" labelCode="label.password"/>
		<f:input name="confirmPassword" value="${registration.confirmPassword}" path="registration.confirmPassword" id="confirmPassword" type="text" labelCode="label.confirmPassword"/>
		
		<div class="btn-group">
			<input type="submit" value="<s:message code='label.register'/>" class="btn btn-primary"/>
		</div>
		<p/>
	</form>
		</div>
	</div>
</div>
<jsp:include page="../includes/footer.jsp"/>