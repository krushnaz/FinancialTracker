<%@page import="com.mysql.cj.xdevapi.DbDoc"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.financialtracker.util.*"%>
<%@ page import="com.financialtracker.dao.*"%>
<%@ page import="com.financialtracker.entities.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Financial Tracker - Add Income</title>

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
	src="https://cdn.jsdelivr.net/npm bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>



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
			<button type="button" class="btn btn-success text-light"
				data-bs-toggle="modal" data-bs-target="#exampleModal">Add
				Income</button>
		</div>

		<!-- Button trigger modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">

						<h1 class="modal-title fs-1" id="exampleModalLabel">Enter
							Income</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<form action="AddIncomeServlet" method="POST">
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
									CategoryDao dao = new CategoryDao();
									List<Category> categories = dao.getAllIncomeCategories();

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
									<td class="cell"><button type="button"
											class="btn-sm app-btn-secondary" data-bs-toggle="modal"
											data-bs-target="#exampleModal2<%=income.getIncomeID()%>">Edit</button>
									</td>
									<td class="cell"><button class="btn-sm app-btn-secondary"
											data-bs-toggle="modal"
											data-bs-target="#exampleModal1<%=income.getIncomeID()%>">Delete
										</button></td>
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
				</div>
			</div>
		</div>

		<!-- Modal for delete -->
		<%
		for (Income income : incomeList) {
		%>
		<div class="modal fade" id="exampleModal1<%=income.getIncomeID()%>"
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
						<form id="deleteForm<%=income.getIncomeID()%>" method="post"
							action="DeleteIncomeServlet">
							<input type="hidden" name="IncomeID"
								value="<%=income.getIncomeID()%>">
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
		for (Income income : incomeList) {
		%>
		<div class="modal fade" id="exampleModal2<%=income.getIncomeID()%>"
			tabindex="-1"
			aria-labelledby="exampleModalLabel2<%=income.getIncomeID()%>"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-1"
							id="exampleModalLabel2<%=income.getIncomeID()%>">Edit Income</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<form action="EditIncomeServlet" method="POST">
						<input type="hidden" id="incomeId" name="incomeId"
							value="<%=income.getIncomeID()%>">
						<div class="modal-body">
							<div class="d-flex justify-content-between">
								<label>Date</label> <input type="date" id="dateid" name="date"
									value="<%=income.getDate()%>">
							</div>
							<div class="d-flex justify-content-between mt-3">
								<label for="amount">Amount</label> <input type="text"
									id="amount" name="amount" value="<%=income.getAmount()%>">
							</div>
							<div class="d-flex justify-content-between mt-3">
								<label for="category">Category</label> <select id="category"
									name="category">
									<%
									CategoryDao dao1 = new CategoryDao();
									List<Category> categories1 = dao1.getAllIncomeCategories();
									for (Category category : categories1) {
									%>
									<option value="<%=category.getCategoryID()%>"
										<%=(category.getCategoryID() == income.getCategoryID()) ? "selected" : ""%>><%=category.getCategoryName()%></option>
									<%
									}
									%>
								</select>
							</div>
							<div class="d-flex justify-content-between mt-3">
								<label for="incomeMode">Payment Mode</label> <select
									id="incomeMode" name="incomeMode">
									<option value="Online"
										<%=("Online".equals(income.getSource())) ? "selected" : ""%>>Online</option>
									<option value="Cash"
										<%=("Cash".equals(income.getSource())) ? "selected" : ""%>>Cash</option>
								</select>
							</div>
							<div class="d-flex justify-content-between mt-3">
								<label for="description">Description</label>
								<textarea id="description" name="description" rows="3" cols="25"><%=income.getDescription()%></textarea>
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
				<small class="copyright">2023 Financial Tracker. All Rights
					Reserved</small>
			</div>
		</footer>
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
				messageDiv.style
				display = "none";
			} else {
				messageDiv.style.display = "";
			}
		});
	</script>
	
	<script>
    document.getElementById("dateid").addEventListener("input", function (e) {
        var inputDate = e.target.value;
        if (/^\d{1,2}-\d{1,2}-\d{4}$/.test(inputDate)) {
            // Date is in 'dd-MM-yyyy' format
            e.target.setCustomValidity("");
        } else {
            // Format the date to 'dd-MM-yyyy'
            var parts = inputDate.split("-");
            if (parts.length === 3) {
                var day = parts[0].trim();
                var month = parts[1].trim();
                var year = parts[2].trim();
                e.target.value = day + "-" + month + "-" + year;
                e.target.setCustomValidity("");
            } else {
                e.target.setCustomValidity("Invalid date format. Use 'dd-MM-yyyy'.");
            }
        }
    });
</script>
	
	
</body>
</html>
