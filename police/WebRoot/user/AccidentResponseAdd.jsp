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
<title>事故定责表</title>

</head>

<body>
	<div class="pd-20">

		<form action="" method="post" class="form form-horizontal" id="form">


			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>定责内容：</label>
				<div class="formControls col-5">
					<textarea style="margin: 0px; width: 690px; height: 126px;" 
						rows="5" cols="30" id="remark" name="remark"></textarea>
				</div>
				<div class="col-4"></div>
			</div>
			<input type="hidden" id="accidentNo" name="accidentNo" />
			<input type="hidden" id="id" name="id" />
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
		var data = {};
		data["act"] = "get";
		data["data"] = id;
		$.ajax({
			url : '${pageContext.request.contextPath}/servlet/AccidentResponse',
			data : data,
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(r) {
				if (r.statusID > 0) {
					var obj = JSON.parse(r.message);
					$("#remark").val(obj.remark);
					$("#id").val(obj.id);
				} else {
					//alert(r.message);
					$("#id").val(0);
				}
			},
			error : function() {
				alert("操作失败");
			}
		});
	});
	
	function btn_Opt() {
		var data = {};
		var id = $("#id").val();
		if(id > 0){
			data["act"] = "update";
		}else{
			data["act"] = "add";
		}
		data["data"] = $.toJSON($("#form").serializeObject());
		$.ajax({
			url : '${pageContext.request.contextPath}/servlet/AccidentResponse',
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
