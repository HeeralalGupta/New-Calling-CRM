<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp"%>
</head>
<body>
	<%-- 
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // Preventing from back after logout.
		if(session.getAttribute("userSession") == null){
			response.sendRedirect("pages/login.jsp");
		}
	
	%> --%>


	<%@ include file="common/admin-header.jsp"%>

	<!-- partial -->
	<div class="container-fluid page-body-wrapper">
		<!-- partial:partials/_sidebar.html -->
		<%@ include file="common/admin-sidebar.jsp"%>
		<!-- partial -->
		<div class="main-panel">
			<div class="content-wrapper">
				<div class="page-header">
					<h3 class="page-title">
						<span class="page-title-icon bg-gradient-primary text-white mr-2">
							<i class="mdi mdi-home"></i>
						</span> Dashboard
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
				<div class="row">

					<div class="col-md-4 stretch-card grid-margin">
						<div class="card bg-gradient-secondary card-img-holder text-white">
							<div class="card-body">
								<img src="assets/images/dashboard/circle.svg"
									class="card-img-absolute" alt="circle-image" />
								<h4 class="font-weight-normal mb-3 text-black">
									Total Weekly Calls <i
										class="mdi mdi-calendar-clock mdi-24px float-right"
										style="color: white"></i>
								</h4>
								<h2 class="mb-5 text-black">0</h2>
								<!-- <h6 class="card-text">Increased by 60%</h6> -->
							</div>
						</div>
					</div>

					<div class="col-md-4 stretch-card grid-margin">
						<div class="card bg-gradient-secondary card-img-holder text-white">
							<div class="card-body">
								<img src="assets/images/dashboard/circle.svg"
									class="card-img-absolute" alt="circle-image" />
								<h4 class="font-weight-normal mb-3 text-black">
									Total Monthly Calls <i
										class="mdi mdi-calendar-clock mdi-24px float-right"
										style="color: white"></i>
								</h4>
								<h2 class="mb-5 text-black">0</h2>
							</div>
						</div>
					</div>

					<div class="col-md-4 stretch-card grid-margin">
						<div class="card bg-gradient-secondary card-img-holder text-white">
							<div class="card-body">
								<img src="assets/images/dashboard/circle.svg"
									class="card-img-absolute" alt="circle-image" />
								<h4 class="font-weight-normal mb-3 text-black">
									Total Calls <i
										class="mdi mdi-phone-classic mdi-24px float-right"
										style="color: white"></i>
								</h4>
								<h2 class="mb-5 text-black">0</h2>
							</div>
						</div>
					</div>

				</div>

				<div class="row">

					<div class="col-md-4 stretch-card grid-margin">
						<div class="card bg-gradient-secondary card-img-holder text-white">
							<div class="card-body">
								<img src="assets/images/dashboard/circle.svg"
									class="card-img-absolute" alt="circle-image" />
								<h4 class="font-weight-normal mb-3 text-black">
									Total Weekly Calls <i
										class="mdi mdi-calendar-clock mdi-24px float-right"
										style="color: white"></i>
								</h4>
								<h2 class="mb-5 text-black">0</h2>
								<!-- <h6 class="card-text">Increased by 60%</h6> -->
							</div>
						</div>
					</div>

					<div class="col-md-4 stretch-card grid-margin">
						<div class="card bg-gradient-secondary card-img-holder text-white">
							<div class="card-body">
								<img src="assets/images/dashboard/circle.svg"
									class="card-img-absolute" alt="circle-image" />
								<h4 class="font-weight-normal mb-3 text-black">
									Total Monthly Calls <i
										class="mdi mdi-calendar-clock mdi-24px float-right"
										style="color: white"></i>
								</h4>
								<h2 class="mb-5 text-black">0</h2>
							</div>
						</div>
					</div>

					<div class="col-md-4 stretch-card grid-margin">
						<div class="card bg-gradient-secondary card-img-holder text-white">
							<div class="card-body">
								<img src="assets/images/dashboard/circle.svg"
									class="card-img-absolute" alt="circle-image" />
								<h4 class="font-weight-normal mb-3 text-black">
									Total Calls <i
										class="mdi mdi-phone-classic mdi-24px float-right"
										style="color: white"></i>
								</h4>
								<h2 class="mb-5 text-black">0</h2>
							</div>
						</div>
					</div>

				</div>

				<div class="row">

					<div class="col-md-4 stretch-card grid-margin">
						<div class="card bg-gradient-secondary card-img-holder text-white">
							<div class="card-body">
								<img src="assets/images/dashboard/circle.svg"
									class="card-img-absolute" alt="circle-image" />
								<h4 class="font-weight-normal mb-3 text-black">
									Total Weekly Calls <i
										class="mdi mdi-calendar-clock mdi-24px float-right"
										style="color: white"></i>
								</h4>
								<h2 class="mb-5 text-black">0</h2>
								<!-- <h6 class="card-text">Increased by 60%</h6> -->
							</div>
						</div>
					</div>

					<div class="col-md-4 stretch-card grid-margin">
						<div class="card bg-gradient-secondary card-img-holder text-white">
							<div class="card-body">
								<img src="assets/images/dashboard/circle.svg"
									class="card-img-absolute" alt="circle-image" />
								<h4 class="font-weight-normal mb-3 text-black">
									Total Monthly Calls <i
										class="mdi mdi-calendar-clock mdi-24px float-right"
										style="color: white"></i>
								</h4>
								<h2 class="mb-5 text-black">0</h2>
							</div>
						</div>
					</div>

					<div class="col-md-4 stretch-card grid-margin">
						<div class="card bg-gradient-secondary card-img-holder text-white">
							<div class="card-body">
								<img src="assets/images/dashboard/circle.svg"
									class="card-img-absolute" alt="circle-image" />
								<h4 class="font-weight-normal mb-3 text-black">
									Total Calls <i
										class="mdi mdi-phone-classic mdi-24px float-right"
										style="color: white"></i>
								</h4>
								<h2 class="mb-5 text-black">0</h2>
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
</body>
</html>