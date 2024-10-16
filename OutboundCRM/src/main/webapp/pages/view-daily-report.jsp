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
										<div class="col-md-3">
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
										
										<div class="col-md-3">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">From<span style="color: red;">*</span></label>
												<div class="col-sm-9">
													<input type="date" id="fromDate" name="fromDate"
														class="form-control" required />
												</div>
											</div>
										</div>

										<div class="col-md-3">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">To<span style="color: red;">*</span></label>
												<div class="col-sm-9">
													<input type="date" id="date"
														name="toDate" class="form-control" required />
												</div>
											</div>
										</div>	
										<div class="row mb-3">
											<div class="col-md-12">
												<button type="submit" class="btn btn-sm btn-gradient-primary btn-fw ml-4">Generate</button>
											</div>
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
									<!-- Download Button -->
					                    <form id="downloadForm" method="GET" action="/generateReport/download">
										    <input type="hidden" name="userId" id="hiddenUserId" />
										    <input type="hidden" name="fromDate" id="hiddenFromDate" />
										    <input type="hidden" name="toDate" id="hiddenToDate" />
										    <button type="submit" id="downloadBtn" class="btn btn-sm btn-gradient-success"><img src="assets/images/excel.png" width="20px" style="margin-right: 5px;"/>Excel</button>
										</form>

									<div class="table-responsive">
										<table class="table table-class mt-1" id="reportTable">
											<thead>
												<tr>
													<th>S.No.</th>
													<th>Task Id</th>
													<th>User Name</th>
													<th>Assign Date</th>
													<th>Assign Time</th>
													<th>Data Alloted</th>
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
													<input type="date" id="startDate" name="startDate"
														class="form-control" required />
												</div>
											</div>
										</div>

										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">To<span style="color: red;">*</span></label>
												<div class="col-sm-9">
													<input type="date" id="endDate"
														name="endDate" class="form-control" required />
												</div>
											</div>
										</div>	
										<div class="row mb-4">
											<div class="col-md-12">
												<button type="submit" class="btn btn-sm btn-gradient-primary btn-fw ml-4"
													cursorshover="true">Generate</button>
											</div>
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
													placeholder="Type to search" id="searchByDate"
													onkeyup="myFunction()" aria-describedby="basic-addon2">
												<div class="input-group-append">
													<button class="btn btn-sm btn-gradient-primary"
														type="button" cursorshover="true">Search</button>
												</div>
											</div>
										</div>
									</div>
									 <!-- Download Button -->
				                      <form id="downloadAllForm" method="GET" action="/generateAllReport/download">
										    <input type="hidden" name="fromDate" id="hiddenAllFromDate" />
										    <input type="hidden" name="toDate" id="hiddenAllToDate" />
										    <button type="submit" id="downloadAllBtn" class="btn btn-sm btn-gradient-success"><img src="assets/images/excel.png" width="20px" style="margin-right: 5px;"/>Excel</button>
										</form>
									<div class="table-responsive">
										<table class="table mt-1" id="reportTableByDateRange">
											<thead>
												<tr>
													<th>S.No.</th>
													<th>Task Id</th>
													<th>User Name</th>
													<th>Assign Date</th>
													<th>Assign Time</th>
													<th>Data Alloted</th>
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
		    $("#reportTable tbody tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		  });
		  // Function to filter rows in the second table
		  $("#searchByDate").on("keyup", function() {
		    var value = $(this).val().toLowerCase();

		    // Filter rows in the second table
		    $("#reportTableByDateRange tbody tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
		    });
		  });
		  
		});
		
		</script>
		
		<!-- Generating Report by name & date range Dynamically  -->	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

		 <script>
		 /* Generating data by user id and date range only */
		 $(document).ready(function() {
			    // Hide the reportData div and disable download button initially
			    $("select[name='userId']").change(function() {
			        $("#reportData").hide();
			        $("#reportDataByDateRange").hide();
			        $("#downloadBtn").attr("disabled", true); // Disable download button
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
			                                  "<td>" + (report.assignId || '-') + "</td>" +
			                                  "<td>" + (report.userName || '-') + "</td>" +
			                                  "<td>" + (report.date || '-') + "</td>" +
			                                  "<td>" + (report.time || '-') + "</td>" +
			                                  "<td>" + (maxSerialNumber - minSerialNumber) + "</td>" +
			                                  "<td>" + (report.dataCategory || '-') + "</td>" +
			                                  "<td>" + (report.callingAreaName || '-') + "</td>" +
			                                  "<td>" + (report.totalCalls || 0) + "</td>" +
			                                  "<td>" + (report.connectedCalls || 0) + "</td>" +
			                                  "</tr>";
			                        $("#reportTable tbody").append(row);
			                    });
			
			                    // Show the report data div
			                    $("#reportData").show();
			
			                    // Enable download button
			                    $("#downloadBtn").attr("disabled", false);
									
			                } else {
			                    alert("No data found for the selected criteria.");
			                    $("#downloadBtn").attr("disabled", true); // Disable download button
			                }
			            },
			            error: function(error) {
			                console.log("Error:", error);
			                alert("There was an error retrieving the data.");
			            }
			        });
			    });
			    
			 // Download button click event
			    $("#downloadBtn").click(function() {
			    	event.preventDefault(); // Prevent the default action
			        // Get the values from the form
			        const userId = $("select[name='userId']").val();
			        const fromDate = $("input[name='fromDate']").val();
			        const toDate = $("input[name='toDate']").val();
					
			        // Log the values to the console
			        console.log("User ID:", userId);
			        console.log("From Date:", fromDate);
			        console.log("To Date:", toDate);

			        // Check if all required fields are filled
				    if (userId && fromDate && toDate) {
				    	// Set the hidden fields in the form
				        $("#hiddenUserId").val(userId);
				        $("#hiddenFromDate").val(fromDate);
				        $("#hiddenToDate").val(toDate);

				        // Submit the form
				        $("#downloadForm").submit();
				    } else {
				        alert("Please select a user and specify the date range before downloading.");
				   
				    }
			    });
			 
			});

		 /* Generating data by user id and date range only end */
		 
		 /* Generating data by date range only */
		 $(document).ready(function() {
			    // Hide the reportData div and disable download button initially
			    $("input[name='startDate']").change(function() {
			        $("#reportDataByDateRange").hide();
			        $("#reportData").hide();
			        $("#downloadAllBtn").attr("disabled", true); // Disable download button
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
			                                  "<td>" + (report.assignId || '-') + "</td>" +
			                                  "<td>" + (report.userName || '-') + "</td>" +
			                                  "<td>" + (report.date || '-') + "</td>" +
			                                  "<td>" + (report.time || '-') + "</td>" +
			                                  "<td>" + (maxSerialNumber - minSerialNumber) + "</td>" +
			                                  "<td>" + (report.dataCategory || '-') + "</td>" +
			                                  "<td>" + (report.callingAreaName || '-') + "</td>" +
			                                  "<td>" + (report.totalCalls || 0) + "</td>" +
			                                  "<td>" + (report.connectedCalls || 0) + "</td>" +
			                                  "</tr>";
			                        $("#reportTableByDateRange tbody").append(row);
			                    });
			
			                    // Show the report data div
			                    $("#reportDataByDateRange").show();
			
			                    // Enable download button
			                    $("#downloadAllBtn").attr("disabled", false);
									
			                } else {
			                    alert("No data found for the selected criteria.");
			                    $("#downloadAllBtn").attr("disabled", true); // Disable download button
			                }
			            },
			            error: function(error) {
			                console.log("Error:", error);
			                alert("There was an error retrieving the data.");
			            }
			        });
			    });
			    
			 // Download button click event
			    $("#downloadAllBtn").click(function() {
			    	event.preventDefault(); // Prevent the default action
			        // Get the values from the form
			        const fromDate = $("input[name='startDate']").val();
			        const toDate = $("input[name='endDate']").val();
					
			        // Log the values to the console
			        console.log("From Date:", fromDate);
			        console.log("To Date:", toDate);

			        // Check if all required fields are filled
				    if (fromDate && toDate) {
				    	// Set the hidden fields in the form
				        $("#hiddenAllFromDate").val(fromDate);
				        $("#hiddenAllToDate").val(toDate);

				        // Submit the form
				        $("#downloadAllForm").submit();
				    } else {
				        alert("Please select a user and specify the date range before downloading.");
				   
				    }
			    });
			    
		 });
		
	   </script>
	    <script>
		    // Select all input elements of type date
		    const dateInputs = document.querySelectorAll('input[type="date"]');
		
		    // Add event listener to open the date picker on focus or click
		    dateInputs.forEach(function(input) {
		        input.addEventListener('focus', function() {
		            input.showPicker(); // Trigger the native date picker
		        });
		        input.addEventListener('click', function() {
		            input.showPicker(); // Trigger the native date picker on click
		        });
		    });
		</script>
		
</body>
</html>
												
												
				