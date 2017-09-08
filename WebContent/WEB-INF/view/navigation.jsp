<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<nav class="navbar navbar-fixed-top navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#topBar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a href="home" class="navbar-brand">AEROBASE</a>
		</div>
		<div id="topBar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="home">Home</a></li>

				<li><a href="parts">Parts</a></li>
				<li><a href="message-board">Message Board</a></li>
				<li><a href="new-stock">New Stock</a></li>
				<c:if test="${not empty currentUser}">
					<li class="dropdown"><a href="#" class="has-submenu">User
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="user-profile" class="lipadtop">User profile</a></li>
							<li class="divider"></li>
							<li><a href="company-profile" class="lipadbottom">Company
									Profile</a></li>
						</ul></li>
				</c:if>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="about">About</a></li>
				<c:if
					test="${currentUser.status == 'Admin' || currentUser.status == 'SuperAdmin'}">
					<li class="dropdown"><a href="admin">Admin <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li class="dropdown"><a href="#" class="lipadtop">Parts
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu">
									<li><a href="admin-part-categories" class="lipadtop">Part
											Categories</a></li>
									<li class="divider"></li>
									<li><a href="admin-parts">Parts</a></li>
									<li class="divider"></li>
									<li><a href="admin-part-conditions">Part Conditions</a></li>
									<li class="divider"></li>
									<li><a href="admin-stock-parts" class="lipadbottom">Stock
											Parts</a></li>
								</ul></li>
							<li class="divider"></li>
							<li class="dropdown"><a href="#">Aircrafts
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu">
									<li><a href="admin-aircraft-type" class="lipadtop">Aircraft
											Types</a></li>
									<li class="divider"></li>
									<li><a href="admin-aircraft-model">Aircraft Models</a></li>
									<li class="divider"></li>
									<li><a href="admin-aircraft-variant">Aircraft Variants</a></li>
									<li class="divider"></li>
									<li><a href="admin-aog" class="lipadbottom">AOG-s</a></li>
								</ul></li>
							<li class="divider"></li>
							<li class="dropdown"><a href="#">Locations
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu">
									<li><a href="admin-countries" class="lipadtop">Countries</a></li>
									<li class="divider"></li>
									<li><a href="admin-cities">Cities</a></li>
									<li class="divider"></li>
									<li><a href="admin-addresses">Addresses</a></li>
									<li class="divider"></li>
									<li><a href="admin-airports" class="lipadbottom">Airports</a></li>
								</ul></li>
							<li class="divider"></li>
							<li class="dropdown"><a href="#">Messaging
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu">
									<li><a href="admin-message-types" class="lipadtop">Message
											Types</a></li>
									<li class="divider"></li>
									<li><a href="admin-messages" class="lipadbottom">Messages</a></li>
								</ul></li>
							<li class="divider"></li>
							<li class="dropdown"><a href="admin-company-hub"
								class="lipadbottom">Companies/Users <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="admin-primary-services" class="lipadtop">Primary
											Services</a></li>
									<li class="divider"></li>
									<li><a href="admin-companies">Companies</a></li>
									<li class="divider"></li>
									<li><a href="admin-users" class="lipadbottom">Users</a></li>
								</ul></li>
						</ul></li>
				</c:if>
				<c:if test="${empty currentUser}">
					<li><a href="register-company">Register <span
							class="glyphicon glyphicon-user"></span></a></li>
					<li><a href="login">Login <span
							class="glyphicon glyphicon-log-in"></span></a></li>
				</c:if>
				<c:if test="${not empty currentUser}">
					<li><a href="" data-toggle="modal" data-target="#logoutModal">Logout
							<span class="glyphicon glyphicon-log-out"></span>
					</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>

<div class="container">
	<div class="modal fade" id="logoutModal" role="dialog" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h3 id="title-form" class="modal-title text-danger">Logout?</h3>
				</div>
				<div class="modal-body row">
					<div class="col-sm-12">
						<h4>Are you sure you want to leave?</h4>
					</div>
					<div class="col-sm-12">&nbsp;</div>
					<sf:form action="logout" method="post">
						<div class="form-group col-sm-12">
							<div>
								<button type="submit" class="btn btn-danger">
									Logout <span class="glyphicon glyphicon-log-out"></span>
								</button>
							</div>
						</div>
					</sf:form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">
						Close <span class="glyphicon glyphicon-remove"></span>
					</button>
				</div>
			</div>
		</div>
	</div>
</div>
