
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Finance Tracker-Sign up</title>

<!-- Meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta name="description"
	content="Portal - Bootstrap 5 Admin Dashboard Template For Developers">
<meta name="author" content="Xiaoying Riley at 3rd Wave Media">
<link rel="shortcut icon" href="assets/img/Rupee Logo.png">

<!-- FontAwesome JS-->
<script defer src="assets/plugins/fontawesome/js/all.min.js"></script>
<!-- Add Bootstrap CSS and JavaScript -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

<!-- App CSS -->
<link id="theme-style" rel="stylesheet" href="assets/css/portal.css">

</head>


<body class="app app-signup p-0">
	<div class="row g-0 app-auth-wrapper">
		<div class="col-12  auth-main-col text-center p-5 mt-1">
			<div class="d-flex flex-column align-content-end">
				<div class="app-auth-body mx-auto shadow  p-4 border">
					<div class="app-auth-branding mb-4">
						<a class="app-logo" href="index.html"><img
							class="logo-icon me-2" src="assets/img/Rupee Logo.png" alt="logo"></a>
					</div>
					<h2 class="auth-heading text-center mb-4">
						Sign up <span class="text-success"> Finacial Tracker</span>
					</h2>

					<div class="auth-form-container text-start mx-auto">
						<form action="RegisterServlet" method="POST"
							enctype="multipart/form-data" class="auth-form auth-signup-form">
							<div class="d-flex gap-2">
								<div class="email mb-3">
									<label class="sr-only" for="signup-email">First Name</label> <input
										id="signup-name" name="firstName" type="text"
										class="form-control signup-name" placeholder="First name"
										required="required">
								</div>
								<div class="email mb-3">
									<label class="sr-only" for="signup-email">Last Name</label> <input
										id="signup-name" name="lastName" type="text"
										class="form-control signup-name" placeholder="Last name"
										required="required">
								</div>
							</div>
							<div class="contact mb-3">
								<label class="sr-only" for="signup-phone">Phone Number</label> <input
									id="signup-phone" name="phoneNumber" type="number"
									class="form-control signup-phone" placeholder="Phone Number"
									required="required">
							</div>
							<div class="email mb-3">
								<label class="sr-only" for="signup-email">Your Email</label> <input
									id="signup-email" name="email" type="email"
									class="form-control signup-email" placeholder="Email"
									required="required">
							</div>

							<div class="password mb-3">
								<label class="sr-only" for="signup-password">Password</label> <input
									id="signup-password" name="password" type="password"
									class="form-control signup-password"
									placeholder="Create a password" required="required"
									onkeyup="validatePassword()">
							</div>
							<div class="password mb-3">
								<label class="sr-only" for="signup-confirm-password">Password</label>
								<input id="signup-confirm-password"
									name="signup-confirm-password" type="password"
									class="form-control signup-password"
									placeholder="Confirm a password" required="required"
									onkeyup="validatePassword()">
								<p id="password-match-status" style="color: red;"></p>
							</div>
							<div class="profile-image mb-3 d-inline-flex gap-2">
								<label class="auth-option mt-2 ms-1" for="signup-profile-image">Image</label>
								<input id="signup-profile-image" name="profilePicture"
									type="file" class="form-control signup-password"
									required="required">
							</div>
							<div class="gender mb-3 d-flex justify-content-between">
								<!-- <label class="sr-only" for="signup-gender">Gender</label> -->
								<div>
									<input class="form-check-input" type="radio" name="gender"
										id="inlineRadio1" value="Male"> <label
										class="form-check-label" for="gender">Male</label>
								</div>
								<div>
									<input class="form-check-input" type="radio" name="gender"
										id="inlineRadio1" value="Female"> <label
										class="form-check-label" for="gender">Female</label>
								</div>
								<div>
									<input class="form-check-input" type="radio" name="gender"
										id="inlineRadio1" value="Other"> <label
										class="form-check-label" for="gender">Other</label>
								</div>
								<!-- <input id="signup-password" name="signup-password" type="password" class="form-control signup-password" placeholder="Create a password" required="required"> -->
							</div>

							<div class="text-center">
								<!-- <button type="submit"
									class="btn app-btn-primary w-100 theme-btn mx-auto">Sign
									Up</button> -->
								<!-- Sign Up Button (Initially Hidden) -->

								<button type="button" id="signUp"
									class="btn app-btn-primary w-100 theme-btn mx-auto"
									data-bs-toggle="modal" data-bs-target="#signupModal"
									onclick="sendOTP()">Sign Up</button>


							</div>

							<!-- Modal -->
							<div class="modal fade" id="signupModal" tabindex="-1"
								aria-labelledby="signupModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="signupModalLabel">OTP has
												been sent to your email.</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											<!-- OTP Verification Section -->
											<div class="email mb-3">
												<label class="sr-only" for="enteredOTP">Enter OTP</label> <input
													id="enteredOTP" name="enteredOTP" type="number"
													class="form-control signup-email" placeholder="Enter OTP"
													required="required">
											</div>

											<button type="button" class="btn app-btn-primary center"
												onclick="verifyOTP()">Verify OTP</button>
											<div class="mb-3"></div>
											<!-- "Complete Sign Up" button placed here -->
											<button type="submit"
												class="btn app-btn-primary w-100 theme-btn mx-auto d-none"
												id="final-signup">Complete Sign Up</button>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>

				</div>
				<!--//auth-form-->
				<div class="auth-option text-center pt-3">
					Already have an account? <a class="text-link" href="login.jsp">Log
						in</a>
				</div>
			</div>
		</div>
	</div>


	<script>
		function validateFields() {
			var firstName = document.getElementById("signup-name").value;
			var lastName = document.getElementById("signup-lastname").value;
			var phoneNumber = document.getElementById("signup-phone").value;
			var email = document.getElementById("signup-email").value;
			var password = document.getElementById("signup-password").value;
			var confirmPassword = document
					.getElementById("signup-confirm-password").value;
			var signUpButton = document.getElementById("final-signup");

			// Check if any required field is empty
			if (firstName.trim() === "" || lastName.trim() === ""
					|| phoneNumber.trim() === "" || email.trim() === ""
					|| password.trim() === "" || confirmPassword.trim() === "") {
				signUpButton.style.display = "none"; // Hide the Sign Up button
			} else {
				signUpButton.style.display = "block"; // Show the Sign Up button
			}
		}

		function validatePassword() {
			var password = document.getElementById("signup-password").value;
			var confirmPassword = document
					.getElementById("signup-confirm-password").value;
			var passwordMatchStatus = document
					.getElementById("password-match-status");
			var signUpButton = document.getElementById("signUp");

			if (password !== confirmPassword) {
				passwordMatchStatus.innerHTML = "Passwords do not match";
				signUpButton.disabled = true; // Disable the Sign Up button
			} else {
				passwordMatchStatus.innerHTML = ""; // Clear the message if passwords match
				signUpButton.disabled = false; // Enable the Sign Up button
			}
		}

		function sendOTP() {
			var email = document.getElementById("signup-email").value;
			if (email) {
				// Send an AJAX request to the servlet to send OTP
				var xhr = new XMLHttpRequest();
				xhr.open("POST", "EmailSender");
				xhr.setRequestHeader("Content-Type",
						"application/x-www-form-urlencoded");
				xhr.onreadystatechange = function() {
					if (xhr.readyState === 4 && xhr.status === 200) {
						var otp = xhr.responseText;
						// Show OTP input field and Verify OTP button
						var otpInput = document.getElementById("enteredOTP");
						otpInput.style.display = "block"; // Make the input visible
						otpInput.removeAttribute("disabled"); // Enable the input
						otpInput.focus(); // Focus on the input
						document.getElementById("otp-sent-message").innerHTML = "OTP has been sent to your email.";
					}
				};
				xhr.send("email=" + email);
			}
		}

		function verifyOTP() {
			var enteredOTP = document.getElementById("enteredOTP").value;

			// Send an AJAX request to the server to verify the OTP
			var xhr = new XMLHttpRequest();
			xhr.open("POST", "VerifyEmailSenderOtp"); // Replace with your OTP verification servlet
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4 && xhr.status === 200) {
					var response = xhr.responseText;
					if (response === "success") {
						// OTP verification succeeded; show the "Submit" button
						document.getElementById("final-signup").classList
								.remove("d-none");

					} else {
						alert("OTP verification failed. Please try again.");
						// OTP verification failed; hide the "Submit" button
						document.getElementById("final-signup").classList
								.add("d-none");
					}
				}
			};
			xhr.send("enteredOTP=" + enteredOTP);
		}
	</script>






	<!--//row-->
</body>
</html>

