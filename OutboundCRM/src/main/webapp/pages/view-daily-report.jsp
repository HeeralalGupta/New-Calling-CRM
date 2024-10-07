<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp"%>
</head>
<body>

	<%@ include file="common/admin-header.jsp"%>

	<!-- partial -->
	<div class="container-fluid page-body-wrapper">
		<!-- partial:partials/_sidebar.html -->
		<%@ include file="common/admin-sidebar.jsp"%>
		<!-- partial -->
		<div class="main-panel">
			<div class="content-wrapper">
				<!-- Page breadcrumb -->
				<div class="page-header">
					<h3 class="page-title">
						<span class="page-title-icon bg-gradient-primary text-white mr-2">
							<i class="mdi mdi-account-multiple-plus"></i>
						</span>Report
					</h3>
					<nav aria-label="breadcrumb">
						<ul class="breadcrumb">
							<li class="breadcrumb-item active" aria-current="page"><span></span>Overview
								<i
								class="mdi mdi-alert-circle-outline icon-sm text-primary align-middle"></i>
							</li>
						</ul>
					</nav>
				</div>
				<!-- ===================== Page body starts ============================================ -->
				<div class="row mt-4">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-description text-info text-bold">By Users</h4>
								<form id="reportForm">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Users<span style="color: red;">*</span></label>
												<div class="col-sm-9">
													<select class="form-control" name="userId">
														<option selected>Select Option</option>
														<c:forEach var="userName" items="${users}">
															<option value="${userName.id}">${userName.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">From<span style="color: red;">*</span></label>
												<div class="col-sm-9">
													<input type="date" id="fromDate" name="fromDate"
														class="form-control" required />
												</div>
											</div>
										</div>

										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">To<span style="color: red;">*</span></label>
												<div class="col-sm-9">
													<input type="date" id="date"
														name="toDate" class="form-control" required />
												</div>
											</div>
										</div>								
									</div>
									
									<div class="row mb-4">
										<div class="col-md-12">
											<button type="submit" class="btn btn-gradient-primary btn-fw"
												cursorshover="true">Generate</button>
										</div>
									</div>
								</form>
								
								
							</div>
						</div>
					</div>
				</div>
				
				<!-- ================= Report Generated ==================== -->
				<div class="row mt-4">
					<div class="col-12">
						<div class="card" id="reportData" style="display: none;">
							<div class="card-body">
								<div class="report" >
									<!-- Search input -->
									<div class="row align-items-center">
										<div class="col-md-4">
											<h4 class="card-title">By User</h4>
										</div>
										<div class="col-md-4 d-flex align-items-center">
											<div class="number-of-rows mr-2">
												<p>Select No. of Rows:</p>
											</div>
											<div class="form-group">
												<!-- Show Numbers Of Rows -->
												<select class="form-control" name="state" id="maxRows">
													<option value="5000">Show ALL Rows</option>
													<option value="5">5</option>
													<option value="10">10</option>
													<option value="15">15</option>
													<option value="20">20</option>
													<option value="50">50</option>
													<option value="70">70</option>
													<option value="100">100</option>
												</select>
											</div>
										</div>
										<div class="col-md-4 mb-3 float-right">
											<div class="input-group">
												<input type="text" class="form-control"
													placeholder="Type to search" id="search"
													onkeyup="myFunction()" aria-describedby="basic-addon2">
												<div class="input-group-append">
													<button class="btn btn-sm btn-gradient-primary"
														type="button" cursorshover="true">Search</button>
												</div>
											</div>
										</div>
									</div>
									<div class="table-responsive">
										<table class="table table-class mt-4" id="reportTable">
											<thead>
												<tr>
													<th>S.No.</th>
													<th>Assign Date</th>
													<th>Assign Time</th>
													<th>Total Data Assigned</th>
													<th>Data Type</th>
													<th>Calling Area</th>
													<th>Total Call</th>
													<th>Call Connected</th>
													
												</tr>
											</thead>
											<tbody>
												<!-- Dynamic data will populate -->
											</tbody>
										</table>
										<!--Start Pagination -->
										<div class='pagination-container my-3'>
											<nav>
												<ul class="pagination">
	
													<li data-page="prev"><span class="prev"> Prev <span
															class="sr-only current">(current)</span></span></li>
													<!--	Here the JS Function Will Add the Rows -->
													<li data-page="next" id="prev"><span class="next"> Next <span
															class="sr-only current">(current)</span></span></li>
												</ul>
											</nav>
										</div>
										<!--End Pagination -->
									</div>
									
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- ================= Report Generated End ==================== -->
				
				
				<!-- ================= Generate Report form by only date range start ==================== -->
				<div class="row mt-4">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-description text-info text-bold">By Date</h4>
								<form id="reportFormByDateRange">
									<div class="row">
										
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">From<span style="color: red;">*</span></label>
												<div class="col-sm-9">
													<input type="date" id="fromDate" name="fromDate"
														class="form-control" required />
												</div>
											</div>
										</div>

										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">To<span style="color: red;">*</span></label>
												<div class="col-sm-9">
													<input type="date" id="date"
														name="toDate" class="form-control" required />
												</div>
											</div>
										</div>								
									</div>
									
									<div class="row mb-4">
										<div class="col-md-12">
											<button type="submit" class="btn btn-gradient-primary btn-fw"
												cursorshover="true">Generate</button>
										</div>
									</div>
								</form>
								
								
							</div>
						</div>
					</div>
				</div>
				<!-- ================= Generate Report form by only date range end ==================== -->
				<!-- ================= Report Generated by date range only ==================== -->
				<div class="row mt-4">
					<div class="col-12">
						<div class="card" id="reportDataByDateRange" style="display: none;">
							<div class="card-body">
								<div class="report" >
									<!-- Search input -->
									<div class="row align-items-center">
										<div class="col-md-4">
											<h4 class="card-title">By Date</h4>
										</div>
										<div class="col-md-4 d-flex align-items-center">
											<div class="number-of-rows mr-2">
												<p>Select No. of Rows:</p>
											</div>
											<div class="form-group">
												<!-- Show Numbers Of Rows -->
												<select class="form-control" name="state" id="maxRows">
													<option value="5000">Show ALL Rows</option>
													<option value="5">5</option>
													<option value="10">10</option>
													<option value="15">15</option>
													<option value="20">20</option>
													<option value="50">50</option>
													<option value="70">70</option>
													<option value="100">100</option>
												</select>
											</div>
										</div>
										<div class="col-md-4 mb-3 float-right">
											<div class="input-group">
												<input type="text" class="form-control"
													placeholder="Type to search" id="search"
													onkeyup="myFunction()" aria-describedby="basic-addon2">
												<div class="input-group-append">
													<button class="btn btn-sm btn-gradient-primary"
														type="button" cursorshover="true">Search</button>
												</div>
											</div>
										</div>
									</div>
									<div class="table-responsive">
										<table class="table table-class mt-4" id="reportTableByDateRange">
											<thead>
												<tr>
													<th>S.No.</th>
													<th>Assign Date</th>
													<th>Assign Time</th>
													<th>Total Data Assigned</th>
													<th>Data Type</th>
													<th>Calling Area</th>
													<th>Total Call</th>
													<th>Call Connected</th>
													
												</tr>
											</thead>
											<tbody>
												<!-- Dynamic data will populate -->
											</tbody>
										</table>
										<!--Start Pagination -->
										<div class='pagination-container my-3'>
											<nav>
												<ul class="pagination">
	
													<li data-page="prev"><span class="prev"> Prev <span
															class="sr-only current">(current)</span></span></li>
													<!--	Here the JS Function Will Add the Rows -->
													<li data-page="next" id="prev"><span class="next"> Next <span
															class="sr-only current">(current)</span></span></li>
												</ul>
											</nav>
										</div>
										<!--End Pagination -->
									</div>
									
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- ================= Report Generated by date range only End ==================== -->
				<%-- <div class="row mt-4">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-description text-info text-bold">List of Telecaller</h4>
								<div class="table-responsive">
									<table class="table">
										<thead>
											<tr>
												<th>S.No.</th>
												<th>Name</th>
												<th>Email</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="user" items="${users}" varStatus="sno">
												<tr>
													<td>${sno.count}</td>
													<td>${user.name}</td>
													<td>${user.email}</td>
													<td><a href="report-details?id=${user.id}" ><button class="btn btn-success">View Daily Report</button></a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>					
				 --%>
				<!-- ===================== Page body ends ============================================== -->

			</div>
			<!-- content-wrapper ends -->
			<!-- partial:partials/_footer.html -->
			<%@ include file="common/footer.jsp"%>
			<!-- partial -->
		</div>
		<!-- main-panel ends -->
	</div>
	<!-- page-body-wrapper ends -->
	<!-- container-scroller -->
	<!-- plugins:js -->
	<%@ include file="common/scripts.jsp"%>
	<!-- End custom js for this page -->
	<script>
	
		/* Searching */
		$(document).ready(function(){
		  $("#search").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#reportTable tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		  });
		});
		/* Searching */
		$(document).ready(function(){
		  $("#search").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#reportTableByDateRange tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		  });
		});
		</script>
		
		<!-- Generating Report by name & date range Dynamically  -->	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

		 <script>
		 /* Generating data by user id and date range only */
		 $(document).ready(function() {
			    // Hide the reportData div when the Telecaller dropdown value changes
			    $("select[name='userId']").change(function() {
			        $("#reportData").hide();
			    });

			    // Submit form and show the report data
			    $("#reportForm").submit(function(event) {
			        event.preventDefault(); // Prevent default form submission
			        
			        $.ajax({
			            url: "/generateReport", // The URL to the controller method
			            type: "POST",
			            data: $(this).serialize(), // Send form data
			            success: function(data) {
			                // Clear existing table rows
			                $("#reportTable tbody").empty();
			                
			                // Check if data is returned
			                if (data.length > 0) {
			                    $.each(data, function(index, report) {
			                    	// Ensure report values exist and provide a fallback if necessary
			                        var minSerialNumber = report.minSerialNumber || 0;
			                        var maxSerialNumber = report.maxSerialNumber || 0;
			                        
			                        var row = "<tr>" +
			                                  "<td>" + (index + 1) + "</td>" +
			                                  "<td>" + report.date + "</td>" +
			                                  "<td>" + report.time + "</td>" +
			                                  "<td>" + (maxSerialNumber-minSerialNumber) + "</td>" +	
			                                  "<td>" + report.dataCategory + "</td>" +
			                                  "<td>" + report.callingAreaName + "</td>" +
			                                  "<td>" + report.totalCalls + "</td>" +
			                                  "<td>" + report.connectedCalls + "</td>" +

			                                  "</tr>";
			                        $("#reportTable tbody").append(row);
			                    });

			                    // Show the report data div
			                    $("#reportData").show();
			                } else {
			                    alert("No data found for the selected criteria.");
			                }
			            },
			            error: function(error) {
			                console.log("Error:", error);
			            }
			        });
			    });
			});
		 /* Generating data by user id and date range only end */
		 
		 /* Generating data by date range only */
		 $(document).ready(function() {
			    // Hide the reportData div when the Telecaller dropdown value changes
			    $("select[name='fromDate']").change(function() {
			        $("#reportDataByDateRange").hide();
			    });

			    // Submit form and show the report data
			    $("#reportFormByDateRange").submit(function(event) {
			        event.preventDefault(); // Prevent default form submission
			        
			        $.ajax({
			            url: "/generateReportByDateRange", // The URL to the controller method
			            type: "POST",
			            data: $(this).serialize(), // Send form data
			            success: function(data) {
			                // Clear existing table rows
			                $("#reportTableByDateRange tbody").empty();
			                
			                // Check if data is returned
			                if (data.length > 0) {
			                    $.each(data, function(index, report) {
			                    	// Ensure report values exist and provide a fallback if necessary
			                        var minSerialNumber = report.minSerialNumber || 0;
			                        var maxSerialNumber = report.maxSerialNumber || 0;
			                        
			                        var row = "<tr>" +
			                                  "<td>" + (index + 1) + "</td>" +
			                                  "<td>" + report.date + "</td>" +
			                                  "<td>" + report.time + "</td>" +
			                                  "<td>" + (maxSerialNumber-minSerialNumber) + "</td>" +	
			                                  "<td>" + report.dataCategory + "</td>" +
			                                  "<td>" + report.callingAreaName + "</td>" +
			                                  "<td>" + report.totalCalls + "</td>" +
			                                  "<td>" + report.connectedCalls + "</td>" +

			                                  "</tr>";
			                        $("#reportTableByDateRange tbody").append(row);
			                    });

			                    // Show the report data div
			                    $("#reportDataByDateRange").show();
			                } else {
			                    alert("No data found for the selected criteria.");
			                }
			            },
			            error: function(error) {
			                console.log("Error:", error);
			            }
			        });
			    });
			});
	    </script>
		
</body>
</html>
												
												
				