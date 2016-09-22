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


<h3 class="h4.right" >Welcome: <c:out value="${sessionScope.user.firstName}"/> </h3> 

<%-- <a href="<%=request.getContextPath() %><c:out value="/logout"/>">Logout </a> --%>

<form action="formPage" method="get">
<h1>Search for your new family member by breed:</h1>
<select name=breed>
<option disabled selected value=>--select an option --</option>
    <option value="Standard+Poodle">Standard Poodle</option>
	<option value="Staffordshire+Bull+Terrier">Staffordshire Bull Terrier</option>
	<option value="Spitz">Spitz</option>
	<option value="Spaniel">Spaniel</option>
	<option value="South+Russian+Ovtcharka">South Russian Ovtcharka</option>
	<option value="Smooth+Fox+Terrier">Smooth Fox Terrier</option>
	<option value="Siberian+Husky">Siberian Husky</option>
	<option value="Shetland+Sheepdog+Sheltie">Shetland Sheepdog Sheltie</option>
	<option value="Shar+Pei">Shar Pei</option>
	<option value="Scottish+Terrier+Scottie">Scottish Terrier Scottie</option>
	<option value="Rottweiler">Rottweiler</option>
	<option value="Retriever">Retriever</option>
	<option value="Mountain+Dog">Mountain Dog</option>
	<option value="Maltese">Maltese</option>
	<option value="Golden+Retriever">Golden Retriever</option>
	<option value="German+Shorthaired+Pointer">German Shorthaired Pointer</option>
	<option value="Pit+Bull+Terrier">Pit Bull Terrier</option>
	<option value="Pointer">Pointer</option>
	<option value="Shepherd">Shepherd</option>
	<option value="Labrador+Retriever">Labrador Retriever</option>
	<option value="German+Shepherd+Dog">German Shepherd Dog</option>
	<option value="Foxhound">Foxhound</option>
	<option value="Staffordshire+Bull+Terrier">Staffordshire Bull Terrier</option>
	<option value="Jack+Russell+Terrier">Jack Russell Terrier</option>
	<option value="Hound">Hound</option>
    <option value="Akita">Akita</option>
	<option value="Beagle">Beagle</option>
	<option value="Greyhound">Greyhound</option>
	<option value="Mastiff">Mastiff</option>
	<option value="Papillon">Papillon</option>
	<option value="Cavalier+King+Charles+Spaniel">Cavalier King Charles Spaniel</option>
	<option value="Yorkshire+Terrier+Yorkie">Yorkshire Terrier Yorkie</option>
	<option value="Yellow+Labrador+Retriever">Yellow Labrador Retriever</option>
	<option value="Wirehaired+Terrier">Wirehaired Terrier</option>
	<option value="Xoloitzcuintle+(Mexican+Hairless)">Xoloitzcuintle (Mexican Hairless)</option>
	<option value="Wire-Haired+Pointing+Griffon">Wire-Haired Pointing Griffon</option>
	<option value="Wire+Fox+Terrier">Wire Fox Terrier</option>
	<option value="Wheaten+Terrier"> Wheaten Terrier</option>
	<option value="West+Highland+White+Terrier+Westie">West Highland White Terrier Westie</option>
	<option value="Welsh+Terrier">Welsh Terrier</option>
	<option value="Welsh+Springer+Spaniel">Welsh Springer Spaniel</option>
	<option value="Weimaraner">Weimaraner</option>
	<option value="Vizsla">Vizsla</option>
	<option value="Toy+Fox+Terrier">Toy Fox Terrier</option>
	<option value="Tosa+Inu">Tosa Inu</option>
	<option value="Tibetan+Terrier">Tibetan Terrier</option>
	<option value="Tibetan+Spaniel">Tibetan Spaniel</option>
	<option value="Tibetan+Mastiff">Tibetan Mastiff</option>
	<option value="Thai+Ridgeback">Thai Ridgeback</option>
	<option value="Terrier">Terrier</option>
	<option value="Swedish+Vallhund">Swedish Vallhund</option>
	<option value="Sussex+Spaniel">Sussex Spaniel</option>
	<option value="Poodle">Poodle</option> 
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
	 	    		
	 </a>	    		
<%-- 	 <a href="<c:out value="${dog.id}"/>"> 	 --%>
	 <c:out value="${dog.name}"/>
	 
   </div>  
</c:forEach>



</body>
</html>