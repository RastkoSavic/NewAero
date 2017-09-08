<%-- 
    Document   : admin-aog
    Created on : Aug 6, 2017, 1:18:21 PM
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
		<h1>AOG-s</h1>
		<hr>
		<br>
		<h3 class="text-primary">AOG List</h3>
		<hr>
		<div class="panel-group" id="accordion">
			<c:forEach items="${messageModels}" var="message"
				varStatus="theCount">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a href="#col${theCount.count}" data-toggle="collapse"
								data-parent="#accordion"> ${message.variant} -
								${message.company} </a>
						</h4>
					</div>
					<div id="col${theCount.count}"
						class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
						<div class="panel-body">
							<h4>AOG details</h4>
							<table id="no-more-tables"
								class="table table-bordered table-condensed cf">
								<thead class="cf">
									<tr>
										<th class="numeric">Status</th>
										<th class="numeric">Notes</th>
										<th class="numeric">Airport</th>
										<th class="numeric">IATA</th>
										<th class="numeric">Location</th>
										<th class="numeric">Date created</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td data-title="Id">${message.aogStatus}</td>
										<td data-title="Notes" class="multicell">&#xA;${message.aogNotes}</td>
										<td data-title="Airport" class="multicell">&#xA;${message.airport}</td>
										<td data-title="IATA">${message.aiportIATA}</td>
										<td data-title="Location" class="multicell">&#xA;${message.city}
											- ${message.country}</td>
										<td data-title="Created" class="multicell">&#xA;${message.created}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<a
								href="view-user-profile?usproid=${message.userId}&onmess=${message.id}"
								class="btn btn-primary">User profile</a>
							<div class="hidden-lg hidden-md hidden-sm">&nbsp;</div>
							<a
								href="view-company-profile?usproid=${message.companyId}&onmess=${message.id}"
								class="btn btn-primary">Company profile</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<%@include file="../../footer.jsp"%>
</body>
</html>
