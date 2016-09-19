<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="mvscreen">
<head>
<spring:url value="/resources/style.css" var="style"/>
<link href="${style}" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>

<h1 align="center">Detroit Dogs</h1>
<section class ="loginform cf">
  <form   action="loginuser" method ="post">
  <h1>Login Page</h1>
  <h2 style="color:red;">
  
    <% if(request.getAttribute("message")!=null){
    	out.print(request.getAttribute("message"));
    }
    	
    %>
  
  </h2>
  <ul>
  <li>
     <label for ="usermail">User name</label>
     <input type="email" name="usermail" placeholder="yourname@email.com" required>
  </li>
  <li>
     <label for ="password">Password</label>
     <input type="password" name="password" placeholder="password" required>
   </li>
   <li>
     <input type="submit" value="login">
    </li>
  </ul>
  <ul>
   <li>
     <label for ="link">Not a Member</label>
     <a href="register"> Sign Up</a>
     </li>
   </ul>
</form>
</section>



</body>
</html>