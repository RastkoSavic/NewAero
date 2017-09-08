<%-- 
    Document   : admin-part-categories
    Created on : Aug 6, 2017, 1:13:14 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
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
	function setId(str, act) {
		z = str;
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
														url : 'get-category?action='
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
															var category = $
																	.parseJSON(result);
															document
																	.getElementById(
																			a
																					+ 'Cat')
																	.setAttribute(
																			'value',
																			category[0]);
															document
																	.getElementById(a
																			+ 'Des').innerHTML = category[1];
														}
													});
										});
					});
</script> --%>
</head>
<body>
	<%@include file="../../navigation.jsp"%>
	<div id="nas" class="container" style="margin-top: 50px;">
		<h1>Part Categories</h1>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>

		<sf:form action="add-category" method="POST"
			modelAttribute="categoryAdd" class="form-horizontal" role="form">
			<h3 id="formHead" class="text-primary">Add new Part Category</h3>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addCat">Part
					Category:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addCat"
						path="category" placeholder="Enter new Category" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="category" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addDes">
					Description: </label>
				<div class="col-sm-10">
					<sf:textarea class="form-control" id="addDes" path="description"
						rows="4" placeholder="Enter brief Description" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="description" cssClass="alert-danger ita" />
				</div>
			</div>

			<input type="hidden" id="ctgrId" name="ctgrId" value="">
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
		<h3 class="text-primary">Part Category list</h3>
		<hr>
		<div class="panel-group" id="accordion">
			<c:forEach items="${categories}" var="category" varStatus="theCount">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a href="#col${theCount.count}" data-toggle="collapse"
								data-parent="#accordion"> ${category.category} </a>
						</h4>
					</div>
					<div id="col${theCount.count}"
						class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
						<div class="panel-body">
							<h4>Category details</h4>
							<table id="no-more-tables"
								class="table table-bordered table-condensed cf">
								<thead class="cf">
									<tr>
										<th class="numeric">Description</th>
										<th class="numeric">Date created</th>
										<th class="numeric">Date updated</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td id="ds${category.category}" data-title="Description"
											class="multicell">&#xA;${category.description}</td>
										<td data-title="Created" class="multicell">&#xA;${category.created}</td>
										<td data-title="Updated" class="multicell">&#xA;${category.updated}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<button type="button" class="btn btn-primary edit-b"
								id="${category.category}">
								Edit <span class="glyphicon glyphicon-edit"></span>
							</button>
							<button type="button" class="btn btn-danger del-b"
								data-toggle="modal" data-target="#deleteModal"
								id="${category.category}">
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
							sure you want to delete this category?</h4>
					</div>
					<div class="modal-body">
						<sf:form action="delete-category" method="POST"
							modelAttribute="categoryDelete" class="form-horizontal"
							role="form">
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteCat">Part
									Category:</label>
								<div class="col-sm-10">
									<sf:input type="text" class="form-control" id="deleteCat"
										path="category" readonly="true" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteDes">
									Description: </label>
								<div class="col-sm-10">
									<sf:textarea class="form-control" id="deleteDes"
										path="description" rows="4" readonly="true" />
								</div>
							</div>
							<input type="hidden" id="ctId" name="ctId" value="">
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
			var category = this.id;
			var description = $("#ds" + category).text();
			$("#addDes").val(description);
			$('#ctgrId').val(category);
			$('#addCat').val(category);
			$("#addCat").prop('readonly', true);
			$('#formHead').text("Edit category - " + category);
			$('html, body').animate({
				scrollTop : $("#nas").offset().top
			}, 1000);
		});

		$(".del-b").click(function() {
			var category = this.id;
			var description = $("#ds" + category).text();
			$("#deleteDes").val(description);
			$("#deleteCat").val(category);
			$("#ctId").val(category);
		});

		$('#res-b').click(function() {
			$('#ctgrId').val("");
			$("#addCat").prop('readonly', false);
			$('#formHead').text("Add new Part");
		});

		$('#dis-mod').click(function() {
			$("#deleteDes").val("");
			$("#deleteCat").val("");
			$("#ctId").val("");
		});
	</script>
</body>
</html>
