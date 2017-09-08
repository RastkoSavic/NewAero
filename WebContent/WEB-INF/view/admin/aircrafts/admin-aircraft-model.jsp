<%-- 
    Document   : admin-aircraft-model
    Created on : Aug 6, 2017, 1:17:48 PM
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
	<div id="nas" class="container" style="margin-top: 50px;">
		<h1>Aircraft Models</h1>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<sf:form action="add-aircraft-model" method="POST"
			modelAttribute="aircraftModelAdd" class="form-horizontal" role="form">
			<h3 id="formHead" class="text-primary">Add new Aircraft Model</h3>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addMod">Aircraft
					Model:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addMod" path="model"
						placeholder="Enter new aircraft model" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="model" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addType">Aircraft
					Type:</label>
				<div class="col-sm-10">
					<select class="form-control" name="typeId" id="addType">
						<c:forEach items="${types}" var="type">
							<option value="${type.id}">${type.type}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<input type="hidden" id="modId" name="modId" value="0">
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
		<h3 class="text-primary">Aircraft Model list</h3>
		<hr>
		<div class="panel-group" id="accordion">
			<c:forEach items="${models}" var="model" varStatus="theCount">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a href="#col${theCount.count}" data-toggle="collapse"
								data-parent="#accordion"> ${model.model} </a>
						</h4>
					</div>
					<div id="col${theCount.count}"
						class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
						<div class="panel-body">
							<h4>Model details</h4>
							<table id="no-more-tables"
								class="table table-bordered table-condensed cf">
								<thead class="cf">
									<tr>
										<th class="numeric">Id</th>
										<th class="numeric">Type</th>
										<th class="numeric">Date created</th>
										<th class="numeric">Date updated</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td data-title="Id">${model.id}</td>
										<td data-title="Type">${model.type.type}</td>
										<td data-title="Created" class="multicell">&#xA;${model.created}</td>
										<td data-title="Updated" class="multicell">&#xA;${model.updated}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<button type="button" class="btn btn-primary edit-b"
								id="${model.id}" name="${model.model}">
								Edit <span class="glyphicon glyphicon-edit"></span>
							</button>
							<button type="button" class="btn btn-danger del-b"
								data-toggle="modal" data-target="#deleteModal" id="${model.id}"
								name="${model.model}">
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
							sure you want to delete this model?</h4>
					</div>
					<div class="modal-body">
						<sf:form action="delete-aircraft-model" method="POST"
							modelAttribute="aircraftModelDelete" class="form-horizontal"
							role="form">
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteVar">Aircraft
									Model:</label>
								<div class="col-sm-10">
									<sf:input type="text" class="form-control" id="deleteMod"
										path="model" readonly="true" />
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
	<%@include file="../../footer.jsp"%>
	<script>
		$(".edit-b").click(function() {
			var z = this.id;
			var x = this.name;
			$("#addType").prop('disabled', true);
			$("#addMod").val(x);
			$('#modId').val(z);
			$('#formHead').text("Edit model - " + x);
			$('html, body').animate({
				scrollTop : $("#nas").offset().top
			}, 1000);
		});

		$(".del-b").click(function() {
			var z = this.id;
			var x = this.name;
			$("#deleteMod").val(x);
			$('#deleteId').val(z);
		});

		$('#res-b').click(function() {
			$('#modId').val('0');
			$('#formHead').text("Add new Aircraft Model");
			$("#addType").prop('disabled', false);
		});

		$('#dis-mod').click(function() {
			$("#deleteMod").val("");
			$('#deleteId').val("");
		});
	</script>
</body>
</html>
