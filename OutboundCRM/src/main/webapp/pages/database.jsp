<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp"%>
<style>
  .badge {
    padding: 5px 10px;
    border-radius: 5px;
    color: white;
    font-size: 12px;
  }
  
  .active-status {
    background-color: green;
  }
  
  .inactive-status {
    background-color: red;
  }
  .updateBtn{
  	border: none;
  	background: transparent;
  }
</style>

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
						</span>Database
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
				<%
				if (session.getAttribute("saveData") != null) {
					out.print("<script>Swal.fire({ title: 'Data Uploaded Successfully!', text: 'Thank You!', icon: 'success'});</script>");
					session.removeAttribute("saveData");
				}
				if (session.getAttribute("updateSuccess") != null) {
					out.print("<script>Swal.fire({ title: 'User Updated!', text: 'Thank You!', icon: 'success'});</script>");
					session.removeAttribute("updateSuccess");
				}
				%>
				
				
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-description text-info text-bold">Add Data</h4>

								<form action="saveData" method="post"
									enctype="multipart/form-data" class="form-sample">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Data Area</label>
												<div class="col-sm-9">
													<input type="text"
														name="dataArea" class="form-control"
														placeholder="Area Name" required />
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">PIN Code<span
													style="color: red;">*</span></label>
												<div class="col-sm-9">
													<input type="tel" name="pinCode" class="form-control" maxlength="6"
       													pattern="[0-9]{6}" inputmode="numeric" placeholder="PIN Code" 
       													title="PIN code must be 6 digits" required />
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Category</label>
												<div class="col-sm-9">
													<select class="form-control"
														name="category" required>
														<option value="">Select Optino</option>
														<option>Active Citizen</option>
														<option>Common Citizen</option>
														<option>Sevika</option>
														<option>Sahaika</option>
														<option>Sahiya</option>
														<option>Para Teacher</option>
														<option>Sanyojika</option>
														<option>BJP</option>
														<option>MMMSY</option>
														<option>Jalsahiya</option>
													</select>
												</div>
											</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Upload File<span
													style="color: red;">*</span>
												</label>
												<div class="col-sm-9">
													<div class="custom-file">
														<input type="file" name="file" class="custom-file-input"
															id="customFile" accept=".xlsx" required> <label
															class="custom-file-label" for="customFile">Choose
															file</label>	
													</div>
													<span style="color:red; font-size:13px;">Upload only excel file.</span>
												</div>
											</div>
										</div>
										
									</div>
					
									<div class="row">
										<div class="col-md-12">
											<button type="submit" class="btn btn-gradient-primary btn-fw">Submit</button>
										</div>
									</div>

								</form>
							</div>
						</div>
					</div>
				</div>

				<div class="row mt-4">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<!-- Search input -->
								<div class="row align-items-center">
									<div class="col-md-4">
										<h4 class="card-title">User List</h4>
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
									<div class="col-md-4 mb-3">
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
									<table class="table table-class" id="addUserTable">
										<thead>
											<tr>
												<th>S.No.</th>
												<th>Date</th>
												<th>Data Area</th>										
												<th>Category</th>	
												<th>PIN Code</th>											
												<th>Download</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="data" items="${excelData}" varStatus="sno">
												<tr>
													<td>${sno.count}</td>
													<td>${data.date}</td>
													<td>${data.dataArea}</td>													
													<td>${data.category}</td>
													<td>${data.pinCode}</td>															
													<td>
														<form action="downlaodData" method="post">
																<input type="hidden" name="dataId" value="${data.id}">
																<button type="submit" class="updateBtn"><img src="assets/images/download-excel-icon.png"></button>
														</form>
													</td>
												</tr>
											</c:forEach>
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
		    $("#addUserTable tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		  });
		});
	</script>
	<script>
	  // Ensure the file name is shown in the label when a file is selected
	  document.querySelector('.custom-file-input').addEventListener('change', function(e) {
	    var fileName = document.getElementById("customFile").files[0].name;
	    var nextSibling = e.target.nextElementSibling;
	    nextSibling.innerText = fileName;
	  });
	</script>
</body>
</html>
												
												
				