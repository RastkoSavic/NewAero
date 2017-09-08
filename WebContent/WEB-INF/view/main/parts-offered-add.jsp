<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="../header.jsp"%>

<script>
	$(document)
			.ready(
					function() {
						$('#addType')
								.on(
										'change',
										function() {
											var x = this.value;
											$
													.ajax({
														type : 'GET',
														url : 'select-type-for-stock?object='
																+ x,
														dataType : "html",
														headers : {
															Accept : "application/json; charset=utf-8",
															"Content-Type" : "application/json; charset=utf-8"
														},
														success : function(json) {
															$('#addMod')
																	.empty();
															var res = $
																	.parseJSON(json);
															var sel = document
																	.getElementById('#addMod');
															var options = "<option value=-1>Select</option>";
															for (var i = 0; i < res.length; i++) {
																options += '<option value= "' + res[i].id + '">'
																		+ res[i].model
																		+ '</option>';
															}
															$('#addMod').html(
																	options);
														}
													});
										});
					});

	//******************************************************************

	$(document)
			.ready(
					function() {
						$('#addMod')
								.on(
										'change',
										function() {

											var y = this.value;

											$
													.ajax({
														type : 'GET',
														url : 'select-model?object='
																+ y,
														dataType : "html",
														headers : {
															Accept : "application/json; charset=utf-8",
															"Content-Type" : "application/json; charset=utf-8"
														},
														success : function(json) {
															$('#addVar')
																	.empty();

															var res = $
																	.parseJSON(json);
															var sel = document
																	.getElementById('#addVar');
															var options = "<option value=-1>Select</option>";
															for (var i = 0; i < res.length; i++) {
																//$('#addMod').append($('<option>')).text(res[i].model).attr('value', res[i].id);
																options += '<option value= "' + res[i].id + '">'
																		+ res[i].variant
																		+ '</option>';
															}
															$('#addVar').html(
																	options);
														}
													});
										});

					});
</script>

</head>
<body>
	<%@include file="../navigation.jsp"%>

	<div class="container" style="margin-top: 50px;">
		<h1>Parts Offered Message</h1>
		<hr>
		<br>
		<c:out value="${mes}" escapeXml="false">
		</c:out>
		<sf:form action="post-offer-add" method="POST"
			modelAttribute="postPAOM" class="form-horizontal" role="form">
			<h3 class="text-primary">Post new Add</h3>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addMessage">Message:</label>
				<div class="col-sm-10">
					<sf:textarea class="form-control" id="addMessage" path="message"
						rows="4" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="message" cssClass="alert-danger ita" />
				</div>
			</div>

			<br>
			<h4>Part details</h4>
			<hr>
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
				<label class="control-label col-sm-2" for="addCond">Part
					Condition:</label>
				<div class="col-sm-10">
					<select class="form-control" name="condition" id="addCond">
						<option value="0">Select</option>
						<c:forEach items="${conditions}" var="condition">
							<option value="${condition.code}">${condition.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
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
				<label class="control-label col-sm-2" for="addQty">Quantity:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addQty"
						path="quantity" placeholder="Enter available quantity" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="quantity" cssClass="alert-danger ita" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="addPrice">Price
					per unit:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" id="addPrice"
						path="price" placeholder="Enter price per unit" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="price" cssClass="alert-danger ita" />
				</div>
			</div>

			<br>
			<h4>Aircraft details</h4>
			<hr>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addType">Aircraft
					Type:</label>
				<div class="col-sm-10">
					<select class="form-control" name="typeId" id="addType">
						<option value="0">Select</option>
						<c:forEach items="${types}" var="type">
							<option value="${type.id}">${type.type}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addMod">Aircraft
					Model:</label>
				<div class="col-sm-10">
					<select class="form-control" name="modelId" id="addMod">
						<option value=-1>Select</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addVar">Variant:</label>
				<div class="col-sm-10">
					<select class="form-control" name="variantId" id="addVar">
						<option value=-1>Select</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="addNote">Notes:</label>
				<div class="col-sm-10">
					<sf:textarea class="form-control" id="addNote" path="notes"
						placeholder="Enter brief notes" rows="4" />
				</div>
				<div class="col-sm-offset-2 col-sm-10">
					<sf:errors path="notes" cssClass="alert-danger ita" />
				</div>
			</div>

			<sf:input type="hidden" path="userId" value="${currentUser.id}" />
			<sf:input type="hidden" path="typeCode" value="PAOM" />
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-success">
						Post Message <span class="glyphicon glyphicon-send"></span>
					</button>
				</div>
			</div>
		</sf:form>
	</div>
	<%@include file="../footer.jsp"%>
</body>
</html>