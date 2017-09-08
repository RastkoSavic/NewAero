<%-- 
    Document   : admin-primary-services
    Created on : Aug 6, 2017, 1:24:41 PM
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
						$('.edit-b')
								.click(
										function() {
											$
													.ajax({
														type : 'GET',
														url : 'get-primary-service?action='
																+ a
																+ '&object='
																+ z,
														dataType : "html",
														headers : {
															Accept : "application/json; charset=utf-8",
															"Content-Type" : "application/json; charset=utf-8"
														},
														success : function(
																result) {
															var service = $
																	.parseJSON(result);
															document
																	.getElementById(
																			a
																					+ 'Serv')
																	.setAttribute(
																			'value',
																			service[1]);
															document
																	.getElementById(
																			a
																					+ 'Id')
																	.setAttribute(
																			'value',
																			service[0]);
															document
																	.getElementById(a
																			+ 'Des').innerHTML = type[2];
														}
													});
										});
					});
</script> --%>

</head>
<body>
	<%@include file="../../navigation.jsp"%>
	<div id="nas" class="container" style="margin-top: 50px;">
		<h1>Primary Services</h1>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<sf:form action="add-primary-service" method="POST"
			modelAttribute="primaryServiceAdd" class="form-horizontal"
			role="form">
			<h3 id="formHead" class="text-primary">Add new Primary Service</h3>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addServ">Primary
					Service:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addServ"
						path="service" placeholder="Enter new primary service" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="service" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addDes">Description:</label>
				<div class="col-sm-10">
					<sf:textarea rows="4" class="form-control" id="addDes"
						path="description" placeholder="Enter brief description" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="description" cssClass="alert-danger ita" />
				</div>
			</div>

			<input type="hidden" id="servId" name="servId" value="0">
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
		<h3 class="text-primary">Service list</h3>
		<hr>
		<div class="panel-group" id="accordion">
			<c:forEach items="${services}" var="service" varStatus="theCount">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a href="#col${theCount.count}" data-toggle="collapse"
								data-parent="#accordion"> ${service.service} </a>
						</h4>
					</div>
					<div id="col${theCount.count}"
						class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
						<div class="panel-body">
							<h4>Service details</h4>
							<table id="no-more-tables"
								class="table table-bordered table-condensed cf">
								<thead class="cf">
									<tr>
										<th class="numeric">Id</th>
										<th class="numeric">Description</th>
										<th class="numeric">Date created</th>
										<th class="numeric">Date updated</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td data-title="Id">${service.id}</td>
										<td id="td${service.id}" data-title="Description"
											class="multicell">&#xA;${service.description}</td>
										<td data-title="Created" class="multicell">&#xA;${service.created}</td>
										<td data-title="Updated" class="multicell">&#xA;${service.updated}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<button type="button" class="btn btn-primary edit-b"
								id="${service.id}" name="${service.service}">
								Edit <span class="glyphicon glyphicon-edit"></span>
							</button>
							<button type="button" class="btn btn-danger del-b"
								data-toggle="modal" data-target="#deleteModal"
								id="${service.id}" name="${service.service}">
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
							sure you want to delete this service?</h4>
					</div>
					<div class="modal-body">
						<sf:form action="delete-primary-service" method="POST"
							modelAttribute="primaryServiceDelete" class="form-horizontal"
							role="form">
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteServ">Primary
									Service:</label>
								<div class="col-sm-10">
									<sf:input type="text" class="form-control" id="deleteServ"
										path="service" readonly="true" />
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
	<%@include file="../../footer.jsp"%>
	<script>
		$(".edit-b").click(function() {
			var z = this.id;
			var x = this.name;
			var y = $('#td' + z).text();
			$("#addServ").val(x);
			$('#addDes').val(y);
			$('#servId').val(z);
			$('#formHead').text("Edit service - " + x);
			$('html, body').animate({
				scrollTop : $("#nas").offset().top
			}, 1000);
		});

		$(".del-b").click(function() {
			var z = this.id;
			var x = this.name;
			$("#deleteServ").val(x);
			$("#deleteId").val(z);
		});

		$('#res-b').click(function() {
			$('#servId').val("0");
			$('#formHead').text("Add new Primary Service");
		});

		$('#dis-mod').click(function() {
			$("#deleteServ").val("");
			$("#deleteId").val("");
		});
	</script>
</body>
</html>
