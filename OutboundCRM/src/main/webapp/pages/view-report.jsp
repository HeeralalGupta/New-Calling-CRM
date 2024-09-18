<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp"%>
<style>
	  #pagination button {
	    margin: 0 2px;
	    padding: 5px 10px;
	    cursor: pointer;
	  }
	
	  #pagination button.active {
	    background-color: #007bff;
	    color: white;
	    border: none;
	  }
	</style>
</head>
<body>

	<%@ include file="common/header.jsp"%>

	<!-- partial -->
	<div class="container-fluid page-body-wrapper">
		<!-- partial:partials/_sidebar.html -->
		<%@ include file="common/sidebar.jsp"%>
		<!-- partial -->
		<div class="main-panel">
			<div class="content-wrapper">
				<!-- Page breadcrumb -->
				<div class="page-header">
					<h3 class="page-title">
						<span class="page-title-icon bg-gradient-primary text-white mr-2">
							<i class="mdi mdi-eye"></i>
						</span> View Report
					</h3>
					
					
				</div>
				<!-- ======================== Page body starts ============================================ -->
				<!-- Rural Table -->
				<%
					if (session.getAttribute("addSuccess") != null) {
						out.print("<script>Swal.fire({ title: 'Report Added!', text: 'Thank You!', icon: 'success'});</script>");
						session.removeAttribute("addSuccess");
					}
					if (session.getAttribute("updateSuccess") != null) {
						out.print("<script>Swal.fire({ title: 'Report Updated!', text: 'Thank You!', icon: 'success'});</script>");
						session.removeAttribute("updateSuccess");
					}
					if (session.getAttribute("deleteSuccess") != null) {
						out.print("<script>Swal.fire({ title: 'Report Deleted!', text: 'Thank You!', icon: 'success'});</script>");
						session.removeAttribute("deleteSuccess");
					}
				%>
				<div class="row">
	              <div class="col-12 grid-margin">
	                <div class="card">
							<div class="card-body">
								
								<!-- Search input -->
								<div class="row">
									<div class="col-md-9">
										<h4 class="card-title">Rural Data</h4>
									</div>
									<div class="col-md-3 mb-3">
										<div class="input-group">
											<input type="text" class="form-control"
												placeholder="Type to search"
												id="r-search"
												aria-label="Recipient's username"
												aria-describedby="basic-addon2">
											<div class="input-group-append">
												<button class="btn btn-sm btn-gradient-primary" type="button"
													cursorshover="true">Search</button>
											</div>
										</div>
									</div>
								</div>
								<div class="table-responsive">
									<table class="table" id="rural-info">
										<thead>
											<tr class="text-info">
												<th>S.No.</th>
												<th>Call Connected</th>
												<th>Call Not Connected</th>
												<th>Name</th>
												<th>Email</th>
												<th>Mobile</th>
												<th>Alternate Mobile</th>
												<th>Profession</th>
												<th>Gender</th>
												<th>Age</th>
												<th>District</th>
												<th>Block</th>
												<th>Panchayat</th>
												<th>Lok Sabha</th>
												<th>Vidhan Sabha</th>
												<th>Sub Divisional</th>
												<th>Calling For</th>
												<th>Note</th>
												<th colspan="2" class="text-center">Action</th>
											</tr>
										</thead>
										<tbody id="ruralTable">
											<c:forEach var="ruralReport" items="${ruralReports}"
												varStatus="sno">
												<tr>
													<td>${sno.count}</td>
													<td>${ruralReport.callConnected}</td>
													<td>${ruralReport.callNotConnected}</td>
													<td>${ruralReport.name}</td>
													<td>${ruralReport.email}</td>
													<td>${ruralReport.mobile}</td>
													<td>${ruralReport.alternateMobile}</td>
													<td>${ruralReport.profession}</td>
													<td>${ruralReport.gender}</td>
													<td>${ruralReport.age}</td>
													<td>${ruralReport.ruralDistrict}</td>
													<td>${ruralReport.ruralBlock}</td>
													<td>${ruralReport.ruralPanchayat}</td>
													<td>${ruralReport.lokSabha}</td>
													<td>${ruralReport.vidhanSabha}</td>
													<td>${ruralReport.subDivision}</td>
													<td>${ruralReport.callingFor}</td>
													<td>${ruralReport.note}</td>

													<td class="text-right"><a href="javascript:void(0);"
														onclick="deleteReport('${ruralReport.id}')"
														class="btn btn-danger">Delete</a></td>
													<td class="text-left">
														<form method="post" action="ruralEdit">
															<input type="hidden" name="ruralReportId"
																value="${ruralReport.id}">
															<button type="submit" class="btn btn-primary">Update</button>
														</form>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<!-- Start Pagination Control-->
								<div id="pagination"></div>
								
							</div>
						</div>
	              </div>
	            </div>
           		
           		<!-- Urban Table -->
           		<div class="row">
	              <div class="col-12 grid-margin">
	                <div class="card">
							<div class="card-body">
								<!-- Search input -->
								<div class="row">
									<div class="col-md-9">
										<h4 class="card-title">Urban Data</h4>
									</div>
									<div class="col-md-3 mb-3">
										<div class="input-group">
											<input type="text" 
												class="form-control"
												placeholder="Type to search"
												id="u-search"
												onkeyup="myFunction()"
												aria-describedby="basic-addon2">
											<div class="input-group-append">
												<button class="btn btn-sm btn-gradient-primary" type="button"
													cursorshover="true">Search</button>
											</div>
										</div>
									</div>
								</div>
								<div class="table-responsive">
									<table class="table" >
										<thead>
											<tr class="text-info">
												<th>S.No.</th>
												<th>Call Connected</th>
												<th>Call Not Connected</th>
												<th>Name</th>
												<th>Email</th>
												<th>Mobile</th>
												<th>Alternate Mobile</th>
												<th>Profession</th>
												<th>Gender</th>
												<th>Age</th>
												<th>District</th>
												<th>Police Station</th>
												<th>Municipality</th>
												<th>Lok Sabha</th>
												<th>Vidhan Sabha</th>
												<th>Sub Divisional</th>
												<th>Calling For</th>
												<th>Note</th>
												<th colspan="2" class="text-center">Action</th>
											</tr>
										</thead>
										<tbody id="urbanTable">
											<c:forEach var="urbanReport" items="${urbanReports}"
												varStatus="sno">
												<tr>
													<td>${sno.count}</td>
													<td>${urbanReport.callConnected}</td>
													<td>${urbanReport.callNotConnected}</td>
													<td>${urbanReport.name}</td>
													<td>${urbanReport.email}</td>
													<td>${urbanReport.mobile}</td>
													<td>${urbanReport.alternateMobile}</td>
													<td>${urbanReport.profession}</td>
													<td>${urbanReport.gender}</td>
													<td>${urbanReport.age}</td>
													<td>${urbanReport.urbanDistrict}</td>
													<td>${urbanReport.urbanPoliceStation}</td>
													<td>${urbanReport.urbanMunicipality}</td>
													<td>${urbanReport.lokSabha}</td>
													<td>${urbanReport.vidhanSabha}</td>
													<td>${urbanReport.subDivision}</td>
													<td>${urbanReport.callingFor}</td>
													<td>${urbanReport.note}</td>

													<td class="text-right"><a href="javascript:void(0);"
														onclick="deleteReport('${urbanReport.id}')"
														class="btn btn-danger">Delete</a></td>
													<td class="text-left">
														<form method="post" action="urbanEdit">
															<input type="hidden" name="urbanReportId"
																value="${urbanReport.id}">
															<button type="submit" class="btn btn-primary">Update</button>
														</form>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
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
	<script>
		function deleteReport(reportId){
			Swal.fire({
				  title: 'Are you sure?',
				  text: "You won't be able to revert this!",
				  icon: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Delete'
				}).then((result) => {
				  if (result.isConfirmed) {
				   window.location="/deleteReport/"+reportId;
				  }
				  else{
					  swal("Your job is safe !!!")
				  }
				})
		}
		</script>
	<!-- End custom js for this page -->
</body>
</html>