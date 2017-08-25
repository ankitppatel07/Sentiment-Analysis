<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Twitter Search</title>
</head>
<body background="bluewallpaper.jpg">
<form action="TwitterServlet" method="post">
 <div align="center">
 <br>
  <h4 align="center" style="background-color:white">K.J Somaiya Institute of Engineering and Information Technology</h4>   
     <br><br><br>
     <label style="background-color:cyan; color:black;font-size:25px;font-family:Arial;text-align:center"><b>Enter Search Query :</b></label>
    <input type="text"  name="input">

<br><br><br>

    <button type="submit">Submit</button>  
   
  </div>

</form>

</body>
</html>