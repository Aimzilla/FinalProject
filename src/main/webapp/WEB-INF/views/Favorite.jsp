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
<title>Insert title here</title>
</head>
<body>
<ul>

  <li><a href="logout">logout</a></li>
  
</ul>
<h4 class="h4.right" >Welcome: <c:out value="${sessionScope.user.firstName}"/> </h4> 

<h4  >Your Favorite dogs are <</h4> 

<c:forEach var="dog" items="${dogs}">
   <div class='img'>	 
	
	   
	 <img src="<c:out value="${dog.photo}"/>" alt="n"  
	 	    		width="300" height="200"> 
	 	    		
<%-- 	 <a href="<c:out value="${dog.id}"/>"> 	 --%>
	 <c:out value="${dog.name}"/>
	 </a>
   </div> 
   
   <a href="favourite?actiontype=remove&dogid=${dog.id}">Remove from favs</a>
   
</c:forEach>
</body>
</html>