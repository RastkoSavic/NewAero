<%-- 
    Document   : parts
    Created on : Aug 5, 2017, 3:09:01 PM
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
		<h1>Parts</h1>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<sf:form class="form-horizontal" method="POST" action="add-part"
			modelAttribute="partAdd">
			<h3 id="formHead" class="text-primary">Add new Part</h3>
			<hr>
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
			<input type="hidden" id="prnmId" name="prnmId" value="">
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
		<h3 class="text-primary">Parts list</h3>
		<hr>
		<div class="panel-group" id="accordion">
			<c:forEach items="${parts}" var="part" varStatus="theCount">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a href="#col${theCount.count}" data-toggle="collapse"
								data-parent="#accordion"> ${part.partNumber} </a>
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
										<th class="numeric">Category</th>
										<th class="numeric">Description</th>
										<th class="numeric">Date created</th>
										<th class="numeric">Date updated</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td data-title="Category">${part.category.category}</td>
										<td data-title="Description" class="multicell">&#xA;${part.description}</td>
										<td data-title="Created" class="multicell">&#xA;${part.created}</td>
										<td data-title="Updated" class="multicell">&#xA;${part.updated}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<button type="button" class="btn btn-primary edit-b"
								id="${part.partNumber}" name="${part.description}">
								Edit <span class="glyphicon glyphicon-edit"></span>
							</button>
							<button type="button" class="btn btn-danger del-b"
								data-toggle="modal" data-target="#deleteModal"
								id="${part.partNumber}" name="${part.description}">
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
							sure you want to delete this part?</h4>
					</div>
					<div class="modal-body">
						<sf:form action="delete-part" method="POST"
							modelAttribute="partDelete" class="form-horizontal" role="form">
							<div class="form-group">
								<label class="control-label col-sm-2" for="deletePart">Part
									number:</label>
								<div class="col-sm-10">
									<sf:input type="text" class="form-control" id="deletePart"
										path="partNumber" readonly="true" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteDes">
									Id: </label>
								<div class="col-sm-10">
									<sf:input type="text" class="form-control" id="deleteDes"
										path="description" readonly="true" />
								</div>
							</div>
							<input type="hidden" id="pnId" name="pnId" value="">
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
			var partNumber = this.id;
			var description = this.name;
			$("#addDes").val(description);
			$('#prnmId').val(partNumber);
			$('#addPart').val(partNumber);
			$("#addPart").prop('readonly', true);
			$('#addCat').prop('disabled', true);
			$('#formHead').text("Edit part - " + partNumber);
			$('html, body').animate({
				scrollTop : $("#nas").offset().top
			}, 1000);
		});

		$(".del-b").click(function() {
			var partNumber = this.id;
			var description = this.name;
			$("#deleteDes").val(description);
			$("#deletePart").val(partNumber);
			$("#pnId").val(partNumber);
		});

		$('#res-b').click(function() {
			$('#prnmId').val("");
			$("#addPart").prop('readonly', false);
			$('#addCat').prop('disabled', false);
			$('#formHead').text("Add new Part");
		});

		$('#dis-mod').click(function() {
			$("#deleteDes").val("");
			$("#deletePart").val("");
			$("#pnId").val("");
		});
	</script>

</body>
</html>
