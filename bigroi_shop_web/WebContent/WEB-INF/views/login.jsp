<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../includes/header.jsp"/>
<div class="panel panel-primary">
	<div class="panel-heading">Login</div>
	<p/>
	<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm'
		  action="<c:url value='login' />" method='POST'>

		  <table>
			<tr>
				<td>Email</td>
				<td><input type='text' name='email' value=''></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" /></td>
			</tr>
		  </table>

<%-- 		  <input type="hidden" name="${_csrf.parameterName}" --%>
<%-- 			value="${_csrf.token}" /> --%>

		</form>
</div>
<jsp:include page="../includes/footer.jsp"/>