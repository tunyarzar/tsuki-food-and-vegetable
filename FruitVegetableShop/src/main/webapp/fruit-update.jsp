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
  background: url("../assets/Fruit-Update.jpg") top center;
  background-size: cover;
  position: relative;
  padding: 0;

</style>
<title>Update Fruit</title>

</head>
<body>
<!-- Responsive navbar-->

	 
	
	
	<div class="container">
	
	<p style="background-image: url('../assets/Fruit-Update.jpg');">
	
    <form action="fruit" method="post">
        <h2 class="text-center">Fruit Registration</h2>
        
        <input type="hidden" name="mode" value="UPDATE">
        <input type="hidden" name="id" value="${fruit.id }">
        <h3> <c:out value="ID : ${fruit.id}"></c:out> </h3>
        
        <div class="mb-3">
            <label for="name" class="form-label">*Fruit Name</label>
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
            <label for="comefrom" class="form-label">*Supplier</label>
            <input type="text" id="cm" name="cm" value="${fruit.cm}" placeholder="Enter City" class="form-control" required="required" autofocus>
        </div>

        
        <button type="submit" class="btn btn-primary">Update</button>
        <button type="reset" class="btn btn-danger">Reset</button>
    </form> <!-- /form -->
 
</div> <!-- ./container -->
	
	
	
</body>
</html>