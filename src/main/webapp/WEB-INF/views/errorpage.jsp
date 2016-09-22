<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Whoops!</title>
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
<h2>Whoops!</h2>
</body>
</html>