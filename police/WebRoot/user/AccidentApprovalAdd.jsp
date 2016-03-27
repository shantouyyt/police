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
<title>事故审批表</title>

</head>

<body>
	<div class="pd-20">

		<form action="" method="post" class="form form-horizontal" id="form">
			<div class="row cl">
				<label class="form-label col-2">事故人名字：</label>
				<div class="formControls col-5">
					<input maxlength="20" type="text" class="input-text" value="" readonly= "true"
						placeholder="" id="name" name="name">
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
					<select class="select" id="sex" name="sex">
						<option value="1">男</option>
						<option value="0">女</option>
					</select>
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-2">交通方式：</label>
				<div class="formControls col-5">
					<input maxlength="100" type="text" class="input-text" value="" readonly= "true"
						placeholder="" id="trafficMode" name="trafficMode">
				</div>
				<div class="col-4"></div>
			</div>

			<div class="row cl">
				<label class="form-label col-2">事故地点：</label>
				<div class="formControls col-5">
					<input maxlength="20" type="text" class="input-text" value="" readonly= "true"
						placeholder="" id="accidentSite" name="accidentSite">
				</div>
				<div class="col-4"></div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-2">事故内容：</label>
				<div class="formControls col-5">
					<textarea style="margin: 0px; width: 690px; height: 126px;" readonly= "true"
						rows="5" cols="30" id="content" name="content"></textarea>
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>审批内容：</label>
				<div class="formControls col-5">
					<textarea style="margin: 0px; width: 690px; height: 126px;" 
						rows="5" cols="30" id="remark" name="remark"></textarea>
				</div>
				<div class="col-4"></div>
			</div>
			<input type="hidden" id="accidentNo" name="accidentNo" />
			<div class="row cl">
				<div class="col-10 col-offset-2">
					
					<button id="ok" onclick="btn_Opt();" class="btn btn-primary radius"
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
	if (id > 0) {
		$("#accidentNo").val(id);
	} else {
		$("#accidentNo").val(0);
	}
	$(function() {
		if (id > 0) {
			var data = {};
			data["act"] = "get";
			data["data"] = id;
			$.ajax({
				url : '${pageContext.request.contextPath}/servlet/AccidentServlet',
				data : data,
				type : 'post',
				cache : false,
				dataType : 'json',
				success : function(r) {
					if (r.statusID > 0) {
						Json2Form(JSON.parse(r.message));
					} else {
						alert(r.message);
					}
				},
				error : function() {
					alert("操作失败");
				}
			});
			//查询审批表		
			data["act"] = "approvalget";
			data["data"] = id;
			$.ajax({
				url : '${pageContext.request.contextPath}/servlet/AccidentServlet',
				data : data,
				type : 'post',
				cache : false,
				dataType : 'json',
				success : function(r) {
					if (r.statusID > 0) {
						var obj = JSON.parse(r.message);
						$("#remark").val(obj.remark);
						if(obj.status!=2){
							$("#remark").attr("readonly","readonly");
							$("#ok").hide();
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
	
	function btn_Opt() {
		var data = {};

		data["act"] = "approvaladd";
		data["data"] = $.toJSON($("#form").serializeObject());
		$.ajax({
			url : '${pageContext.request.contextPath}/servlet/AccidentServlet',
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
