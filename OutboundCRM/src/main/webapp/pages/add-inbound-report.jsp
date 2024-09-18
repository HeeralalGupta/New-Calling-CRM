<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp"%>
<style>
.cid {
	width: 170px;
	height: 35px;
	border: 1px solid rgba(0, 0, 0, 0.10);
	padding: 5px 10px 5px 10px;
	background: rgba(4, 3, 4, 0.05);
	color: black;
	text-align: center;
	align-content: center;
	margin-bottom: 20px;
	border-radius: 5px;
	font-size: 16px;
}

.notification {
	padding: 10px;
	border-radius: 6px;
	font-size: 16px;
	color: #DC143C;
	background: #FF7F7F;
	color: wheat;
	text-align: center;
	margin-bottom: 20px;
	margin-left: auto;
	color: white;
	width: fit-content;
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
							<i class="mdi mdi-plus"></i>
						</span> Add Inbound Report
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
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<c:if test="${assignedData != null}">
									<div class="notification">${assignedData}</div>
								</c:if>

								<c:if test="${tcId != null}">
									<div class="text-bold cid" id="tcid">${tcId}</div>
								</c:if>
								<h4 class="card-description text-info text-bold">Call
									Disposition</h4>

								<form action="/add-inbound" method="post" class="form-sample">
									<input type="hidden" name="userId" value="${userId}">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Calling For</label>
												<div class="col-sm-9">
													<select class="form-control" id="callingFor"
														name="callingFor">
														<option selected disabled>Select</option>
														<option>PR</option>
														<option>Political Campaign</option>
														<option>Election Management</option>
														<!-- Dynamic Data will populate -->
													</select>
												</div>
											</div>
										</div>
										<c:if test="${assignedData != null}">
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Call Type<span
														style="color: red;">*</span></label>
													<div class="col-sm-9">
														<select class="form-control" id="connectionType"
															name="connectionType">
															<option value="">Select</option>
															<option value="connected">Connected</option>
															<option value="notConnected">Not Connected</option>
														</select>
													</div>
												</div>
											</div>
										</c:if>
										<c:if test="${assignedData == null}">
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Call Type<span
														style="color: red;">*</span></label>
													<div class="col-sm-9">
														<select class="form-control" id="connectionType"
															name="connectionType" required>
															<option value="">Select</option>
															<option value="connected">Connected</option>
															<option value="notConnected">Not Connected</option>
														</select>
													</div>
												</div>
											</div>
										</c:if>
										<div class="col-md-4" id="connectedDropdown"
											style="display: none;">

											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Call
													Connected<span style="color: red;">*</span>
												</label>
												<div class="col-sm-9">
													<select class="form-control" name="callConnected">
														<option value="">Select</option>
														<option>Matured call</option>
														<option>No response</option>
														<option>Call drop/Incomplete call</option>
													</select>
												</div>
											</div>
										</div>

										<div class="col-md-4" id="notConnectedDropdown"
											style="display: none;">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Call Not
													Connected<span style="color: red;">*</span>
												</label>
												<div class="col-sm-9">
													<select class="form-control" name="callNotConnected">
														<option value="">Select</option>
														<option>Switched off</option>
														<option>Out of coverage area</option>
														<option>Out of service</option>
														<option>Wrong number</option>
														<option>Other</option>
													</select>
												</div>
											</div>
										</div>
									</div>

									<h4 class="card-description text-info text-bold">Personal
										Information</h4>

									<c:if test="${csvRow != null}">
										<div class="row">
											<c:forEach var="data" items="${csvRow}" varStatus="status">
												<c:choose>
													<c:when test="${status.count == 2}">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-3 col-form-label">Name<span
																	style="color: red;">*</span></label>
																<div class="col-sm-9">
																	<input type="text" name="name" class="form-control"
																		value="${data != null ? data : ''}" placeholder="Name"
																		required />
																</div>
															</div>
														</div>
													</c:when>

													<c:when test="${status.count == 4 }">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-3 col-form-label">Mobile
																	No.<span style="color: red;">*</span>
																</label>
																<div class="col-sm-9">
																	<input type="tel" name="mobile" value="${data}"
																		class="form-control" maxlength="10"
																		placeholder="Mobile" required />
																</div>
															</div>
														</div>
													</c:when>

													<c:when test="${status.count == 5 }">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-3 col-form-label">Alternate
																	Mobile</label>
																<div class="col-sm-9">
																	<input type="tel" name="alternateMobile"
																		value="${data}" class="form-control" maxlength="10"
																		placeholder="Mobile" required />
																</div>
															</div>
														</div>
													</c:when>
												</c:choose>
											</c:forEach>
										</div>
									</c:if>
									<c:if test="${csvRow != null}">
										<div class="row">
											<c:forEach var="data" items="${csvRow}" varStatus="status">

												<c:choose>
													<c:when test="${status.count == 6 }">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-3 col-form-label">Profession<span
																	style="color: red;">*</span></label>
																<div class="col-sm-9">
																	<input type="text" name="profession" value="${data}"
																		class="form-control" placeholder="Profession" required />
																</div>
															</div>
														</div>
													</c:when>
													<c:when test="${status.count == 7 }">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-3 col-form-label">Email</label>
																<div class="col-sm-9">
																	<input type="email" name="email" value="${data}"
																		class="form-control" placeholder="Email" />
																</div>
															</div>
														</div>
													</c:when>
													<c:when test="${status.count == 8 }">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-3 col-form-label">Age</label>
																<div class="col-sm-9">
																	<input type="number" name="age" value="${data}"
																		class="form-control" placeholder="Age" />
																</div>
															</div>
														</div>
													</c:when>
												</c:choose>
											</c:forEach>
										</div>
									</c:if>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Gender</label>
												<div class="col-sm-4">
													<div class="form-check">
														<label class="form-check-label"> <input
															type="radio" class="form-check-input" name="gender"
															id="membershipRadios1" value="Male" checked> Male
														</label>
													</div>
												</div>
												<div class="col-sm-5">
													<div class="form-check">
														<label class="form-check-label"> <input
															type="radio" class="form-check-input" name="gender"
															id="membershipRadios2" value="Female"> Female
														</label>
													</div>
												</div>
											</div>
										</div>
									</div>

									<h4 class="card-description text-info text-bold">Address</h4>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Residential
													Type</label>
												<div class="col-sm-4">
													<div class="form-check">
														<label class="form-check-label"> <input
															type="radio" class="form-check-input"
															name="residentialType" id="ruralRadio" value="Rural"
															checked> Rural
														</label>
													</div>
												</div>
												<div class="col-sm-5">
													<div class="form-check">
														<label class="form-check-label"> <input
															type="radio" class="form-check-input"
															name="residentialType" id="urbanRadio" value="Urban">
															Urban
														</label>
													</div>
												</div>
											</div>
										</div>
									</div>

									<!-- Rural Inputs -->
									<!-- Rural Inputs -->
									<div id="ruralInputs">
										<c:if test="${csvRow != null}">
											<div class="row">
												<c:forEach var="data" items="${csvRow}" varStatus="status">
													<!-- Choose the correct dropdown based on the status -->
													<c:choose>
														<c:when test="${status.count == 10}">
															<!-- District dropdown -->
															<div class="col-md-4">
																<div class="form-group row">
																	<label class="col-sm-3 col-form-label">District</label>
																	<div class="col-sm-9">
																		<select class="form-control" id="districtDropdown"
																			name="ruralDistrict">
																			<option value="">Select District</option>
																			<option value="${data}" selected>${data}</option>
																			<!-- Display the data dynamically -->
																		</select>
																	</div>
																</div>
															</div>
														</c:when>

														<c:when test="${status.count == 11}">
															<!-- Block dropdown -->
															<div class="col-md-4">
																<div class="form-group row">
																	<label class="col-sm-3 col-form-label">Block</label>
																	<div class="col-sm-9">
																		<select class="form-control" id="blockDropdown"
																			name="ruralBlock">
																			<option value="">Select Block</option>
																			<option value="${data}" selected>${data}</option>
																			<!-- Display the data dynamically -->
																		</select>
																	</div>
																</div>
															</div>
														</c:when>

														<c:when test="${status.count == 12}">
															<!-- Panchayat dropdown -->
															<div class="col-md-4">
																<div class="form-group row">
																	<label class="col-sm-3 col-form-label">Panchayat</label>
																	<div class="col-sm-9">
																		<select class="form-control" id="panchayatDropdown"
																			name="ruralPanchayat">
																			<option value="">Select Panchayat</option>
																			<option value="${data}" selected>${data}</option>
																			<!-- Display the data dynamically -->
																		</select>
																	</div>
																</div>
															</div>
														</c:when>

														<c:when test="${status.count == 13}">
															<!-- Village dropdown -->
															<div class="col-md-4">
																<div class="form-group row">
																	<label class="col-sm-3 col-form-label">Village</label>
																	<div class="col-sm-9">
																		<select class="form-control" id="villageDropdown"
																			name="ruralVillage">
																			<option value="">Select Village</option>
																			<option value="${data}" selected>${data}</option>
																			<!-- Display the data dynamically -->
																		</select>
																	</div>
																</div>
															</div>
														</c:when>
													</c:choose>
												</c:forEach>
											</div>
										</c:if>
									</div>



									<!-- Urban Inputs -->
									<div id="urbanInputs" style="display: none;">
										<div class="row">
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">District</label>
													<div class="col-sm-9">
														<select class="form-control" id="urbanDistrictDropdown"
															name="urbanDistrict">
															<option value="">Select District</option>
															<option>Test</option>
															<!-- Dynamic Data will populate -->
														</select>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Police
														Station</label>
													<div class="col-sm-9">
														<select class="form-control" id="policeStationDropdown"
															name="urbanPoliceStation">
															<option value="">Select Police Station</option>
															<!-- Dynamic Data will populate -->
															<option>Test</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Municipality</label>
													<div class="col-sm-9">
														<select class="form-control" id="municipalityDropdown"
															name="urbanMunicipality">
															<option value="">Select Municipality</option>
															<!-- Dynamic Data will populate -->
															<option>Test</option>
														</select>
													</div>
												</div>
											</div>
										</div>
									</div>

									<!-- Assembly Constituency -->
									<h4 class="card-description text-info text-bold">Assembly
										Constituency</h4>

									<c:if test="${csvRow != null}">
										<div class="row">
											<c:forEach var="data" items="${csvRow}" varStatus="status">
												<!-- Choose the correct drop down based on the status -->
												<c:choose>
													<c:when test="${status.count == 17}">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-3 col-form-label">Lok Sabha</label>
																<div class="col-sm-9">
																	<select class="form-control" id="lokSabhaDropdown"
																		name="lokSabha">
																		<option value="">Select Option</option>
																		<option value="${data}" selected>${data}</option>
																		<!-- Dynamic Data will populate -->

																	</select>
																</div>
															</div>
														</div>
													</c:when>

													<c:when test="${status.count == 18}">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-3 col-form-label">Vidhan
																	Sabha</label>
																<div class="col-sm-9">
																	<select class="form-control" id="vidhanSabhaDropdown"
																		name="vidhanSabha">
																		<option value="">Select Option</option>
																		<option value="${data}" selected>${data}</option>
																		<!-- Dynamic Data will populate -->
																	</select>
																</div>
															</div>
														</div>
													</c:when>

													<c:when test="${status.count == 20}">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-3 col-form-label">Sub
																	Division</label>
																<div class="col-sm-9">
																	<select class="form-control" id="subDivisionDropdown"
																		name="subDivision">
																		<option value="">Select Option</option>
																		<option value="${data}" selected>${data}</option>
																		<!-- Dynamic Data will populate -->
																	</select>
																</div>
															</div>
														</div>
													</c:when>
												</c:choose>
											</c:forEach>
										</div>
									</c:if>

									<div class="row">

										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Booth No.</label>
												<div class="col-sm-9">
													<input type="number" name="ruralWardNumber" value="0"
														placeholder="Ward Number" class="form-control" required />
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Note :</label>
												<div class="col-sm-9">
													<textarea name="note" class="form-control"
														placeholder="Note"></textarea>
												</div>
											</div>
										</div>
									</div>
									<input type="hidden" name="currentSerialNumber"
										value="${currentSerialNumber}" />

									<c:if
										test="${assignedData != 'Thank You! You have done your job!' }">
										<div class="row">

											<c:if test="${assignedData == null}">
												<div class="col-md-6">
													<button type="submit"
														class="btn btn-gradient-primary btn-fw"
														cursorshover="true">Save & Next</button>
												</div>
											</c:if>
											<c:if test="${assignedData != null}">
												<div class="col-md-6">
													<button type="submit"
														class="btn btn-gradient-primary btn-fw"
														cursorshover="true">Skip</button>
												</div>
											</c:if>
										</div>
									</c:if>
								</form>
							</div>
						</div>
					</div>
				</div>



				<%-- <form action="/add-inbound" method="post">
			    <c:if test="${csvRow != null}">
			        <c:forEach var="data" items="${csvRow}" varStatus="status">
			            <label>Test</label>
			            <input type="text" name="name" value="${data}" />
			            <br/>
			        </c:forEach>
			    </c:if>
			
			    <input type="hidden" name="currentSerialNumber" value="${currentSerialNumber}" />
			    <button type="submit">Submit</button>
			</form>
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
</body>
</html>