<%-- 
    Document   : company-profile
    Created on : Aug 5, 2017, 12:11:17 PM
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

<script>
	$(document)
			.ready(
					function() {
						$('#addType')
								.on(
										'change',
										function() {

											var x = this.value;

											$
													.ajax({
														type : 'GET',
														url : 'select-type-for-stock?object='
																+ x,
														dataType : "html",
														headers : {
															Accept : "application/json; charset=utf-8",
															"Content-Type" : "application/json; charset=utf-8"
														},
														success : function(json) {
															$('#addMod')
																	.empty();

															var res = $
																	.parseJSON(json);
															var sel = document
																	.getElementById('#addMod');
															var options = "<option value=-1>Select</option>";
															for (var i = 0; i < res.length; i++) {
																//$('#addMod').append($('<option>')).text(res[i].model).attr('value', res[i].id);
																options += '<option value= "' + res[i].id + '">'
																		+ res[i].model
																		+ '</option>';
															}
															$('#addMod').html(
																	options);
														}
													});
										});

					});
	//********************************************

	$(document)
			.ready(
					function() {
						$('#addMod')
								.on(
										'change',
										function() {

											var y = this.value;

											$
													.ajax({
														type : 'GET',
														url : 'select-model?object='
																+ y,
														dataType : "html",
														headers : {
															Accept : "application/json; charset=utf-8",
															"Content-Type" : "application/json; charset=utf-8"
														},
														success : function(json) {
															$('#addVar')
																	.empty();

															var res = $
																	.parseJSON(json);
															var sel = document
																	.getElementById('#addVar');
															var options = "<option value=-1>Select</option>";
															for (var i = 0; i < res.length; i++) {
																//$('#addMod').append($('<option>')).text(res[i].model).attr('value', res[i].id);
																options += '<option value= "' + res[i].id + '">'
																		+ res[i].variant
																		+ '</option>';
															}
															$('#addVar').html(
																	options);
														}
													});
										});

					});
