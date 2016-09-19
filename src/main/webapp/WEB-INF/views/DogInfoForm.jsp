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
<spring:url value="/resources/style.css" var="style"/>
<link href="${style}" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>
<h4 style="color:blue;" >Logged in as : <c:out value="${sessionScope.user.email}"/> </h4> 
<a href="<%=request.getContextPath() %><c:out value="/logout"/>"> logout </a>

   <div class="img">	 
	 
	 	    		<img src="<c:out value="${dog.photo}"/>" alt="No image" width="300" height="200" />
	              
                  	 
   </div>  


<div class="img1">
<h1 class="center">

	   <c:out value="${dog.name}"/>
	  
</h1>
<h1 class="center">

	Breed: <c:out value="${dog.breed}"/>
	  
</h1>
 

 <p> "${dog.desc}"</p>
 

	<p class="thick">Click Here for Shelter Information  </p>
	
	<c:out value="${dog.breed}"/>
		 
	 <a href="<%=request.getContextPath() %><c:out value="/shelterdetail?shelterid="/><c:out value="${dog.shelterID}"/>">  
	 
	<c:out value="${dog.shelterID}"/>
	 </a>
   

	 	
</div>


</body>
</html>