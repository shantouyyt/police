<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="../common/head.jsp" %>
	<title>接警管理</title>
  </head>
  
  <body>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页  <span class="c-gray en">&gt;</span> 接警管理 
	    <a class="btn btn-success radius r mr-20" style="line-height: 1.6em; margin-top: 3px" 
	    	href="#"  onclick="aupdate();"  title="刷新">
	    	<i class="Hui-iconfont">&#xe68f;</i>
	   	</a>
	   	
	   
   	</nav>
    <div class="pd-20">
    	
        <form id="searchform">
            <div class="text-c">
                                       日期范围：
                <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="datemin" name="createDate" class="input-text Wdate" style="width: 170px;">
                    -
                <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="datemax" name="endDate" class="input-text Wdate" style="width: 170px;">
                
                <input type="text" class="input-text" style="width: 250px" placeholder="报警人" id="un" name="name">
                <button onclick="search();" type="button" class="btn btn-success radius" id="Button1" name=""><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
            </div>
        </form>
        
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <span class="l">
                <a class="btn btn-primary radius" data-title="添加接警" _href="InPoliceAdd.jsp" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加接警</a>
            </span>
        </div>

        <div class="mt-20">
            <table class="table table-border table-bordered table-hover table-bg table-sort" id="datatable">
                <thead>
                    <tr class="text-c">
                        <th width="8%">事故编号</th>
                        <th width="8%">报警人</th>
                        <th width="5%">性别</th>
                        <th width="15%">报警日期</th>
                        <th width="20%">备注</th>
                        <th width="10%">手机号码</th>
                        <th width="25%">操作</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
  </body>
  
  <%@ include file="../common/script.jsp" %>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
		var table;
        $(function () {
            table = $('#datatable').dataTable({
               	"iDisplayLength": 5, //每页显示10条数据
            	"bFilter": false,
	            "bProcessing": true,
	            "sAjaxSource": "${pageContext.request.contextPath}/servlet/InPoliceServlet",
	            "bSort": false,
	            "bLengthChange": false,
	            "bServerSide": true,
                "aoColumns": [
                    { "mData": "id"},
                    { "mData": "name"},
                    { 
                    	"mData": "sex",
                    	"render": function (data, type, full, meta) {
                    		if(data=="1"){
                    			return "男";
                    		}
                    		return "女";
                    	}
                   	},
                    { "mData": "createDate" },
                    { "mData": "remark" },
                    { "mData": "tel"},
                    {
                        "mData": "",
                        "render": function (data, type, full, meta) {
                            var html = "";
        					html = '<a data-title="添加接警" _href="InPoliceAdd.jsp?id='+full.id+'" onclick="Hui_admin_tab(this)" href="javascript:;" ">修改</a>';
                            html += '&nbsp;&nbsp;<a href="#" onclick="operation(\'delete\',' + full.id + ',this);">删除</a>';
                            html += '&nbsp;&nbsp;<a data-title="添加现场证据" _href="EvidentAdd.jsp?id='+full.id+'" onclick="Hui_admin_tab(this)" href="javascript:;" ">添加现场证据</a>';
                            html += '&nbsp;&nbsp;<a data-title="出警" _href="OutPoliceAdd.jsp?id='+full.id+'" onclick="Hui_admin_tab(this)" href="javascript:;" ">出警</a>';
                            
                            return html;
                        }
                    },
                ],
				
                "fnServerData": fnServerData
            });


            $('.table-sort tbody').on('click', 'tr', function () {
                if ($(this).hasClass('selected')) {
                    $(this).removeClass('selected');
                }
                else {
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
        //删除
        function operation(ActionName, UserID, Row) {
			if(ActionName=="delete"){
				var data = {};
				data["act"] = "delete";
				data["data"] = UserID;
				$.ajax({
					url : '${pageContext.request.contextPath}/servlet/InPoliceServlet',
					data : data,
					type : 'post',
					cache : false,
					dataType : 'json',
					success : function(r) {
						alert(r.message);
						if(r.statusID > 0){
							
							var len = $("#datatable").find("tr").length;
							if(len==2){
								table.fnPageChange('previous', true);
							}else{
								//当前页刷新
				                var _iDisplayStart = table.fnSettings()._iDisplayStart;
				                var _iDisplayLength = table.fnSettings()._iDisplayLength;
				                var page = _iDisplayStart / _iDisplayLength;
				                table.fnPageChange(page);
			                }
			                
		                }
					},
					error : function() {
						alert("操作失败");
					}
				});
			}
        }
        
        function aupdate(){
        	//table.fnDraw();
        	location.reload();
        }
		
	</script>
</html>
