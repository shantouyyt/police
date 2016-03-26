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
<title>增加驾驶员</title>

</head>

<body>
	<div class="pd-20">

		<form action="" method="post" class="form form-horizontal" id="form">

			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>姓名：</label>
				<div class="formControls col-5">
					<input maxlength="100" type="text" class="input-text" value="" readonly= "true"
						placeholder="" id="name" name="name">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>驾驶证：</label>
				<div class="formControls col-5">
					<input maxlength="100" type="text" class="input-text" value="" readonly= "true"
						placeholder="" id="license" name="license">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-2">驾驶证到期时间：</label>
				<div class="formControls col-2">
					<input name="licenseExpire" type="text" readonly= "true"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						id="licenseExpire" class="input-text Wdate">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-2">地址：</label>
				<div class="formControls col-5">
					<input maxlength="100" type="text" class="input-text" value="" readonly= "true"
						placeholder="" id="address" name="address">
				</div>
				<div class="col-4"></div>
			</div>

			<div class="row cl">
				<label class="form-label col-2">电话：</label>
				<div class="formControls col-5">
					<input maxlength="20" type="text" class="input-text" value="" readonly= "true"
						placeholder="" id="tel" name="tel">
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
			<div class="row cl">
				<label class="form-label col-2">备注：</label>
				<div class="formControls col-5">
					<textarea style="margin: 0px; width: 690px; height: 126px;" readonly= "true"
						rows="5" cols="30" id="remark" name="remark"></textarea>
				</div>
				<div class="col-4"></div>
			</div>
			<input type="hidden" id="id" name="id" />
			<div class="row cl">
				<div class="col-10 col-offset-2">
					

					<button onclick="removeIframe();" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;关闭&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>

	</div>
</body>

<%@ include file="../common/script.jsp"%>
<script>
	var id = getUrlVar("id");
	if (id > 0) {
		$("#id").val(id);
	} else {
		$("#id").val(0);
	}

	$(function() {
		if (id > 0) {
			var data = {};
			data["act"] = "get";
			data["data"] = id;
			$
					.ajax({
						url : '${pageContext.request.contextPath}/servlet/DriverServlet',
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
							} else {
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
