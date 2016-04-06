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
<title>交通方式</title>
</head>

<body>
	<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span> 交通方式 <a
		class="btn btn-success radius r mr-20"
		style="line-height: 1.6em; margin-top: 3px" href="#"
		onclick="aupdate();" title="刷新"> <i class="Hui-iconfont">&#xe68f;</i>
	</a> </nav>
	<div class="pd-20">
		<div id="container" style="min-width:700px;height:400px"></div>

		<form action="" method="post" class="form form-horizontal" id="form">
			<div class="row cl">

				<div class="formControls col-5">
					<textarea style="margin: 0px; width: 690px; height: 126px;"
						rows="5" cols="30" id="remark" name="remark"></textarea>
				</div>
				<div class="col-4"></div>
			</div>
			<br />
			<button onclick="btn_Opt();" class="btn btn-primary radius"
				type="button">
				<i class="Hui-iconfont">&#xe632;</i> 保存
			</button>
		</form>

	</div>
</body>

<%@ include file="../common/script.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/lib/Highcharts/4.1.7/js/highcharts.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/lib/Highcharts/4.1.7/js/modules/exporting.js"></script>
<script type="text/javascript">
	$(function() {
		var series = [];
		var data = {};
		data["act"] = "TrafficMode";
		$
				.ajax({
					url : '${pageContext.request.contextPath}/servlet/HighchartsServlet',
					data : data,
					async : false,
					type : 'post',
					cache : false,
					dataType : 'json',
					success : function(r) {
						for ( var i = 0; i < r.length; i++) {
							var obj = {};
							obj["name"] = r[i].name;
							obj["data"] = Str2Int(r[i].data);
							series.push(obj);
						}
					}
				});

		$('#container').highcharts(
				{
					title : {
						text : '交通方式',
						x : -20
					//center
					},

					xAxis : {
						categories : [ '一月', '二月', '三月', '四月', '五月', '六月',
								'七月', '八月', '九月', '十月', '十一月', '十二月' ]
					},
					yAxis : {
						title : {
							text : '数量'
						},
						plotLines : [ {
							value : 0,
							width : 1,
							color : '#808080'
						} ]
					},
					tooltip : {
						valueSuffix : ''
					},
					legend : {
						layout : 'vertical',
						align : 'right',
						verticalAlign : 'middle',
						borderWidth : 0
					},
					series : series
				});
	});

	function Str2Int(a) {
		var str = a.split(",");
		var array = [];
		for ( var i = 0; i < str.length; i++) {
			array[i] = parseInt(str[i]);
		}
		return array;
	}

	function btn_Opt() {
		var data = {};

		data["act"] = "add";

		data["data"] = $.toJSON($("#form").serializeObject());
		$.ajax({
			url : '${pageContext.request.contextPath}/servlet/AnalysisServlet',
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
