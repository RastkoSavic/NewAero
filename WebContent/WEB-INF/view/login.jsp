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
			<span class="for-aero">Login</span> - get access
		</h3>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<sf:form action="login" method="POST" modelAttribute="userSet"
			class="form-horizontal" role="form">

			<div class="form-group">
				<label class="control-label col-sm-2" for="setEmail"> Email:
				</label>
				<div class="col-sm-10">
					<sf:input type="email" class="form-control" id="setEmail"
						path="email" placeholder="Enter email" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="email" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="setPass">
					Password: </label>
				<div class="col-sm-10">
					<sf:input type="password" class="form-control" id="setPass"
						path="password" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="password" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-6 col-xs-4">
					<button type="submit" class="btn btn-success">
						Login <span class="glyphicon glyphicon-log-in"></span>
					</button>
				</div>
				<div class="col-sm-offset-2 col-sm-2 col-xs-offset-3 col-xs-5">
					<button id="res-b" type="reset" class="btn btn-warning">
						Reset <span class="glyphicon glyphicon-refresh"></span>
					</button>
				</div>
			</div>
		</sf:form>
		<br>
		<h4 class="col-sm-offset-2 col-sm-10">
			<a href="register-company">Create account?</a>
		</h4>
		<hr>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>