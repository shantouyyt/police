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
<title>现场证据</title>

</head>

<body>
	<div class="pd-20">

		<form action="" method="post" class="form form-horizontal" id="form">

			<div class="row cl">
				<label class="form-label col-2">事故地点：</label>
				<div class="formControls col-5">
					<input maxlength="100" type="text" class="input-text" value=""
						placeholder="" id="sGDD" name="sGDD">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-2">天气：</label>
				<div class="formControls col-5">
					<input maxlength="100" type="text" class="input-text" value=""
						placeholder="" id="tQ" name="tQ">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-2">轻伤人数：</label>
				<div class="formControls col-5">
					<input maxlength="100" type="text" class="input-text" value=""
						placeholder="" id="qSRS" name="qSRS">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-2">事故时间：</label>
				<div class="formControls col-2">
					<input name="createDate" type="text"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						id="createDate" class="input-text Wdate">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-2">失踪人数：</label>
				<div class="formControls col-5">
					<input maxlength="100" type="text" class="input-text" value=""
						placeholder="" id="sZRS" name="sZRS">
				</div>
				<div class="col-4"></div>
			</div>

			<div class="row cl">
				<label class="form-label col-2">直接经济损失：</label>
				<div class="formControls col-5">
					<input maxlength="20" type="text" class="input-text" value=""
						placeholder="" id="zJJJSS" name="zJJJSS">
				</div>
				<div class="col-4"></div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-2">事故原因1：</label>
				<div class="formControls col-5">
					<input maxlength="20" type="text" class="input-text" value=""
						placeholder="" id="yJ1" name="yJ1">
				</div>
				<div class="col-4"></div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-2">事故原因2：</label>
				<div class="formControls col-5">
					<input maxlength="20" type="text" class="input-text" value=""
						placeholder="" id="yJ2" name="yJ2">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-2">死亡人数：</label>
				<div class="formControls col-5">
					<input maxlength="20" type="text" class="input-text" value=""
						placeholder="" id="sWRS" name="sWRS">
				</div>
				<div class="col-4"></div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-2">道路宽度：</label>
				<div class="formControls col-5">
					<input maxlength="20" type="text" class="input-text" value=""
						placeholder="" id="dLKD" name="dLKD">
				</div>
				<div class="col-4"></div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-2">重伤人数：</label>
				<div class="formControls col-5">
					<input maxlength="20" type="text" class="input-text" value=""
						placeholder="" id="zSRS" name="zSRS">
				</div>
				<div class="col-4"></div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-2">交通方式：</label>
				<div class="formControls col-5">
					<input maxlength="20" type="text" class="input-text" value=""
						placeholder="" id="jTFS" name="jTFS">
				</div>
				<div class="col-4"></div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-2">备注：</label>
				<div class="formControls col-5">
					<textarea style="margin: 0px; width: 690px; height: 126px;"
						rows="5" cols="30" id="bZ" name="bZ"></textarea>
				</div>
				<div class="col-4"></div>
			</div>
			<input type="hidden" id="accidentNO" name="accidentNO" />
			<input type="hidden" id="id" name="id" value="0" />
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
	var id = getUrlVar("id");
	if (id > 0) {
		$("#accidentNO").val(id);
	} else {
		$("#accidentNO").val(0);
	}

	$(function() {
		if (id > 0) {
			var data = {};
			data["act"] = "getAccidentNo";
			data["data"] = id;
			$
					.ajax({
						url : '${pageContext.request.contextPath}/servlet/EvidenceServlet',
						data : data,
						type : 'post',
						cache : false,
						dataType : 'json',
						success : function(r) {
							if (r.statusID > 0) {
								Json2Form(JSON.parse(r.message));
							}
						}
					});
		}
	});
	function btn_Opt() {
		var data = {};
		if ($("#id") > 0) {
			data["act"] = "update";
		} else {
			data["act"] = "add";
		}
		data["data"] = $.toJSON($("#form").serializeObject());
		$.ajax({
			url : '${pageContext.request.contextPath}/servlet/EvidenceServlet',
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
