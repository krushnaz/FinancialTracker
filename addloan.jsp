<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.financialtracker.util.*"%>
<%@ page import="com.financialtracker.dao.*"%>
<%@ page import="com.financialtracker.entities.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Financial Tracker-Add Loan</title>

<!-- Meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta name="description"
	content="Portal - Bootstrap 5 Admin Dashboard Template For Developers">
<meta name="author" content="Xiaoying Riley at 3rd Wave Media">
<link rel="shortcut icon" href="assets/images/Rupee Logo.png">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<!-- FontAwesome JS-->
<script defer src="assets/plugins/fontawesome/js/all.min.js"></script>

<!-- App CSS -->
<link id="theme-style" rel="stylesheet" href="assets/css/portal.css">
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous"> -->
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script> -->
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
		<div class="d-flex justify-content-center mt-5">
			<button type="button" class="btn btn-warning text-light"
				data-bs-toggle="modal" data-bs-target="#exampleModal">Add
				Loan</button>
		</div>

		<!-- Button trigger modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-1" id="exampleModalLabel">Enter
							Loan</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<form action="AddLoanServlet" method="POST">
						<div class="modal-body">
							<div class="d-flex justify-content-between">
								<label>Date</label> <input type="date" id="dateid" name="date"
									placeholder="Enter date">
							</div>
							<!-- <div class="d-flex justify-content-between mt-3">
							<label>Loan Name</label> <input type="text" name="debtName"
								placeholder="Enter Loan Name">
						</div> -->
							<div class="d-flex justify-content-between mt-3">
								<label>Amount</label> <input type="number" name="amount"
									placeholder="Currency Rupee only">
							</div>
							<div class="d-flex justify-content-between mt-3">
								<label>Interest Rate</label> <input type="number"
									name="interestRate" placeholder="Enter Interest Rate">
							</div>
							<div class="d-flex justify-content-between mt-3">
								<label for="category">Category</label> <select id="category"
									name="category">
									<%
									CategoryDao catdao = new CategoryDao();
									List<Category> categories = catdao.getAllLoanCategories();

									for (Category category : categories) {
									%>
									<option value="<%=category.getCategoryID()%>"><%=category.getCategoryName()%></option>
									<%
									}
									%>
								</select>
							</div>
							<div class="d-flex justify-content-between mt-3">
								<label>Payment Schedule</label> <input type="date"
									name="paymentschedule">
							</div>
							<div class="d-flex justify-content-between mt-3">
								<label>Creditor</label> <input type="text" name="creditor"
									placeholder="Enter Creditor Name">
							</div>
							<div class="d-flex justify-content-between mt-3">
								<label>Payment Mode</label> <select name="incomeMode">
									<option>Select payment mode</option>
									<option value="Online">Online</option>
									<option value="Cash">Cash</option>
								</select>
							</div>
							<div class="d-flex justify-content-between mt-3">
								<label>Description</label>
								<textarea id="w3review" name="description" rows="3" cols="25"></textarea>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-success">Save</button>
						</div>
					</form>
				</div>

			</div>
		</div>

		<!-- Searchbox -->
		<div class="input-group mb-2 d-flex justify-content-center mt-5">
			<input type="text" class="rounded p-1 w-25" id="search"
				placeholder="Search..." aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default">
		</div>

		<div class="tab-pane fade show active mt-5 ms-3 me-3" id="orders-all"
			role="tabpanel" aria-labelledby="orders-all-tab">
			<div class="app-card app-card-orders-table shadow-sm mb-5">
				<div class="app-card-body">
					<div class="table-responsive">
						<table class="table app-table-hover mb-0 text-left">
							<thead>
								<tr>
									<th class="cell">Date</th>
									<th class="cell">Amount</th>
									<th class="cell">Category</th>
									<th class="cell">Mode of method</th>
									<th class="cell">Creditor Name</th>
									<th class="cell">Status</th>
									<th class="cell">Description</th>
									<th class="cell">Edit</th>
									<th class="cell">Delete</th>
								</tr>
							</thead>
							<tbody>
								<%
								/* HttpSession session1 = request.getSession();
								Integer userID = (Integer) session1.getAttribute("UserID"); */
								
								LoanDao loanDao = new LoanDao(ConnectionProvider.getConnection()); // Instantiate the LoanDao
								List<Loan> loanList = loanDao.getAllLoans(userID); // Fetch loan data from the database

								if (loanList != null && !loanList.isEmpty()) {
									for (Loan loan : loanList) {
								%>
								<tr>
									<td class="cell"><%=loan.getDate()%></td>
									<td class="cell"><%=loan.getAmount()%></td>
									<td class="cell"><%=loan.getLoanCategoryName()%></td>
									<td class="cell"><%=loan.getPaymentMode()%></td>
									<td class="cell"><%=loan.getCreditor()%></td>
									<td class="cell"><span class="badge bg-warning ">Loan</span></td>
									<td class="cell"><span class="truncate"><%=loan.getDescription()%></span></td>
									<td class="cell"><button class="btn-sm app-btn-secondary"
											data-bs-toggle="modal"
											data-bs-target="#exampleModal2<%=loan.getDebtID()%>">Edit</button>
									</td>
									<td class="cell"><button class="btn-sm app-btn-secondary"
											data-bs-toggle="modal"
											data-bs-target="#exampleModal1<%=loan.getDebtID()%>">Delete
										</button></td>
								</tr>
								<%
								}
								} else {
								%>
								<tr>
									<td class="cell" colspan="8">No loan data available</td>
								</tr>
								<%
								}
								%>
							</tbody>

						</table>
					</div>
					<!--//table-responsive-->

				</div>
				<!--//app-card-body-->
			</div>
			<!--//app-card-->

		</div>
		<!--//tab-pane-->

		<!-- Modal for delete -->

		<!-- Modal -->
		<%
		for (Loan loan : loanList) {
		%>
		<div class="modal fade" id="exampleModal1<%=loan.getDebtID()%>"
			tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-sm modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-body">
						<h5>Want to delete this EMI record?</h5>
					</div>
					<div class="modal-footer">
						<button type="button"
							class="btn btn-secondary justify-content-evenly"
							data-bs-dismiss="modal">Cancel</button>
						<form id="deleteForm<%=loan.getDebtID()%>" method="post"
							action="DeleteLoanServlet">
							<input type="hidden" name="loanID" value="<%=loan.getDebtID()%>">
							<button type="submit" class="btn btn-danger">Delete</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<%
		}
		%>

		<!----------------------Edit Modal----------------------------------------------->
		<%
		for (Loan existingLoan : loanList) {
		%>
		<div class="modal fade" id="exampleModal2<%=existingLoan.getDebtID()%>" tabindex="-1"
			aria-labelledby="exampleModal2<%=existingLoan.getDebtID()%>" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-1" id="exampleModalLabel2<%=existingLoan.getDebtID()%>">Edit
							Loan</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<form action="EditLoanServlet" method="POST">
						<!-- Include a hidden input field for the loan ID so that the servlet can identify the loan to be edited -->
						<input type="hidden" name="debtId"
							value="<%=existingLoan.getDebtID()%>">
						<div class="modal-body">
							<!-- Date input -->
							<div class="d-flex justify-content-between">
								<label>Date</label> <input type="date" id="dateid" name="date"
									value="<%=existingLoan.getDate()%>">
							</div>
							<!-- Amount input -->
							<div class="d-flex justify-content-between mt-3">
								<label>Amount</label> <input type="number" name="amount"
									value="<%=existingLoan.getAmount()%>"
									placeholder="Currency Rupee only">
							</div>
							<!-- Interest Rate input -->
							<div class="d-flex justify-content-between mt-3">
								<label>Interest Rate</label> <input type="number"
									name="interestRate" value="<%=existingLoan.getInterestRate()%>"
									placeholder="Enter Interest Rate">
							</div>
							<!-- Category selection -->
							<div class="d-flex justify-content-between mt-3">
								<label for="category">Category</label> <select id="category"
									name="category">
									<%
									CategoryDao dao1 = new CategoryDao();
									List<Category> categories1 = dao1.getAllLoanCategories();
									for (Category category : categories1) {
									%>
									<option value="<%=category.getCategoryID()%>"
										<%if (category.getCategoryID() == existingLoan.getDebtCategoryID()) {%>
										selected <%}%>><%=category.getCategoryName()%></option>
									<%
									}
									%>
								</select>
							</div>
							<!-- Payment Schedule input -->
							<div class="d-flex justify-content-between mt-3">
								<label>Payment Schedule</label> <input type="date"
									name="paymentschedule"
									value="<%=existingLoan.getPaymentSchedule()%>">
							</div>
							<!-- Creditor input -->
							<div class="d-flex justify-content-between mt-3">
								<label>Creditor</label> <input type="text" name="creditor"
									value="<%=existingLoan.getCreditor()%>"
									placeholder="Enter Creditor Name">
							</div>
							<!-- Payment Mode selection -->
							<div class="d-flex justify-content-between mt-3">
								<label>Payment Mode</label> <select name="incomeMode">
									<option>Select payment mode</option>
									<option value="Online"
										<%if ("Online".equals(existingLoan.getPaymentMode())) {%>
										selected <%}%>>Online</option>
									<option value="Cash"
										<%if ("Cash".equals(existingLoan.getPaymentMode())) {%>
										selected <%}%>>Cash</option>
								</select>
							</div>
							<!-- Description textarea -->
							<div class="d-flex justify-content-between mt-3">
								<label>Description</label>
								<textarea id="w3review" name="description" rows="3" cols="25"><%=existingLoan.getDescription()%></textarea>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-success">Save</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<%
		}
		%>



		<!-- Message for no result found -->
		<div id="message"
			style="display: none; text-align: center; margin-bottom: 2%; font-size: 16px;">No
			match found &#128546;</div>

		<footer class="app-footer">
			<div class="container text-center py-3">
				<!--/* This template is free as long as you keep the footer attribution link. If you'd like to use the template without the attribution link, you can buy the commercial license via our website: themes.3rdwavemedia.com Thank you for your support. :) */-->
				<small class="copyright">2023 Financial Tracker. All Rights
					Reserved</small>

			</div>
		</footer>
		<!--//app-footer-->

	</div>
	<!--//app-wrapper-->


	<!-- Javascript -->
	<script src="assets/plugins/popper.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>

	<!-- Page Specific JS -->
	<script src="assets/js/app.js"></script>
	<script type="text/javascript">
		var searchInput = document.getElementById("search");
		var tableRows = document.getElementsByTagName("tr");
		var messageDiv = document.getElementById("message");

		searchInput.addEventListener("keyup", function() {
			var searchString = this.value.toLowerCase();
			var matchFound = false;
			for (var i = 1; i < tableRows.length; i++) {
				var rowData = tableRows[i].textContent.toLowerCase();
				if (rowData.includes(searchString)) {
					tableRows[i].style.display = "";
					matchFound = true;
				} else {
					tableRows[i].style.display = "none";
					tableRows[i].style.backgroundColor = "";
				}
			}
			if (matchFound) {
				messageDiv.style.display = "none";
			} else {
				messageDiv.style.display = "";
			}
		});
	</script>


</body>
</html>

