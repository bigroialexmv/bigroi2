<jsp:include page="../includes/header.jsp"/>
	<form action="save" method="POST" style="padding-left: 10%;">
		<div class="form-group" style="width: 60%;">
			<label for="firstName">First name:</label>
			<input id="firstName" type="text" name="firstName" value="${user.firstName}" class="form-control">
		</div>
		<div class="form-group" style="width: 60%;">
			<label for="firstName">Last name:</label>
			<input type="text" name="lastName" value="${user.lastName}" class="form-control">
		</div>
		<div class="form-group" style="width: 60%;">
			<label for="firstName">Email:</label>
			<input type="text" name="email" value="${user.email}" class="form-control">
			</div>
		<div class="form-group" style="width: 60%;">
			<label for="firstName">Phone:</label>
			<input type="text" name="phone" value="${user.phone}" class="form-control">
		</div>
		
		<input type="hidden" name="id" value="${user.id}">
		<input type="submit" value="Save" class="btn btn-primary"/>
	</form>
<jsp:include page="../includes/footer.jsp"/>