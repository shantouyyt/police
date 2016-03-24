package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.InPoliceInfo;
import Model.Result;
import Model.UsersInfo;
import Service.UsersService;
import Utils.StringHelper;
import Utils.WebUtils;
import Utils.JqTable.jqOutInfo;
import Utils.JqTable.jqProcessInfo;

import com.google.gson.Gson;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred http://wangcheng.iteye.com/blog/550831
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// 生成Service实体
		UsersService us = new UsersService();
		Result oret = Result.Fail("");
		Gson gson = new Gson();

		String act = request.getParameter("act");
		if ("login".equals(act)) {
			// 判断验证码
			String rCode = (String) request.getSession().getAttribute("rCode");
			String validcode = request.getParameter("validcode");
			if (StringHelper.IsStrNull(validcode)) {
				oret = Result.Fail("请输入验证码");
			} else if (StringHelper.IsStrNull(rCode)) {
				oret = Result.Fail("验证码过期");
			} else if (!validcode.equals(rCode)) {
				oret = Result.Fail("验证码不对");
			} else {
				// 得到界面传过来的参数
				UsersInfo bean = WebUtils
						.request2Bean(request, UsersInfo.class);
				// 登录
				Result ret = us.Login(bean);
				if (ret.statusID > 0) {
					// 存session
					UsersInfo retInfo = (UsersInfo) ret.obj;
					request.getSession().setAttribute("UsersInfo", retInfo);
					if (retInfo.getUserType() == 3) {
						// 系统管理员
						ret.message = request.getContextPath()
								+ "/system/index.jsp";
					} else if (retInfo.getUserType() == 2) {
						// 领导
						ret.message = request.getContextPath()
								+ "/leader/index.jsp";
					} else {
						// 警员
						ret.message = request.getContextPath()
								+ "/user/index.jsp";
					}
					// 清空验证码
					request.getSession().removeAttribute("rCode");
				}
				oret = Result.Create(ret.statusID, ret.message);
			}

		} else if ("reg".equals(act)) {
			// 注册
			String data = request.getParameter("data");
			UsersInfo bean = gson.fromJson(data, UsersInfo.class);
			oret = us.Register(bean);
		} else if ("logout".equals(act)) {
			// 退出
			// 清空session
			request.getSession().removeAttribute("UsersInfo");
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} else if ("list".equals(act)) {
			// 查询列表
			jqProcessInfo jpi = WebUtils.GetJqProcessInfo(request);

			String data = request.getParameter("data");
			UsersInfo bean = gson.fromJson(data, UsersInfo.class);

			jqOutInfo<UsersInfo> oinfo = us.List(bean, jpi.getiDisplayStart(),
					jpi.getiDisplayLength());
			oinfo.setsEcho(jpi.getsEcho());
			// 输出
			out.print(oinfo.toString());
			out.flush();
			out.close();
			return;
		}

		String json = gson.toJson(oret);
		out.print(json);

		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
