package Servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.AccidentApprovalInfo;
import Model.AccidentInfo;
import Model.DriverInfo;
import Model.Result;
import Model.UsersInfo;
import Service.AccidentApprovalService;
import Service.AccidentService;
import Utils.StringHelper;
import Utils.WebUtils;
import Utils.JqTable.jqOutInfo;
import Utils.JqTable.jqProcessInfo;

import com.google.gson.Gson;

public class AccidentServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AccidentServlet() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		AccidentService as = new AccidentService();
		Result oret = Result.Fail("用户未登陆或者登陆超时");
		Gson gson = new Gson();

		UsersInfo sessionInfo = (UsersInfo) request.getSession().getAttribute(
				"UsersInfo");
		if (sessionInfo == null) {
			String json = gson.toJson(oret);
			out.print(json);

			out.flush();
			out.close();
			return;
		}

		String act = request.getParameter("act");
		if ("add".equals(act)) {
			// 增加
			String data = request.getParameter("data");
			AccidentInfo bean = gson.fromJson(data, AccidentInfo.class);

			bean.setUserID(sessionInfo.getId());
			oret = as.Insert(bean);
		} else if ("list".equals(act)) {
			// 查询列表
			jqProcessInfo jpi = WebUtils.GetJqProcessInfo(request);

			String data = request.getParameter("data");
			AccidentInfo bean = gson.fromJson(data, AccidentInfo.class);

			String noCheck = request.getParameter("noCheck");
			if (!"1".equals(noCheck)) {
				// 警员
				if (sessionInfo.getUserType() == 1) {
					bean.setUserID(sessionInfo.getId());
				}
			}

			jqOutInfo<AccidentInfo> oinfo = as.List(bean,
					jpi.getiDisplayStart(), jpi.getiDisplayLength());

			oinfo.setsEcho(jpi.getsEcho());
			// 输出
			out.print(oinfo.toString());
			out.flush();
			out.close();
			return;
		} else if ("get".equals(act)) {
			// 得到单个实体
			String data = request.getParameter("data");
			AccidentInfo info = as.GetInfoByID(StringHelper.Str2Int(data));
			// 输出
			if (info == null) {
				oret.statusID = 0;
				oret.message = "查询失败";
			} else {
				oret.statusID = 1;
				oret.message = info.toString();
			}
		} else if ("update".equals(act)) {
			// 修改
			String data = request.getParameter("data");
			AccidentInfo bean = gson.fromJson(data, AccidentInfo.class);
			oret = as.Update(bean);
		} else if ("approvaladd".equals(act)) {
			// 填写审批
			String data = request.getParameter("data");
			AccidentApprovalInfo bean = gson.fromJson(data,
					AccidentApprovalInfo.class);

			bean.setUserID(sessionInfo.getId());
			AccidentApprovalService aas = new AccidentApprovalService();
			oret = aas.Insert(bean);
		} else if ("approvalget".equals(act)) {
			// 得到审批单个实体
			String data = request.getParameter("data");
			AccidentApprovalService aas = new AccidentApprovalService();
			AccidentApprovalInfo info = aas.GetInfoByAccidentNo(data);
			// 输出
			if (info == null) {
				oret.statusID = 0;
				oret.message = "查询失败";
			} else {
				oret.statusID = 1;
				oret.message = info.toString();
			}
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
