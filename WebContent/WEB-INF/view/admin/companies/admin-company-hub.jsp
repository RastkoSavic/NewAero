<%-- 
    Document   : admin-company-hub
    Created on : Aug 6, 2017, 1:24:22 PM
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
		<h1>Company Hub</h1>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<h3 id="formHead" class="text-primary">Add new User Status</h3>
		<hr>
		<sf:form action="add-user-status" method="POST"
			modelAttribute="userStatusAdd" class="form-horizontal" role="form">
			<div class="form-group">
				<label class="control-label col-sm-2" for="addStat">User
					Status:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addStat"
						path="status" placeholder="Enter new user status" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="status" cssClass="alert-danger ita" />
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

			<input type="hidden" id="stat" name="stat" value="">
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
		<h3 class="text-primary">Status list</h3>
		<hr>
		<div class="panel-group" id="accordion">
			<c:forEach items="${statuses}" var="status" varStatus="theCount">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a href="#col${theCount.count}" data-toggle="collapse"
								data-parent="#accordion"> ${status.status} </a>
						</h4>
					</div>
					<div id="col${theCount.count}"
						class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
						<div class="panel-body">
							<table id="no-more-tables"
								class="table table-bordered table-condensed cf">
								<thead class="cf">
									<tr>
										<th class="numeric">Description</th>
										<th class="numeric">Date added</th>
										<th class="numeric">Date updated</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td id="td${status.status}" data-title="Description">${status.description}</td>
										<td data-title="Date Created">${status.created}</td>
										<td data-title="Date Updated">${status.updated}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<button type="button" class="btn btn-primary edit-b"
								id="${status.status}">
								Edit <span class="glyphicon glyphicon-edit"></span>
							</button>
							<button type="button" class="btn btn-danger del-b"
								data-toggle="modal" data-target="#deleteModal"
								id="${status.status}">
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
							sure you want to delete this status?</h4>
					</div>
					<div class="modal-body">
						<sf:form action="delete-user-status" method="POST"
							modelAttribute="userStatusDelete" class="form-horizontal"
							role="form">
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteStat">User
									Status:</label>
								<div class="col-sm-10">
									<sf:input type="text" class="form-control" id="deleteStat"
										path="status" readonly="true" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-danger">Delete</button>
								</div>
							</div>
						</sf:form>
					</div>
					<div class="modal-footer">
						<button type="button" id="dis-mod" class="btn btn-primary"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	<%@include file="../../footer.jsp"%>
	<script>
		$(".edit-b").click(function() {
			var z = this.id;
			var x = $('#td' + z).text();
			$("#addStat").prop('readonly', true);
			$("#addStat").val(z);
			$('#addDes').val(x);
			$('#stat').val(z);
			$('#formHead').text("Edit status - " + z);
			$('html, body').animate({
				scrollTop : $("#nas").offset().top
			}, 1000);
		});

		$(".del-b").click(function() {
			var z = this.id;
			$("#deleteStat").val(z);
		});

		$('#res-b').click(function() {
			$('#stat').val("");
			$('#formHead').text("Add new User Status");
			$("#addStat").prop('readonly', false);
		});

		$('#dis-mod').click(function() {
			$("#deleteStat").val("");
		});
	</script>


</body>
</html>
