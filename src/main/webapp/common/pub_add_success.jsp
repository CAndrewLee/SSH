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
    <title>��Ӽ�¼�ɹ�</title>
    <script type="text/javascript">
	    function closewindow(){
	    	if(window.opener){
	    		window.opener.location.reload(true);
	    		window.close();
	    	}
	    }
	    function clock(){
	    	i = i -1;
	    	if(document.getElementById("info")){
	    		document.getElementById("info").innerHTML = "�����ڽ���"+i+"����Զ��ر�";
	    	}
	    	if(i > 0)
	    		setTimeout("clock();",1000);
	    	else
	    		closewindow();
	    }

	    var i = 4;
	    clock();
    </script>
  </head>
  
  <body>
    <center>
    	��Ӽ�¼�ɹ�!<p>
    	<div id="info">�����ڽ���3����Զ��ر�</div>
    	<input type="button" value="�رմ���" onclick="closewindow();"/>
    </center>
  </body>
</html>
