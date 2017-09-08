<%-- 
    Document   : admin-aircraft-variant
    Created on : Aug 6, 2017, 1:18:05 PM
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
						$('#addType')
								.on(
										'change',
										function() {

											var x = this.value;

											$
													.ajax({
														type : 'GET',
														url : 'select-type?object='
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
</script>

</head>
<body>
	<%@include file="../../navigation.jsp"%>
	<div id="nas" class="container" style="margin-top: 50px;">
		<h1>Aircraft Variants</h1>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<sf:form action="add-aircraft-variant" method="POST"
			modelAttribute="aircraftVariantAdd" class="form-horizontal"
			role="form">
			<h3 id="formHead" class="text-primary">Add new Aircraft Variant</h3>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addVar">Aircraft
					Variant:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addVar"
						path="variant" placeholder="Enter new aircraft model variant" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="variant" cssClass="alert-danger ita" />
				</div>
			</div>
			
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
			<input type="hidden" id="varId" name="varId" value="0">
			<div class="form-group row">
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
		<h3 class="text-primary">Aircraft Variant list</h3>
		<hr>
		<div class="panel-group" id="accordion">
			<c:forEach items="${variants}" var="variant" varStatus="theCount">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a href="#col${theCount.count}" data-toggle="collapse"
								data-parent="#accordion"> ${variant.variant} </a>
						</h4>
					</div>
					<div id="col${theCount.count}"
						class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
						<div class="panel-body">
							<h4>Variant details</h4>
							<table id="no-more-tables"
								class="table table-bordered table-condensed cf">
								<thead class="cf">
									<tr>
										<th class="numeric">Id</th>
										<th class="numeric">Model</th>
										<th class="numeric">Date created</th>
										<th class="numeric">Date updated</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td data-title="Id">${variant.id}</td>
										<td data-title="Model">${variant.model.model}</td>
										<td data-title="Created" class="multicell">&#xA;${variant.created}</td>
										<td data-title="Updated" class="multicell">&#xA;${variant.updated}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<button type="button" class="btn btn-primary edit-b"
								id="${variant.id}" name="${variant.variant}">
								Edit <span class="glyphicon glyphicon-edit"></span>
							</button>
							<button type="button" class="btn btn-danger del-b"
								data-toggle="modal" data-target="#deleteModal"
								id="${variant.id}" name="${variant.variant}">
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
							sure you want to delete this variant?</h4>
					</div>
					<div class="modal-body">
						<sf:form action="delete-aircraft-variant" method="POST"
							modelAttribute="aircraftVariantDelete" class="form-horizontal"
							role="form">
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteVar">Aircraft
									Variant:</label>
								<div class="col-sm-10">
									<sf:input type="text" class="form-control" id="deleteVar"
										path="variant" readonly="true" />
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
	<%@include file="../../footer.jsp" %>
	<script>
		$(".edit-b").click(function() {
			var z = this.id;
			var x = this.name;
			$("#addType").prop('disabled', true);
			$("#addMod").prop('disabled', true);
			$("#addVar").val(x);
			$('#varId').val(z);
			$('#formHead').text("Edit variant - " + x);
			$('html, body').animate({
				scrollTop : $("#nas").offset().top
			}, 1000);
		});

		$(".del-b").click(function() {
			var z = this.id;
			var x = this.name;
			$("#deleteVar").val(x);
			$('#deleteId').val(z);
		});

		$('#res-b').click(function() {
			$('#varId').val('0');
			$('#formHead').text("Add new Aircraft Variant");
			$("#addType").prop('disabled', false);
			$("#addMod").prop('disabled', false);
		});

		$('#dis-mod').click(function() {
			$("#deleteVar").val("");
			$('#deleteId').val("");
		});
	</script>
</body>
</html>
