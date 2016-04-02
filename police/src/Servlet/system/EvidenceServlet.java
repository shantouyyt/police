package Servlet.system;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.EvidenceInfo;
import Model.InPoliceInfo;
import Model.Result;
import Service.EvidenceService;
import Utils.StringHelper;

import com.google.gson.Gson;

public class EvidenceServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public EvidenceServlet() {
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
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
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
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		EvidenceService es = new EvidenceService();
		Result oret = Result.Fail("");
		Gson gson = new Gson();

		String act = request.getParameter("act");
		if ("add".equals(act)) {
			// 增加
			String data = request.getParameter("data");
			EvidenceInfo bean = gson.fromJson(data, EvidenceInfo.class);
			oret = es.Insert(bean);
		} else if ("get".equals(act)) {
			// 得到单个实体
			String data = request.getParameter("data");
			EvidenceInfo info = es.GetInfoByID(StringHelper.Str2Int(data));
			// 输出
			if (info == null) {
				oret.statusID = 0;
				oret.message = "查询失败";
			} else {
				oret.statusID = 1;
				oret.message = info.toString();
			}
		}else if ("getAccidentNo".equals(act)) {
			// 得到单个实体
			String data = request.getParameter("data");
			EvidenceInfo info = es.GetInfoByAccidentNo(data);
			// 输出
			if (info == null) {
				oret.statusID = 0;
				oret.message = "查询失败";
			} else {
				oret.statusID = 1;
				oret.message = info.toString();
			}
		}  else if ("delete".equals(act)) {
			// 删除
			String data = request.getParameter("data");
			oret = es.Delete(StringHelper.Str2Int(data));
		}

		String json = gson.toJson(oret);
		out.print(json);

		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
