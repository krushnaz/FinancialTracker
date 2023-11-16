<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.financialtracker.util.*"%>
<%@ page import="com.financialtracker.dao.*"%>
<%@ page import="com.financialtracker.entities.*"%>
<%@ page import="java.util.*"%>
<%!
    // Define a function to get the CSS class based on the status
    String getStatusColor(String status) {
        if ("Income".equals(status)) {
            return "bg-success"; // Green color
        } else if ("Expense".equals(status)) {
            return "bg-danger"; // Red color
        } else if ("Investment".equals(status)) {
            return "bg-secondary"; // Blue color
        } else if ("Loan".equals(status)) {
            return "bg-warning"; // Yellow color
        } else if ("SIP".equals(status)) {
            return "bg-info"; // Some other color
        } else if ("EMI".equals(status)) {
            return "bg-dark"; // Another color
        } else {
            return "bg-light"; // Default color for an unknown status
        }
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Financial Tracker-Transaction History</title>

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
</head>

<body class="app">

<!-- Rest of your code follows -->
<!-- ... -->

	<%@ include file="dashboard.jsp"%>
	<div class="app-wrapper">

		<div class="app-content pt-3 p-md-3 p-lg-4">
			<div class="container-xl">

				<div class="row g-3 mb-4 align-items-center justify-content-between">
					<div class="col-auto">
						<h1 class="app-page-title mb-0">Transaction History</h1>
					</div>
					<div class="col-auto">
						<div class="page-utilities">
							<!-- Searchbox -->
							<div class="input-group mb-2">
								<input type="text" class="rounded p-1 w-25!important"
									id="search" placeholder="Search..."
									aria-label="Sizing example input"
									aria-describedby="inputGroup-sizing-default">
							</div>
						</div>
						<!--//table-utilities-->
					</div>
					<!--//col-auto-->
				</div>
				<!--//row-->


				<nav id="orders-table-tab"
					class="orders-table-tab app-nav-tabs nav shadow-sm flex-column flex-sm-row mb-4">
					<a class="flex-sm-fill text-sm-center nav-link active"
						id="orders-all-tab" data-bs-toggle="tab" href="#orders-all"
						role="tab" aria-controls="orders-all" aria-selected="true">All</a>
					<a class="flex-sm-fill text-sm-center nav-link"
						id="orders-paid-tab" data-bs-toggle="tab" href="#orders-paid"
						role="tab" aria-controls="orders-paid" aria-selected="false">Income</a>
					<a class="flex-sm-fill text-sm-center nav-link"
						id="orders-pending-tab" data-bs-toggle="tab"
						href="#orders-pending" role="tab" aria-controls="orders-pending"
						aria-selected="false">Expenses</a> <a
						class="flex-sm-fill text-sm-center nav-link"
						id="orders-cancelled-tab" data-bs-toggle="tab"
						href="#orders-cancelled" role="tab"
						aria-controls="orders-cancelled" aria-selected="false">Investment</a>
					<a class="flex-sm-fill text-sm-center nav-link"
						id="orders-loan-tab" data-bs-toggle="tab" href="#orders-loan"
						role="tab" aria-controls="orders-loan" aria-selected="false">Loan</a>
					<a class="flex-sm-fill text-sm-center nav-link" id="orders-SIP-tab"
						data-bs-toggle="tab" href="#orders-SIP" role="tab"
						aria-controls="orders-SIP" aria-selected="false">SIP</a> <a
						class="flex-sm-fill text-sm-center nav-link" id="orders-EMI-tab"
						data-bs-toggle="tab" href="#orders-EMI" role="tab"
						aria-controls="orders-EMI" aria-selected="false">EMI</a>
				</nav>


				<div class="tab-content" id="orders-table-tab-content">
					<div class="tab-pane fade show active" id="orders-all"
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
											</tr>
										</thead>

										
										<tbody>
										

											<%
											/* HttpSession session1 = request.getSession();
											Integer userID = (Integer) session1.getAttribute("UserID"); */
            		
											TransactionDao Tdao = new TransactionDao(ConnectionProvider.getConnection());

											List<Transaction> allTransactions = new ArrayList<>();
											allTransactions.addAll(Tdao.getAllIncome(userID));
											allTransactions.addAll(Tdao.getAllExpenses(userID));
											allTransactions.addAll(Tdao.getAllInvestments(userID));
											allTransactions.addAll(Tdao.getAllSIP(userID));
											allTransactions.addAll(Tdao.getAllEMI(userID));
											allTransactions.addAll(Tdao.getAllDebts(userID));

											if (allTransactions != null && !allTransactions.isEmpty()) {
												for (Transaction transaction : allTransactions) {
											%>
											
											<tr>
												<td class="cell"><%=transaction.getDate()%></td>
												<td class="cell"><%=transaction.getAmount()%></td>
												<td class="cell"><%=transaction.getCategory()%></td>
												<td class="cell"><%=transaction.getMethod()%></td>
												<td class="cell"><span
													class="badge <%=getStatusColor(transaction.getStatus())%>">
														<%=transaction.getStatus()%>
												</span></td>
												<td class="cell"><%=transaction.getDescription()%></td>
											</tr>
											<%
											}
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

					<div class="tab-pane fade" id="orders-paid" role="tabpanel"
						aria-labelledby="orders-paid-tab">
						<div class="app-card app-card-orders-table mb-5">
							<div class="app-card-body">
								<div class="table-responsive">

									<table class="table mb-0 text-left">
										<thead>
											<tr>
												<th class="cell">Date</th>
												<th class="cell">Amount</th>
												<th class="cell">Category</th>
												<th class="cell">Mode of method</th>
												<th class="cell">Status</th>
												<th class="cell">Description</th>
											</tr>
										</thead>
										<tbody>
											<%
											IncomeDao incomeDao = new IncomeDao(ConnectionProvider.getConnection()); // Instantiate the IncomeDao
											List<Income> incomeList = incomeDao.getAllIncome(userID); // Fetch income data from the database

											if (incomeList != null && !incomeList.isEmpty()) {
												for (Income income : incomeList) {
											%>
											<tr>
												<td class="cell"><%=income.getDate()%></td>
												<td class="cell"><%=income.getAmount()%></td>
												<td class="cell"><%=income.getCategoryName()%></td>
												<td class="cell"><%=income.getSource()%></td>
												<td class="cell"><span class="badge bg-success">Income</span></td>
												<td class="cell"><span class="truncate"><%=income.getDescription()%></span></td>
												<%-- 	<td class="cell"><button
														class="btn-sm app-btn-secondary" data-bs-toggle="modal"
														data-bs-target="#exampleModal">Edit</button></td>
												<td class="cell"><button
														class="btn-sm app-btn-secondary" data-bs-toggle="modal"
														data-bs-target="#exampleModal1<%=income.getIncomeID()%>">Delete
													</button></td> --%>
											</tr>
											<%
											}
											} else {
											%>
											<tr>
												<td class="cell" colspan="8">No income data available</td>
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

					<div class="tab-pane fade" id="orders-pending" role="tabpanel"
						aria-labelledby="orders-pending-tab">
						<div class="app-card app-card-orders-table mb-5">
							<div class="app-card-body">
								<div class="table-responsive">
									<table class="table mb-0 text-left">
										<thead>
											<tr>
												<th class="cell">Date</th>
												<th class="cell">Amount</th>
												<th class="cell">Category</th>
												<th class="cell">Mode of method</th>
												<th class="cell">Status</th>
												<th class="cell">Description</th>
											</tr>
										</thead>
										<tbody>
											<%
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
												<%-- <td class="cell"><button
														class="btn-sm app-btn-secondary" data-bs-toggle="modal"
														data-bs-target="#exampleModal">Edit</button></td>
												<td class="cell"><button
														class="btn-sm app-btn-secondary" data-bs-toggle="modal"
														data-bs-target="#exampleModal1<%=expense.getExpenseID()%>">Delete
													</button></td> --%>
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
					<div class="tab-pane fade" id="orders-cancelled" role="tabpanel"
						aria-labelledby="orders-cancelled-tab">
						<div class="app-card app-card-orders-table mb-5">
							<div class="app-card-body">
								<div class="table-responsive">
									<table class="table mb-0 text-left">
										<thead>
											<tr>
												<th class="cell">Date</th>
												<th class="cell">Amount</th>
												<th class="cell">Category</th>
												<th class="cell">Mode of method</th>
												<th class="cell">Status</th>
												<th class="cell">Description</th>
											</tr>
										</thead>
										<tbody>
											<%
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
											
											</tr>
											<%
											}
											} else {
											%>
											<tr>
												<td class="cell" colspan="8">No investment data
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

					<!--//New-->
					<div class="tab-pane fade" id="orders-loan" role="tabpanel"
						aria-labelledby="orders-loan-tab">
						<div class="app-card app-card-orders-table mb-5">
							<div class="app-card-body">
								<div class="table-responsive">
									<table class="table mb-0 text-left">
										<thead>
											<tr>
												<th class="cell">Date</th>
												<th class="cell">Amount</th>
												<th class="cell">Category</th>
												<th class="cell">Mode of method</th>
												<th class="cell">Creaditor</th>
												<th class="cell">Status</th>
												<th class="cell">Description</th>
											</tr>
										</thead>
										<tbody>
											<%
											LoanDao loanDao = new LoanDao(ConnectionProvider.getConnection()); // Instantiate the LoanDao
											List<Loan> loanList = loanDao.getAllLoans(userID); // Fetch loan data from the database

											if (loanList != null && !loanList.isEmpty()) {
												for (Loan loan : loanList) {
											%>
											<tr>
												<td class="cell"><%=loan.getPaymentSchedule()%></td>
												<td class="cell"><%=loan.getAmount()%></td>
												<td class="cell"><%=loan.getLoanCategoryName()%></td>
												<td class="cell"><%=loan.getPaymentMode()%></td>
												<td class="cell"><%=loan.getCreditor()%></td>
												<td class="cell"><span class="badge bg-warning">Loan</span></td>
												<td class="cell"><span class="truncate"><%=loan.getDescription()%></span></td>
												
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
					<div class="tab-pane fade" id="orders-SIP" role="tabpanel"
						aria-labelledby="orders-SIP-tab">
						<div class="app-card app-card-orders-table mb-5">
							<div class="app-card-body">
								<div class="table-responsive">
									<table class="table mb-0 text-left">
										<thead>
											<tr>
												<th class="cell">Start Date</th>
												<th class="cell">Amount</th>
												<th class="cell">Category</th>
												<th class="cell">Fund Name</th>
												<th class="cell">Frequency</th>
												<th class="cell">Mode of method</th>
												<th class="cell">Status</th>
												<th class="cell">Description</th>
											</tr>
										</thead>
										<tbody>
											<%
											SIPDao sipDao = new SIPDao(ConnectionProvider.getConnection()); // Instantiate the SIPDao
											List<SIP> sipList = sipDao.getAllSIP(userID); // Fetch SIP data from the database

											if (sipList != null && !sipList.isEmpty()) {
												for (SIP sip : sipList) {
											%>
											<tr>
												<td class="cell"><%=sip.getStartDate()%></td>
												<td class="cell"><%=sip.getAmount()%></td>
												<td class="cell"><%=sip.getSIPcategoryName()%></td>
												<td class="cell"><%=sip.getFundName()%></td>
												<td class="cell"><%=sip.getFrequency()%></td>
												<td class="cell"><%=sip.getPaymentMode()%></td>
												<td class="cell"><span class="badge bg-info">SIP</span></td>
												<td class="cell"><span class="truncate"><%=sip.getDescription()%></span></td>
												<%-- <td class="cell"><button class="btn-sm app-btn-secondary"
											data-bs-toggle="modal" data-bs-target="#exampleModal">Edit</button></td>
									<td class="cell"><button class="btn-sm app-btn-secondary"
											data-bs-toggle="modal"
											data-bs-target="#exampleModal1<%=sip.getSIPID()%>">Delete
										</button></td> --%>
												<%
												}
												} else {
												%>
											
											<tr>
												<td class="cell" colspan="10">No SIP data available</td>
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
					<div class="tab-pane fade" id="orders-EMI" role="tabpanel"
						aria-labelledby="orders-EMI-tab">
						<div class="app-card app-card-orders-table mb-5">
							<div class="app-card-body">
								<div class="table-responsive">
									<table class="table mb-0 text-left">
										<thead>
											<tr>
												<th class="cell">Start Date</th>
												<th class="cell">Amount</th>
												<th class="cell">Category</th>
												<th class="cell">Mode of method</th>
												<th class="cell">Status</th>
												<th class="cell">Description</th>
											</tr>
										</thead>
										<tbody>
											<%
											EMIDao emiDao = new EMIDao(ConnectionProvider.getConnection());
											EMI emi1 = new EMI();
											List<EMI> emiList = emiDao.getAllEMI(userID);
											if (emiList != null && !emiList.isEmpty()) {
												for (EMI emi : emiList) {
											%>
											<tr>
												<td class="cell"><%=emi.getPaymentDate()%></td>
												<td class="cell"><%=emi.getAmount()%></td>
												<td class="cell"><%=emi.getEMIcategoryName()%></td>
												<td class="cell"><%=emi.getPaymentMethod()%></td>
												<td class="cell"><span class="badge bg-dark">EMI</span></td>
												<td class="cell"><span class="truncate"><%=emi.getNotes()%></span></td>
												<%-- <td class="cell"><button class="btn-sm app-btn-secondary"
											data-bs-toggle="modal" data-bs-target="#exampleModal">Edit</button></td>
									<td class="cell"><button class="btn-sm app-btn-secondary"
											data-bs-toggle="modal"
											data-bs-target="#exampleModal1<%=emi.getEMIID()%>">Delete
										</button> --%>
											</tr>
											<%
											}
											} else {
											%>
											<tr>
												<td class="cell" colspan="8">No EMI data available</td>
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
					<!--/New end/-->


				</div>
				<!--//tab-content-->
			</div>
			<!--//container-fluid-->
		</div>
		<!--//app-content-->

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

