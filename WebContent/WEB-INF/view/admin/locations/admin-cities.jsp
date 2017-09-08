<%-- 
    Document   : admin-cities
    Created on : Aug 6, 2017, 1:19:25 PM
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
		<h1>Cities</h1>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<sf:form action="add-city" method="POST" modelAttribute="cityAdd"
			class="form-horizontal" role="form">
			<h3 id="formHead" class="text-primary">Add new City</h3>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addCity">City
					Name:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addCity" path="name"
						placeholder="Enter new city" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="name" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addStNa">State
					Name:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addStNa"
						path="stateName" placeholder="Enter state name (USA)" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="stateName" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addStCo">State
					Code:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addStCo"
						path="stateCode" placeholder="Enter state code (USA)" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="stateCode" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addCoun">Country:</label>
				<div class="col-sm-10">
					<select class="form-control" name="countryCode" id="addCoun">
						<c:forEach items="${countries}" var="country">
							<option value="${country.code}">${country.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<input type="hidden" id="citId" name="citId" value="0">
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
		<h3 class="text-primary">City list</h3>
		<hr>
		<div class="panel-group" id="accordion">
			<c:forEach items="${cities}" var="city" varStatus="theCount">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a href="#col${theCount.count}" data-toggle="collapse"
								data-parent="#accordion"> ${city.name} </a>
						</h4>
					</div>
					<div id="col${theCount.count}"
						class="panel-collapse collapse ${theCount.first ? 'in' : ''}">
						<div class="panel-body">
							<h4>City details</h4>
							<table id="no-more-tables"
								class="table table-bordered table-condensed cf">
								<thead class="cf">
									<tr>
										<th class="numeric">Id</th>
										<th class="numeric">State Name</th>
										<th class="numeric">State Code</th>
										<th class="numeric">Country</th>
										<th class="numeric">Date added</th>
										<th class="numeric">Date updated</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td data-title="Id">${city.id}</td>
										<td id="sn${city.id}" data-title="State Name">${city.stateName}</td>
										<td id="sc${city.id}" data-title="State Code">${city.stateCode}</td>
										<td data-title="Country">${city.country.name}</td>
										<td data-title="Date Created" class="multicell">&#xA;${city.created}</td>
										<td data-title="Date Updated" class="multicell">&#xA;${city.updated}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<button type="button" class="btn btn-primary edit-b"
								id="${city.id}" name="${city.name}">
								Edit <span class="glyphicon glyphicon-edit"></span>
							</button>
							<button type="button" class="btn btn-danger del-b"
								data-toggle="modal" data-target="#deleteModal" id="${city.id}"
								name="${city.name}">
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
							sure you want to delete this city?</h4>
					</div>
					<div class="modal-body">
						<sf:form action="delete-city" modelAttribute="cityDelete"
							method="POST" class="form-horizontal" role="form">
							<div class="form-group">
								<label class="control-label col-sm-2" for="deleteName">City:</label>
								<div class="col-sm-10">
									<sf:input type="text" class="form-control" id="deleteName"
										path="name" readonly="true" />
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
							<input type="hidden" id="ciId" name="ciId" value="0">
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
			var name = this.name;
			var stName = $('#sn' + id).text();
			var stCode = $('#sc' + id).text();
			$("#addCity").val(name);
			$('#addStNa').val(stName);
			$('#addStCo').val(stCode);
			$('#citId').val(id);
			$("#addCoun").prop('disabled', true);
			$('#formHead').text("Edit city - " + name);
			$('html, body').animate({
				scrollTop : $("#nas").offset().top
			}, 1000);
		});

		$(".del-b").click(function() {
			var id = this.id;
			var name = this.name;
			$("#deleteName").val(name);
			$("#deleteId").val(id);
			$("#ciId").val(id);
		});

		$('#res-b').click(function() {
			$('#citId').val("0");
			$("#addCoun").prop('disabled', false);
			$('#formHead').text("Add new City");
		});

		$('#dis-mod').click(function() {
			$("#deleteName").val("");
			$("#deleteId").val("");
			$("#ciId").val("0");
		});
	</script>
</body>
</html>
