<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.*"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import = "com.finalproject.myapp.MyDog"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="mvscreen7">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<spring:url value="/resources/style.css" var="style"/>
<link href="${style}" rel="stylesheet" />
<title>Doggy Info</title>
<style>
body{background-color:#60C8E7;
font-size: 12pt;}
#nav {
  background-color: #bbbbbb;
  font-family: Helvetica, sans-serif;
  height: auto;
}

ul {
  background-color: #3973ac;
  overflow: auto;
  padding: 20px;
  list-style-type: none;
  margin: 0;
  padding: 0;
   
 }

li {
  background-color: #3973ac;
  color: White;
  list-style: none;
  float: left;
  text-align: center;
}

a {
  background-color: #3973ac;
  text-decoration: none;
  color: White;
  display: block;
  margin: 0px;
  padding: 10px 20px;
  text-align: center;
}

a:hover {
  background-color: #60C8E7;
  transition: 2s background-color;
}
</style>

</head>
<body>
<div id="nav">
    <ul>
    <li><a href="home">Home</a></li>
    <li><a href="getRandomDog">Search</a></li>
    <li><a href="favourite?actiontype">Favorites</a></li>
    <li><a href="home">Logout</a></li>
  </ul>
</div>
<h4 style="color:black;" >You are logged in as:  <c:out value="${sessionScope.user.firstName}"/> </h4> 
<%-- <a href="<%=request.getContextPath() %><c:out value="/logout"/>">Logout </a> --%>

   <div class="img">	 
	 
	 	    		<img src="<c:out value="${dog.photo}"/>" alt="No image" width="300" height="200" />
	              
                  	 
   </div>  


<div class="img1">
<h1 class="center">

	   <c:out value="${dog.name}"/>
	  
</h1>
<h1 class="center">

	Breed: <c:out value="${dog.breed}"/><br>
 	CuddleFactor: <c:out value="${cuddlefactor}"/>    
</h1>
 

 <p> "${dog.desc}"</p>
 



		 
	 <a href="<%=request.getContextPath() %><c:out value="/shelterdetail?shelterid="/><c:out value="${dog.shelterID}"/>">  
	 
<%-- 	<c:out value="${dog.shelterID}"/> --%>
         <c:out value="Click here for shelter contact information"/>
	 </a>
   
   <br>
    <a href="favourite?actiontype=add&dogid=${dog.id}">Add to favorites</a>

 
</div>



</body>
</html>