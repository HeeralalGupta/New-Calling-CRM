<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp"%>
<style type="text/css">
	.connected{
		background-color: lightgreen;
		width: auto;
		padding: 5px;
		color: darkgreen;
		border-radius: 5px;
		text-align: center;
	}
	.not-connected{
		background-color: #FF7F7F;
		width: auto;
		padding: 5px;
		color: #8B0000;
		border-radius: 5px;
		text-align: center;
	}
	/* Pagination */
	div.pager {
    text-align: right;
    width: 40em;
    margin: 1em auto;
	}
	
	div.pg-goto {
	color: #000000;
	font-size: 15px;
	cursor: pointer;
	background: #D0B389;
	padding: 2px 4px 2px 4px;
	}
	
	div.pg-selected {
	color: #fff;
	font-size: 15px;
	background: #000000;
	padding: 2px 4px 2px 4px;
	}
	
	div.pg-normal {
	color: #000000;
	font-size: 15px;
	cursor: pointer;
	background: #D0B389;
	padding: 4px 6px 4px 6px;
	margin: 5px;
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
						</span> View Inbound Report
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
				<!-- Urban Table -->
           		<div class="row">
	              <div class="col-12 grid-margin">
	                <div class="card">
							<div class="card-body">
								<!-- Search input -->
								<div class="row">
									<div class="col-md-9">
										<h4 class="card-title">Called Number</h4>
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
									<table class="table paginated" >
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
												<!-- <th colspan="2" class="text-center">Action</th> -->
											</tr>
										</thead>
										<tbody id="urbanTable">
											<c:forEach var="callerReport" items="${callerDataList}"
												varStatus="sno">
												<tr>
													<td>${sno.count}</td>
													<td><div class="connected">${callerReport.callConnected}</div></td>
													<td><div class="not-connected">${callerReport.callNotConnected}</div></td>
													<td>${callerReport.name}</td>
													<td>${callerReport.email}</td>
													<td>${callerReport.mobile}</td>
													<td>${callerReport.alternateMobile}</td>
													<td>${callerReport.profession}</td>
													<td>${callerReport.gender}</td>
													<td>${callerReport.age}</td>
													<td>${callerReport.urbanDistrict}</td>
													<td>${callerReport.urbanPoliceStation}</td>
													<td>${callerReport.urbanMunicipality}</td>
													<td>${callerReport.lokSabha}</td>
													<td>${callerReport.vidhanSabha}</td>
													<td>${callerReport.subDivision}</td>
													<td>${callerReport.callingFor}</td>
													<td>${callerReport.note}</td>

													<%-- <td class="text-right"><a href="javascript:void(0);"
														onclick="deleteReport('${callerReport.id}')"
														class="btn btn-danger">Delete</a></td>
													<td class="text-left">
														<form method="post" action="urbanEdit">
															<input type="hidden" name="callerReportId"
																value="${callerReport.id}">
															<button type="submit" class="btn btn-primary">Update</button>
														</form>
													</td> --%>
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
	<!-- End custom js for this page -->
</body>
</html>