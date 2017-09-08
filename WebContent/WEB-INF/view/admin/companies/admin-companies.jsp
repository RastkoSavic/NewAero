<%-- 
    Document   : admin-companies
    Created on : Aug 6, 2017, 1:25:03 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../../header.jsp"%>
</head>
<body>
	<%@include file="../../navigation.jsp"%>
	<div class="container" style="margin-top: 50px;">
		<h1>Companies</h1>
		<hr>
		<br>
		<h3 class="text-primary">Company list</h3>
		<hr>
		<div class="panel-group" id="accordion">
			<c:forEach items="${companies}" var="company" varStatus="theCount">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a href="#col${theCount.count}" data-toggle="collapse"
								data-parent="#accordion"> ${company.name} </a>
						</h4>
					</div>
					<div id="col${theCount.count}"
						class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
						<div class="panel-body">
							<h4>Company details</h4>
							<table id="no-more-tables"
								class="table table-bordered table-condensed cf">
								<thead class="cf">
									<tr>
										<th class="numeric">Id</th>
										<th class="numeric">Primary Service</th>
										<th class="numeric">Date created</th>
										<th class="numeric">Date updated</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td data-title="Id">${company.id}</td>
										<td data-title="Service">${company.primaryService.service}</td>
										<td data-title="Created" class="multicell">&#xA;${company.created}</td>
										<td data-title="Updated" class="multicell">&#xA;${company.updated}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<a href="view-company-profile?usproid=${company.id}&onmess=0"
								class="btn btn-primary">Company profile <span
								class="glyphicon glyphicon-plane"></span></a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<%@include file="../../footer.jsp"%>
</body>
</html>
