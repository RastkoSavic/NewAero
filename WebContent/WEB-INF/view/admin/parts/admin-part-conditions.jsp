<%-- 
    Document   : admin-part-conditions
    Created on : Aug 6, 2017, 1:14:02 PM
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
														url : 'get-part-condition?action='
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
															var condition = $
																	.parseJSON(result);
															document
																	.getElementById(
																			a
																					+ 'ConN')
																	.setAttribute(
																			'value',
																			condition[1]);
															document
																	.getElementById(
																			a
																					+ 'ConC')
																	.setAttribute(
																			'value',
																			condition[0]);
															document
																	.getElementById(a
																			+ 'Des').innerHTML = condition[2];
														}
													});
										});
					});
</script> --%>

</head>
<body>
	<%@include file="../../navigation.jsp"%>
	<div id="nas" class="container" style="margin-top: 50px;">
		<h1>Part Conditions</h1>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<sf:form action="add-part-condition" method="POST"
			modelAttribute="partConditionAdd" class="form-horizontal" role="form">
			<h3 id="formHead" class="text-primary">Add new Part Condition</h3>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addConN">Condition:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addConN" path="name"
						placeholder="Enter new part condition" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="name" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addConC">Condition
					Code:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addConC" path="code"
						placeholder="Enter 2 character condition code" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="code" cssClass="alert-danger ita" />
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

			<input type="hidden" id="cndnId" name="cndnId" value="">
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
		<h3 class="text-primary">Part Condition list</h3>
		<hr>
		<div class="panel-group" id="accordion">
			<c:forEach items="${conditions}" var="condition" varStatus="theCount">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a href="#col${theCount.count}" data-toggle="collapse"
								data-parent="#accordion"> ${condition.name} </a>
						</h4>
					</div>
					<div id="col${theCount.count}"
						class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
						<div class="panel-body">
							<h4>Condition details</h4>
							<table id="no-more-tables"
								class="table table-bordered table-condensed cf">
								<thead class="cf">
									<tr>
										<th class="numeric">Code</th>
										<th class="numeric">Description</th>
										<th class="numeric">Date created</th>
										<th class="numeric">Date updated</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td data-title="Code">${condition.code}</td>
										<td id="ds${condition.code}" data-title="Description"
											class="multicell">&#xA;${condition.description}</td>
										<td data-title="Created" class="multicell">&#xA;${condition.created}</td>
										<td data-title="Updated" class="multicell">&#xA;${condition.updated}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<button type="button" class="btn btn-primary edit-b"
								id="${condition.code}" name="${condition.name}">
								Edit <span class="glyphicon glyphicon-edit"></span>
							</button>
							<button type="button" class="btn btn-danger del-b"
								data-toggle="modal" data-target="#deleteModal"
								id="${condition.code}" name="${condition.name}">
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
							sure you want to delete this condition?</h4>
					</div>
					<div class="modal-body">
						<sf:form action="delete-part-condition" method="POST"
							modelAttribute="partConditionDelete" class="form-horizontal"
							role="form">
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteConN">Condition:</label>
								<div class="col-sm-10">
									<sf:input type="text" class="form-control" id="deleteConN"
										path="name" readonly="true" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteConC">Condition
									Code:</label>
								<div class="col-sm-10">
									<sf:input type="text" class="form-control" id="deleteConC"
										path="code" readonly="true" />
								</div>
							</div>
							<input type="hidden" id="cdId" name="cdId" value="">
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
			var code = this.id;
			var name = this.name;
			var description = $("#ds" + code).text();
			$("#addConN").val(name);
			$("#addDes").val(description);
			$('#cndnId').val(code);
			$('#addConC').val(code);
			$("#addConC").prop('readonly', true);
			$('#formHead').text("Edit condition - " + name);
			$('html, body').animate({
				scrollTop : $("#nas").offset().top
			}, 1000);
		});

		$(".del-b").click(function() {
			var code = this.id;
			var name = this.name;
			$("#deleteConN").val(name);
			$("#deleteConC").val(code);
			$("#cdId").val(code);
		});

		$('#res-b').click(function() {
			$('#cndnId').val("");
			$("#addConC").prop('readonly', false);
			$('#formHead').text("Add new Part Condition");
		});

		$('#dis-mod').click(function() {
			$("#deleteConN").val("");
			$("#deleteConC").val("");
			$("#cdId").val("");
		});
	</script>
</body>
</html>
