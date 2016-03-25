<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="../common/head.jsp"%>
<title>备份-管理</title>

</head>

<body>
	<div class="pd-20">

		<form action="" method="post" class="form form-horizontal" id="form">
			
			<div class="row cl">
				<div class="col-10 col-offset-2">
					<button onclick="btn_Opt('backup');" class="btn btn-primary radius"
						type="button">
						<i class="Hui-iconfont">&#xe632;</i> 备份
					</button>

					<button onclick="btn_Opt('load');" class="btn btn-primary radius"
						type="button">
						<i class="Hui-iconfont">&#xe632;</i> 还原
					</button>
				</div>
			</div>
		</form>

	</div>
</body>

<%@ include file="../common/script.jsp"%>
<script>


	function btn_Opt(act) {
		var data = {};
		data["act"] = act;
		$.ajax({
			url : '${pageContext.request.contextPath}/servlet/BackupServlet',
			data : data,
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(r) {
				alert(r.message);
				
			},
			error : function() {
				alert("操作失败");
			}
		});
	}
</script>
</html>
