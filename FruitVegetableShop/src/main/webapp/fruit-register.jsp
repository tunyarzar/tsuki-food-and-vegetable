<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="common/header.jsp"/>
    <style>

width: 100%;
  height: 100vh;
  background: url("../assets/fruit-register.webp") top center;
  background-size: cover;
  position: relative;
  padding: 0;

</style>
	<title>Fruit Update Page</title>
</head>
<body>
<!-- Responsive navbar-->

	
	<div class="container">
    <form action="fruit" method="post">
        <h2 class="text-center">Fruit Update</h2>
        
        <input type="hidden" name="mode" value="CREATE">
        
        <div class="mb-3">
            <label for="name" class="form-label">*name</label>
            <input type="text" id="name" name="name" value="${fruit.name }" placeholder="Enter Fruit Name" class="form-control" required="required" autofocus>
        </div>
        
        <div class="mb-3">
            <label for="quantity" class="form-label">*Quantity</label>
            <input type="number" id="qty" name="qty" value="${fruit.qty }" placeholder="Enter Quantity" class="form-control" required="required" autofocus>
        </div>
        
        
        <div class="mb-3">
            <label for="date" class="form-label">*Date</label>
            <input type="date" id="date" name="date" value="${fruit.date }" class="form-control" required="required" autofocus>
        </div>
        
         <div class="mb-3">
            <label for="price" class="form-label">*Price per Each</label>
            <input type="number" id="price" name="price" value="${fruit.price }" placeholder="Enter Price" class="form-control" required="required" autofocus>
        </div>
        
        <div class="mb-3">
            <label for="city" class="form-label">*ComeFrom</label>
            <input type="text" id="cm" name="cm" value="${fruit.cm}" placeholder="Enter City" class="form-control" required="required" autofocus>
        </div>

        
        <button type="submit" class="btn btn-primary">Add</button>
        <button type="reset" class="btn btn-danger">Reset</button>
    </form> <!-- /form -->
</div> <!-- ./container -->
	
	
	<script>
	$(document).ready(function() {
		$('#fruitTable').DataTable();
	});
</script>
</body>
</html>