</script>
<style>
body {
	position: relative;
}
</style>
</head>
<body data-spy="scroll" data-target="#myScrollspy" data-offset="25">
	<%@include file="../navigation.jsp"%>
	<div class="container" style="margin-top: 50px;">
		<h3 class="ita">
			<span class="for-aero">Company PROFILE</span> - your command bridge
		</h3>
		<hr>
		<br>
		<c:out value="${messt}" escapeXml="false">
		</c:out>
		<div class="row">
			<div class="col-sm-3">
				<h2>${company.name}</h2>
				<hr>
				<div class="container logoprofile">
					<img src="resources/images/${company.name}.jpg"
						class="img-responsive" alt="logo">
				</div>
				<hr>
				<h3 class="text-primary">Primary service:
					${company.primaryService.service}</h3>
				<br> <a href="company-addresses" class="btn btn-primary">Adresses</a>
				<br>
				<div id="myScrollspy">
					<h4 class="text-primary">Post messages</h4>
					<hr>
					<ul class="nav nav-pills nav-stacked" data-spy="affix"
						data-offset-top="470">
						<li><a href="general-sales-message" class="text-primary">General
								Sales Add</a></li>
						<li><a href="general-purchase-message" class="text-primary">General
								Purchase Add</a></li>
						<li><a href="parts-offered-add" class="text-primary">Offer
								parts</a></li>
						<li><a href="parts-wanted-add" class="text-primary">Parts
								Wanted Message</a></li>
						<li><a href="admin-question" class="text-primary">Ask
								Admin a Question <span class="glyphicon glyphicon-question-sign"></span>
						</a></li>
					</ul>
				</div>
			</div>
			<div id="nas" class="col-sm-9"
				style="border-left: 1px solid #EFEFEF;">
				<br>
				<sf:form class="form-horizontal" method="POST"
					action="add-stock-part" modelAttribute="stockAdd">
					<h3 id="formHead" class="text-primary">Add new Stock Part</h3>
					<hr>
					<br>
					<h4>Part details</h4>
					<hr>
					<div class="form-group">
						<label class="control-label col-sm-2" for="addCat">Part
							Category:</label>
						<div class="col-sm-10">
							<select class="form-control" name="category" id="addCat">
								<c:forEach items="${categories}" var="category">
									<option value="${category.category}">${category.category}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="addCond">Part
							Condition:</label>
						<div class="col-sm-10">
							<select class="form-control" name="condition" id="addCond">
								<option value="0">Select</option>
								<c:forEach items="${conditions}" var="condition">
									<option value="${condition.code}">${condition.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="addPart">Part
							Number:</label>
						<div class="col-sm-10">
							<sf:input type="text" class="form-control" id="addPart"
								path="partNumber" placeholder="Enter part number" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="partNumber" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="addDes">Description:</label>
						<div class="col-sm-10">
							<sf:input type="text" class="form-control" id="addDes"
								path="description" placeholder="Enter brief description" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="description" cssClass="alert-danger ita" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="addQty">Quantity:</label>
						<div class="col-sm-10">
							<sf:input type="text" class="form-control" id="addQty"
								path="quantity" placeholder="Enter available quantity" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="quantity" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="addPrice">Price
							per unit:</label>
						<div class="col-sm-10">
							<sf:input type="text" class="form-control" id="addPrice"
								path="price" placeholder="Enter price per unit" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="price" cssClass="alert-danger ita" />
						</div>
					</div>

					<br>
					<h4>Aircraft details</h4>
					<hr>
					<div class="form-group">
						<label class="control-label col-sm-2" for="addType">Aircraft
							Type:</label>
						<div class="col-sm-10">
							<select class="form-control" name="typeId" id="addType">
								<option value="0">Select</option>
								<c:forEach items="${types}" var="type">
									<option value="${type.id}">${type.type}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="addMod">Aircraft
							Model:</label>
						<div class="col-sm-10">
							<select class="form-control" name="modelId" id="addMod">
								<option value=-1>Select</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="addVar">Variant:</label>
						<div class="col-sm-10">
							<select class="form-control" name="variantId" id="addVar">
								<option value=-1>Select</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="addNote">Notes:</label>
						<div class="col-sm-10">
							<sf:textarea class="form-control" id="addNote" path="notes"
								placeholder="Enter brief notes" rows="4" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="notes" cssClass="alert-danger ita" />
						</div>
					</div>

					<input type="hidden" name="costId" value="${company.id}" />
					<input type="hidden" name="usstId" value="${user.id}" />
					<input type="hidden" id="stprId" name="stprId" value="0">
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-success">
								Submit <span class="glyphicon glyphicon-save"></span>
							</button>
						</div>
					</div>
				</sf:form>
				<br>
				<hr>
				<br>
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
												<td id="ds${stockPart.id}" data-title="Description">${stockPart.part.description}</td>
												<td id="qu${stockPart.id}" data-title="Quantity">${stockPart.quantity}</td>
												<td id="pr${stockPart.id}" data-title="Price">${stockPart.price}</td>
											</tr>
										</tbody>
									</table>
									<br>
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
												<td id="nt${stockPart.id}" data-title="Notes"
													class="multicell">&#xA;${stockPart.notes}</td>
												<td data-title="Created" class="multicell">&#xA;${stockPart.created}</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="panel-footer">
									<button type="button" class="btn btn-primary edit-b"
										id="${stockPart.id}" name="${stockPart.part.partNumber}">
										Edit <span class="glyphicon glyphicon-edit"></span>
									</button>
									<button type="button" class="btn btn-danger dels-b"
										data-toggle="modal" data-target="#deleteStockModal"
										id="${stockPart.id}" name="${stockPart.part.partNumber}">
										Delete <span class="glyphicon glyphicon-trash"></span>
									</button>
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
				<hr>
				<br>
				<c:set var="me" value="${mesu}"></c:set>
				<c:out value="${me}" escapeXml="false">
				</c:out>
				<sf:form action="add-company-user" method="POST"
					modelAttribute="userAdd" class="form-horizontal" role="form">
					<h3 class="text-primary">Add new company User</h3>
					<hr>
					<div class="form-group">
						<label class="control-label col-sm-2" for="addFName">First
							name:</label>
						<div class="col-sm-10">
							<sf:input type="text" class="form-control" id="addFName"
								path="firstName" placeholder="Enter First name" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="firstName" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="addLName">Last
							name:</label>
						<div class="col-sm-10">
							<sf:input type="text" class="form-control" id="addLName"
								path="lastName" placeholder="Enter Last name" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="lastName" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="addPos">Position:</label>
						<div class="col-sm-10">
							<sf:input type="text" class="form-control" id="addPos"
								path="position" placeholder="Enter user position" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="position" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="addEmail">Email:</label>
						<div class="col-sm-10">
							<sf:input type="email" class="form-control" id="addEmail"
								path="email" placeholder="Enter user email" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="email" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="addPass">Password:</label>
						<div class="col-sm-10">
							<sf:input type="password" class="form-control" id="addPass"
								path="password" placeholder="Enter user password" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="password" cssClass="alert-danger ita" />
						</div>
					</div>

					<input type="hidden" name="compId" value="${company.id}" />
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-success">
								Add User <span class="glyphicon glyphicon-plus-sign"></span>
							</button>
						</div>
					</div>
				</sf:form>
				<br>
				<hr>
				<br>
				<c:out value="${mest}" escapeXml="false">
				</c:out>
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
									<button type="button" class="btn btn-danger del-b"
										data-toggle="modal" data-target="#deleteModal" id="${user.id}">
										Remove <span class="glyphicon glyphicon-remove-sign"></span>
									</button>
									<div class="hidden-lg hidden-md hidden-sm">&nbsp;</div>
									<a href="view-user-profile?usproid=${user.id}&onmess=0"
										class="btn btn-primary">User profile <span
										class="glyphicon glyphicon-user"></span></a>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<br>
				<hr>
				<br>
				<c:out value="${mes}" escapeXml="false">
				</c:out>
				<sf:form action="company-sett" method="POST"
					modelAttribute="companySettings" class="form-horizontal"
					role="form">
					<h3 class="text-primary">
						Edit Company Settings <span class="glyphicon glyphicon-cog"></span>
					</h3>
					<hr>
					<br>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label><sf:checkbox path="logo" id="logo"
										data-toggle="toggle" data-onstyle="primary"
										data-offstyle="danger" />Display Logo?</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="logoUrl">Logo
							URL:</label>
						<div class="col-sm-10">
							<sf:input type="text" class="form-control" id="logoUrl"
								path="logoUrl" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="logoUrl" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label><sf:checkbox path="phoneAllowed"
										id="phoneAllowed" data-toggle="toggle" data-onstyle="primary"
										data-offstyle="danger" />Display Phone?</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="phone">Phone:</label>
						<div class="col-sm-10">
							<sf:input type="text" class="form-control" id="phone"
								path="phone" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="phone" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="phoneAlt">Alternate
							Phone:</label>
						<div class="col-sm-10">
							<sf:input type="text" class="form-control" id="phoneAlt"
								path="phoneAlt" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="phoneAlt" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label><sf:checkbox path="emailAllowed"
										id="emailAllowed" data-toggle="toggle" data-onstyle="primary"
										data-offstyle="danger" />Display Email?</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Email:</label>
						<div class="col-sm-10">
							<sf:input type="email" class="form-control" id="email"
								path="email" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="email" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="emailAlt">Alternate
							Email:</label>
						<div class="col-sm-10">
							<sf:input type="email" class="form-control" id="emailAlt"
								path="emailAlt" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="emailAlt" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="web">Website:</label>
						<div class="col-sm-10">
							<sf:input type="text" class="form-control" id="web" path="web" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="web" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label><sf:checkbox path="stock" id="stock"
										data-toggle="toggle" data-onstyle="primary"
										data-offstyle="danger" />Display Stock?</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label><sf:checkbox path="service" id="service"
										data-toggle="toggle" data-onstyle="primary"
										data-offstyle="danger" />Display Primary Service?</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="notes">Notes:</label>
						<div class="col-sm-10">
							<sf:textarea class="form-control" id="notes" path="notes"
								rows="4" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="notes" cssClass="alert-danger ita" />
						</div>
					</div>

					<input type="hidden" name="coId" value="${company.id}" />
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-success">
								Save Settings <span class="glyphicon glyphicon-save"></span>
							</button>
						</div>
					</div>
				</sf:form>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="modal fade" id="deleteModal" role="dialog" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 id="title-form" class="modal-title text-danger">Are you
							sure you want to remove this user?</h4>
					</div>
					<div class="modal-body">
						<sf:form action="delete-company-user" method="POST"
							modelAttribute="userDelete" class="form-horizontal" role="form">

							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteId">
									Id: </label>
								<div class="col-sm-10">
									<sf:input type="text" class="form-control" id="deleteId"
										path="id" readonly="true" />
								</div>
							</div>
							<input type="hidden" id="cuId" name="cuId" value="0">
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-danger">
										Remove <span class="glyphicon glyphicon-remove-sign"></span>
									</button>
								</div>
							</div>
						</sf:form>
					</div>
					<div class="modal-footer">
						<button type="button" id="dis-mod" class="btn btn-primary"
							data-dismiss="modal">
							Close <span class="glyphicon glyphicon-remove"></span>
						</button>
					</div>
				</div>
			</div>
		</div>

	</div>

	<div class="container">
		<div class="modal fade" id="deleteStockModal" role="dialog"
			tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 id="title-form" class="modal-title text-danger">Are you
							sure you want to delete this stock collection?</h4>
					</div>
					<div class="modal-body">
						<sf:form action="delete-stock-part" method="POST"
							modelAttribute="stockPartDelete" class="form-horizontal"
							role="form">
							<div class="form-group">
								<label class="control-label col-sm-2" for="deletePart">Part
									number:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="deletePart"
										name="partNumber" readonly />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteStId">
									Id: </label>
								<div class="col-sm-10">
									<sf:input type="text" class="form-control" id="deleteStId"
										path="id" readonly="true" />
								</div>
							</div>
							<input type="hidden" id="spId" name="spId" value="0">
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-danger">
										Delete <span class="glyphicon glyphicon-trash"></span>
									</button>
								</div>
							</div>
						</sf:form>
					</div>
					<div class="modal-footer">
						<button type="button" id="dis-mod" class="btn btn-primary"
							data-dismiss="modal">
							Close <span class="glyphicon glyphicon-remove"></span>
						</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	<%@include file="../footer.jsp"%>
	<script>
		$(".del-b").click(function() {
			var id = this.id;
			var name = $('#un' + id).text();
			$("#deleteId").val(id);
			$("#cuId").val(id);
		});

		$('#dis-mod').click(function() {
			$("#deleteId").val("");
			$("#cuId").val("");
		});
	</script>

	<script>
		$(document).ready(function() {
			// Handler for .ready() called.
			if ($('#mes').length) {
				$('html, body').animate({
					scrollTop : $('#mes').offset().top - 50
				}, 'slow');
			}
		});
	</script>

	<script>
		$(".edit-b").click(function() {
			var id = this.id;
			var partNumber = this.name;
			var description = $('#ds' + id).text();
			var quantity = $('#qu' + id).text();
			var price = $('#pr' + id).text();
			var notes = $('#nt' + id).text();
			$('#stprId').val(id);
			$("#addDes").val(description);
			$("#addQty").val(quantity);
			$("#addPrice").val(price);
			$("#addNote").val(notes);
			$('#addPart').val(partNumber);
			$("#addPart").prop('readonly', true);
			$('#addCat').prop('disabled', true);
			$('#addCond').prop('disabled', true);
			$('#addType').prop('disabled', true);
			$('#addVar').prop('disabled', true);
			$('#addMod').prop('disabled', true);
			$('#formHead').text("Edit stock for " + partNumber);
			$('html, body').animate({
				scrollTop : $("#nas").offset().top
			}, 1000);
		});

		$(".dels-b").click(function() {
			var id = this.id;
			var partNumber = this.name;
			$('#deleteStId').val(id);
			$("#deletePart").val(partNumber);
			$("#spId").val(id);
		});

		$('#res-b').click(function() {
			$('#stprId').val("0");
			$("#addPart").prop('readonly', false);
			$('#addCat').prop('disabled', false);
			$('#addCond').prop('disabled', false);
			$('#addType').prop('disabled', false);
			$('#addVar').prop('disabled', false);
			$('#addMod').prop('disabled', false);
			$('#formHead').text("Add new Stock Part");
		});

		$('#dis-mod').click(function() {
			$("#deleteStId").val("");
			$("#deletePart").val("");
			$("#spId").val("0");
		});
	</script>
</body>
</html>
