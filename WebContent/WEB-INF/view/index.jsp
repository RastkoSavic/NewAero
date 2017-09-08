<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Spring Web MVC project</title>
<%@include file="header.jsp"%>
</head>

<body>
	<%@include file="navigation.jsp"%>
	<div class="container" style="margin-top: 50px;">
		<h3 class="ita">
			<span class="for-aero">AEROBASE</span> - a stock market for aviation
			Manufacturers, Airlines, Retailers and Brokers
		</h3>
		<hr>
		<br>
		<c:out value="${regmes}" escapeXml="false">
		</c:out>
		<c:remove var="regmes" scope="session" />
		<hr>
		<div class="col-xs-12 container-fluid">
			<img src="resources/images/v.jpg" class="image-responsive"
				style="width: 100%">
		</div>
		<div class="col-xs-12">
			<br>
			<hr>
			<h4>Here you can easily boost your turnover by getting in touch
				with the right client</h4>
			<hr>
			<h4 class="text-primary">Be the first to know when your wanted
				part hits the market</h4>
			<hr>
			<h4>Post your stock and get noticed instantly</h4>
			<hr>
			<h4 class="text-primary">Get that surplus part you need more
				than others</h4>
			<hr>
			<h4>
				Let others know where your AOG is located and parts will come <span
					class="acc">flying...</span>
			</h4>
		</div>
	</div>

	<%@include file="footer.jsp"%>
</body>
</html>
