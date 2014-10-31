<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>添加书籍</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript" src="js/form.js" ></script>
	</head>
	<body>
		<h3>
			添加书籍
		</h3>
		<form action="<%=path%>/add.action"  method="post">
			<table width="50%" border="0">
					<tr>
						<td>
							书籍名：
						</td>
						<td>
							<input name="bookname" />
							<br>
						</td>
					</tr>
					<tr>
						<td>
							作者:
						</td>
						<td>
							<input name="bookauthor" />
						</td>
					</tr>
					<tr>
						<td>
							价格：
						</td>
						<td>
							<input name="bookprice" />
						</td>
					</tr>
					<tr>
						<td>
							<input type="submit" value="添加" name="button1">
						</td>
						<td>
							<input type="Reset" value="重填" name="button2">
						</td>
					</tr>
			</table>
		</form>
		<input type="button" onclick="document.location='<%=path%>/list.action?startNum=0&limit=5';" value="返回列表">
	</body>
</html>


