<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="common/header.jsp" />
<style>

width: 100%;
  height: 100vh;
  background: url("../assets/fruitbuy.jpg") top center;
  background-size: cover;
  position: relative;
  padding: 0;

</style>
<title>Fruit List</title>
</head>
<body>

	<c:import url="common/nav.jsp" />

	<!-- Page content-->
	<section class="container px-4 px-lg-5 my-5" id="py-5">
	
		<div class="section-title">
			<h2>Today</h2>
			<p>Fruit and Vegetables List</p>
		</div>
		<div class="container mt-5">
			<table id="fruitTable" class="table table-striped"
				style="width: 100%">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Quantity</th>
						<th>Date</th>
						<th>Price per Each(Kyats)</th>
						<th>Supplier</th>
						<c:if test="${user.role == 'admin' }">
							<th>Action</th>
						</c:if>


					</tr>
				</thead>
				<tbody>
					<c:forEach var="fruit" items="${fruitList }">

						<c:url var="editLink" value="fruit">
							<c:param name="mode" value="LOAD"></c:param>
							<c:param name="id" value="${fruit.id}"></c:param>
						</c:url>

						<c:url var="deleteLink" value="fruit">
							<c:param name="mode" value="DELETE"></c:param>
							<c:param name="id" value="${fruit.id}"></c:param>
						</c:url>

						<tr>
							<td>${fruit.id}</td>
							<td>${fruit.name}</td>
							<td>${fruit.qty}</td>
							<td>${fruit.date}</td>
							<td>${fruit.price}</td>
							<td>${fruit.cm}</td>

							<c:if test="${user.role == 'admin' }">
								<td><a href="${editLink }" class="btn btn-primary">Edit</a>
									<a href="${deleteLink }" class="btn btn-danger"
									onclick="return confirm('Are you sure to delete?');">Delete</a>
								</td>
							</c:if>


						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Quantity</th>
						<th>Date</th>
						<th>Price per Each(Kyats)</th>
						<th>Supplier</th>
						<c:if test="${user.role == 'admin' }">
							<th>Action</th>
						</c:if>
					</tr>
				</tfoot>
			</table>
		</div>
		
	</section>


	<c:import url="common/footer.jsp" />


	<script>
		$(document).ready(function() {
			$('#fruitTable').DataTable();
		});
	</script>

</body>

</html>