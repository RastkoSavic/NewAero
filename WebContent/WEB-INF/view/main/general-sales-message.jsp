<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="../header.jsp"%>
</head>
<body>
	<%@include file="../navigation.jsp"%>

	<div class="container" style="margin-top: 50px;">
		<h1>General Sales Add</h1>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<sf:form action="post-sales-add" method="POST"
			modelAttribute="postGNSM" class="form-horizontal" role="form">
			<h3 class="text-primary">Post new Add</h3>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addMessage">Message:</label>
				<div class="col-sm-10">
					<sf:textarea class="form-control" id="addMessage" path="message"
						rows="4" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="message" cssClass="alert-danger ita" />
				</div>
			</div>

			<input type="hidden" name="userId" value="${currentUser.id}" />
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-success">
						Post Message <span class="glyphicon glyphicon-send"></span>
					</button>
				</div>
			</div>
		</sf:form>
	</div>
	<%@include file="../footer.jsp"%>
</body>
</html>