package Servlet.system;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.InPoliceInfo;
import Model.OutPoliceInfo;
import Model.Result;
import Service.OutPoliceService;
import Utils.StringHelper;
import Utils.WebUtils;
import Utils.JqTable.jqOutInfo;
import Utils.JqTable.jqProcessInfo;

import com.google.gson.Gson;

public class OutPoliceServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OutPoliceServlet() {
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

		OutPoliceService os = new OutPoliceService();
		Result oret = Result.Fail("");
		Gson gson = new Gson();

		String act = request.getParameter("act");
		if ("add".equals(act)) {
		
			String UserID = request.getParameter("UserID");
			String InPoliceID = request.getParameter("InPoliceID");
			
			//删除
			oret = os.Delete(StringHelper.Str2Int(InPoliceID));
			
			// 增加
			String date = StringHelper.GetCurrentDate();
			String[] UserIDs = UserID.split(",");
			for (String uid : UserIDs) {
				OutPoliceInfo info = new OutPoliceInfo();
				info.setUserID(StringHelper.Str2Int(uid));
				info.setInPoliceID(StringHelper.Str2Int(InPoliceID));
				info.setCreateDate(date);
				
				oret = os.Insert(info);
			}

		}else if ("list".equals(act)) {
			// 查询列表
			jqProcessInfo jpi = WebUtils.GetJqProcessInfo(request);

			String InPoliceID = request.getParameter("InPoliceID");
			
//			String data = request.getParameter("data");
//			OutPoliceInfo bean = gson.fromJson(data, OutPoliceInfo.class);
			
			OutPoliceInfo bean = new OutPoliceInfo();
			bean.setInPoliceID(StringHelper.Str2Int(InPoliceID));

			jqOutInfo<OutPoliceInfo> oinfo = os.List(bean,
					jpi.getiDisplayStart(), jpi.getiDisplayLength());

			oinfo.setsEcho(jpi.getsEcho());
			// 输出
			out.print(oinfo.toString());
			out.flush();
			out.close();
			return;
		}else if ("listDistinct".equals(act)) {
			// 查询列表
			jqProcessInfo jpi = WebUtils.GetJqProcessInfo(request);

			String data = request.getParameter("data");
			OutPoliceInfo bean = gson.fromJson(data, OutPoliceInfo.class);

			jqOutInfo<OutPoliceInfo> oinfo = os.ListDistinct(bean,
					jpi.getiDisplayStart(), jpi.getiDisplayLength());

			oinfo.setsEcho(jpi.getsEcho());
			// 输出
			out.print(oinfo.toString());
			out.flush();
			out.close();
			return;
		} else if ("delete".equals(act)) {
			// 删除
			String data = request.getParameter("data");
			oret = os.Delete(StringHelper.Str2Int(data));
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
