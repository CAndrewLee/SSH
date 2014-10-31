<%@ page language="java" pageEncoding="UTF-8" %>   
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">   
<html>   
<head>   
<title>欢迎</title>   
</head>   
<body>   
<a href="<%=path %>/list.action?offset=0&limit=10">查看书籍列表</a><br>   
</body>   
</html>  


