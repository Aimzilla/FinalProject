<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.*"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import = "com.finalproject.myapp.MyDog"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="mvscreen8">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<spring:url value="/resources/style.css" var="style"/>
<link href="${style}" rel="stylesheet" />
<title>Shelter Info</title>
<style>
body{background-color:#60C8E7;}
</style>
</head>

<body>
<h4 style="color:black;" >You are logged in as: <c:out value="${sessionScope.user.firstName}"/> </h4> 
<h2>Shelter contact information:</h2>

<a href="<%=request.getContextPath() %><c:out value="/logout"/>"> Logout </a>

  


<div class="img1">
<h2 class="center">

	   <c:out value="ID #: ${shelter.id}"/>
	   <br>
	   <br>
	    <c:out value="${shelter.name}"/>
	    <br>
	     <c:out value="${shelter.address1}"/>
	      <c:out value="${shelter.address2}"/>
	      <br>
	       <c:out value="${shelter.city}"/>
	        <c:out value="${shelter.state}"/>
	         <c:out value="${shelter.zip}"/>
	         <br>
	          <!--<c:out value="${shelter.country}"/>-->
	          
	           <c:out value="${shelter.phone}"/>
	           <br>
	           <br>
	            <%-- <c:out value="${shelter.email}"/> --%>
	            
<%-- 	            <c:out <a href=value="${shelter.email}"></a>/> --%>
	            
<%-- 	            <a href="${shelter.email}"></a> --%>

<a href="mailto:${shelter.email}">${shelter.email}</a>
</h2>

 
   

	 	
</div>


</body>
</html>