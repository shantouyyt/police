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
                <dl id="Dl12">
                    <li><a _href="UsersList.jsp" data-title="警员管理" href="javascript:void(0)">警员管理</a></li>
                </dl>
                <dl id="Dl13">
                    <li><a _href="article-list.html" data-title="管理员管理" href="javascript:void(0)">管理员管理</a></li>
                </dl>
                <dl id="Dl14">
                    <li><a _href="article-list.html" data-title="商家管理" href="javascript:void(0)">备份还原管理</a></li>
                </dl>
                <dl id="Dl15">
                    <li><a _href="article-list.html" data-title="商品管理" href="javascript:void(0)">商品管理</a></li>
                </dl>
                <dl id="Dl1">
                    <li><a _href="/system/userlist.aspx" data-title="用户管理" href="javascript:void(0)">用户管理</a></li>
                </dl>


                <!-- <dl id="Dl12">
			<dt><i class="Hui-iconfont">&#xe616;</i> 修改密码<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="article-list.html" data-title="修改密码" href="javascript:void(0)">修改密码</a></li>
				</ul>
			</dd>
		</dl>-->

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
                    
                    <p class="f-20 text-success">管理后台！</p>

                </div>
            </div>
        </section>
    </form>
  </body>
  
  <%@ include file="../common/script.jsp" %>
</html>
