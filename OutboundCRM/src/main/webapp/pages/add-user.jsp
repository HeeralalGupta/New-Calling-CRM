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
						</span>User
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
				if (session.getAttribute("userSuccess") != null) {
					out.print("<script>Swal.fire({ title: 'User Added!', text: 'Thank You!', icon: 'success'});</script>");
					session.removeAttribute("userSuccess");
				}
				if (session.getAttribute("updateSuccess") != null) {
					out.print("<script>Swal.fire({ title: 'User Updated!', text: 'Thank You!', icon: 'success'});</script>");
					session.removeAttribute("updateSuccess");
				}
				%>
				
				<!-- ================================== Alert dialogue start======================================== -->
		
				
				<!-- Modal -->
				<div class="modal fade" id="otpDialog" style="display:none;" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
				  <div class="modal-dialog modal-dialog-centered" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body">
				        <div>
						  <h2>Confirm Deletion</h2>
						  <p>An OTP has been sent to your email. Please enter it below to confirm deletion.</p>
						  <input type="text" id="otpInput" placeholder="Enter OTP">					  
						</div>
				      </div>
				      <div class="modal-footer">
				          <button class="btn btn-info" onclick="verifyOtp()">Delete</button>
						  <button class="btn btn-danger" onclick="closeOtpDialog()">Cancel</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!-- =================================== Alert dialogue end======================================= -->
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-description text-info text-bold">Add User</h4>

								<form action="create-user" method="post" class="form-sample">
									<div class="row">
										<div class="col-md-3">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Name<span
													style="color: red;">*</span></label>
												<div class="col-sm-9">
													<input type="text" name="name" class="form-control"
														placeholder="Name" required />
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Mobile No.<span
													style="color: red;">*</span></label>
												<div class="col-sm-9">
													<input type="tel" name="mobile" class="form-control" maxlength="10"
       													pattern="[0-9]{10}" inputmode="numeric" placeholder="Mobile Number" 
       													title="Mobile number must be 10 digits" required />
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Email<span
													style="color: red;">*</span></label>
												<div class="col-sm-9">
													<input type="email" name="email" class="form-control"
														placeholder="Email" required />
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">
													Password<span style="color: red;">*</span>
												</label>
												<div class="col-sm-9">
													<input type="password" name="password" class="form-control"
														placeholder="Password" required />
												</div>
											</div>
										</div>
										<div class="col-md-3">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Role<span
														style="color: red;">*</span></label>
													<div class="col-sm-9">
														<select class="form-control" name="role">
															<option selected>Choose</option>
															<option value="user">User</option>
															<option value="admin">Team Leader</option>
														</select>
													</div>
												</div>
											</div>
									</div>
									<div class="row">
											<input type="hidden" name="status" value="Active">
											<div class="col-md-12">
												<button type="submit"
													class="btn btn-gradient-primary btn-fw">Create</button>
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
												<th>Name</th>
												<th>Mobile No.</th>
												<th>Email</th>
												<th>Status</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="user" items="${users}" varStatus="sno">
												<tr>
													<td>${sno.count}</td>
													<td><img src="data:${user.fileType};Base64,${user.fileName}" alt="image"
                  											onerror="this.onerror=null;this.src='assets/images/faces/user.png';" style="margin-right:5px">${user.name}
                  									</td>
													<td>${user.mobile}</td>
													<td>${user.email}</td>
													<td>
														<c:choose>
													        <c:when test="${user.status == 'Active'}">
													            <span class="badge active-status">Active</span>
													        </c:when>
													        <c:otherwise>
													            <span class="badge inactive-status">Inactive</span>
													        </c:otherwise>
													    </c:choose>
													</td>
													<td>
														<form action="edit-user" method="post">
																<input type="hidden" name="userId" value="${user.id}">
																<button type="submit" class="updateBtn"><img src="assets/images/update-icon.png"></button>
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
</body>
</html>
												
												
				