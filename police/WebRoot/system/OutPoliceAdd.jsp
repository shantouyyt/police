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
<title>增加接警</title>

</head>

<body>
	<div class="pd-20">

		<form action="" method="post" class="form form-horizontal" id="form">
			
			<div class="row cl">
                <label class="form-label col-2">出警：</label>
                <div class="formControls col-5">
                    <a class="btn radius btn-primary size-L" href="#myModal" data-toggle="modal">添加警员</a>
                    <!--用来存放商品item-->
                    <div id="divProList" class="uploader-list">
                    </div>
                </div>
                <div class="col-4"></div>
            </div>
			
		
			<input type="hidden" id="id" name="id" />
			<div class="row cl">
				<div class="col-10 col-offset-2">
					<button onclick="btn_Opt();" class="btn btn-primary radius"
						type="button">
						<i class="Hui-iconfont">&#xe632;</i> 保存
					</button>

					<button onclick="removeIframe();" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
		
		<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-header">
                <h3 id="myModalLabel">警员信息</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body" style="height:300px">
                <table class="table table-border table-bordered table-hover table-bg table-sort" id="datatable">
	                <thead>
	                    <tr class="text-c">
	                        <th width="10%">警员编号</th>
	                        <th width="10%">用户名</th>
	                        <th width="10%">操作</th>
	                    </tr>
	                </thead>
	                <tbody>
	                </tbody>
	            </table>
            </div>
            <div class="modal-footer">
                
                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
         

	</div>
</body>

<%@ include file="../common/script.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/bootstrap-modal/2.2.4/bootstrap-modalmanager.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/bootstrap-modal/2.2.4/bootstrap-modal.js"></script>
<script>
	var id = getUrlVar("id");
	if(id > 0){
		$("#id").val(id);
	}else{
		$("#id").val(0);
	}
	
	var table;
        $(function () {
            table = $('#datatable').dataTable({
               	"iDisplayLength": 5, //每页显示10条数据
            	"bFilter": false,
	            "bProcessing": true,
	            "sAjaxSource": "${pageContext.request.contextPath}/servlet/UsersServlet",
	            "bSort": false,
	            "bLengthChange": false,
	            "bServerSide": true,
                "aoColumns": [
                    { "mData": "no"},
                    { "mData": "userName"},

 
                    {
                        "mData": "",
                        "render": function (data, type, full, meta) {
                            var html = "";
        					
                            html += '&nbsp;&nbsp;<a href="#" onclick="operation(\'delete\',' + full.id + ',\'' + full.userName + '\');">选择</a>';
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
			data["data"] = '{"createDate":"","endDate":"","userName":""}';
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
        var $user = $("#divProList");
        function operation(ActionName, UserID, name) {
        	var flag = $user.find('div[id="'+UserID+'"]');
        	if(flag==undefined || flag.length==0){
	        	var $li = $(
	                     '<div id="' + UserID + '" class="file-item thumbnail">' +
	                         name +
	                         '<input type="button" class="input-text" value="删除" onclick="delUser('+UserID+')" />' +
	                     '</div>'
	                     );
	 
	            $li.appendTo($user);
            }
        }
        function delUser(UserID){
          var div=$user.find('div[id="'+UserID+'"]');
          div.remove();
        }
	function btn_Opt() {
		var data = {};
		if(id>0){
			data["act"] = "update";
		}else{
			data["act"] = "add";
		}
		data["data"] = $.toJSON($("#form").serializeObject());
		$.ajax({
			url : '${pageContext.request.contextPath}/servlet/InPoliceServlet',
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
