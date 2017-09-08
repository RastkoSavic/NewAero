<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../header.jsp"%>
</head>
<body>
	<%@include file="../navigation.jsp"%>
	<div id="nas" class="container" style="margin-top: 50px;">
		<h1>Parts</h1>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<sf:form class="form-horizontal" method="POST" action="add-user-part"
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
						<div class="panel-footer"></div>
					</div>
				</div>
			</c:forEach>
		</div>

	</div>
	<%@include file="../footer.jsp"%>
</body>