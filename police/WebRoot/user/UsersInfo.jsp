<%@ page language="java" import="java.util.*,Model.UsersInfo" pageEncoding="utf-8"%>
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
<title>警员管理</title>

</head>

<body>
	<div class="pd-20">

		<form action="" method="post" class="form form-horizontal" id="form">
			 
			<div class="row cl">
				<label class="form-label col-2">用户名：</label>
				<div class="formControls col-5">
					<input maxlength="100" type="text" class="input-text" value=""  readonly= "true"
						placeholder="" id="userName" name="userName">
				</div>
				<div class="col-4"></div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-2">警员编号：</label>
				<div class="formControls col-5">
					<input maxlength="20" type="text" class="input-text" value="" readonly= "true"
						placeholder="" id="no" name="no">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-2">性别：</label>
				<div class="formControls col-5">
					<input maxlength="20" type="text" class="input-text" value="" readonly= "true"
						placeholder="" id="sex" name="sex">
				</div>
				<div class="col-4"></div>
			</div>
	
			<input type="hidden" id="id" name="id" />
			<div class="row cl">
				<div class="col-10 col-offset-2">
					<button onclick="removeIframe();" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>

	</div>
</body>

<%@ include file="../common/script.jsp"%>
<script>
	var id = <%=((UsersInfo)session.getAttribute("UsersInfo")).getId() %>;
	$(function(){
		if(id > 0){
			var data = {};
			data["act"] = "get";
			data["data"] = id;
			$.ajax({
				url : '${pageContext.request.contextPath}/servlet/UsersServlet',
				data : data,
				type : 'post',
				cache : false,
				dataType : 'json',
				success : function(r) {
					if (r.statusID > 0) {
						Json2Form(JSON.parse(r.message));
						if($("#sex").val()==1){
							$("#sex").val('男');
						}else{
							$("#sex").val('女');
						}
					}else{
						alert(r.message);
					}
				},
				error : function() {
					alert("操作失败");
				}
			});
		}
	});

</script>
</html>
