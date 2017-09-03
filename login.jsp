<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>

form {
    border: 3px solid #f1f1f1;
}

button:hover {
    opacity: 0.8;
}


</style>

<body background="bluewallpaper.jpg">
<%
if(request.getAttribute("valid")!=null){
	if(request.getAttribute("valid").equals("invalid")){ 
%>
<div align="center">
<label style="background-color:cyan;color:red;font-size:25px;font-family:Arial;text-align:center">Invalid Credentials</label></div><br/>
<%
}} 
%>
<h4 align="center" style="background-color:white">K.J Somaiya Institute of Engineering and Information Technology</h4>
<br>
<h1 align="center" style="background-color:white">Sentiment analysis of Twitter Data Using Machine Learning Approach</h1>
<br>
<form action="TwitterServlet" method="post">
 <div align="center">
     <label style="background-color:cyan; color:black;font-size:25px;font-family:Arial;text-align:center"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="name">

<br><br><br>

    <label style="background-color:cyan; color:black;font-size:25px;font-family:Arial;text-align:center"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw">
   
<br><br><br>

    <button type="submit">Login</button>  
    <button type="button" class="resetbtn">Reset</button>
  </div>

</form>
</body>
</html>