<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Random"%>
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
							<i class="mdi mdi-upload"></i>
						</span> Upload CSV File
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
				if (session.getAttribute("message") != null) {
					out.print("<script>Swal.fire({ title: 'File Upload Success!', text: 'Thank You!', icon: 'success'});</script>");
					session.removeAttribute("message");
				}
				if (session.getAttribute("assigned") != null) {
					out.print("<script>Swal.fire({ title: 'Task Assigned Success!', text: 'Thank You!', icon: 'success'});</script>");
					session.removeAttribute("assigned");
				}
				%>
				<!-- Upload Csv file -->
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-description text-info text-bold">CSV File</h4>

								<form action="uploadCsv" method="post"
									enctype="multipart/form-data" class="form-sample">
									<div class="row">

										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Upload File<span
													style="color: red;">*</span>
												</label>
												<div class="col-sm-9">
													<div class="custom-file">
														<input type="file" name="file" class="custom-file-input"
															id="customFile" required> <label class="custom-file-label"
															for="customFile">Choose file</label>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<button type="submit" class="btn btn-gradient-primary btn-fw"
												cursorshover="true">Upload</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

				<!-- Assign Task to user -->
				<%
				int id = new Random().nextInt(100000, 199999);
				%>
				<div class="row mt-4">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-description text-info text-bold">Assign
									Task</h4>

								<form action="assignTask" method="post"
									enctype="multipart/form-data" class="form-sample">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Assign Id</label>
												<div class="col-sm-9">
													<input type="text" name="assignId" value="TCI-<%=id%>"
														class="form-control" readonly />
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Assign To<span
													style="color: red;">*</span></label>
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
												<label class="col-sm-3 col-form-label">CSV Min S.No.</label>
												<div class="col-sm-9">
													<input type="number" id="minSerialNumber"
														name="minSerialNumber" class="form-control"
														placeholder="Min" oninput="validateSerialNumbers()" required/>
												</div>
											</div>
										</div>

										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">CSV Max S.No.</label>
												<div class="col-sm-9">
													<input type="number" id="maxSerialNumber"
														name="maxSerialNumber" class="form-control"
														placeholder="Max" oninput="validateSerialNumbers()" required/>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Category</label>
												<div class="col-sm-9">
													<select class="form-control" id="urbanDistrictDropdown"
														name="dataCategory" required>
														<option value="">Select Optino</option>
														<option>Active Citizen</option>
														<option>Common Citizen</option>
														<option>Sevika</option>
														<option>Sahaika</option>
													</select>
												</div>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-12">
											<button type="submit" class="btn btn-gradient-primary btn-fw"
												cursorshover="true">Submit</button>
										</div>
									</div>

								</form>
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
		// Add the following code if you want the name of the file appear on select
		$(".custom-file-input").on(
				"change",
				function() {
					var fileName = $(this).val().split("\\").pop();
					$(this).siblings(".custom-file-label").addClass("selected")
							.html(fileName);
				});
	</script>
	<!-- Checking min serial number is always less than max -->
	<script>
		function validateSerialNumbers() {
			const minSerial = document.getElementById('minSerialNumber').value;
			const maxSerial = document.getElementById('maxSerialNumber').value;

			if (minSerial && maxSerial) {
				if (parseInt(minSerial) >= parseInt(maxSerial)) {
					alert('Min S.No. must be less than Max S.No.');
					document.getElementById('minSerialNumber').value = '';
				}
			}
		}
	</script>
</body>
</html>