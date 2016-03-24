package Utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.UsersInfo;

/**
 * http://www.cnblogs.com/xdp-gacl/p/3948353.html
 * http://www.cnblogs.com/lanxuezaipiao/archive/2012/08/05/2623547.html
 * @author Administrator
 *
 */
public class SessionFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		
		Object obj = session.getAttribute("UsersInfo");
		if(obj==null){
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		
		String path = request.getRequestURI();
		UsersInfo info = (UsersInfo)obj;
		if(path.indexOf("/system/") > -1 && info.getUserType()!=3) {
			//管理员
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}else if(path.indexOf("/leader/") > -1 && info.getUserType()!=2) {
			//领导
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}else if(path.indexOf("/user/") > -1 && info.getUserType()!=1) {
			//用户
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		//通过，放行
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
