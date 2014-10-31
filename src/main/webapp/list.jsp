<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>书籍列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/style/style.css">
	<script type="text/javascript" src="<%=path %>/js/jquery-2.1.0.js"></script>
	<script type="text/javascript">
	$(function(){
		$("tbody>tr:odd").addClass("odd");
		$("tbody>tr:even").addClass("even");
		
		$("tbody>tr").click(function(){
			/* if($(this).hasClass("selected"))
			{
				$(this).removeClass("selected").find(":checkbox").attr("checked",false);
			}
			else
			{
				$(this).addClass("selected").find(":checkbox").attr("checked",true);
			} */
			//等价
			var hasSelected = $(this).hasClass("selected");
			$(this)[hasSelected?"removeClass":"addClass"]("selected")
				.find(":checkbox").attr("checked",!hasSelected);
		});
		
		//全选
		$("#checkAll").click(function(){
			$("[name=check]:checkbox").attr("checked",this.checked);
			$("tbody>tr")[this.checked?"addClass":"removeClass"]("selected");
		});
		//加载时就处理选中的行
		$("tbody>tr:has(:checked)").addClass("selected");
		
		//滑动换色
		$("tbody>tr").hover(function(){
			$(this).each(function(){
				var $checked = $(this).find(":checkbox");
				//如果没有被选中采用滑动样式
				if(!$checked.attr("checked")) 
				{
					$(this).addClass("selected");
				} 
			});
		},function(){
			$(this).each(function(){
				var $checked = $(this).find(":checkbox");
				//如果没有被选中采用滑动样式
				if(!$checked.attr("checked")) 
				{
					$(this).removeClass("selected");
				} 
			});
		});
	});
	</script>
  </head>
    
    <body>   
        <b id="head"><a href="<%=path %>/list.action?offset=0&limit=10">书籍列表页面</a></b>   
        <a href="<%=path%>/new.jsp">添加书籍</a>   
         <form action="<%=path%>/list.action" method="post" >   
            <fieldset>   
                	<legend> 查找书籍 </legend>   
                	关键字（书籍名、作者）<input name="key">   
      				<input type="submit" value="查找">   
             </fieldset>   
        </form>
        <%-- 输出用户列表 --%>   
        <c:choose>   
            <c:when test="${not empty books}">   
	        <table class="table-report">
	  		<thead>
	  			<tr>
	  				<th><input type="checkbox" id="checkAll" ></th>
	  				 <th><b>书籍ID</b></th>   
	                <th><b>书籍名称</b></th>   
	                <th> <b>作者</b></th>   
	                <th><b>价格</b></th>   
	                <th><b>操作</b> </th>   
	  			</tr>
	  		</thead>
	  		<tbody>
	  			 <c:forEach items="${books}" var="book">
	          	 <tr>
	           			<td><input type="checkbox" name="check" ></td>
	                    <td> ${book.id}</td>   
	                    <td>  ${book.bookname} </td>   
	                    <td>${book.bookauthor}</td>   
	                    <td> ${book.bookprice} </td>   
	                    <td>   
	                        <a href="<%=path%>/query.action?id=${book.id}">修改</a>   
	                        <a href="<%=path%>/del.action?id=${book.id}">删除</a>   
	                   </td>   
	                </tr>  
	            </c:forEach>
	  		</tbody>
	  		</table>
       	 	</c:when>   
        	<c:otherwise>抱歉,没有找到相关的记录!</c:otherwise>   
        </c:choose>   
        <%-- <div id="header">  
		<ul style="font-size:18px">
			<c:if test="${pagenum==1}">
				首页 上一页
			</c:if>
			<c:if test="${pagenum>1}">
				<li><a href="<%=path %>/list.action?offset=0&limit=10">首页</a></li>
				<li><a href="<%=path %>/list.action?offset=${10*(pagenum-2)}&limit=10">上一页</a></li>
			</c:if>
			<c:if test="${pagenum==totalPage}">
				下一页  末页
			</c:if>
			<c:if test="${pagenum<totalPage}">
				<li><a href="<%=path %>/list.action?offset=${10*pagenum}&limit=10">下一页</a> </li>
				<li><a href="<%=path %>/list.action?offset=${(totalPage-1)*10}&limit=10">末页 </a></li>
			</c:if>
		</ul> 
		</div>      --%> 
		<div class="pagelist">
			<span>${totalcount}条数据 共${totalPage}页</span>
			<c:if test="${pagenum>1}">
				<a href="<%=path %>/list.action?offset=0&limit=10">首页</a>
				<a href="<%=path %>/list.action?offset=${10*(pagenum-2)}&limit=10">上一页</a>
			</c:if>
			<c:forEach begin="1" end="${totalPage}" step="1" var="page">
				<c:if test="${pagenum==page}">
					<strong>${page}</strong>
				</c:if>
				<c:if test="${pagenum!=page}">
					<a href="<%=path %>/list.action?offset=${10*(page-1)}&limit=10">${page}</a>
				</c:if>
			</c:forEach>
			<c:if test="${pagenum<totalPage}">
				<a href="<%=path %>/list.action?offset=${10*pagenum}&limit=10">下一页</a>
				<a href="<%=path %>/list.action?offset=${(totalPage-1)*10}&limit=10">尾页 </a>
			</c:if>
		</div>
    </body>   
</html>  


