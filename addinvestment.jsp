<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.financialtracker.util.*"%>
<%@ page import="com.financialtracker.dao.*"%>
<%@ page import="com.financialtracker.entities.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Financial Tracker-Add Investment</title>

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
			<button type="button" class="btn bg-secondary text-light"
				data-bs-toggle="modal" data-bs-target="#exampleModal">Add
				Investment</button>
		</div>

		<div class="modal fade" id="exampleModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-1" id="exampleModalLabel">Enter
							Investment</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<form action="AddInvestmentServlet" method="POST">
						<div class="modal-body">
							<div class="container">
								<div class="row">
									<div class="col-md-6">
										<!-- Left side content -->
										<!-- Date input -->
										<div class="form-group">
											<label for="dateid"> Date</label> <input type="date"
												class="form-control" id="dateid" name="date"
												placeholder="Enter date">
										</div>
										<!-- Amount input -->
										<div class="form-group mt-3">
											<label for="amount">Invested Amount</label> <input
												type="number" class="form-control" name="amount"
												placeholder="Currency Rupee only">
										</div>
										<!-- Description textarea -->
										<div class="form-group mt-3">
											<label for="w3review" class="form-label">Description</label>
											<textarea class="form-control form-control-sm" id="w3review"
												name="w3review" rows="3" cols="25"></textarea>
										</div>
									</div>
									<div class="col-md-6">
										<!-- Right side content -->
										<!-- Category selection -->
										<div class="d-flex justify-content-between mt-3">
											<label for="category">Category</label> <select id="category"
												name="category">
												<%
												CategoryDao catdao = new CategoryDao();
												List<Category> categories = catdao.getAllInvestmentCategories();

												for (Category category : categories) {
												%>
												<option value="<%=category.getCategoryID()%>"><%=category.getCategoryName()%></option>
												<%
												}
												%>
											</select>
										</div>
										<!-- Payment mode selection -->
										<div class="form-group mt-3">
											<label for="incomeMode">Payment Mode</label> <select
												class="form-select" name="incomeMode">
												<option value="Not Define">Select payment mode</option>
												<option value="Online">Online</option>
												<option value="Cash">Cash</option>
											</select>
										</div>
										<!-- Purchase Date input -->
										<div class="form-group mt-3">
											<label for="PurchaseDate" class="form-label">Purchase
												Date</label> <input type="date" class="form-control form-control-sm"
												id="dateid" name="PurchaseDate" placeholder="Enter date">
										</div>
									</div>
								</div>
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
													
								InvestmentDao investmentDao = new InvestmentDao(ConnectionProvider.getConnection()); // Instantiate the InvestmentDao
								List<Investment> investmentList = investmentDao.getAllInvestments(userID); // Fetch investment data from the database

								if (investmentList != null && !investmentList.isEmpty()) {
									for (Investment investment : investmentList) {
								%>
								<tr>
									<td class="cell"><%=investment.getDate()%></td>
									
									<td class="cell"><%=investment.getAmount()%></td>
									<td class="cell"><%=investment.getInvestmentCategoryName()%></td>
									<td class="cell"><%=investment.getPaymentMode()%></td>
									<td class="cell"><span class="badge bg-secondary">Investment</span></td>
									<td class="cell"><span class="truncate"><%=investment.getDescription()%></span></td>
									<td class="cell"><button class="btn-sm app-btn-secondary"
											data-bs-toggle="modal" data-bs-target="#exampleModal2<%=investment.getInvestmentID()%>">Edit</button></td>
									<td class="cell"><button class="btn-sm app-btn-secondary"
											data-bs-toggle="modal"
											data-bs-target="#exampleModal1<%=investment.getInvestmentID()%>">Delete
										</button></td>
								</tr>
								<%
								}
								} else {
								%>
								<tr>
									<td class="cell" Investmcolspan="8">No investment data
										available</td>
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


		<!-- Modal -->
		<%
		for (Investment invest : investmentList) {
		%>
		<div class="modal fade"
			id="exampleModal1<%=invest.getInvestmentID()%>" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-sm modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-body">
						<h5>Want to delete this EMI record?</h5>
					</div>
					<div class="modal-footer">
						<button type="button"
							class="btn btn-secondary justify-content-evenly"
							data-bs-dismiss="modal">Cancel</button>
						<form id="deleteForm<%=invest.getInvestmentID()%>" method="post"
							action="DeleteInvestmentServlet">
							<input type="hidden" name="investmentID"
								value="<%=invest.getInvestmentID()%>">
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
		for (Investment invest : investmentList) {
		%>
		<div class="modal fade" id="exampleModal2<%=invest.getInvestmentID()%>"
			tabindex="-1"
			aria-labelledby="exampleModalLabel2<%=invest.getInvestmentID()%>"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-1"
							id="exampleModalLabel2<%=invest.getInvestmentID()%>">Edit
							Investment</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<form action="EditInvestmentServlet" method="POST">
						<input type="hidden" name="investmentID"
							value="<%=invest.getInvestmentID()%>">
						<div class="modal-body">
							<div class="container">
								<div class="row">
									<div class="col-md-6">
										<!-- Left side content -->
										<!-- Date input -->
										<div class="form-group">
											<label for="dateid"> Date</label> <input type="date"
												class="form-control" id="dateid" name="date"
												placeholder="Enter date" value="<%=invest.getDate()%>">
										</div>
										<!-- Amount input -->
										<div class="form-group mt-3">
											<label for="amount">Invested Amount</label> <input
												type="number" class="form-control" name="amount"
												placeholder="Currency Rupee only"
												value="<%=invest.getAmount()%>">
										</div>
										<!-- Description textarea -->
										<div class="form-group mt-3">
											<label for="description" class="form-label">Description</label>
											<textarea class="form-control form-control-sm"
												id="description" name="description" rows="3" cols="25"><%=invest.getDescription()%></textarea>
										</div>
									</div>
									<div class="col-md-6">
										<!-- Right side content -->
										<!-- Category selection -->
										<div class="d-flex justify-content-between mt-3">
											<label for="category">Category</label> <select id="category"
												name="category">
												<%
												CategoryDao dao1 = new CategoryDao();
												List<Category> categories1 = dao1.getAllInvestmentCategories();
												for (Category category : categories1) {
												%>
												<option value="<%=category.getCategoryID()%>"
													<%=category.getCategoryID() == invest.getInvestmentCategoryID() ? "selected" : ""%>><%=category.getCategoryName()%></option>
												<%
												}
												%>
											</select>
										</div>
										<!-- Payment mode selection -->
										<div class="form-group mt-3">
											<label for="incomeMode">Payment Mode</label> <select
												class="form-select" name="incomeMode">
												<option value="Not Define" selected>Select payment
													mode</option>
												<option value="Online"
													<%=invest.getPaymentMode().equals("Online") ? "selected" : ""%>>Online</option>
												<option value="Cash"
													<%=invest.getPaymentMode().equals("Cash") ? "selected" : ""%>>Cash</option>
											</select>
										</div>
										<!-- Purchase Date input -->
										<div class="form-group mt-3">
											<label for="PurchaseDate" class="form-label">Purchase
												Date</label> <input type="date" class="form-control form-control-sm"
												id="PurchaseDate" name="purchaseDate"
												placeholder="Enter date"
												value="<%=invest.getPurchaseDate()%>">
										</div>
									</div>
								</div>
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

