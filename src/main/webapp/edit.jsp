<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib  prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>修改书籍</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript" src="js/form.js"></script>
	</head>
	<body>
		<h3>
			修改书籍
		</h3>
		<form action="<%=path%>/update.action" method="post">
			<input type="hidden" value="<s:property value="#request.book.id"/>" name="id" />
			<table width="50%" border="0">
				<tr>
					<td>
						书籍名：
					</td>
					<td>
						<input name="bookname" value="<s:property value="#request.book.bookname"/>" />
						<br>
					</td>
				</tr>
				<tr>
					<td>
						作者:
					</td>
					<td>
						<input name="bookauthor" value="<s:property value="#request.book.bookauthor"/>" />
					</td>
				</tr>
				<tr>
					<td>
						价格：
					</td>
					<td>
						<input name="bookprice" value="<s:property value="#request.book.bookprice"/>" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="提交">
					</td>
					<td>
						<input type="reset" value="重填">
					</td>
				</tr>
			</table>
		</form>
		<input type="button" onclick="document.location='list.action?startNum=0&limit=5';"value="返回列表">
	</body>
</html>


