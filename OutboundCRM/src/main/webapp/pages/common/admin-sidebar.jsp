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
                  <span class="text-secondary text-small">Team Leader</span>
                </div>
                <i class="mdi mdi-bookmark-check text-success nav-profile-badge"></i>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/admin-dashboard">
                <span class="menu-title">Dashboard</span>
                <i class="mdi mdi-home menu-icon"></i>
              </a>
            </li>
           
            <li class="nav-item sidebar-actions">
              <span class="nav-link">
                <div class="border-bottom"></div>
              </span>
            </li>
            
             <li class="nav-item">
              <a class="nav-link" href="add-user">
                <span class="menu-title">Add User</span>
                <i class="mdi mdi-account-multiple-plus menu-icon"></i>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="add-csv">
                <span class="menu-title">Upload CSV File</span>
                <i class="mdi mdi-file menu-icon"></i>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="view-task">
                <span class="menu-title">View Assigned Task</span>
                <i class="mdi mdi-format-list-bulleted-type menu-icon"></i>
              </a>
            </li>
          </ul>
        </nav>
        
</body>
</html>