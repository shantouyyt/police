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
			<form class="form form-horizontal" action="index.html" method="post">
				<div class="row cl">
					<label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i>
					</label>
					<div class="formControls col-8">
						<input id="userName" name="userName" type="text" placeholder="账户"
							class="input-text size-L">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i>
					</label>
					<div class="formControls col-8">
						<input id="passWord" name="passWord" type="password"
							placeholder="密码" class="input-text size-L">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-3" style="margin-top: 15;">类型
					</label>
					<div class="formControls col-8">
						<select class="select" id="userType" name="userType" style="margin-top: 10px;width: 120px;height: 30;">
							<option value="1">警员</option>
							<option value="2">领导</option>
							<option value="3">ddd管理员</option>
						</select>
					</div>
				</div>
				<div class="row cl">
					<div class="formControls col-8 col-offset-3">
						<input id="validcode" class="input-text size-L" type="text"
							placeholder="验证码" value="" style="width: 122px;height: 30;"> <img
							id="imgcode" src="${pageContext.request.contextPath}/number.jsp"
							onclick="changeImage()"> <a id="kanbuq" href="javascript:;"
							onclick="changeImage()">看不清，换一张</a>
					</div>
				</div>

				<div class="row">
					<div class="formControls col-8 col-offset-3">
						<input name="" type="button" onclick="btn_login();"
							class="btn btn-success radius size-L"
							value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;"> <input
							name="" type="button" onclick="btn_reset();"
							class="btn btn-default radius size-L"
							value="&nbsp;注&nbsp;&nbsp;&nbsp;&nbsp;册&nbsp;">
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


	<script>
		function btn_reset() {
			window.location = "${pageContext.request.contextPath}/register.jsp";
		}
		function btn_login() {
			var data = {};
			data["act"] = "login";
			data["userName"] = $("#userName").val();
			data["passWord"] = $("#passWord").val();
			data["userType"] = $("#userType").val();
			data["validcode"] = $("#validcode").val();
			$
					.ajax({
						url : '${pageContext.request.contextPath}/servlet/LoginServlet',
						data : data,
						type : 'post',
						cache : false,
						dataType : 'json',
						success : function(r) {
							if (r.statusID == 0) {
								alert(r.message);
							} else {
								//window.location = "${pageContext.request.contextPath}/login.jsp";
								//window.location = r.message;
								window.top.location = r.message;
							}
						}
					});
		}
		function changeImage() {
			$("#imgcode").attr(
					"src",
					"${pageContext.request.contextPath}/number.jsp?key=login&t="
							+ (new Date()).valueOf());
		}
	</script>
</body>
</html>
