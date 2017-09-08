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
						$('#addCoun')
								.on(
										'change',
										function() {

											var x = this.value;

											$
													.ajax({
														type : 'GET',
														url : 'select-country?object='
																+ x,
														dataType : "html",
														headers : {
															Accept : "application/json; charset=utf-8",
															"Content-Type" : "application/json; charset=utf-8"
														},
														success : function(json) {
															$('#addCity')
																	.empty();

															var res = $
																	.parseJSON(json);
															var sel = document
																	.getElementById('#addCity');
															var options = "<option value=-1>Select</option>";
															for (var i = 0; i < res.length; i++) {
																//$('#addMod').append($('<option>')).text(res[i].model).attr('value', res[i].id);
																options += '<option value= "' + res[i].id + '">'
																		+ res[i].name
																		+ '</option>';
															}
															$('#addCity').html(
																	options);
														}
													});
										});

					});
</script>

</head>
<body>
	<%@include file="../navigation.jsp"%>
	<div id="nas" class="container" style="margin-top: 50px;">
		<h1>Addresses</h1>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<sf:form action="add-company-address" method="POST"
			modelAttribute="addressAdd" class="form-horizontal" role="form">
			<h3 id="formHead" class="text-primary">Add new Address</h3>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addAddr">Address:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addAddr"
						path="address" placeholder="Enter new address" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="address" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addType">Address
					Type:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addType" path="type"
						placeholder="Enter address type" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="type" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addUsed">Used
					for:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addUsed"
						path="usedFor" placeholder="Enter address purpose" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="usedFor" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addCoun">Country:</label>
				<div class="col-sm-10">
					<select class="form-control" name="countryCode" id="addCoun">
						<option value="">Select</option>
						<c:forEach items="${countries}" var="country">
							<option value="${country.code}">${country.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addCity">City:</label>
				<div class="col-sm-10">
					<select class="form-control" name="cityId" id="addCity">
						<option value=-1>Select</option>
					</select>
				</div>
			</div>
			<input type="hidden" id="addrId" name="addrId" value="0">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-6 col-xs-4">
					<button type="submit" class="btn btn-success">
						Submit <span class="glyphicon glyphicon-save"></span>
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
						<div class="panel-footer">
							<button type="button" class="btn btn-primary edit-b"
								id="${address.id}" name="${address.address}">
								Edit <span class="glyphicon glyphicon-edit"></span>
							</button>
							<button type="button" class="btn btn-danger del-b"
								data-toggle="modal" data-target="#deleteModal"
								id="${address.id}" name="${address.address}">
								Delete <span class="glyphicon glyphicon-trash"></span>
							</button>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

	</div>

	<div class="container">
		<div class="modal fade" id="deleteModal" role="dialog" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 id="title-form" class="modal-title text-danger">Are you
							sure you want to delete this address?</h4>
					</div>
					<div class="modal-body">
						<sf:form action="delete-company-address"
							modelAttribute="addressDelete" method="POST"
							class="form-horizontal" role="form">
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteAddr">Address:</label>
								<div class="col-sm-10">
									<sf:input type="text" class="form-control" id="deleteAddr"
										path="address" readonly="true" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteId">
									Id: </label>
								<div class="col-sm-10">
									<sf:input type="text" class="form-control" id="deleteId"
										path="id" readonly="true" />
								</div>
							</div>
							<input type="hidden" id="adId" name="adId" value="0">
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
		$(".edit-b").click(function() {
			var id = this.id;
			var address = this.name;
			var type = $('#ty' + id).text();
			var used = $('#uf' + id).text();
			$("#addAddr").val(address);
			$('#addType').val(type);
			$('#addUsed').val(used);
			$('#addrId').val(id);
			$("#addCoun").prop('disabled', true);
			$("#addCity").prop('disabled', true);
			$('#formHead').text("Edit adress - " + address);
			$('html, body').animate({
				scrollTop : $("#nas").offset().top
			}, 1000);
		});

		$(".del-b").click(function() {
			var id = this.id;
			var address = this.name;
			$("#deleteAddr").val(address);
			$("#deleteId").val(id);
			$("#adId").val(id);
		});

		$('#res-b').click(function() {
			$('#addrId').val("0");
			$("#addCoun").prop('disabled', false);
			$("#addCity").prop('disabled', false);
			$('#formHead').text("Add new Address");
		});

		$('#dis-mod').click(function() {
			$("#deleteAddr").val("");
			$("#deleteId").val("");
			$("#adId").val("0");
		});
	</script>
</body>
</html>