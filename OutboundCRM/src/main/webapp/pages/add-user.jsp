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
						</span> Add User
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
					out.print("<script>Swal.fire({ title: 'Tele caller Added!', text: 'Thank You!', icon: 'success'});</script>");
					session.removeAttribute("userSuccess");
				}
				if (session.getAttribute("deleteSuccess") != null) {
					out.print("<script>Swal.fire({ title: 'Tele Caller Deleted!', text: 'Thank You!', icon: 'success'});</script>");
					session.removeAttribute("deleteSuccess");
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
								<h4 class="card-description text-info text-bold">Add a
									Telecaller</h4>

								<form action="create-user" method="post" class="form-sample">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Name<span
													style="color: red;">*</span></label>
												<div class="col-sm-9">
													<input type="text" name="name" class="form-control"
														placeholder="Name" required />
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Email<span
													style="color: red;">*</span></label>
												<div class="col-sm-9">
													<input type="email" name="email" class="form-control"
														placeholder="Email" required />
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Create
													Password<span style="color: red;">*</span>
												</label>
												<div class="col-sm-9">
													<input type="password" name="password" class="form-control"
														placeholder="Password" required />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<button type="submit"
													class="btn btn-gradient-primary btn-fw" cursorshover="true">Create
													User</button>
											</div>
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
													<td><a href="javascript:void(0);" onclick="deleteUser('${user.id}')" <%-- onclick="openOtpDialog('${user.id}', '${user.email}')" --%>><img src="assets/images/delete-icon.png"></a></td>
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
	<script>
		function deleteUser(userId){
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
				   window.location="/deleteUser/"+userId;
				  }
				  else{
					  swal("Your job is safe !!!")
				  }
				})
		}
		</script>
</body>
</html>
												
												
				