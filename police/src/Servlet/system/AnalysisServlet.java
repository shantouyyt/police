package Servlet.system;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.AnalysisInfo;
import Model.Result;
import Service.AnalysisService;
import Utils.WebUtils;
import Utils.JqTable.jqOutInfo;
import Utils.JqTable.jqProcessInfo;

import com.google.gson.Gson;

public class AnalysisServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AnalysisServlet() {
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

		AnalysisService es = new AnalysisService();
		Result oret = Result.Fail("");
		Gson gson = new Gson();

		String act = request.getParameter("act");
		if ("add".equals(act)) {
			// 增加
			String data = request.getParameter("data");
			AnalysisInfo bean = gson.fromJson(data, AnalysisInfo.class);
			oret = es.Insert(bean);
		} else if ("list".equals(act)) {
			// 查询列表
			jqProcessInfo jpi = WebUtils.GetJqProcessInfo(request);

			String data = request.getParameter("data");
			AnalysisInfo bean = gson.fromJson(data, AnalysisInfo.class);

			jqOutInfo<AnalysisInfo> oinfo = es.List(bean,
					jpi.getiDisplayStart(), jpi.getiDisplayLength());

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
