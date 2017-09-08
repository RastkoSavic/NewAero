<%-- 
    Document   : admin-users
    Created on : Aug 6, 2017, 1:25:33 PM
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
	<div class="container" style="margin-top: 50px;">
		<h1>Users</h1>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<sf:form action="register-admin" method="POST"
			modelAttribute="adminAdd" class="form-horizontal" role="form">
			<h3 class="text-primary">Add new Admin</h3>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addEmail">Email:
				</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addEmail"
						path="email" placeholder="Enter admins email" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="email" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addPass">Password:
				</label>
				<div class="col-sm-10">
					<sf:input type="password" class="form-control" id="addPass"
						path="password" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="password" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addRPass">Repeat
					Password: </label>
				<div class="col-sm-10">
					<sf:input type="password" class="form-control" id="addRPass"
						path="repeatPass" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="repeatPass" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addFName">First
					name: </label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addFName"
						path="firstName" placeholder="Enter first name" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="firstName" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addLName">Last
					name: </label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addLName"
						path="lastName" placeholder="Enter last name" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="lastName" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addStat">Status:
				</label>
				<div class="col-sm-10">
					<select class="form-control" name="status" id="addStat">
						<option value="Admin">Admin</option>
						<option value="SuperAdmin">SuperAdmin</option>
					</select>
				</div>
			</div>
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
		<h3 class="text-primary">User list</h3>
		<hr>
		<div class="panel-group" id="accordion">
			<c:forEach items="${users}" var="user" varStatus="theCount">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a href="#col${theCount.count}" data-toggle="collapse"
								data-parent="#accordion"> ${user.firstName} ${user.lastName}
								- ${user.company.name}</a>
						</h4>
					</div>
					<div id="col${theCount.count}"
						class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
						<div class="panel-body">
							<h4>User details</h4>
							<table id="no-more-tables"
								class="table table-bordered table-condensed cf">
								<thead class="cf">
									<tr>
										<th class="numeric">Id</th>
										<th class="numeric">Position</th>
										<th class="numeric">Status</th>
										<th class="numeric">Date created</th>
										<th class="numeric">Date updated</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td data-title="Id">${user.id}</td>
										<td data-title="Position">${user.position}</td>
										<td data-title="Status">${user.status.status}</td>
										<td data-title="Created" class="multicell">&#xA;${user.created}</td>
										<td data-title="Date Updated" class="multicell">&#xA;${user.updated}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<button type="button" class="btn btn-danger del-b"
								data-toggle="modal" data-target="#deleteModal" id="${user.id}"
								name="${user.firstName} ${user.lastName}">
								Delete <span class="glyphicon glyphicon-trash"></span>
							</button>
							<div class="hidden-lg hidden-md hidden-sm">&nbsp;</div>
							<a href="view-user-profile?usproid=${user.id}&onmess=0"
								class="btn btn-primary">User profile <span
								class="glyphicon glyphicon-user"></span></a>
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
							sure you want to remove this admin?</h4>
					</div>
					<div class="modal-body">
						<form action="delete-admin" method="POST" class="form-horizontal"
							role="form">
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteName">Name:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="deleteName"
										name="name" readonly />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteId">
									Id: </label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="deleteId" readonly />
								</div>
							</div>
							<input type="hidden" id="adminId" name="adminId" value="0" />
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-danger">Delete</button>
								</div>
							</div>
						</form>
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
		$(".del-b").click(function() {
			var z = this.id;
			var x = this.name;
			$("#deleteName").val(x);
			$("#deleteId").val(z);
			$("#adminId").val(z);
		});

		$('#dis-mod').click(function() {
			$("#deleteName").val("");
			$("#deleteId").val("");
		});
	</script>
</body>
</html>
