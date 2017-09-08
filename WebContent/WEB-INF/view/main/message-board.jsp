<%-- 
    Document   : message-board
    Created on : Aug 5, 2017, 12:11:54 PM
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
<%@include file="../header.jsp"%>
</head>
<body>
	<%@include file="../navigation.jsp"%>
	<div class="container" style="margin-top: 50px;">
		<h3 class="ita">
			<span class="for-aero">MESSAGE BOARD</span> - get the latest adds
		</h3>
		<hr>
		<br>
		<c:out value="${bormes}" escapeXml="false">
		</c:out>
		<c:remove var="bormes" scope="session" />
		<br>
		<div class="col-xs-12 container-fluid">
			<hr>
			<img src="resources/images/bb.jpg" class="image-responsive"
				style="width: 100%">
			<hr>
		</div>
		<h2 class="text-primary">Message List</h2>
		<hr>
		<div class="panel-group" id="accordion">
			<c:forEach items="${messageModels}" var="message"
				varStatus="theCount">
				<c:if
					test="${message.typeCode == 'PAOM' || message.typeCode == 'PAWM'}">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a href="#col${theCount.count}" data-toggle="collapse"
									data-parent="#accordion"> ${message.company} -
									${message.partNumber} - ${message.type} </a>
							</h4>
						</div>
						<div id="col${theCount.count}"
							class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
							<div class="panel-body">
								<h4>Message details</h4>
								<table id="no-more-tables"
									class="table table-bordered table-condensed cf">
									<thead class="cf">
										<tr>
											<th class="numeric">Id</th>
											<th class="numeric">Message</th>
											<th class="numeric">Sender</th>
											<th class="numeric">Date created</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td data-title="Id">${message.id}</td>
											<td data-title="Message" class="multicell">&#xA;${message.message}</td>
											<td data-title="Sender" class="multicell">&#xA;${message.firstName}
												${message.lastName}</td>
											<td data-title="Created" class="multicell">&#xA;${message.created}</td>
										</tr>
									</tbody>
								</table>
								<br>
								<h4>Part details</h4>
								<table id="no-more-tables"
									class="table table-bordered table-condensed cf">
									<thead class="cf">
										<tr>
											<th class="numeric">Category</th>
											<th class="numeric">Description</th>
											<th class="numeric">Condition</th>
											<th class="numeric">Quantity</th>
											<th class="numeric">Price</th>
											<th class="numeric">Notes</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td data-title="Category">${message.category}</td>
											<td data-title="Description" class="multicell">&#xA;${message.description}</td>
											<td data-title="Condition">${message.condition}</td>
											<td data-title="Qty">${message.quantity}</td>
											<td data-title="Price">${message.price}</td>
											<td data-title="Notes" class="multicell">&#xA;${message.notes}</td>
										</tr>
									</tbody>
								</table>
								<c:if test="${not empty message.aogStatus}">
									<br>
									<h4>AOG details</h4>
									<table id="no-more-tables"
										class="table table-bordered table-condensed cf">
										<thead class="cf">
											<tr>
												<th class="numeric">AOG Status</th>
												<th class="numeric">Model</th>
												<th class="numeric">Airport</th>
												<th class="numeric">IATA</th>
												<th class="numeric">City</th>
												<th class="numeric">AOG Notes</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td data-title="AOG Status">${message.aogStatus}</td>
												<td data-title="Model">${message.variant}</td>
												<td data-title="Airport" class="multicell">&#xA;${message.airport}</td>
												<td data-title="IATA">${message.aiportIATA}</td>
												<td data-title="City">${message.city}</td>
												<td data-title="AOG Notes" class="multicell">&#xA;${message.aogNotes}</td>
											</tr>
										</tbody>
									</table>
								</c:if>
							</div>
							<div class="panel-footer">
								<a href="response?reto=${message.userId}&onmess=${message.id}"
									class="btn btn-primary">Respond <span
									class="glyphicon glyphicon-share-alt"></span></a>
								<div class="hidden-lg hidden-md hidden-sm">&nbsp;</div>
								<a
									href="view-user-profile?usproid=${message.userId}&onmess=${message.id}"
									class="btn btn-primary">User profile <span
									class="glyphicon glyphicon-user"></span></a>
								<div class="hidden-lg hidden-md hidden-sm">&nbsp;</div>
								<a
									href="view-company-profile?usproid=${message.companyId}&onmess=${message.id}"
									class="btn btn-primary">Company profile <span
									class="glyphicon glyphicon-plane"></span></a>
							</div>
						</div>
					</div>
				</c:if>
				<c:if
					test="${message.typeCode == 'ADGM' || message.typeCode == 'GNPM' || message.typeCode == 'GNSM' }">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a href="#col${theCount.count}" data-toggle="collapse"
									data-parent="#accordion"> ${message.company} -
									${message.type} </a>
							</h4>
						</div>
						<div id="col${theCount.count}"
							class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
							<div class="panel-body">
								<h4>Message details</h4>
								<table id="no-more-tables"
									class="table table-bordered table-condensed cf">
									<thead class="cf">
										<tr>
											<th class="numeric">Id</th>
											<th class="numeric">Message</th>
											<th class="numeric">Sender</th>
											<th class="numeric">Date created</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td data-title="Id">${message.id}</td>
											<td data-title="Message" class="multicell">&#xA;${message.message}</td>
											<td data-title="Sender" class="multicell">&#xA;${message.firstName}
												${message.lastName}</td>
											<td data-title="Created" class="multicell">&#xA;${message.created}</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="panel-footer">
								<a href="response?reto=${message.userId}&onmess=${message.id}"
									class="btn btn-primary">Respond <span
									class="glyphicon glyphicon-share-alt"></span></a>
								<div class="hidden-lg hidden-md hidden-sm">&nbsp;</div>
								<a
									href="view-user-profile?usproid=${message.userId}&onmess=${message.id}"
									class="btn btn-primary">User profile <span
									class="glyphicon glyphicon-user"></span></a>
								<div class="hidden-lg hidden-md hidden-sm">&nbsp;</div>
								<a
									href="view-company-profile?usproid=${message.companyId}&onmess=${message.id}"
									class="btn btn-primary">Company profile <span
									class="glyphicon glyphicon-plane"></span></a>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	<%@include file="../footer.jsp"%>
</body>
</html>
