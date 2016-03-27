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
	<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span>事故审批表 <a
		class="btn btn-success radius r mr-20"
		style="line-height: 1.6em; margin-top: 3px" href="#"
		onclick="aupdate();" title="刷新"> <i class="Hui-iconfont">&#xe68f;</i>
	</a> </nav>
	<div class="pd-20">

		<form id="searchform">
			<div class="text-c">
				<input type="hidden" id="status" name="status" value="3" />
				<span class="select-box inline">
					<select name="approvalstatus" class="select" id="approvalstatus">
						<option value="0">全部</option>
						<option value="2">待审批</option>
						<option value="3">已通过</option>
						<option value="1">未通过</option>
					</select>
				</span>
				日期范围：
                <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="datemin" name="createDate" class="input-text Wdate" style="width: 170px;">
                    -
                <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="datemax" name="endDate" class="input-text Wdate" style="width: 170px;">
                
					
				<button onclick="search();" type="button"
					class="btn btn-success radius" id="Button1" name="">
					<i class="Hui-iconfont">&#xe665;</i> 搜索
				</button>
			</div>
		</form>
		



		<div class="mt-20">
			<table
				class="table table-border table-bordered table-hover table-bg table-sort"
				id="datatable">
				<thead>
					<tr class="text-c">
						<th width="8%">事故编号</th>
						<th width="8%">姓名</th>
						<th width="5%">性别</th>
						<th width="10%">手机号码</th>
						
						<th width="10%">交通方式</th>
						<th width="20%">事故地点</th>
						<th width="15%">事故日期</th>
						<th>状态</th>
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
							"sAjaxSource" : "${pageContext.request.contextPath}/servlet/AccidentServlet",
							"bSort" : false,
							"bLengthChange" : false,
							"bServerSide" : true,
							"aoColumns" : [
									{
										"mData" : "id"
									},
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
										"mData" : "trafficMode"
									},
									{
										"mData" : "accidentSite"
									},
									{
										"mData" : "createDate"
									},
									{
										"mData" : "approvalstatus",
										"render" : function(data, type, full,
												meta) {
											if (data == "1") {
												return "未通过";
											} else if (data == "2") {
												return "待审批";
											}
											return "已通过";
										}
									},
									{
										"mData" : "",
										"render" : function(data, type, full,meta) {
											var html = "";
											if(full.status=="3"){
												//已审批
												html += '&nbsp;&nbsp;<a data-title="填写审批" _href="AccidentApprovalAdd.jsp?id='+ full.id + '" onclick="Hui_admin_tab(this)" href="javascript:;" >填写审批</a>';
											}
													
											return html;
										}
									} ],

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
