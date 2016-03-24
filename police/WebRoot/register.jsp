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
<base href="<%=basePath%>">

<!--[if lt IE 9]>
		<script type="text/javascript" src="${pageContext.request.contextPath}/lib/html5.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/lib/respond.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/lib/PIE_IE678.js"></script>
	<![endif]-->
<link href="${pageContext.request.contextPath}/css/H-ui.min.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/H-ui.login.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/lib/Hui-iconfont/1.0.6/iconfont.css"
	rel="stylesheet" type="text/css" />
<!--[if IE 6]>
	<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
<title>管理系统</title>
<meta name="keywords" content="">
<meta name="description" content="">

</head>

<body>
	<input type="hidden" id="TenantId" name="TenantId" value="" />
	<div class="header"></div>
	<div class="loginWraper">
		<div id="loginform" class="loginBox">
			<form class="form form-horizontal" action="index.html" method="post" id="form">
				
				
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>账号：</label>
					<div class="formControls col-5">
						<input id="userName" name="userName" type="text"
							placeholder="账号" autocomplete="off" value="" class="input-text"
							datatype="*6-20" nullmsg="账号不能为空">
					</div>
					<div class="col-4"></div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>密码：</label>
					<div class="formControls col-5">
						<input id="passWord" name="passWord" type="password"
							placeholder="密码" autocomplete="off" value="" class="input-text"
							datatype="*6-20" nullmsg="密码不能为空">
					</div>
					<div class="col-4"></div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-3">性别：</label>
					<div class="formControls col-5">
						<select class="select" id="sex" name="sex">
							<option value="1">男</option>
							<option value="0">女</option>
						</select>
					</div>
					<div class="col-4"></div>
				</div>
				<div class="row cl">
					<label class="form-label col-3">类型：</label>
					<div class="formControls col-5">
						<select class="select" id="userType" name="userType">
							<option value="1">警员</option>
							<option value="2">领导</option>
							<option value="3">管理员</option>
						</select>
					</div>
					<div class="col-4"></div>
				</div>

				<div class="row">
					<div class="formControls col-8 col-offset-3">
						<input name="" type="button" onclick="btn_login();"
							class="btn btn-success radius size-L"
							value="&nbsp;注&nbsp;&nbsp;&nbsp;&nbsp;册&nbsp;"> <input
							name="" type="button" onclick="btn_reset();"
							class="btn btn-default radius size-L"
							value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="footer"></div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.json-2.3.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/H-ui.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/common.js"></script>


	<script>
		function btn_reset() {
			 window.location = "${pageContext.request.contextPath}/login.jsp";
		}
		function btn_login() {
			var data = {};
			data["act"] = "reg";
			data["data"] = $.toJSON($("#form").serializeObject());
			$.ajax({
				url : '${pageContext.request.contextPath}/servlet/LoginServlet',
				data : data,
				type : 'post',
				cache : false,
				dataType : 'json',
				success : function(r) {
					alert(r.message);
					if(r.statusID > 0){
						window.location = "${pageContext.request.contextPath}/login.jsp";
					}
				}
			});
		}

	</script>
</body>
</html>
