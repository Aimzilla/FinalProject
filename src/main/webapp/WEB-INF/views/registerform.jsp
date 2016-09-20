<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="mvscreen">
<head>
<spring:url value="/resources/style.css" var="style" />
<link href="${style}" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up!</title>
<style>
body{background-color:#60C8E7;}
</style>
</head>

<body>

	<h1 align="center">Please sign up to find your new family member!</h1>
	
	<section class="signup cf">
	<form action="loginuser" method="post">
		<h1>Sign Up Here:</h1>
		<ul>
			<label for="firstname">First name:</label>
			<input type="text" name="first name" placeholder="yourfirstname"
				required autofocus required>
			</li>

			<li><label for="last name">Last name:</label> <input type="text"
				name="last name" placeholder="last name" autofocus required></li>
			<li><label for="usermail">User name</label> <input type="email"
				name="usermail" placeholder="yourname@email.com" required></li>
			<li><label for="password">Password</label> <input
				type="password" name="password" placeholder="password" required>
			</li>
			<li><label for="password">Retype Password:</label> <input
				type="password" name="password" placeholder="password" autofocus
				required></li>
			<li>
			<li><input type="submit" value="sign up">
			<li>
		</ul>
	</form>
	</section>

</body>
</html>