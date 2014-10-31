<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>   
<%   
String path = request.getContextPath();   
String basePath =   
request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";   
%>   
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">   
<html>   
<head>   
<base href="<%=basePath%>">   
<title>出错了！</title>   
</head>   
<body>   
出错了！<br/>   
详细信息是：<br/>   
${message}<br/><br/>   
<a href="javascript:history.back();">返回</a>   
</body>   
</html>  


