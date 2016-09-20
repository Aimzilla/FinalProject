<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.*"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shelter Table</title>
<style>
body{background-color:#60C8E7;}
</style>
</head>
<body>
<table border=1>
<c:forEach var="shel" items="${shelter}">
   
  		<tr>    
  		   <td><c:out value="${shel.id}"/></td>
  		   <td><c:out value="${shel.name}"/></td>
  		   <td><c:out value="${shel.address1}"/></td>
  		   <td><c:out value="${shel.address2}"/></td>
  		   <td><c:out value="${shel.city}"/></td>
  		   <td><c:out value="${shel.state}"/></td>
  		   <td><c:out value="${shel.zip}"/></td>
  		   <td><c:out value="${shel.country}"/></td>
  		   <td><c:out value="${shel.phone}"/></td>
  		   <td><c:out value="${shel.email}"/></td>
	  
	   </tr>  
	  
</c:forEach>
 </table>
</body>
</html>