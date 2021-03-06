<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import = "java.util.*"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import = "com.finalproject.myapp.MyDog"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Favorites</title>
</head>
<style>
body{background-color:#60C8E7;}
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
<body>
<div id="nav">
    <ul>
    <li><a href="home">Home</a></li>
    <li><a href="getRandomDog">Search</a></li>
    <li><a href="favourite?actiontype">Favorites</a></li>
    <li><a href="home">Logout</a></li>
  </ul>
</div>
<ul>

<!--   <li><a href="logout">logout</a></li> -->
  
</ul>
<h4 class="h4.right" >Welcome: <c:out value="${sessionScope.user.firstName}"/> </h4> 

<h4>Your Favorite dogs are: </h4> 

<c:forEach var="dog" items="${dogs}">
   <div class='img'>	 
	
	   
	 <img src="<c:out value="${dog.photo}"/>" alt="n"  
	 	    		width="300" height="200"> 
	 	    		
<%-- 	 <a href="<c:out value="${dog.id}"/>"> 	 --%>
	 <c:out value="${dog.name}"/>
	 </a>
   </div> 
   
   <a href="favourite?actiontype=remove&dogid=${dog.id}">Remove from favorites</a>
   
</c:forEach>
</body>
</html>