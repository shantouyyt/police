package Servlet.system;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import Model.UsersInfo;
import Service.UsersService;
import Utils.ExportExcel;

public class ExcelServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ExcelServlet() {
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

		doPost(request,response);
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

		// 定义输出流，以便打开保存对话框______________________begin

		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流
		response.setHeader("Content-disposition", "attachment; filename="
				+ "police.xls");
		// 设定输出文件头
		response.setContentType("application/msexcel");// 定义输出类型
		// 定义输出流，以便打开保存对话框_______________________end

		
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		
		//导出用户信息
		UsersService us = new UsersService();
		List<UsersInfo> list = us.queryList();
		String[] Title = { "ID", "用户名", "密码", "性别", "警员编号","类型", "时间" };
		ExportExcel.exportExcel(workbook,"个人信息",Title, list);
		
		ExportExcel.exportExcel(workbook,"个人信息2",Title, list);
		
		/** **********将以上缓存中的内容写到EXCEL文件中******** */
		workbook.write();
		/** *********关闭文件************* */
		try {
			workbook.close();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		os.flush();
		os.close();
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
