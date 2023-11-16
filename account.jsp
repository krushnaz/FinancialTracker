<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.financialtracker.util.*"%>
<%@ page import="com.financialtracker.dao.*"%>
<%@ page import="com.financialtracker.entities.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Financial Tracker-Account</title>

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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<!-- App CSS -->
<link id="theme-style" rel="stylesheet" href="assets/css/portal.css">

</head>

<body class="app">
	<%@ include file="dashboard.jsp"%>
	<div class="app-wrapper">
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
		<div class="app-content pt-3 p-md-3 p-lg-4">
			<div class="container-xl">

				<h1 class="app-page-title">My Account</h1>
				<div class="row gy-4">

					<!-- My Account -->

					<div class="col-12 col-lg-6">
						<div
							class="app-card app-card-account shadow-sm d-flex flex-column align-items-start">
							<div class="app-card-header p-3 border-bottom-0">
								<div class="row align-items-center gx-3">
									<div class="col-auto">
										<div class="app-icon-holder">
											<svg width="1em" height="1em" viewBox="0 0 16 16"
												class="bi bi-person" fill="currentColor"
												xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd"
													d="M10 5a2 2 0 1 1-4 0 2 2 0 0 1 4 0zM8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm6 5c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z" />
</svg>
										</div>
										<!--//icon-holder-->

									</div>
									<!--//col-->
									<div class="col-auto">
										<h4 class="app-card-title">Profile</h4>
									</div>
									<!--//col-->
								</div>
								<!--//row-->
							</div>
							<!--//app-card-header-->
							<div class="app-card-body px-4 w-100">
								<div class="item border-bottom py-3">
									<div class="row justify-content-between align-items-center">
										<div class="col-auto">
											<div class="item-label mb-2 ">
												<strong>Profile Picture</strong>
											</div>
											<div class="item-data text-center">
												<img class="rounded-circle overflow-hidden mx-auto"
													style="width: 150px; height: 150px;"
													src="assets/images/profiles/<%=userFromDao.getProfilePicture()%>"
													alt="user profile">
											</div>

										</div>
										<!--//col-->
									</div>
									<!--//row-->
								</div>
								<!--//item-->
								<div class="item border-bottom py-3">
									<div class="row justify-content-between align-items-center">
										<div class="col-auto">
											<div class="item-label">
												<strong>First Name</strong>
											</div>
											<div class="item-data"><%=userFromDao.getFirstName()%></div>
										</div>
										<!--//col-->

									</div>
									<!--//row-->
								</div>
								<!--//item-->
								<div class="item border-bottom py-3">
									<div class="row justify-content-between align-items-center">
										<div class="col-auto">
											<div class="item-label">
												<strong>Last Name</strong>
											</div>
											<div class="item-data"><%=userFromDao.getLastName()%></div>
										</div>
										<!--//col-->

									</div>
									<!--//row-->
								</div>
								<!--//item-->
								<div class="item border-bottom py-3">
									<div class="row justify-content-between align-items-center">
										<div class="col-auto">
											<div class="item-label">
												<strong>Email</strong>
											</div>
											<div class="item-data"><%=userFromDao.getEmail()%></div>
										</div>
										<!--//col-->

									</div>
									<!--//row-->
								</div>
								<!--//item-->
								<div class="item border-bottom py-3">
									<div class="row justify-content-between align-items-center">
										<div class="col-auto">
											<div class="item-label">
												<strong>Phone</strong>
											</div>
											<div class="item-data"><%=userFromDao.getPhoneNumber()%></div>
										</div>
										<!--//col-->

									</div>
									<!--//row-->
								</div>
								<!--//item-->

								<div class="item border-bottom py-3">
									<div class="row justify-content-between align-items-center">
										<div class="col-auto">
											<div class="item-label">
												<strong>Gender</strong>
											</div>
											<div class="item-data"><%=userFromDao.getGender()%></div>
										</div>
										<!--//col-->

									</div>
									<!--//row-->
								</div>
								<!--//item-->
							</div>
							<!--//app-card-body-->
							<div class="app-card-footer p-4 mt-auto">
								<button type="button" class="btn app-btn-secondary"
									data-bs-toggle="modal" data-bs-target="#exampleModal2">
									Update Profile</button>
							</div>

							<!-- Modal for manage profile -->

							<div class="modal fade" id="exampleModal2" tabindex="-1"
								aria-labelledby="exampleModalLabel2" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered">
									<form action="EditAccountServlet" method="POST"
										enctype="multipart/form-data">
										<div class="modal-content">
											<div class="modal-header">
												<h1 class="modal-title fs-1" id="exampleModalLabel2">Update
													Profile</h1>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<div class="item-data">
													<img class="rounded-circle overflow-hidden"
														style="width: 50px; height: 50px;"
														src="assets/images/profiles/<%=userFromDao.getProfilePicture()%>"
														alt="user profile"> <input type="file"
														value="<%=userFromDao.getProfilePicture()%>" id="imgInput"
														name="img" style="margin-left: 10px;">
												</div>


												<div class="d-flex justify-content-between mt-3">
													<label>First Name</label> <input type="text" name="f-name"
														placeholder="Enter First Name"
														value="<%=userFromDao.getFirstName()%>">
												</div>
												<div class="d-flex justify-content-between mt-3">
													<label>Last Name</label> <input type="text" name="l-name"
														placeholder="Enter Last Name"
														value="<%=userFromDao.getLastName()%>">
												</div>
												<div class="d-flex justify-content-between mt-3">
													<label>Phone</label> <input type="text" name="phone"
														placeholder="Enter Phone Number"
														value="<%=userFromDao.getPhoneNumber()%>">
												</div>
												<div class="d-flex justify-content-between mt-3">
													<label>Gender</label> <select name="gender"
														value="<%=userFromDao.getGender()%>">
														<option value="Male">Male</option>
														<option value="Female">Female</option>
														<option value="Other">Other</option>
													</select>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Close</button>
												<button type="submit" class="btn btn-success">Save</button>
											</div>
										</div>
									</form>
								</div>
							</div>


							<!--//app-card-footer-->

						</div>
						<!--//app-card-->
					</div>
					<!--//col-->

					<!-- Preferences -->



					<!-- Security -->

					<div class="col-12 col-lg-6">
						<div
							class="app-card app-card-account h-auto shadow-sm  flex-column align-items-start">
							<div class="app-card-header p-3 border-bottom-0">
								<div class="row align-items-center gx-3">
									<div class="col-auto">
										<div class="app-icon-holder">
											<svg width="1em" height="1em" viewBox="0 0 16 16"
												class="bi bi-shield-check" fill="currentColor"
												xmlns="http://www.w3.org/2000/svg">
  												<path fill-rule="evenodd"
													d="M5.443 1.991a60.17 60.17 0 0 0-2.725.802.454.454 0 0 0-.315.366C1.87 7.056 3.1 9.9 4.567 11.773c.736.94 1.533 1.636 2.197 2.093.333.228.626.394.857.5.116.053.21.089.282.11A.73.73 0 0 0 8 14.5c.007-.001.038-.005.097-.023.072-.022.166-.058.282-.111.23-.106.525-.272.857-.5a10.197 10.197 0 0 0 2.197-2.093C12.9 9.9 14.13 7.056 13.597 3.159a.454.454 0 0 0-.315-.366c-.626-.2-1.682-.526-2.725-.802C9.491 1.71 8.51 1.5 8 1.5c-.51 0-1.49.21-2.557.491zm-.256-.966C6.23.749 7.337.5 8 .5c.662 0 1.77.249 2.813.525a61.09 61.09 0 0 1 2.772.815c.528.168.926.623 1.003 1.184.573 4.197-.756 7.307-2.367 9.365a11.191 11.191 0 0 1-2.418 2.3 6.942 6.942 0 0 1-1.007.586c-.27.124-.558.225-.796.225s-.526-.101-.796-.225a6.908 6.908 0 0 1-1.007-.586 11.192 11.192 0 0 1-2.417-2.3C2.167 10.331.839 7.221 1.412 3.024A1.454 1.454 0 0 1 2.415 1.84a61.11 61.11 0 0 1 2.772-.815z" />
  												<path fill-rule="evenodd"
													d="M10.854 6.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 8.793l2.646-2.647a.5.5 0 0 1 .708 0z" />
