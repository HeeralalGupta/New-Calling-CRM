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
							<i class="mdi mdi-account-circle"></i>
						</span> Your Profile
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
					if (session.getAttribute("success") != null) {
						out.print("<script>Swal.fire({ title: 'Profile Updated!', text: 'Thank You!', icon: 'success'});</script>");
						session.removeAttribute("success");
					}
				%>
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<!-- Profile Section -->
								<div class="profile-container">
									<!-- Profile Picture Preview -->
									<div class="profile-pic-container">
									
										<img src="data:${userProfile.fileType};base64,${userProfile.fileName}" 
									         alt="Profile Picture" class="profile-pic" id="image" 
									         onerror="this.onerror=null;this.src='assets/images/faces/user.png';">
									</div>

									<div class="profile-info">
										<h1>${userProfile.name}</h1>
										<a href="#" class="email-link">${userProfile.email}</a>
										<br> <span>Team Leader</span>

										<!-- Form starts here -->
										<form action="updateProfile/${userProfile.id}" method="post" id="profileForm" enctype="multipart/form-data">
											<!-- File Upload Section -->
											<div class="file-upload" id="uploadArea">
												<p>
													Drop your files here or <a href="#" id="clickUpload">click
														in this area</a>
												</p>
												<input type="file" id="fileInput" name="image"
													style="display: none;" /> <span id="fileName"></span>
												<!-- Show the uploaded file name -->
											</div>
											<input type="hidden" name="userId" value="${userProfile.id}"/>
											<!-- Full Name Input -->
											<div class="form-group">
												<label for="username">Full Name</label> <input type="text"
													id="username" name="name" value="${userProfile.name}">
											</div>

											<!-- Email Input -->
											<div class="form-group">
												<label for="email">Email <span>*</span></label> <input
													type="email" id="email" name="email"
													value="${userProfile.email}">
											</div>

											<!-- Password Input with Eye Toggle -->
											<div class="form-group password-group">
												<label for="password">Password</label> <input
													type="password" id="password" name="password"
													value="${userProfile.password}"> <i class="fas fa-eye"
													id="togglePassword" style="cursor: pointer;"></i>
											</div>

											<!-- Update Button -->
											<div class="form-group">
												<button type="submit" class="update-btn">Update</button>
											</div>
										</form>
									</div>
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
		// Handle file upload
		document.getElementById("clickUpload").addEventListener("click",
				function(e) {
					e.preventDefault();
					document.getElementById("fileInput").click();
				});

		document.getElementById("uploadArea").addEventListener("click",
				function() {
					document.getElementById("fileInput").click();
				});

		// Show file name and preview image when a file is uploaded
		document.getElementById("fileInput")
				.addEventListener(
						"change",
						function() {
							const fileInput = this;
							const fileName = fileInput.files[0].name;
							const fileNameDisplay = document
									.getElementById("fileName");
							fileNameDisplay.textContent = "Selected file: "
									+ fileName;

							// Preview uploaded image (if it's an image)
							const reader = new FileReader();
							reader.onload = function(e) {
								const image = document
										.getElementById("image");
								image.src = e.target.result;
							}
							if (fileInput.files[0]) {
								reader.readAsDataURL(fileInput.files[0]);
							}
						});

		// Toggle password visibility
		const togglePassword = document.getElementById('togglePassword');
		const passwordField = document.getElementById('password');

		togglePassword
				.addEventListener(
						'click',
						function() {
							// Toggle the type attribute
							const type = passwordField.getAttribute('type') === 'password' ? 'text'
									: 'password';
							passwordField.setAttribute('type', type);

							// Toggle the eye / eye-slash icon
							this.classList.toggle('fa-eye-slash');
						});
	</script>

</body>
</html>


