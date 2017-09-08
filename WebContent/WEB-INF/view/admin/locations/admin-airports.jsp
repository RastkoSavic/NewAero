<%-- 
    Document   : admin-airports
    Created on : Aug 6, 2017, 1:20:30 PM
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
														url : 'select-country-for-airport?object='
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
	<%@include file="../../navigation.jsp"%>
	<div id="nas" class="container" style="margin-top: 50px;">
		<h1>Airports</h1>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<sf:form action="add-airport" method="POST"
			modelAttribute="airportAdd" class="form-horizontal">
			<h3 id="formHead" class="text-primary">Add new Airport</h3>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addAirp">Airport
					name:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addAirp" path="name"
						placeholder="Enter airport name" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="name" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addIATA">IATA
					Code:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addIATA"
						path="IATACode" placeholder="Enter 3 character IATA Code" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="IATACode" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addAddr">Address:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addAddr"
						path="address" placeholder="Enter airport address" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="address" cssClass="alert-danger ita" />
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
			<input type="hidden" id="airpId" name="airpId" value="0">
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
		<h3 class="text-primary">Airport list</h3>
		<hr>
		<div class="panel-group" id="accordion">
			<c:forEach items="${airports}" var="airport" varStatus="theCount">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a href="#col${theCount.count}" data-toggle="collapse"
								data-parent="#accordion"> ${airport.name} </a>
						</h4>
					</div>
					<div id="col${theCount.count}"
						class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
						<div class="panel-body">
							<h4>Airport details</h4>
							<table id="no-more-tables"
								class="table table-bordered table-condensed cf">
								<thead class="cf">
									<tr>
										<th class="numeric">Id</th>
										<th class="numeric">IATA Code</th>
										<th class="numeric">Address</th>
										<th class="numeric">City</th>
										<th class="numeric">Date added</th>
										<th class="numeric">Date updated</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td data-title="Id">${airport.id}</td>
										<td id="co${airport.id}" data-title="IATA">${airport.IATACode}</td>
										<td id="ad${airport.id}" data-title="Adress" class="multicell">&#xA;${airport.address.address}</td>
										<td data-title="City">${airport.address.city.name}</td>
										<td data-title="Date Created" class="multicell">&#xA;${airport.created}</td>
										<td data-title="Date Updated" class="multicell">&#xA;${airport.updated}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<button type="button" class="btn btn-primary edit-b"
								id="${airport.id}" name="${airport.name}">
								Edit <span class="glyphicon glyphicon-edit"></span>
							</button>
							<button type="button" class="btn btn-danger del-b"
								data-toggle="modal" data-target="#deleteModal"
								id="${airport.id}" name="${airport.name}">
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
							sure you want to delete this airport?</h4>
					</div>
					<div class="modal-body">
						<sf:form action="delete-airport" method="POST"
							class="form-horizontal" role="form">
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteName">Name:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="deleteName"
										name="airpName" readonly />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteId">
									Id: </label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="deleteId" name="id"
										readonly />
								</div>
							</div>
							<input type="hidden" id="aiId" name="aiId" value="0">
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
	<%@include file="../../footer.jsp"%>
	<script>
		$(".edit-b").click(function() {
			var id = this.id;
			var name = this.name;
			var iata = $('#co' + id).text();
			var address = $('#ad' + id).text();
			$("#addAirp").val(name);
			$('#addIATA').val(iata);
			$('#addAddr').val(address);
			$('#airpId').val(id);
			$("#addCoun").prop('disabled', true);
			$("#addCity").prop('disabled', true);
			$('#formHead').text("Edit airport - " + name);
			$('html, body').animate({
				scrollTop : $("#nas").offset().top
			}, 1000);
		});

		$(".del-b").click(function() {
			var id = this.id;
			var name = this.name;
			$("#deleteName").val(name);
			$("#deleteId").val(id);
			$("#aiId").val(id);
		});

		$('#res-b').click(function() {
			$('#airpId').val("0");
			$("#addCoun").prop('disabled', false);
			$("#addCity").prop('disabled', false);
			$('#formHead').text("Add new Airport");
		});

		$('#dis-mod').click(function() {
			$("#deleteName").val("");
			$("#deleteId").val("");
			$("#aiId").val("0");
		});
	</script>
</body>
</html>
