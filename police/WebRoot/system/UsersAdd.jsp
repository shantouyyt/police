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
<title>警员管理</title>

</head>

<body>
	<div class="pd-20">

		<form action="" method="post" class="form form-horizontal" id="form">
			<!-- 
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>用户名：</label>
				<div class="formControls col-5">
					<input maxlength="100" type="text" class="input-text" value=""
						placeholder="" id="userName" name="userName">
				</div>
				<div class="col-4"></div>
			</div>
			-->
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>密码：</label>
				<div class="formControls col-5">
					<input maxlength="20" type="password" class="input-text" value=""
						placeholder="" id="passWord" name="passWord">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-2">性别：</label>
				<div class="formControls col-5">
					<select class="select" id="sex" name="sex">
						<option value="1">男</option>
						<option value="0">女</option>
					</select>
				</div>
				<div class="col-4"></div>
			</div>
	
			<input type="hidden" id="id" name="id" />
			<div class="row cl">
				<div class="col-10 col-offset-2">
					<button onclick="btn_Opt();" class="btn btn-primary radius"
						type="button">
						<i class="Hui-iconfont">&#xe632;</i> 保存
					</button>

					<button onclick="removeIframe();" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>

	</div>
</body>

<%@ include file="../common/script.jsp"%>
<script>
	var id = getUrlVar("id");
	if(id > 0){
		$("#id").val(id);
	}else{
		$("#id").val(0);
	}
	
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
	function btn_Opt() {
		var data = {};
		if(id>0){
			data["act"] = "update";
		}else{
			data["act"] = "add";
		}
		data["data"] = $.toJSON($("#form").serializeObject());
		$.ajax({
			url : '${pageContext.request.contextPath}/servlet/UsersServlet',
			data : data,
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(r) {
				alert(r.message);
				if (r.statusID > 0) {
					removeIframe();
				}
			},
			error : function() {
				alert("操作失败");
			}
		});
	}
</script>
</html>
