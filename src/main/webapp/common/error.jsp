<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=GB18030">
	<title>系统出现错误</title>
  </head>
  
  <body>
    <center>
    	<input type="button" value="关闭窗口" onclick="javascript:window.close();">
    </center>
  </body>
</html>
