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
			<span class="for-aero">Company Profile</span> - get your info
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
							<hr>
					</div>
					<hr>
				</c:if>
				<c:if test="${companySettings.service == 1}">
					<h3 class="text-primary">Primary service:
						${company.primaryService.service}</h3>
					<br>
				</c:if>
			</div>
			<div id="nas" class="col-sm-9"
				style="border-left: 1px solid #EFEFEF;">
				<h3 class="text-primary">Company details</h3>
				<hr>
				<c:if test="${companySettings.email == 1 }">
					<h4>
						<span class="glyphicon glyphicon-send"></span> Email:
						${company.email}
					</h4>
					<br>
					<h4>
						<span class="glyphicon glyphicon-send"></span> Alternative Email:
						${company.emailAlt}
					</h4>
					<br>
					<h4>
						<span class="glyphicon glyphicon-globe"></span> Website: <a
							href="${company.web}" target="_blank">${company.web}</a>
					</h4>
					<br>
				</c:if>
				<c:if test="${companySettings.phone == 1}">
					<h4>
						<span class="glyphicon glyphicon-earphone"></span> Phone:
						${company.phone}
					</h4>
					<br>
					<h4>
						<span class="glyphicon glyphicon-earphone"></span> Alternative
						Phone: ${company.phoneAlt}
					</h4>
					<br>
					<h3 class="text-primary">Address list</h3>
					<hr>
					<div class="panel-group" id="accordion">
						<c:forEach items="${addresses}" var="address" varStatus="theCount">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a href="#col${theCount.count}" data-toggle="collapse"
											data-parent="#accordion"> ${address.address} </a>
									</h4>
								</div>
								<div id="col${theCount.count}"
									class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
									<div class="panel-body">
										<h4>Address details</h4>
										<table id="no-more-tables"
											class="table table-bordered table-condensed cf">
											<thead class="cf">
												<tr>
													<th class="numeric">Id</th>
													<th class="numeric">City</th>
													<th class="numeric">Type</th>
													<th class="numeric">Used for</th>
													<th class="numeric">Date added</th>
													<th class="numeric">Date updated</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td data-title="Id">${address.id}</td>
													<td data-title="City">${address.city.name}</td>
													<td id="ty${address.id}" data-title="Type">${address.type}</td>
													<td id="uf${address.id}" data-title="Used For">${address.usedFor}</td>
													<td data-title="Date Created" class="multicell">&#xA;${address.created}</td>
													<td data-title="Date Updated" class="multicell">&#xA;${address.updated}</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="panel-footer"></div>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:if>
				<br>
				<c:if test="${companySettings.stock == 1}">
					<h3 class="text-primary">Stock list</h3>
					<hr>
					<div class="panel-group" id="accordion2">
						<c:forEach items="${stockParts}" var="stockPart"
							varStatus="theCount">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h4 class="panel-title"
										style="font-weight: bold; text-shadow: 1px 1px darkblue">
										<a href="#col${theCount.count}" data-toggle="collapse"
											data-parent="#accordion2"> ${stockPart.part.partNumber} </a>
									</h4>
								</div>
								<div id="col${theCount.count}"
									class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
									<div class="panel-body">
										<h4>Part details</h4>
										<table id="no-more-tables"
											class="table table-bordered table-condensed cf">
											<thead class="cf">
												<tr>
													<th class="numeric">Id</th>
													<th class="numeric">Category</th>
													<th class="numeric">Condition</th>
													<th class="numeric">Description</th>
													<th class="numeric">Quantity</th>
													<th class="numeric">Price</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td data-title="Id">${stockPart.id}</td>
													<td data-title="Category">${stockPart.part.category.category}</td>
													<td data-title="Condition">${stockPart.condition.name}</td>
													<td data-title="Description" class="multicell">&#xA;${stockPart.part.description}</td>
													<td data-title="Quantity">${stockPart.quantity}</td>
													<td data-title="Price">${stockPart.price}</td>
												</tr>
											</tbody>
										</table>
										<h4>Stock details</h4>
										<table id="no-more-tables"
											class="table table-bordered table-condensed cf">
											<thead class="cf">
												<tr>
													<th class="numeric">Added by</th>
													<th class="numeric">Notes</th>
													<th class="numeric">Date created</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td data-title="Added by" class="multicell">&#xA;${stockPart.user.firstName}
														${stockPart.user.lastName}</td>
													<td data-title="Notes" class="multicell">&#xA;${stockPart.notes}</td>
													<td data-title="Created" class="multicell">&#xA;${stockPart.created}</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="panel-footer">
										<a
											href="response?reto=${stockPart.user.id}&onmess=${stockPart.id}"
											class="btn btn-primary">Inquire <span
											class="glyphicon glyphicon-envelope"></span></a>
										<div class="hidden-lg hidden-md hidden-sm">&nbsp;</div>
										<a
											href="view-user-profile?usproid=${stockPart.user.id}&onmess=${stockPart.id}"
											class="btn btn-primary">User profile <span
											class="glyphicon glyphicon-user"></span></a>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<br>
				</c:if>
				<h3 class="text-primary">Company User List</h3>
				<hr>
				<div class="panel-group" id="accordion">
					<c:forEach items="${users}" var="user" varStatus="theCount">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a href="#col${theCount.count}" data-toggle="collapse"
										data-parent="#accordion"> ${user.firstName}
										${user.lastName } </a>
								</h4>
							</div>
							<div id="col${theCount.count}"
								class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
								<div class="panel-body">
									<h4>User details</h4>
									<table id="no-more-tables"
										class="table table-bordered table-condensed cf">
										<thead class="cf">
											<tr>
												<th class="numeric">Id</th>
												<th class="numeric">Position</th>
												<th class="numeric">Phone</th>
												<th class="numeric">Email</th>
												<th class="numeric">Date created</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td data-title="Id">${user.id}</td>
												<td data-title="Position">${user.position}</td>
												<td data-title="Phone">${user.phone}</td>
												<td data-title="Email" class="multicell">&#xA;${user.email}</td>
												<td data-title="Created" class="multicell">&#xA;${user.created}</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="panel-footer">
									<a href="view-user-profile?usproid=${user.id}&onmess=0"
										class="btn btn-primary">User profile <span
										class="glyphicon glyphicon-user"></span></a>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>

			</div>
		</div>
	</div>
	<%@include file="../footer.jsp"%>
</body>
</html>