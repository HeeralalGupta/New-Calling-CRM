<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
	 <!-- partial:partials/_sidebar.html -->
        <nav class="sidebar sidebar-offcanvas fixed" id="sidebar">
          <ul class="nav">
            <li class="nav-item nav-profile">
              <a href="#" class="nav-link">
                <div class="nav-profile-image">
                  <img src="assets/images/faces/user.png" alt="profile">
                  <span class="login-status online"></span>
                  <!--change to offline or busy as needed-->
                </div>
                <div class="nav-profile-text d-flex flex-column">
                  <span class="font-weight-bold mb-2">${loginUserName}</span>
                  <span class="text-secondary text-small">Telecaller</span>
                </div>
                <i class="mdi mdi-bookmark-check text-success nav-profile-badge"></i>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/user-dashboard">
                <span class="menu-title">Dashboard</span>
                <i class="mdi mdi-home menu-icon"></i>
              </a>
            </li>
            <li class="nav-item sidebar-actions">
              <span class="nav-link">
                <div class="border-bottom">
                  <h6 class="font-weight-normal mb-3">Outbound Calls</h6>
                </div>
                <li class="nav-item">
                  <a class="nav-link" href="add-report">
                    <span class="menu-title">Add Report</span>
                    <i class="mdi mdi-account-multiple-plus menu-icon"></i>
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="view">
                    <span class="menu-title">View Report</span>
                    <i class="mdi mdi mdi-eye menu-icon"></i>
                  </a>
                </li>
              </span>
            </li>
            <li class="nav-item sidebar-actions">
              <span class="nav-link">
                <div class="border-bottom">
                  <h6 class="font-weight-normal mb-3">Inbound Calls</h6>
                </div>
                <li class="nav-item">
                  <a class="nav-link" href="add-inbound">
                    <span class="menu-title">Add Report</span>
                    <i class="mdi mdi-account-multiple-plus menu-icon"></i>
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="view-inbound">
                    <span class="menu-title">View Report</span>
                    <i class="mdi mdi mdi-eye menu-icon"></i>
                  </a>
                </li>
              </span>
            </li>
            <li class="nav-item sidebar-actions">
              <span class="nav-link">
                <div class="border-bottom"></div>
              </span>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">
                <span class="menu-title">More</span>
                <i class="mdi mdi-contacts menu-icon"></i>
              </a>
            </li>
          </ul>
        </nav>
        
</body>
</html>