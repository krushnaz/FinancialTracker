<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.financialtracker.entities.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Finance Tracker-Login</title>

<!-- Meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta name="description"
	content="Portal - Bootstrap 5 Admin Dashboard Template For Developers">
<meta name="author" content="Xiaoying Riley at 3rd Wave Media">
<link rel="shortcut icon" href="assets/images/Rupee Logo.png">

<!-- FontAwesome JS-->
<script defer src="assets/plugins/fontawesome/js/all.min.js"></script>

<!-- App CSS -->
<link id="theme-style" rel="stylesheet" href="assets/css/portal.css">

</head>

<body class="app app-login p-0">
	<div class="row g-0 app-auth-wrapper">
		<div class="col-12  auth-main-col text-center p-5 mt-5">
			<div class="d-flex flex-column align-content-end">
				<div class="app-auth-body mx-auto shadow  p-4 border">
					<div class="app-auth-branding mb-4">
						<a class="app-logo" href="index.jsp"><img
							class="logo-icon me-2" src="assets/images/Rupee Logo.png"
							alt="logo"></a>
					</div>
					<h2 class="auth-heading text-center mb-5">
						Welcome<span class="text-success"> Finacial Tracker</span>
					</h2>
					<div class="auth-form-container text-start">



						<%-- Check if an error message is set --%>
						<%
						String errorMessage = (String) request.getAttribute("errorMessage");
						if (errorMessage != null && !errorMessage.isEmpty()) {
						%>
						<div class="alert alert-danger">
							<%=errorMessage%>
						</div>
						<%
						}
						%>




						<%
						Message m = (Message) session.getAttribute("msg");
						if (m != null) {
						%>
						<div class="alert <%=m.getCSSType()%>" role="alert">
							<%=m.getContentType()%>
						</div>
						<%
						session.removeAttribute("msg");
						}
						%>

						<form action="LoginServlet" method="POST"
							class="auth-form login-form">
							<div class="email mb-3">
								<label class="sr-only" for="signin-email">Email</label> <input
									id="signin-email" name="signin-email" type="email"
									class="form-control signin-email" placeholder="Email address"
									required="required">
							</div>
							<!--//form-group-->
							<div class="password mb-3">
								<label class="sr-only" for="signin-password">Password</label> <input
									id="signin-password" name="signin-password" type="password"
									class="form-control signin-password" placeholder="Password"
									required="required">
								<div class="extra mt-3 row">
									<!-- <div class="col-6">
										<div class="form-check">
											<input class="form-check-input" type="checkbox" value="" id="RememberPassword">
											<label class="form-check-label" for="RememberPassword">
											Remember me
											</label>
										</div>
									</div>//col-6 -->
									<div class="col-6">
										<div class="forgot-password ms-2">
											<a href="forgetpassword.jsp">Forgot password?</a>
										</div>
									</div>
									<!--//col-6-->
								</div>
							</div>
							<!--//form-group-->
							<div class="text-center">
								<button type="submit"
									class="btn app-btn-primary w-100 theme-btn mx-auto">Log
									In</button>
							</div>
						</form>

						<div class="auth-option text-center pt-5">
							No Account? Sign up <a class="text-link" href="signup.jsp">here</a>.
						</div>
					</div>
					<!--//auth-form-container-->

				</div>
				<!--//auth-body-->

			</div>
			<!--//flex-column-->
		</div>
		<!--//auth-main-col-->


	</div>
	<!--//row-->


</body>
</html>

