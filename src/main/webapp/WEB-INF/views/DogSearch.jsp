<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.*"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import = "com.finalproject.myapp.MyDog"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="mvscreen2">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<spring:url value="/resources/style.css" var="style"/>
<spring:url value="/resources/images" var="images"/>
<spring:url value="/" var="mdetail"/>
<link href="${style}" rel="stylesheet" />
<title>Detroit Dog Search</title>
<style>
body{background-color:#60C8E7;}
</style>
</head>
<body>
<h4 class="h4.right" >Logged in as : <c:out value="${sessionScope.user.email}"/> </h4> 
<a href="<%=request.getContextPath() %><c:out value="/logout"/>"> logout </a>
<form action="formPage" method="get">
<h3>Search for your new family member by breed:</h3>
<select name=breed>
        <option disabled selected value=>--select an option --</option>
        <option value="Akita">Akita</option>
        <option value="Greyhound">Greyhound</option>
        <option value="Australian Shepherd">Australian Shepherd</option>
        <option value="Bichon Frise">Bichon Frise</option>
        <option value="Corgi">Corgi</option>
        <option value="Dalmation">Dalmation</option>
        <option value="Mastiff">Mastiff</option>
        <option value="Poodle">Poodle</option>
        <option value="Papillon">Papillon</option>
    </select>

<input type="submit" value="Submit!">

</form>
<!-- <form:form  method ="post" action="searchDog.html">
<input class="searchform" type="text" name="category">
<input class="searchform2" type="submit" value="search by breed">
</form:form> -->
<c:forEach var="dog" items="${dogs}">
   <div class='img'>	 
	 <a href="<%=request.getContextPath() %><c:out value="/dogsearch?id="/><c:out value="${dog.id}"/>">  
		 	    	 <%-- <img src="${images}/<c:out value="${dog.name}"/><c:out value=".jpg"/>" alt="n"  
	 	    		width="300" height="200"> --%>
	   
	 <img src="<c:out value="${dog.photo}"/>" alt="n"  
	 	    		width="300" height="200"> 
	 	    		
	 <a href="<c:out value="${dog.id}"/>"> 	
	 <c:out value="${dog.name}"/>
	 </a>
   </div>  
</c:forEach>

</body>
</html>