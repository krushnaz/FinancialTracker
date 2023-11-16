<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.financialtracker.entities.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Finance Tracker-Reset Password</title>

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

<body class="app app-reset-password p-0">
	<div class="row g-0 app-auth-wrapper">
		<div class="col-12 auth-main-col text-center p-5 mt-5">
			<div class="d-flex flex-column align-content-end">
				<div class="app-auth-body mx-auto shadow  p-4 border">
					<div class="app-auth-branding mb-4">
						<a class="app-logo" href="index.jsp"><img
							class="logo-icon me-2" src="assets/images/Rupee Logo.png"
							alt="logo"></a>
					</div>
					<h2 class="auth-heading text-center mb-4">
						Password Reset<span class="text-success"> Financial Tracker</span>
					</h2>

					<div class="auth-intro mb-4 text-center">Enter your email
						address below. We'll send you email OTP after validating you can
						easily create a new password.</div>

					<div class="auth-form-container text-left">
						<%
						Message successMsg = (Message) session.getAttribute("msg");
						if (successMsg != null) {
						%>
						<div class="alert <%=successMsg.getCSSType()%>" role="alert">
							<%=successMsg.getContentType()%>
						</div>
						<%
							session.removeAttribute("msg"); // Remove the message from the session
							}
						%>


						<form action="resetPassword" method="POST"
							class="auth-form resetpass-form" id="reset-form">
							<div class="email mb-3">
								<label class="sr-only" for="reg-email">Your Email</label> <input
									id="reg-email" name="reg-email" type="email"
									class="form-control login-email" placeholder="Your Email"
									required>
							</div>
							<!--//form-group-->
							<div class="otp mb-3" style="display: none;">
								<label class="sr-only" for="reg-otp">OTP</label> <input
									id="reg-otp" name="reg-otp" type="text"
									class="form-control login-email" placeholder="OTP" required
									autocomplete="one-time-code">
							</div>

							<div class="new-password mb-3" style="display: none;">
								<label class="sr-only" for="new-password">New Password</label> <input
									id="new-password" name="new-password" type="password"
									class="form-control login-password" placeholder="New Password"
									oninput="validatePassword()" required
									autocomplete="new-password">
							</div>

							<div class="confirm-password mb-3" style="display: none;">
								<label class="sr-only" for="confirm-password">Confirm
									Password</label> <input id="confirm-password" name="confirm-password"
									type="password" class="form-control login-password"
									placeholder="Confirm Password" oninput="validatePassword()"
									required autocomplete="new-password">
							</div>


							<!-- <button type="button"
								class="btn app-btn-primary btn-block theme-btn mx-auto"
								id="resetPasswordButton" style="display: none"
								onclick="resetPassword()">Reset Password</button>
 -->
							<input type="submit"
								class="btn app-btn-primary btn-block theme-btn mx-auto"
								id="resetPasswordButton" style="display: none"
								value="Reset Password">
							<!--//form-group-->
							<div class="verify-otp mb-3" style="display: none;">
								<label class="sr-only" for="verify-otp">Verify OTP</label>
								<!-- input
									id="verify-otp" name="verify-otp" type="text"
									class="form-control login-email" placeholder="Verify OTP"
									required> -->
							</div>
							<!--//form-group-->
							<div class="text-center">
								<button type="button"
									class="btn app-btn-primary btn-block theme-btn mx-auto"
									id="getOTPButton" onclick="sendOTP()">Get OTP</button>
								<button type="button"
									class="btn app-btn-primary btn-block theme-btn mx-auto"
									id="verifyOTPButton" style="display: none;"
									onclick="verifyOTP()">Verify OTP</button>
							</div>


						</form>

						<div class="auth-option text-center pt-5">
							<a class="app-link" href="login.jsp">Log in</a> <span
								class="px-2">|</span> <a class="app-link" href="login.jsp">Sign
								up</a>
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

	<script>
		function sendOTP() {
			const email = document.getElementById('reg-email').value.trim();
			if (email === '') {
				alert('Please enter your email.');
				return;
			}

			// Send an AJAX request to the servlet to send OTP
			var xhr = new XMLHttpRequest();
			xhr.open("POST", "sendOTP"); // Replace with your OTP sending servlet URL
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4) {
					if (xhr.status === 200) {
						var response = xhr.responseText;
						if (response === "success") {
							// OTP sent successfully
							alert('OTP sent to your email.');
							document.querySelector('.otp').style.display = 'block';
							document.querySelector('.verify-otp').style.display = 'block';
							document.getElementById('getOTPButton').style.display = 'none';
							document.getElementById('verifyOTPButton').style.display = 'block';
						} else {
							// OTP sending failed
							alert('OTP sending failed. Please try again later.');
						}
					} else {
						// Request to the server failed
						alert('Failed to send OTP. Please try again later.');
					}
				}
			};
			xhr.send("reg-email=" + email);
		}

		function verifyOTP() {
			var enteredOTP = document.getElementById("reg-otp").value;

			// Send an AJAX request to the server to verify the OTP
			var xhr = new XMLHttpRequest();
			xhr.open("POST", "verifyOTP"); // Replace with your OTP verification servlet URL
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4 && xhr.status === 200) {
					var response = xhr.responseText;
					if (response === "success") {
						// OTP verification succeeded
						alert('OTP verified');

						// Hide the "OTP" field and the "Verify OTP" button
						document.querySelector('.otp').style.display = 'none';
						document.querySelector('.verify-otp').style.display = 'none';

						// Show the "New Password" and "Confirm Password" fields
						document.querySelector('.new-password').style.display = 'block';
						document.querySelector('.confirm-password').style.display = 'block';

						// Show the "Reset Password" button and hide the "Verify OTP" button
						document.getElementById('resetPasswordButton').style.display = 'block';
						document.getElementById('verifyOTPButton').style.display = 'none';
					} else {
						alert('OTP verification failed. Please enter a valid OTP.');

						// Show the "OTP" field and the "Verify OTP" button
						document.querySelector('.otp').style.display = 'block';
						document.querySelector('.verify-otp').style.display = 'block';

						// Hide the "New Password" and "Confirm Password" fields
						document.querySelector('.new-password').style.display = 'none';
						document.querySelector('.confirm-password').style.display = 'none';

						// Hide the "Reset Password" button and show the "Verify OTP" button
						document.getElementById('resetPasswordButton').style.display = 'none';
						document.getElementById('verifyOTPButton').style.display = 'block';
					}
				}
			};
			xhr.send("enteredOTP=" + enteredOTP);
		}

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
			const email = document.getElementById("reg-email").value;
			console.log(password);
			console.log(email);
			const xhr = new XMLHttpRequest();
			xhr.open("POST", "resetPassword"); // Replace with your ResetPasswordServlet URL
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
			const requestBody = `password=${password}&email=${email}`;
			xhr.send(requestBody);
		}
	</script>


</body>
</html>