</svg>
										</div>
										<!--//icon-holder-->

									</div>
									<!--//col-->
									<div class="col-auto">
										<h4 class="app-card-title">Security</h4>
									</div>
									<!--//col-->
								</div>
								<!--//row-->
							</div>
							<!--//app-card-header-->
							<div class="app-card-body px-4 w-100">

								<div class="item border-bottom py-3">
									<div class="row justify-content-between align-items-center">
										<div class="col-auto">
											<div class="item-label">
												<strong>Password</strong>
											</div>
											<div class="item-data"><h1>.....</h1></div>
										</div>
										<!--//col-->
										<!--//col-->
									</div>
									<!--//row-->
								</div>
								<!--//item-->

							</div>
							<!--//app-card-body-->

							<div class="app-card-footer p-4">
								<button type="button" class="btn app-btn-secondary"
									data-bs-toggle="modal" data-bs-target="#exampleModal">
									Change Password</button>
							</div>

							<!-- Button trigger modal for verify password -->
							<div class="modal fade" id="exampleModal" tabindex="-1"
								aria-labelledby="verifyPasswordLabel" aria-hidden="true">
								<form id="verifyPasswordForm" action="VerifyPasswordServlet"
									method="POST">
									<div class="modal-dialog modal-dialog-centered">
										<div class="modal-content">
											<div class="modal-header">
												<h1 class="modal-title fs-1" id="verifyPasswordLabel">Verify
													Password</h1>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<div class="d-flex justify-content-between">
													<label>Previous Password</label> <input type="password"
														id="password" name="password" placeholder="Enter Password">


												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Close</button>
												<!-- <button type="button" class="btn btn-success"
												onclick="verifyPassword()">Verify</button> -->
												<button type="submit" class="btn btn-success">Verify</button>
											</div>
										</div>
									</div>
								</form>
							</div>


							<!-- Modal for changing password -->
							<div class="modal fade" id="exampleModal1" tabindex="-1"
								aria-labelledby="exampleModalLabel1" aria-hidden="true">
								<form action="ChangePasswordServlet" method="POST"
									class="auth-form resetpass-form" id="reset-form">
									<div class="modal-dialog modal-dialog-centered">
										<div class="modal-content">
											<div class="modal-header">
												<h1 class="modal-title fs-1" id="exampleModalLabel1">Change
													Password</h1>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<div class="d-flex justify-content-between">
													<label>Enter Password</label> <input type="password"
														id="new-password" name="password"
														placeholder="Enter Password" oninput="validatePassword()"
														required>
												</div>
												<div class="d-flex justify-content-between mt-3">
													<label>Confirm Password</label> <input type="password"
														id="confirm-password" name="confirm-password"
														placeholder="Confirm Password"
														oninput="validatePassword()" required>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Close</button>
												<input type="submit" class="btn btn-success"
													id="resetPasswordButton" style="display: none"
													value="Change Password">
											</div>
										</div>

									</div>
								</form>
							</div>

							<!--//app-card-footer-->

						</div>
						<!--//app-card-->
					</div>

				</div>
				<!--//row-->

			</div>
			<!--//container-fluid-->
		</div>
		<!--//app-content-->

		<footer class="app-footer">
			<div class="container text-center py-3">
				<!--/* This template is free as long as you keep the footer attribution link. If you'd like to use the template without the attribution link, you can buy the commercial license via our website: themes.3rdwavemedia.com Thank you for your support. :) */-->
				<small class="copyright">2023 Financial Tracker. All Rights
					Reserved</small>

			</div>
		</footer>
		<!--//app-footer-->
		<!--//app-footer-->

	</div>
	<!--//app-wrapper-->


	<!-- Javascript -->
	<!-- Include jQuery from a CDN -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<!-- Include Bootstrap's JavaScript and Popper.js from CDNs -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.5.0/dist/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.10.2/umd/popper.min.js"></script>

	<!-- Include your Page Specific JS -->
	<script src="assets/js/app.js"></script>


	<!-- Page Specific JS -->
	<script src="assets/js/app.js"></script>


	<script>
	<!-- Add an ID to the form for the Verify Password modal -->
		function showVerifyPasswordModal() {
			$('#exampleModal').modal('show');
		}

		function handleVerifyPasswordResponse(response) {
			if (response === "success") {
				$('#exampleModal').modal('hide'); // Close the "Verify Password" modal
				alert('Password verification succeeded.');
				// Open the "Change Password" modal
				$('#exampleModal1').modal('show');
			} else {
				alert('Password verification failed. Please enter the correct password.');
			}
		}

		// Handle form submission for password verification
		$('#verifyPasswordForm').submit(function(e) {
			e.preventDefault(); // Prevent the form from being submitted normally
			var formData = $(this).serialize(); // Serialize the form data
			$.ajax({
				type : 'POST',
				url : 'VerifyPasswordServlet',
				data : formData,
				success : function(response) {
					handleVerifyPasswordResponse(response);
				}
			});
		});

		window.onload = function() {
			console.log("hello");
		};

		function validatePassword() {
			var newPassword = document.getElementById("new-password").value;
			var confirmPassword = document.getElementById("confirm-password").value;
			var resetPasswordButton = document
					.getElementById("resetPasswordButton");
			var passwordMatchStatus = document
					.getElementById("password-match-status");

			if (newPassword !== confirmPassword) {
				if (passwordMatchStatus) {
					passwordMatchStatus.innerHTML = "Passwords do not match";
				}
				if (resetPasswordButton) {
					resetPasswordButton.style.display = 'none'; // Hide the Reset Password button
				}
			} else {
				if (passwordMatchStatus) {
					passwordMatchStatus.innerHTML = ""; // Clear the message if passwords match
				}
				if (resetPasswordButton) {
					resetPasswordButton.style.display = 'block'; // Show the Reset Password button
				}
			}
		}

		function resetPassword() {
			// Retrieve the new password and email from the input fields
			const password = document.getElementById("new-password").value;
			
			console.log(password);
			console.log(email);
			const xhr = new XMLHttpRequest();
			xhr.open("POST", "ChangePasswordServlet"); // Replace with your ResetPasswordServlet URL
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");

			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4) {
					if (xhr.status === 200) {
						const response = xhr.responseText;
						/* console.log(response);  */
						if (response === "success") {
							alert("Password reset successful.");
							// You can redirect the user to a login page here
						} else {
							alert("Password reset failed. Please try again.");
						}
					} else {
						alert("Error: Password reset request failed.");
					}
				}
			};

			// Send the new password and email to the servlet
			const requestBody = `password=${password}`;
			xhr.send(requestBody);
		}
	</script>


</body>
</html>