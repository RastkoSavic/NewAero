<%-- 
    Document   : admin-aircraft-type
    Created on : Aug 6, 2017, 1:17:26 PM
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
	$(document).ready(function() {
		$('.edit-b').click(function() {
			$.ajax({
				type : 'GET',
				url : 'get-aircraft-type?action=' + a + '&object=' + z,
				dataType : "html",
				headers : {
					Accept : "application/json; charset=utf-8",
					"Content-Type" : "application/json; charset=utf-8"
				},
				success : function(result) {
					var type = $.parseJSON(result);
					document.getElementById(a + 'Type').setAttribute('value', type[1]);
					document.getElementById(a + 'Id').setAttribute('value', type[0]);
				}
			});
	});
});
</script> --%>

</head>
<body>
	<%@include file="../../navigation.jsp"%>
	<div id="nas" class="container" style="margin-top: 50px;">
		<h1>Aircraft Types</h1>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<sf:form action="add-aircraft-type" method="POST"
			modelAttribute="aircraftTypeAdd" class="form-horizontal" role="form">
			<h3 id="formHead" class="text-primary">Add new Aircraft Type</h3>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addType">Aircraft
					Type:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addType" path="type"
						placeholder="Enter new aircraft type" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="type" cssClass="alert-danger ita" />
				</div>
				
			</div>
			<input type="hidden" id="typeId" name="typeId" value="0">
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
		<h3 class="text-primary">Aircraft Type list</h3>
		<hr>
		<div class="panel-group" id="accordion">
			<c:forEach items="${types}" var="type" varStatus="theCount">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a href="#col${theCount.count}" data-toggle="collapse"
								data-parent="#accordion"> ${type.type} </a>
						</h4>
					</div>
					<div id="col${theCount.count}"
						class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
						<div class="panel-body">
							<h4>Aircraft Type details</h4>
							<table id="no-more-tables"
								class="table table-bordered table-condensed cf">
								<thead class="cf">
									<tr>
										<th class="numeric">Id</th>
										<th class="numeric">Date created</th>
										<th class="numeric">Date updated</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td data-title="Id">${type.id}</td>
										<td data-title="Created" class="multicell">&#xA;${type.created}</td>
										<td data-title="Updated" class="multicell">&#xA;${type.updated}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<button type="button" class="btn btn-primary edit-b"
								id="${type.id}" name="${type.type}">
								Edit <span class="glyphicon glyphicon-edit"></span>
							</button>
							<button type="button" class="btn btn-danger del-b"
								data-toggle="modal" data-target="#deleteModal" id="${type.id}"
								name="${type.type}">
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
							sure you want to delete this type?</h4>
					</div>
					<div class="modal-body">
						<sf:form action="delete-aircraft-type" method="POST"
							modelAttribute="aircraftTypeDelete" class="form-horizontal"
							role="form">
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteType">Aircraft
									Type:</label>
								<div class="col-sm-10">
									<sf:input type="text" class="form-control" id="deleteType"
										path="type" readonly="true" />
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
			$("#addType").val(x);
			$('#typeId').val(z);
			$('#formHead').text("Edit type - " + x);
			$('html, body').animate({
				scrollTop : $("#nas").offset().top
			}, 1000);
		});

		$(".del-b").click(function() {
			var z = this.id;
			var x = this.name;
			$("#deleteType").val(x);
			$('#deleteId').val(z);
		});

		$('#res-b').click(function() {
			$('#typeId').val('0');
			$('#formHead').text("Add new Aircraft Type");
		});

		$('#dis-mod').click(function() {
			$("#deleteType").val("");
			$('#deleteId').val("");
		});
	</script>
</body>
</html>
