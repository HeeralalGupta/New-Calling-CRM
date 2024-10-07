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
						</span>Update
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
				

				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-description text-info text-bold">Update User</h4>

								<form action="update-user-by-admin/${user.id}" method="post" class="form-sample">
									<div class="row">
										<div class="col-md-3">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Name<span
													style="color: red;">*</span></label>
												<div class="col-sm-9">
													<input type="text" name="name" value="${user.name}" class="form-control"
														placeholder="Name" required />
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Mobile No.<span
													style="color: red;">*</span></label>
												<div class="col-sm-9">
													<input type="tel" name="mobile" maxlength="10"
       													pattern="[0-9]{10}" inputmode="numeric" value="${user.mobile}" class="form-control" pattern="[0-9]{10}" 
														placeholder="Mobile Number" title="Mobile number must be 10 digits" required />
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group row">
												<label class="col-sm-3 col-form-label">Email<span
													style="color: red;">*</span></label>
												<div class="col-sm-9">
													<input type="email" name="email" value="${user.email}" class="form-control"
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
													<input type="password" name="password" value="${user.password}" class="form-control"
														placeholder="Password" required />
												</div>
											</div>
										</div>
										<div class="col-md-3">
												<div class="form-group row">
													<label class="col-sm-3 col-form-label">Status<span
														style="color: red;">*</span></label>
													<div class="col-sm-9">
														<select class="form-control" name="status">
															<option value="${user.status}"selected>${user.status}</option>
															<option value="Active">Active</option>
															<option value="Inactive">Inactive</option>
														</select>
													</div>
												</div>
											</div>
										<div class="row">											
											<div class="col-md-4">
												<button type="submit"
													class="btn btn-gradient-primary btn-fw" cursorshover="true">Update</button>
											</div>
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
</body>
</html>
												
												
				