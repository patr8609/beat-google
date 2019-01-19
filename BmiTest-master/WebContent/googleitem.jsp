<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
</div><div id='navi'> </div>
<div id="container">
	<div id='MainShow'>
		<h1>REVIEWS:</h1>
		<!--原生_文章內置_1 Start-->
		<div id="Ads1">
</head>
<body bgColor="#FFFFF">
<%
String[][] orderList = (String[][])  request.getAttribute("result");
for(int i =0 ; i < orderList.length;i++){%>
	<a href='<%= orderList[i][1] %>'><%= orderList[i][0] %></a><br><h style="font-size:10px ;"><%= orderList[i][1] %></h><br><br>
<%
}
%>
</body>
</html>