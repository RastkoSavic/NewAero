<%-- 
    Document   : admin-countries
    Created on : Aug 9, 2017, 7:44:34 PM
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
														url : 'get-country?action='
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
															var country = $
																	.parseJSON(result);
															document
																	.getElementById(
																			a
																					+ 'Name')
																	.setAttribute(
																			'value',
																			country[1]);
															document
																	.getElementById(
																			a
																					+ 'Code')
																	.setAttribute(
																			'value',
																			country[0]);
														}
													});
										});
					});
</script> --%>

</head>
<body>
	<%@include file="../../navigation.jsp"%>
	<div id="nas" class="container" style="margin-top: 50px;">
		<h1>Countries</h1>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<sf:form action="add-country" method="POST"
			modelAttribute="countryAdd" class="form-horizontal" role="form">
			<h3 id="formHead" class="text-primary">Add new Country</h3>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addName">Country
					Name:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addName" path="name"
						placeholder="Enter new country" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="name" cssClass="alert-danger ita" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="addCode">Country
					Code:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addCode" path="code"
						placeholder="Enter 3 character country code" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="code" cssClass="alert-danger ita" />
				</div>
			</div>
			
			<input type="hidden" id="cntrId" name="cntrId" value="">
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
		<h3 class="text-primary">Country list</h3>
		<hr>
		<div class="panel-group" id="accordion">
			<c:forEach items="${countries}" var="country" varStatus="theCount">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="row">
							<h4 class="panel-title col-sm-6 col-xs-6">
								<a href="#col${theCount.count}" data-toggle="collapse"
									data-parent="#accordion"> ${country.name} </a>
							</h4>
							<div class="col-sm-offset-5 col-sm-1 col-xs-offset-3 col-xs-3">
								<img class="image"
									src="resources/images/flags/${country.name}.png" />
							</div>
						</div>
					</div>
					<div id="col${theCount.count}"
						class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
						<div class="panel-body">
							<h4>Country details</h4>
							<table id="no-more-tables"
								class="table table-bordered table-condensed cf">
								<thead class="cf">
									<tr>
										<th class="numeric">Country Code</th>
										<th class="numeric">Date created</th>
										<th class="numeric">Date updated</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td data-title="Code">${country.code}</td>
										<td data-title="Created" class="multicell">&#xA;${country.created}</td>
										<td data-title="Updated" class="multicell">&#xA;${country.updated}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<button type="button" class="btn btn-primary edit-b"
								id="${country.code}" name="${country.name}">
								Edit <span class="glyphicon glyphicon-edit"></span>
							</button>
							<div class="hidden-lg hidden-md hidden-sm">&nbsp;</div>
							<button type="button" class="btn btn-danger del-b"
								data-toggle="modal" data-target="#deleteModal"
								id="${country.code}" name="${country.name}">
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
							sure you want to delete this country?</h4>
					</div>
					<div class="modal-body">
						<sf:form action="delete-country" method="POST"
							modelAttribute="countryDelete" class="form-horizontal"
							role="form">
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteName">Aircraft
									Type:</label>
								<div class="col-sm-10">
									<sf:input type="text" class="form-control" id="deleteName"
										path="name" readonly="true" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteCode">
									Id: </label>
								<div class="col-sm-10">
									<sf:input type="text" class="form-control" id="deleteCode"
										path="code" readonly="true" />
								</div>
							</div>
							<input type="hidden" id="cyId" name="cyId" value="">
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
			var code = this.id;
			var name = this.name;
			$("#addName").val(name);
			$('#cntrId').val(code);
			$('#addCode').val(code);
			$("#addCode").prop('readonly', true);
			$('#formHead').text("Edit country - " + name);
			$('html, body').animate({
				scrollTop : $("#nas").offset().top
			}, 1000);
		});

		$(".del-b").click(function() {
			var code = this.id;
			var name = this.name;
			$("#deleteName").val(name);
			$("#deleteCode").val(code);
			$("#cyId").val(code);
		});

		$('#res-b').click(function() {
			$('#cntrId').val("");
			$("#addCode").prop('readonly', false);
			$('#formHead').text("Add new Country");
		});

		$('#dis-mod').click(function() {
			$("#deleteName").val("");
			$("#deleteCode").val("");
			$("#cyId").val("");
		});
	</script>
</body>
</html>
