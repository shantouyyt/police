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
<title>驾驶员管理</title>

</head>

<body>
	<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span> 驾驶员管理 <a
		class="btn btn-success radius r mr-20"
		style="line-height: 1.6em; margin-top: 3px" href="#"
		onclick="aupdate();" title="刷新"> <i class="Hui-iconfont">&#xe68f;</i>
	</a> </nav>
	<div class="pd-20">

		<form id="searchform">
			<div class="text-c">
				<input type="text" class="input-text" style="width: 250px"
					placeholder="姓名" id="un" name="name"> 
				<input type="text"
					class="input-text" style="width: 250px" placeholder="驾驶证" id="license"
					name="license">
				<button onclick="search();" type="button"
					class="btn btn-success radius" id="Button1" name="">
					<i class="Hui-iconfont">&#xe665;</i> 搜用户
				</button>
			</div>
		</form>



		<div class="mt-20">
			<table
				class="table table-border table-bordered table-hover table-bg table-sort"
				id="datatable">
				<thead>
					<tr class="text-c">
						<th width="10%">姓名</th>
						<th width="10%">性别</th>
						<th width="10%">手机号码</th>
						<th width="10%">驾驶证</th>
						<th width="20%">驾驶证到期日期</th>
						<th width="20%">创建日期</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</body>

<%@ include file="../common/script.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">
	var table;
	$(function() {
		table = $('#datatable')
				.dataTable(
						{
							"iDisplayLength" : 5, //每页显示10条数据
							"bFilter" : false,
							"bProcessing" : true,
							"sAjaxSource" : "${pageContext.request.contextPath}/servlet/DriverServlet",
							"bSort" : false,
							"bLengthChange" : false,
							"bServerSide" : true,
							"aoColumns" : [
									{
										"mData" : "name"
									},
									{
										"mData" : "sex",
										"render" : function(data, type, full,
												meta) {
											if (data == "1") {
												return "男";
											}
											return "女";
										}
									},
									{
										"mData" : "tel"
									},

									{
										"mData" : "license"
									},
									{
										"mData" : "licenseExpire"
									},
									{
										"mData" : "createDate"
									},
									{
										"mData" : "",
										"render" : function(data, type, full,
												meta) {
											var html = "";
											html = '<a data-title="修改驾驶员" _href="DriverAdd.jsp?id='
													+ full.id
													+ '" onclick="Hui_admin_tab(this)" href="javascript:;" >查看</a>';
													
											return html;
										}
									}, ],

							"fnServerData" : fnServerData
						});

		$('.table-sort tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});
	});

	function fnServerData(sSource, aoData, fnCallback) {

		var data = {};
		data["act"] = "list";
		data["data"] = $.toJSON($("#searchform").serializeObject());
		data["aoData"] = $.toJSON(aoData);
		$.ajax({
			url : sSource,
			data : data,
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(r) {
				fnCallback(r);
			},
			error : function() {
				//alert("操作失败");
			}
		});
	}
	//搜索
	function search() {
		table.fnDraw();
	}


	function aupdate() {
		//table.fnDraw();
		location.reload();
	}
</script>
</html>
