<%-- 
    Document   : user-profile
    Created on : Aug 5, 2017, 12:10:45 PM
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
<%@include file="../header.jsp"%>
</head>
<body>
	<%@include file="../navigation.jsp"%>
	<div class="container" style="margin-top: 50px;">
		<h3 class="ita">
			<span class="for-aero">User PROFILE</span> - customize your
			experience
		</h3>
		<hr>
		<br>
		<div class="row">
			<div class="col-sm-3">
				<h2>${company.name}</h2>
				<hr>
				<h3>${user.firstName}${user.lastName}</h3>
				<hr>
				<div class="container logoprofile">
					<img src="resources/images/${company.name}.jpg"
						class="img-responsive" alt="logo">
				</div>
				<hr>
				<h3 class="text-primary">Primary service:
					${company.primaryService.service}</h3>
				<hr>
				<div id="myScrollspy">
					<h4 class="text-primary">
						Post messages <span class="glyphicon glyphicon-envelope"></span>
					</h4>
					<hr>
					<ul class="nav nav-pills nav-stacked" data-spy="affix"
						data-offset-top="470">
						<li><a href="general-sales-message" class="text-primary">General
								Sales Add</a></li>
						<li><a href="general-purchase-message" class="text-primary">General
								Purchase Add</a></li>
						<li><a href="parts-offered-add" class="text-primary">Offer
								parts</a></li>
						<li><a href="parts-wanted-add" class="text-primary">Parts
								Wanted Message</a></li>
						<li><a href="admin-question" class="text-primary">Ask
								Admin a Question <span class="glyphicon glyphicon-question-sign"></span>
						</a></li>
					</ul>
				</div>
			</div>
			<div class="col-sm-9" style="border-left: 1px solid #EFEFEF;">
				<c:out value="${mes}" escapeXml="false">
				</c:out>

				<h2 class="text-primary">Message List</h2>
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
									<a
										href="response?reto=${message.sender.id}&onmess=${message.id}"
										class="btn btn-primary">Respond <span
										class="glyphicon glyphicon-share-alt"></span></a>
									<div class="hidden-lg hidden-md hidden-sm">&nbsp;</div>
									<a
										href="view-user-profile?usproid=${message.sender.id}&onmess=${message.id}"
										class="btn btn-primary">User profile <span
										class="glyphicon glyphicon-user"></span></a>
									<div class="hidden-lg hidden-md hidden-sm">&nbsp;</div>
									<a
										href="view-company-profile?usproid=${message.sender.company.id}&onmess=${message.id}"
										class="btn btn-primary">Company profile <span
										class="glyphicon glyphicon-plane"></span></a>


								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<br>

				<sf:form action="user-sett" method="POST"
					modelAttribute="userSettings" class="form-horizontal" role="form">
					<h3 class="text-primary">
						Edit User Settings <span class="glyphicon glyphicon-cog"></span>
					</h3>
					<hr>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="firstName">First
							name:</label>
						<div class="col-sm-10">
							<sf:input type="text" class="form-control" id="firstName"
								path="firstName" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="firstName" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="lastName">Last
							name:</label>
						<div class="col-sm-10">
							<sf:input type="text" class="form-control" id="lastName"
								path="lastName" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="lastName" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="position">Position:</label>
						<div class="col-sm-10">
							<sf:input type="text" class="form-control" id="position"
								path="position" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="position" cssClass="alert-danger ita" />
						</div>
					</div>
					<sf:errors path="position" />
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label><sf:checkbox path="phoneAllowed"
										id="phoneAllowed" data-toggle="toggle" data-onstyle="primary"
										data-offstyle="danger" />Display Phone?</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="phone">Phone:</label>
						<div class="col-sm-10">
							<sf:input type="text" class="form-control" id="phone"
								path="phone" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="phone" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="phoneAlt">Alternate
							Phone:</label>
						<div class="col-sm-10">
							<sf:input type="text" class="form-control" id="phoneAlt"
								path="phoneAlt" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="phoneAlt" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label><sf:checkbox path="emailAllowed"
										id="emailAllowed" data-toggle="toggle" data-onstyle="primary"
										data-offstyle="danger" />Display Email?</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Email:</label>
						<div class="col-sm-10">
							<sf:input type="email" class="form-control" id="email"
								path="email" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="email" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="emailAlt">Alternate
							Email:</label>
						<div class="col-sm-10">
							<sf:input type="email" class="form-control" id="emailAlt"
								path="emailAlt" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="emailAlt" cssClass="alert-danger ita" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label><sf:checkbox path="messageAllowed"
										id="messageAllowed" data-toggle="toggle"
										data-onstyle="primary" data-offstyle="danger" />Allow
									messages?</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="notes">Notes:</label>
						<div class="col-sm-10">
							<sf:textarea class="form-control" id="notes" path="notes"
								rows="4" />
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<sf:errors path="notes" cssClass="alert-danger ita" />
						</div>
					</div>

					<input type="hidden" name="usId" value="${userSettings.id}" />
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-success">
								Save Settings <span class="glyphicon glyphicon-save"></span>
							</button>
						</div>
					</div>
				</sf:form>
			</div>
		</div>
	</div>
	<%@include file="../footer.jsp"%>
</body>
</html>
