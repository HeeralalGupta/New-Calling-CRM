<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@ include file="common/head.jsp" %>
<body>
	 <div class="container-scroller">
      <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="content-wrapper d-flex align-items-center auth">
          <div class="row flex-grow">
            <div class="col-lg-4 mx-auto">
              <div class="auth-form-light text-left p-5">
                <div class="brand-logo">
                  <img src="../../assets/images/logos (1).png">
                </div>
                <h4>INPRAVIA GOLOBAL NETWORKS PVT. LTD.</h4>
                <h6 class="font-weight-light">Sign in to continue.</h6>
                <h6 class="font-weight-bold text-danger mt-2">${errorMsg}</h6>
                <form action="login" method="post" class="pt-3">
                  <div class="form-group">
                    <input type="email" name="email" class="form-control form-control-lg" id="exampleInputEmail1" placeholder="Username" required>
                  </div>
                  <div class="form-group">
                    <input type="password" name="password" class="form-control form-control-lg" id="exampleInputPassword1" placeholder="Password" required>
                  </div>
                  <div class="mt-3">
                    <!-- <a class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn" href="">SIGN IN</a> -->
                    <button type="submit" class="btn btn-gradient-info btn-lg btn-block" cursorshover="true">SIGN IN <i class="mdi mdi-chevron-double-right float-right"></i>
                      </button>
                  </div>
                  <div class="my-2 d-flex justify-content-between align-items-center">
                    <div class="form-check">
                      <label class="form-check-label text-muted">
                        <input type="checkbox" class="form-check-input"> Keep me signed in </label>
                    </div>
                    <a href="#" class="auth-link text-black">Forgot password?</a>
                  </div>
                 
                </form>
              </div>
            </div>
          </div>
        </div>
        <!-- content-wrapper ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
    <!-- container-scroller -->
    
</body>
</html>