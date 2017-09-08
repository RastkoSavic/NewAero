<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../header.jsp"%>
</head>
<body>
	<%@include file="../navigation.jsp"%>
	<div class="container" style="margin-top: 50px;">
		<h3 class="ita">
			<span class="for-aero">User Profile</span> - get your contact
		</h3>
		<hr>
		<br>
		<div class="row">
			<div class="col-sm-3">
				<h2>${company.name}</h2>
				<hr>
				<c:if test="${companySettings.logo == 1}">
					<div class="container logoprofile">
						<img src="resources/images/${company.name}.jpg"
							class="img-responsive" alt="logo">
					</div>
					<hr>
				</c:if>
				<c:if test="${companySettings.service == 1}">
					<h3 class="text-primary">Primary service:
						${company.primaryService.service}</h3>
					<hr>
				</c:if>
				<c:if test="${userSettings.message == 1}">
					<a href="response?reto=${user.id}&onmess=0" class="btn btn-primary">Inquire
						<span class="glyphicon glyphicon-envelope"></span>
					</a>
				</c:if>
				<div class="hidden-lg hidden-md hidden-sm">&nbsp;</div>
				<a href="view-company-profile?usproid=${company.id}&onmess=0"
					class="btn btn-primary">Company profile <span
					class="glyphicon glyphicon-plane"></span></a>

			</div>
			<div class="col-sm-9" style="border-left: 1px solid #EFEFEF;">
				<h2 class="text-primary">User details</h2>
				<hr>
				<h4>Name: ${user.firstName} ${user.lastName}</h4>
				<br>
				<h4>Title: ${user.position}</h4>
				<br>
				<c:if test="${userSettings.email == 1 }">
					<h4>
						<span class="glyphicon glyphicon-send"></span> Email:
						${user.email}
					</h4>
					<br>
					<h4>
						<span class="glyphicon glyphicon-send"></span> Alternative Email:
						${user.emailAlt}
					</h4>
					<br>
				</c:if>
				<c:if test="${userSettings.phone == 1}">
					<h4>
						<span class="glyphicon glyphicon-earphone"></span> Phone:
						${user.phone}
					</h4>
					<br>
					<h4>
						<span class="glyphicon glyphicon-earphone"></span> Alternative Phone:
						${user.phoneAlt}
					</h4>
				</c:if>
			</div>
		</div>
	</div>
	<%@include file="../footer.jsp" %>
</body>
</html>