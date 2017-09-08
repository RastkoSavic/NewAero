<%-- 
    Document   : admin-messages
    Created on : Aug 6, 2017, 1:23:38 PM
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
		<h1>Messages</h1>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<sf:form action="add-admin-message" method="POST"
			modelAttribute="messageAdd" class="form-horizontal" role="form">
			<h3 id="formHead" class="text-primary">Add new Message</h3>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addMess">Message:</label>
				<div class="col-sm-10">
					<sf:textarea class="form-control" id="addMess" path="message"
						placeholder="Enter new admin global message" rows="5" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="message" cssClass="alert-danger ita" />
				</div>
			</div>
			
			<input type="hidden" name="userId" value="${currentUser.id}" />
			<input type="hidden" id="mssgId" name="mssgId" value="0">
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
		<h3 class="text-primary">Message List</h3>
		<hr>
		<div class="panel-group" id="accordion">
			<c:forEach items="${messages}" var="message" varStatus="theCount">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a href="#col${theCount.count}" data-toggle="collapse"
								data-parent="#accordion"> ${message.type.type} </a>
						</h4>
					</div>
					<div id="col${theCount.count}"
						class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
						<div class="panel-body">
							<h4>Message details</h4>
							<table id="no-more-tables"
								class="table table-bordered table-condensed cf">
								<thead class="cf">
									<tr>
										<th class="numeric">Id</th>
										<th class="numeric">Sender</th>
										<th class="numeric">Message</th>
										<th class="numeric">Date created</th>
										<th class="numeric">Date updated</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td data-title="Id">${message.id}</td>
										<td data-title="Sender">${message.sender.firstName}
											${message.sender.lastName}</td>
										<td id="ms${message.id}" data-title="Message"
											class="multicell">&#xA;${message.message}</td>
										<td data-title="Created" class="multicell">&#xA;${message.created}</td>
										<td data-title="Updated" class="multicell">&#xA;${message.updated}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<button type="button" class="btn btn-primary edit-b"
								id="${message.id}">
								Edit <span class="glyphicon glyphicon-edit"></span>
							</button>
							<button type="button" class="btn btn-danger del-b"
								data-toggle="modal" data-target="#deleteModal"
								id="${message.id}">
								Delete <span class="glyphicon glyphicon-trash"></span>
							</button>
							<div class="hidden-lg hidden-md hidden-sm">&nbsp;</div>
							<a href="#" class="btn btn-primary">View sender</a> <a
								href="response?reto=${message.sender.id}&onmess=${message.id}"
								class="btn btn-primary">Respond</a>
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
							sure you want to delete this message?</h4>
					</div>
					<div class="modal-body">
						<sf:form action="delete-message" method="POST"
							modelAttribute="messageDelete" class="form-horizontal"
							role="form">
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteMess">Message:</label>
								<div class="col-sm-10">
									<sf:textarea rows="4" class="form-control" id="deleteMess"
										path="message" readonly="true" />
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
							<input type="hidden" id="msId" name="msId" value="0">
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
			var id = this.id;
			var message = $('#ms' + id).text();
			$("#addMess").val(message);
			$('#mssgId').val(id);
			$('#formHead').text("Edit message");
			$('html, body').animate({
				scrollTop : $("#nas").offset().top
			}, 1000);
		});

		$(".del-b").click(function() {
			var id = this.id;
			var message = $('#ms' + id).text();
			$("#deleteMess").val(message);
			$("#deleteId").val(id);
			$("#msId").val(id);
		});

		$('#res-b').click(function() {
			$('#mssgId').val("0");
			$('#formHead').text("Add new Message");
		});

		$('#dis-mod').click(function() {
			$("#deleteMess").val("");
			$("#deleteId").val("");
			$("#msId").val("0");
		});
	</script>
</body>
</html>
