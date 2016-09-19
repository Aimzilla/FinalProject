<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Home</title>
<!-- 	<spring:url value="/resources/css/homepage.css" var="homepage"/> -->
	<link rel="stylesheet" href="resources/css/homepage.css">
	
<!-- <link href="homepage.css" rel="stylesheet"> -->
<%-- <link href="${homepage}" rel="stylesheet" > --%>
</head>
<body>

<h1>Detroit Dogs</h1>

<p>Welcome!  Please login or sign up below:</p>
<div class="dog">
  <div class="ears"></div>
  
  <div class="body">
    <div class="eyes"></div>
    <div class="beard">
      <div class="mouth">
        <div class="tongue"></div>
      </div>
    </div>
    <div class="belt">
      <div class="locket"></div>
      <div class="dot dot1"></div>
      <div class="dot dot2"></div>
      <div class="dot dot3"></div>
      <div class="dot dot4"></div>
      <div class="tag"></div>
    </div>
    <div class="stomach">
    </div>
    <div class="legs">
      <div class="left"></div>
      <div class="right"></div>
    </div>
  </div>
  <div class="tail">
  </div>
</div>





<%-- <h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P> --%>
<a href="login">Click to LogIn</a>
</body>
</html>
