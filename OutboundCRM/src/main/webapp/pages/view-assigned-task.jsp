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
							<i class="mdi mdi-format-list-bulleted-type"></i>
						</span> Assigned Task
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
								<h4 class="card-description text-info text-bold">Assigned
									Task</h4>
								<div class="table-responsive">
									<table class="table">
										<thead>
											<tr>
												<th>S.No.</th>
												<th>Assigned Id</th>
												<th>Name</th>
												<th>Data Category</th>
												<th>Serial No.</th>
												<th>Time</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="task" items="${tasks}" varStatus="sno">
												<tr>
													<td>${sno.count}</td>
													<td>${task.assignId}</td>
													<td>${userIdToUserName[task.userId]}</td>
													<td>${task.dataCategory}</td>
													<td>${task.minSerialNumber}- ${task.maxSerialNumber}</td>
													<td>${task.time}</td>
													<td><a href="javascript:void(0);"
														onclick="deleteTask('${task.id}')"><img
															src="assets/images/delete-icon.png"></a></td>
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
		function deleteTask(taskId){
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
				   window.location="/deleteTask/"+taskId;
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