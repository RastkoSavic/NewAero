<%-- 
    Document   : new-stock
    Created on : Aug 5, 2017, 2:14:48 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
			<span class="for-aero">NEW STOCK</span> - check out the fresh stock
		</h3>
		<hr>
		<br>
		<div class="col-xs-12 container-fluid">
			<hr>
			<img src="resources/images/p.jpg" class="image-responsive"
				style="width: 100%">
			<hr>
		</div>
		<h2 class="text-primary">Stock list</h2>
		<hr>
		<div class="panel-group" id="accordion2">
			<c:forEach items="${stockParts}" var="stockPart" varStatus="theCount">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title"
							style="font-weight: bold; text-shadow: 1px 1px darkblue">
							<a href="#col${theCount.count}" data-toggle="collapse"
								data-parent="#accordion2">${stockPart.company }
								${stockPart.partNumber} </a>
						</h4>
					</div>
					<div id="col${theCount.count}"
						class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
						<div class="panel-body">
							<h4 style="padding-left: 5px">Part details</h4>
							<table id="no-more-tables"
								class="table table-bordered table-condensed cf">
								<thead class="cf">
									<tr>
										<th class="numeric">Id</th>
										<th class="numeric">Description</th>
										<th class="numeric">Category</th>
										<th class="numeric">Condition</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td data-title="Id">${stockPart.id}</td>
										<td data-title="Description" class="multicell">&#xA;${stockPart.description}</td>
										<td data-title="Category">${stockPart.category}</td>
										<td data-title="Condition">${stockPart.condition}</td>
									</tr>
								</tbody>
							</table>
							<h4 style="padding-left: 5px">Stock details</h4>
							<table id="no-more-tables"
								class="table table-bordered table-condensed cf">
								<thead class="cf">
									<tr>
										<th class="numeric">Quantity</th>
										<th class="numeric">Price</th>
										<th class="numeric">Notes</th>
										<th class="numeric">Date created</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td data-title="Quantity">${stockPart.quantity}</td>
										<td data-title="Price">${stockPart.price}</td>
										<td data-title="Notes" class="multicell">&#xA;${stockPart.notes}</td>
										<td data-title="Created" class="multicell">&#xA;${stockPart.created}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<a href="response?reto=${stockPart.userId}&onmess=0"
								class="btn btn-primary">Inquire <span
								class="glyphicon glyphicon-envelope"></span></a>
							<div class="hidden-lg hidden-md hidden-sm">&nbsp;</div>
							<a href="view-user-profile?usproid=${stockPart.userId}&onmess=0"
								class="btn btn-primary">User profile <span
								class="glyphicon glyphicon-user"></span></a>
							<div class="hidden-lg hidden-md hidden-sm">&nbsp;</div>
							<a
								href="view-company-profile?usproid=${stockPart.companyId}&onmess=${stockPart.id}"
								class="btn btn-primary">Company profile <span
								class="glyphicon glyphicon-plane"></span></a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<%@include file="../footer.jsp"%>
</body>
</html>
