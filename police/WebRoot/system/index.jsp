<%@ page language="java" import="java.util.*,Model.UsersInfo" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="../common/head.jsp" %>
	<title>管理员-管理系统</title>
  </head>
  
  <body>
     <form id="form1" >
        <header class="Hui-header cl">
              <span class="Hui-subtitle l"> &nbsp; &nbsp;管理后台</span>

            <ul class="Hui-userbar">
                
                <li class="dropDown dropDown_hover">
                	<a href="#" class="dropDown_A"><%=((UsersInfo)session.getAttribute("UsersInfo")).getUserName() %>
                		<i class="Hui-iconfont">&#xe6d5;</i>
                	</a>
                    <ul class="dropDown-menu radius box-shadow">
                        <li><a href="${pageContext.request.contextPath}/servlet/LoginServlet?act=logout">退出</a></li>
                    </ul>
                </li>

            </ul>
            <a href="javascript:;" class="Hui-nav-toggle Hui-iconfont" aria-hidden="false">&#xe667;</a>
        </header>
        <aside class="Hui-aside">
            <input runat="server" id="divScrollValue" type="hidden" value="" />
            <div class="menu_dropdown bk_2">
                <dl id="menu-article">
                    <li><a _href="InPoliceList.jsp" data-title="接警管理" href="javascript:void(0)">接警管理</a></li>
                </dl>
                <dl id="Dl17">
                    <li><a _href="OutPoliceList.jsp" data-title="出警管理" href="javascript:void(0)">出警管理</a></li>
                </dl>
                
                <dl id="Dl16">
                    <li><a _href="EvidenceList.jsp" data-title="现场证据" href="javascript:void(0)">现场证据</a></li>
                </dl>
                
                <dl id="Dl12">
                    <li><a _href="UsersList.jsp" data-title="警员管理" href="javascript:void(0)">警员管理</a></li>
                </dl>
                <dl id="Dl13">
                    <li><a _href="DriverList.jsp" data-title="管理员管理" href="javascript:void(0)">驾驶员管理</a></li>
                </dl>
                
                <dl id="Dl33">
                    <li><a _href="TrafficMode.jsp" data-title="交通方式统计" href="javascript:void(0)">交通方式统计</a></li>
                </dl>
                <dl id="Dl33">
                    <li><a _href="Backup.jsp" data-title="事故地点统计" href="javascript:void(0)">事故地点统计</a></li>
                </dl>
                
                <dl id="Dl14">
                    <li><a _href="Backup.jsp" data-title="备份还原" href="javascript:void(0)">备份还原</a></li>
                </dl>
                
                <dl id="Dl15">
                    <li><a _href="OutPoliceExcel.jsp" data-title="数据导出" href="javascript:void(0)">数据导出</a></li>
                </dl>
                
     

            </div>
        </aside>
        <div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);" onclick="displaynavbar(this)"></a></div>
        <section class="Hui-article-box">
            <div id="Hui-tabNav" class="Hui-tabNav">
                <div class="Hui-tabNav-wp">
                    <ul id="min_title_list" class="acrossTab cl">
                        <li class="active"><span title="首页" data-href="welcome.html">首页</span><em></em></li>
                    </ul>
                </div>
                <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
            </div>
            <div id="iframe_box" class="Hui-article">
                <div class="show_iframe" style="text-align:center">
                    <div style="display: none" class="loading"></div>
                    <%--<iframe scrolling="yes" frameborder="0" src="welcome.html"></iframe>--%>
                    
                    <p class="f-20 text-success" style="margin-top: 138px;font-size: 38px;">管理员后台！</p>

                </div>
            </div>
        </section>
    </form>
  </body>
  
  <%@ include file="../common/script.jsp" %>
</html>
