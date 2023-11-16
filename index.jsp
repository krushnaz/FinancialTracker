<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.financialtracker.util.*"%>
<%@ page import="com.financialtracker.dao.*"%>
<%@ page import="com.financialtracker.entities.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Finance Tracker-Dashboard</title>

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



		<%
		IncomeDao incomeDAO = new IncomeDao(ConnectionProvider.getConnection()); // Initialize your DAO with a connection
		List<Income> incomeList2 = incomeDAO.getAllIncomeAmount(userID);
		double totalIncome = 0;
		for (Income income : incomeList2) {
			totalIncome += income.getTotalAmount();
		}

		ExpenseDao expenseDao = new ExpenseDao(ConnectionProvider.getConnection()); // Initialize your DAO with a connection
		List<Expense> expenseList = expenseDao.getAllExpenseAmount(userID);
		double totalExpense = 0;
		for (Expense expense : expenseList) {
			totalExpense += expense.getTotalAmount();
		}

		InvestmentDao investDao = new InvestmentDao(ConnectionProvider.getConnection()); // Initialize your DAO with a connection
		List<Investment> investmentList = investDao.getAllInvestmentAmount(userID);
		double totalInvestment = 0;
		for (Investment invest : investmentList) {
			totalInvestment += invest.getTotalAmount();
		}

		LoanDao loanDao = new LoanDao(ConnectionProvider.getConnection()); // Initialize your DAO with a connection
		List<Loan> loanList = loanDao.getAllLoanAmount(userID);
		double totalLoan = 0;
		for (Loan loan : loanList) {
			totalLoan += loan.getTotalLoan();
		}
		
		SIPDao SIPDao = new SIPDao(ConnectionProvider.getConnection()); // Initialize your DAO with a connection
		List<SIP> sipList = SIPDao.getAllSIPAmount(userID);
		double totalSIP = 0;
		for (SIP sip : sipList) {
			totalSIP += sip.getTotalSIP();
		}
		
		EMIDao EMIDao = new EMIDao(ConnectionProvider.getConnection()); // Initialize your DAO with a connection
		List<EMI> emiList = EMIDao.getAllEMIAmount(userID);
		double totalEMI = 0;
		for (EMI emi : emiList) {
			totalEMI += emi.getTotalEMI();
		}

		double netIncome = totalIncome - (totalExpense + totalInvestment + totalSIP + totalEMI);

		request.setAttribute("netIncome", netIncome);
		%>

		<div class="app-content pt-3 p-md-3 p-lg-4">
			<div class="container-xl">

				<h1 class="app-page-title">Dashboard</h1>
			</div>
			<div class="row g-4 mb-4">
				<div class="col-6 col-lg-3">
					<div class="app-card app-card-stat shadow-sm h-90">
						<div class="app-card-body p-3 p-lg-4">
							<h4 class="stats-type mb-1 text-success">Income</h4>
							<div class="stats-figure"><%=netIncome%></div>
						</div>
						<!--//app-card-body-->
					</div>
					<!--//app-card-->
				</div>
				<!--//col-->

				<div class="col-6 col-lg-3">
					<div class="app-card app-card-stat shadow-sm h-90">
						<div class="app-card-body p-3 p-lg-4">
							<h4 class="stats-type mb-1 text-danger">Expenses</h4>
							<div class="stats-figure"><%=totalExpense%></div>
						</div>
						<!--//app-card-body-->
					</div>
					<!--//app-card-->
				</div>
				<!--//col-->
				<div class="col-6 col-lg-3">
					<div class="app-card app-card-stat shadow-sm h-90">
						<div class="app-card-body p-3 p-lg-4">
							<h4 class="stats-type mb-1">Investment</h4>
							<div class="stats-figure"><%=totalInvestment%></div>
						</div>
						<!--//app-card-body-->
					</div>
					<!--//app-card-->
				</div>
				<!--//col-->
				<div class="col-6 col-lg-3">
					<div class="app-card app-card-stat shadow-sm h-90">
						<div class="app-card-body p-3 p-lg-4">
							<h4 class="stats-type mb-1  text-warning">Loan</h4>
							<div class="stats-figure"><%=totalLoan%></div>
						</div>
						<!--//app-card-->
					</div>
					<!--//col-->
				</div>
				<!--//row-->

				<div class="row g-4 mb-4">
					<div class="col-12 col-lg-6">
						<div class="app-card app-card-stats-table h-100 shadow-sm">
							<div class="app-card-header p-3">
								<div class="row justify-content-between align-items-center">
									<div class="col-auto">
										<h4 class="app-card-title">Income History</h4>
									</div>
									<!--//col-->
									<div class="col-auto">
										<div class="card-header-action">
											<a href="addincome.jsp">Add Income</a>
										</div>
										<!--//card-header-actions-->
									</div>
									<!--//col-->
								</div>
								<!--//row-->
							</div>
							<!--//app-card-header-->
							<div class="app-card-body p-3 p-lg-4">
								<div class="table-responsive">
									<table class="table table-borderless mb-0">
										<thead>
											<tr>
												<th class="meta">Date</th>
												<th class="meta">Amount</th>
												<th class="meta">Category</th>
											</tr>
										</thead>
										<tbody>
											<%
											/* HttpSession session2 = request.getSession();
											Integer userID = (Integer) session2.getAttribute("UserID"); */
											
											IncomeDao incomeDao = new IncomeDao(ConnectionProvider.getConnection()); // Instantiate the IncomeDao
											List<Income> incomeList = incomeDao.getAllIncome(userID); // Fetch income data from the database

											if (incomeList != null && !incomeList.isEmpty()) {
												for (int i = 0; i < 5 && i < incomeList.size(); i++) {
													Income income = incomeList.get(i);
											%>
											<tr>
												<td><%=income.getDate()%></td>
												<td><%=income.getAmount()%></td>
												<td><%=income.getCategoryName()%></td>
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
					<!--//col-->
					<div class="col-12 col-lg-6">
						<div class="app-card app-card-stats-table h-100 shadow-sm">
							<div class="app-card-header p-3">
								<div class="row justify-content-between align-items-center">
									<div class="col-auto">
										<h4 class="app-card-title">Expense History</h4>
									</div>
									<!--//col-->
									<div class="col-auto">
										<div class="card-header-action">
											<a class="text-danger" href="addexpense.jsp">Add Expense</a>
										</div>
										<!--//card-header-actions-->
									</div>
									<!--//col-->
								</div>
								<!--//row-->
							</div>
							<!--//app-card-header-->
							<div class="app-card-body p-3 p-lg-4">
								<div class="table-responsive">
									<table class="table table-borderless mb-0">
										<thead>
											<tr>
												<th class="meta">Date</th>
												<th class="meta">Amount</th>
												<th class="meta">Category</th>
											</tr>
										</thead>
										<tbody>
											<%
											
							
											ExpenseDao expenseDao1 = new ExpenseDao(ConnectionProvider.getConnection()); // Instantiate the ExpenseDao
											List<Expense> expenseList1 = expenseDao.getAllExpenses(userID); // Fetch expense data from the database

											if (expenseList1 != null && !expenseList1.isEmpty()) {

												for (int i = 0; i < 5 && i < expenseList1.size(); i++) {
													Expense expense1 = expenseList1.get(i);
											%>
											<tr>
												<td><%=expense1.getDate()%></td>
												<td><%=expense1.getAmount()%></td>
												<td><%=expense1.getExpenseCategoryName()%></td>
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
					<!--//col-->
				</div>
				<!--//row-->
				<div class="app-wrapper">

					<div class="app-content pt-3 p-md-3 p-lg-4">
						<div class="container-xl">
							<div class="row g-4 mb-4">
								<div class="col-12 col-lg-6">
									<div class="app-card app-card-chart h-100 shadow-sm">
										<div class="app-card-header p-3 border-0">
											<h4 class="app-card-title text-center">Financial Health</h4>
										</div>
										<!--//app-card-header-->
										<div class="app-card-body p-4">
											<div class="chart-container">
												<canvas id="chart-doughnut"></canvas>
											</div>
										</div>
										<!--//app-card-body-->
									</div>
									<!--//app-card-->
								</div>
								<!--//col-->
							</div>
							<!--//row-->
						</div>
						<!--//container-fluid-->
					</div>
					<!--//app-content-->
				</div>
				<!--//app-wrapper-->
				<footer class="app-footer">
					<div class="container text-center py-3">
						<!--/* This template is free as long as you keep the footer attribution link. If you'd like to use the template without the attribution link, you can buy the commercial license via our website: themes.3rdwavemedia.com Thank you for your support. :) */-->
						<small class="copyright">2023 Financial Tracker. All
							Rights Reserved</small>
					</div>
				</footer>
				<!--//app-footer-->
				<!-- Javascript -->
				<script src="assets/plugins/popper.min.js"></script>
				<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>

				<!-- Charts JS -->
				<script src="assets/plugins/chart.js/chart.min.js"></script>
				<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
				<!-- Page Specific JS -->
				<script src="assets/js/app.js"></script>

				<script>
					// Get the total values
					var totalIncome =
				<%=totalIncome%>
					;
					var totalExpense =
				<%=totalExpense%>
					;
					var totalInvestment =
				<%=totalInvestment%>
					;
					var totalLoan =
				<%=totalLoan%>
					;

					// Calculate the remaining income
					var remainingIncome = totalIncome
							- (totalExpense + totalInvestment);

					// Get the canvas element
					var ctx = document.getElementById('chart-doughnut')
							.getContext('2d');

					// Create the doughnut chart
					var chart = new Chart(ctx, {
						type : 'doughnut',
						data : {
							labels : [ 'Remaining Income', 'Total Expenses',
									'Total Investment', 'Total Loan' ],
							datasets : [ {
								data : [ remainingIncome, totalExpense,
										totalInvestment, totalLoan ],
								backgroundColor : [ '#20c997', '#fd7e14',
										'#828d9f', '#ffc107' ],
							} ],
						},
						options : {
							responsive : true,
							maintainAspectRatio : false,
						},
					});
				</script>

				<script>
					// Get the net income and total expenses
					var netIncome =
				<%=netIncome%>
					;
					var totalExpense =
				<%=totalExpense%>
					;

					console.log('Net Income:', netIncome);
					console.log('Total Expense:', totalExpense);

					// ...
				</script>
</body>
</html>
