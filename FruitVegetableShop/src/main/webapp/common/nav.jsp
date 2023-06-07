<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!-- Navigation
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container px-4 px-lg-5">
		<a class="navbar-brand" href="main">TsuKi</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
				<li><a class="nav-link scrollto active" href="main">Home</a></li>
				<li><a class="nav-link scrollto" href="#py">Instock</a></li>
				<li><a class="nav-link scrollto" href="#py-5">FruitList</a></li>

				<c:if test="${user.role == 'admin' }">
					<li><a class="nav-link scrollto" href="fruit-register.jsp">AddFruit</a></li>
				</c:if>
				
				<li><a class="nav-link scrollto" href="#contact">Contact</a></li>
			
			
				<li class="nav-item dropdown" style="text-align: right"><a
					class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">${user.username }</a>
					<ul class="dropdown-menu dropdown-menu-end "
						aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="logout">Logout</a></li>
					</ul></li>
			
			</ul>
		</div>
	</div>
</nav>
-->
<!-- New nav -->
 <header id="header" class="fixed-top navnew" >
    <div class="container d-flex align-items-center">

      <h1 class="logo me-auto"><a href="main">TsuKi</a></h1>
      <!-- Uncomment below if you prefer to use an image logo -->
      <!-- <a href="index.html" class="logo me-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

      <nav id="navbar" class="navbar order-last order-lg-0 " >
        <ul>
          <li><a class="active" href="main">Home</a></li>
          <li><a href="#py">Instock</a></li>
          <li><a href="#py-5">FruitList</a></li>
          
          <c:if test="${user.role == 'admin' }">
          <li><a href="fruit-register.jsp">Add Fruits</a></li>
          </c:if>
          <c:if test="${user.role == 'user' }">
          <li><a href="login">Register</a></li>
       
          </c:if>
          <li class="dropdown"><a href="#"><span>${user.username }</span> <i class="bi bi-chevron-down"></i></a>
            <ul>
              <li><a href="logout">Logout</a></li>
            </ul>
          </li>
          <li><a href="#contact">Contact</a></li>
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->

      <a href="#py-5" class="get-started-btn">TO BUY</a>

    </div>
  </header><!-- End Header -->
