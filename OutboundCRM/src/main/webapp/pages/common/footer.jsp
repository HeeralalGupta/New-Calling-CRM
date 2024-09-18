<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.time.Year" %>
<!DOCTYPE html>
<html>

<body>
	<footer class="footer">
		<div class="container-fluid clearfix">
			<span
				class="text-muted d-block text-center text-sm-left d-sm-inline-block">Copyright
				© inpraviagroup.com <% out.print(Year.now().getValue()); %></span> <span
				class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">
				Inbound <a
				href="#"
				target="_blank">and Outbound Calling</a> CRM
			</span>
		</div>
	</footer>
</body>
</html>