<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="common/header.jsp"></c:import>
<title>Error Page</title>
<link href="css/styles.css" rel="stylesheet" />
</head>
<body>
<!-- Responsive navbar-->


<h1 class="alert alert-danger">${errorTitle }</h1>
<p class="alert alert-danger">${errorMessage }</p>
<a href="fruit-list.jsp" class="nav-link scrollto">Go to Home</a>

</body>
</html>