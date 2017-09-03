<%@page import="java.util.ArrayList"%>
<%@page import="weka.classifiers.Evaluation"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h4 align="center" style="background-color:cyan">K.J Somaiya Institute of Engineering and Information Technology</h4>
<br>
<div align="center">
<table border="2"><tr><td>
<table><tr><td>
<form action="Accuracy.jpeg" >
    <input type="submit" value="Accuracy of the Classifier" align= "middle"/>
</form></td><td>
<form action="PieChart.jpeg">
    <input type="submit" value="Overall Positive and Negative Tweets" />
</form></td></tr></table>
<table><tr><td>
<form action="TwitterServlet" method="post">
	<input type="hidden" name="tweets" value="tweets" />
    <input type="submit" value="See Tweets" />
</form></td><td>
<form action="TwitterServlet" method="post">
	<input type="hidden" name="cleanTweets" value="cleanTweets" />
    <input type="submit" value="Clean Tweets" />
</form></td><td>
<form action="search.jsp">
    <input type="submit" value="New Search" />
</form></td><td>
<form action="TwitterServlet" method="post">
	<input type="hidden" name="summary" value="summary" />
    <input type="submit" value="Perform Sentiment Analysis" />
</form></td></tr>
</table>
</td><td><%
Thread.sleep(3000);
	ArrayList<String> list = (ArrayList<String>) session.getAttribute("tweets");
	ArrayList<String> cleanList = (ArrayList<String>) session.getAttribute("cleanTweets");
	Evaluation eval=(Evaluation) session.getAttribute("eval");
	
if(list!=null){
	
	for(String tweet : list) {
		out.println(tweet);%><br/><br/>
		<%}}else if(cleanList!=null){
			for(String cleanTweet : cleanList) {
				out.println(cleanTweet);%><br/><br/>
				<%}
}else if(eval!=null){%>
	<table border="2"><thead align="center">Detailed Accuracy By Class</thead><tr>
	<td>TP Rate</td>
	<td>FP Rate</td>
	<td>TN Rate</td>
	<td>FN Rate</td>
	<td>Precision</td>
	<td>Recall</td>
	<td>Class</td>
	</tr>
	<tr>
	<td><%out.println(eval.truePositiveRate(0));%></td>
	<td><%out.println(eval.falsePositiveRate(0));%></td>
	<td><%out.println(eval.trueNegativeRate(0));%></td>
	<td><%out.println(eval.falseNegativeRate(0));%></td>
	<td><%out.println(eval.precision(0));%></td>
	<td><%out.println(eval.recall(0));%></td>	
	<td>negativeC</td>
	</tr>
	<tr>
	<td><%out.println(eval.truePositiveRate(1));%></td>
	<td><%out.println(eval.falsePositiveRate(1));%></td>
	<td><%out.println(eval.trueNegativeRate(1));%></td>
	<td><%out.println(eval.falseNegativeRate(1));%></td>
	<td><%out.println(eval.precision(1));%></td>
	<td><%out.println(eval.recall(1));%></td>
	<td>positiveC</td></tr></table>
	<br/><br/>
	<table border="2"><tr>
	<td>Correctly Classified Instances</td>
	<td>Incorrectly Classified Instances</td>
	 <td>Total Number of Instances</td>
	 <td>Accuracy</td>
	 </tr>
	 <tr>
	 <td><%out.println(eval.correct());%></td>
	<td><%out.println(eval.incorrect());%></td>
	<td><%out.println(eval.numInstances());%></td>
	<td><%out.println(100*((eval.truePositiveRate(0)*eval.correct())+(eval.trueNegativeRate(0)*eval.incorrect()))/((eval.truePositiveRate(0)*eval.correct())+(eval.trueNegativeRate(0)*eval.incorrect())+(eval.falsePositiveRate(0)*eval.incorrect())+(eval.falseNegativeRate(0)*eval.correct()))); %></td>
	</tr>
	</table>
<%}%>
<img alt="" src="Accuracy.jpeg" >
<img alt="" src="PieChart.jpeg" >

</td></tr></table>
</div>
</body>
</html>