<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="header.jsp"%>
</head>
<body>

	<%@include file="navigation.jsp"%>
	<div class="container" style="margin-top: 50px;">
		<h3 class="ita">
			<span class="for-aero">Register Company</span> - get started
		</h3>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<h3 class="text-primary">Fill out registration form</h3>
		<hr>
		<br>
		<sf:form action="register-company" method="POST"
			modelAttribute="companyAdd" class="form-horizontal" role="form">
			<h4>Company details</h4>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addCNam">Company
					Name:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addCNam" path="name"
						placeholder="Enter company name" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="name" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addPrim">Primary
					Service:</label>
				<div class="col-sm-10">
					<select class="form-control" name="serviceId" id="addPrim">
						<c:forEach items="${services}" var="service">
							<option value="${service.id}">${service.service}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<br>
			<h4>Primary User details</h4>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addEmail"> Email:
				</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addEmail"
						path="companyEmail" placeholder="Enter company email" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="companyEmail" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addMPass"> Master
					Password: </label>
				<div class="col-sm-10">
					<sf:input type="password" class="form-control" id="addMPass"
						path="masterPass" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="masterPass" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addRPass"> Repeat
					Password: </label>
				<div class="col-sm-10">
					<sf:input type="password" class="form-control" id="addRPass"
						path="repeatPass" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="repeatPass" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addFName">First
					name: </label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addFName"
						path="firstName" placeholder="Enter first name" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="firstName" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addLName">Last
					name: </label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addLName"
						path="lastName" placeholder="Enter last name" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="lastName" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="col-sm-offset-2 col-sm-6 col-xs-4">
				<button type="submit" class="btn btn-success">
					Register <span class="glyphicon glyphicon-save"></span>
				</button>
			</div>
			<div class="col-sm-offset-2 col-sm-2 col-xs-offset-3 col-xs-5">
				<button id="res-b" type="reset" class="btn btn-warning">
					Reset <span class="glyphicon glyphicon-refresh"></span>
				</button>
			</div>

		</sf:form>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>