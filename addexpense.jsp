<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.financialtracker.util.*"%>
<%@ page import="com.financialtracker.dao.*"%>
<%@ page import="com.financialtracker.entities.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Financial Tracker - Add Expense</title>
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
	crossorigin="anonymous">
	
</script>
<!-- FontAwesome JS-->
<script defer src="assets/plugins/fontawesome/js/all.min.js"></script>
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
		<div class="d-flex justify-content-center mt-5">
			<button type="button" class="btn btn-danger text-light" data-bs-toggle="modal"
				data-bs-target="#exampleModal">Add Expense</button>
		</div>
		<!-- Button trigger modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-1" id="exampleModalLabel">Enter
							Expense</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<form action="AddExpenseServlet" method="post">
						<div class="modal-body">
							<div class="d-flex justify-content-between">
								<label>Date</label> <input type="date" id="dateid" name="date"
									placeholder="Enter date">
							</div>
							<div class="d-flex justify-content-between mt-3">
								<label for="amount">Amount</label> <input type="text"
									id="amount" name="amount" placeholder="Currency Rupee only">
							</div>
							<div class="d-flex justify-content-between mt-3">
								<label for="category">Category</label> <select id="category"
									name="category">
									<%
									CategoryDao catdao = new CategoryDao();
									List<Category> categories = catdao.getAllExpenseCategories();
									for (Category category : categories) {
									%>
									<option value="<%=category.getCategoryID()%>"><%=category.getCategoryName()%></option>
									<%
									}
									%>
								</select>
							</div>
							<div class="d-flex justify-content-between mt-3">
								<label for="incomeMode">Payment Mode</label> <select
									id="incomeMode" name="incomeMode">
									<option value="Not Define" disabled selected>Select payment mode</option>
									<option value="Online">Online</option>
									<option value="Cash">Cash</option>
								</select>
							</div>

							<div class="d-flex justify-content-between mt-3">
								<label for="description">Description</label>
								<textarea id="description" name="description" rows="3" cols="25"></textarea>
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
			<!-- <span class="input-group-text" id="inputGroup-sizing-default">Default</span> -->
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
									<th class="cell">Status</th>
									<th class="cell">Description</th>
									<th class="cell">Edit</th>
									<th class="cell">Delete</th>
								</tr>
							</thead>
							<tbody>
								<%
							/* 	HttpSession session1 = request.getSession();
								Integer userID = (Integer) session1.getAttribute("UserID"); */
										
								ExpenseDao expenseDao = new ExpenseDao(ConnectionProvider.getConnection()); // Instantiate the ExpenseDao
								List<Expense> expenseList = expenseDao.getAllExpenses(userID); // Fetch expense data from the database

								if (expenseList != null && !expenseList.isEmpty()) {
									for (Expense expense : expenseList) {
								%>
								<tr>
									<td class="cell"><%=expense.getDate()%></td>
									<td class="cell"><%=expense.getAmount()%></td>
									<td class="cell"><%=expense.getExpenseCategoryName()%></td>
									<td class="cell"><%=expense.getPaymentMode()%></td>
									<td class="cell"><span class="badge bg-danger">Expense</span></td>
									<td class="cell"><span class="truncate"><%=expense.getDescription()%></span></td>
									<td class="cell"><button class="btn-sm app-btn-secondary"
											data-bs-toggle="modal" data-bs-target="#exampleModal2<%=expense.getExpenseID()%>">Edit</button></td>
									<td class="cell"><button class="btn-sm app-btn-secondary"
											data-bs-toggle="modal"
											data-bs-target="#exampleModal1<%=expense.getExpenseID()%>">Delete
										</button></td>
								</tr>
								<%
								}
								} else {
								%>
								<tr>
									<td class="cell" colspan="8">No expense data available</td>
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

		<%
		for (Expense expense : expenseList) {
		%>
		<div class="modal fade" id="exampleModal1<%=expense.getExpenseID()%>"
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
						<form id="deleteForm<%=expense.getExpenseID()%>" method="post"
							action="DeleteExpenseServlet">
							<input type="hidden" name="expenseID"
								value="<%=expense.getExpenseID()%>">
							<button type="submit" class="btn btn-danger">Delete</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<%
		}
		%>
		
		
		
		<!-- 		Edit Modal -->

		<%
		for (Expense expense : expenseList) {
		%>
		<div class="modal fade" id="exampleModal2<%=expense.getExpenseID()%>"
			tabindex="-1"
			aria-labelledby="exampleModalLabel2<%=expense.getExpenseID()%>"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-1"
							id="exampleModalLabel2<%=expense.getExpenseID()%>">Edit
							Income</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<form action="EditExpenseServlet" method="POST">
						<input type="hidden" id="expenseId" name="expenseId"
							value="<%=expense.getExpenseID()%>">
						<div class="modal-body">
							<div class="d-flex justify-content-between">
								<label>Date</label> <input type="date" id="dateid" name="date"
									value="<%=expense.getDate()%>">
							</div>
							<div class="d-flex justify-content-between mt-3">
								<label for="amount">Amount</label> <input type="text"
									id="amount" name="amount" value="<%=expense.getAmount()%>">
							</div>
							<div class="d-flex justify-content-between mt-3">
								<label for="category">Category</label> <select id="category"
									name="category">
									<%
									CategoryDao dao1 = new CategoryDao();
									List<Category> categories1 = dao1.getAllExpenseCategories();
									for (Category category : categories1) {
									%>
									<option value="<%=category.getCategoryID()%>"
										<%=(category.getCategoryID() == expense.getExpenseCategoryID()) ? "selected" : ""%>><%=category.getCategoryName()%></option>
									<%
									}
									%>
								</select>
							</div>
							<div class="d-flex justify-content-between mt-3">
								<label for="incomeMode">Payment Mode</label> <select
									id="incomeMode" name="incomeMode">
									<option value="Online"
										<%=("Online".equals(expense.getPaymentMode())) ? "selected" : ""%>>Online</option>
									<option value="Cash"
										<%=("Cash".equals(expense.getPaymentMode())) ? "selected" : ""%>>Cash</option>
								</select>
							</div>
							<div class="d-flex justify-content-between mt-3">
								<label for="description">Description</label>
								<textarea id="description" name="description" rows="3" cols="25"><%=expense.getDescription()%></textarea>
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
