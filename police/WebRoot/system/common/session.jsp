<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*,Model.UsersInfo" %>
<%
	UsersInfo usersInfo = null;
	Object name = session.getAttribute("UsersInfo");
	if (name == null) {
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}else{
		usersInfo = (UsersInfo)name;
		if(usersInfo.getUserType() != 3){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
	}
%>