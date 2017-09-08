<%-- 
    Document   : parts
    Created on : Aug 5, 2017, 12:11:29 PM
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

<%-- <script>
	var z = "";
	var a = "";
	function setId(id, act) {
		z = id;
		a = act;
	}
	$(document)
			.ready(
					function() {
						$('#search-b')
								.click(
										function() {
											$
													.ajax({
														type : 'GET',
														url : 'search-parts?object='
																+ $('#search-box').val(),
														dataType : "html",
														headers : {
															Accept : "application/json; charset=utf-8",
															"Content-Type" : "application/json; charset=utf-8"
														},
														success : function(
																result) {
															$('#results').empty
															var res = $
																	.parseJSON(result);
															var accordion = '<div class="panel-group" id="accordion">';
															if (res.length > 0) {
																for (var i = 0; i < res.length; i++) {
																	accordion += '<div class="panel panel-primary"><div class="panel-heading"><h4 class="panel-title"><a href="#col' + i + '" data-toggle="collapse" data-parent="#accordion">' + res[i].company + '</a></h4></div><div id="col$' + i + '"class="panel-collapse collapse'
																	if (i === 0) {
																		accordion += ' in';
																	}
																	accordion += '>'
																}
															}
															
															
														}
													});
										});
					});
</script>--%>

</head>
<body>
	<%@include file="../navigation.jsp"%>
	<div class="container" style="margin-top: 50px;">
		<h3 class="ita">
			<span class="for-aero">PARTS</span> - search available on stock
		</h3>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<sf:form action="search-parts" method="post">
			<div class="input-group">
				<input type="text" id="search-box" name="pNumber"
					class="form-control" placeholder="Search by Part Number">
				<div class="input-group-btn">
					<button class="btn btn-primary" type="submit">
						<i class="glyphicon glyphicon-search"></i>
					</button>
				</div>
			</div>
		</sf:form>
		<br>
		<hr>

		<c:if test="${not empty stockPartModels}">
			<h2 class="text-primary">Stock found</h2>
			<hr>
			<div class="panel-group" id="accordion2">
				<c:forEach items="${stockPartModels}" var="stockPart"
					varStatus="theCount">
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
											<th class="numeric">Part</th>
											<th class="numeric">Description</th>
											<th class="numeric">Category</th>
											<th class="numeric">Condition</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td data-title="Id">${stockPart.id}</td>
											<td data-title="Part">${stockPart.partNumber}</td>
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
		</c:if>
		<c:if test="${not empty partFound.partNumber}">
			<h2 class="text-primary">Parts found</h2>
			<hr>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4 class="panel-title"
						style="color: #FFEA97; text-shadow: 2px 1px #333333;">${partFound.partNumber}</h4>
				</div>

				<div class="panel-body">
					<h4 style="padding-left: 5px">Part details</h4>
					<table id="no-more-tables"
						class="table table-bordered table-condensed cf">
						<thead class="cf">
							<tr>
								<th class="numeric">Category</th>
								<th class="numeric">Part number</th>
								<th class="numeric">Description</th>
								<th class="numeric">Date created</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td data-title="Category">${partFound.category.category}</td>
								<td data-title="Part">${partFound.partNumber}</td>
								<td data-title="Description" class="multicell">&#xA;${partFound.description}</td>
								<td data-title="Created" class="multicell">&#xA;${partFound.created}</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="panel-footer">
					<a href="admin-question" class="btn btn-primary">Ask a question
						<span class="glyphicon glyphicon-question-sign"></span>
					</a>
				</div>

			</div>
		</c:if>

		<div class="col-xs-12 container-fluid">
			<hr>
			<img src="resources/images/p.jpg" class="image-responsive"
				style="width: 100%">
			<hr>
		</div>
	</div>

	<%@include file="../footer.jsp"%>
</body>
</html>
