package Servlet.system;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import Model.AccidentApprovalInfo;
import Model.AccidentInfo;
import Model.AccidentResponseInfo;
import Model.DriverInfo;
import Model.EvidenceInfo;
import Model.InPoliceInfo;
import Model.OutPoliceInfo;
import Model.UsersInfo;
import Service.AccidentApprovalService;
import Service.AccidentResponseService;
import Service.AccidentService;
import Service.DriverService;
import Service.EvidenceService;
import Service.InPoliceService;
import Service.OutPoliceService;
import Service.UsersService;
import Utils.ExportExcel;
import Utils.StringHelper;
import Utils.WebUtils;
import Utils.JqTable.jqOutInfo;
import Utils.JqTable.jqProcessInfo;

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

		String act = request.getParameter("act");
		if ("export".equals(act)) {
			// 导出用户信息
			OutPoliceService OutPoliceService = new OutPoliceService();
			jqProcessInfo jpi = WebUtils.GetJqProcessInfo(request);
			String InPoliceID = request.getParameter("InPoliceID");
			OutPoliceInfo bean = new OutPoliceInfo();
			bean.setInPoliceID(StringHelper.Str2Int(InPoliceID));
			jqOutInfo<OutPoliceInfo> oinfo = OutPoliceService.List(bean,
					jpi.getiDisplayStart(), jpi.getiDisplayLength());

			String[] Title = { "事故编号", "时间", "警员" };
			String[] Column = { "inPoliceID", "createDate", "userName" };

			ExportExcel.exportExcel(workbook, "出警信息", Title, Column,
					oinfo.aaData);
		} else {

			// 导出用户信息
			UsersService us = new UsersService();
			List<UsersInfo> ulist = us.queryList();
			String[] Title = { "ID", "用户名", "密码", "性别", "警员编号", "类型", "时间" };
			String[] Column = { "id", "userName", "passWord", "sex", "no",
					"userType", "createDate" };
			ExportExcel.exportExcel(workbook, "个人信息", Title, Column, ulist);

			// 出警信息
			OutPoliceService OutPoliceService = new OutPoliceService();
			List<OutPoliceInfo> oslist = OutPoliceService.queryList();
			String[] OutPoliceInfoTitle = { "ID", "报警编号", "用户", "时间" };
			String[] OutPoliceInfoColumn = { "id", "inPoliceID", "userID",
					"createDate" };
			ExportExcel.exportExcel(workbook, "出警信息", OutPoliceInfoTitle,
					OutPoliceInfoColumn, oslist);

			// 接警信息
			InPoliceService InPoliceService = new InPoliceService();
			List<InPoliceInfo> InPoliceInfolist = InPoliceService.queryList();
			String[] InPoliceInfoTitle = { "ID", "事故地点", "死亡情况", "时间", "报警人信息",
					"性别", "备注", "电话" };
			String[] InPoliceInfoColumn = { "id", "sGDD", "sWQK", "createDate",
					"name", "sex", "remark", "tel" };
			ExportExcel.exportExcel(workbook, "接警信息", InPoliceInfoTitle,
					InPoliceInfoColumn, InPoliceInfolist);

			// 现场证据
			EvidenceService EvidenceService = new EvidenceService();
			List<EvidenceInfo> EvidenceInfolist = EvidenceService.queryList();
			String[] EvidenceInfoTitle = { "ID", "事故编号", "事故地点", "天气", "轻伤人数",
					"事故时间", "失踪人数", "直接经济损失", "事故原因1", "事故原因2", "死亡人数", "道路宽度",
					"重伤人数", "交通方式", "备注" };
			String[] EvidenceInfoColumn = { "id", "accidentNO", "sGDD", "tQ",
					"qSRS", "createDate", "sZRS", "zJJJSS", "yJ1", "yJ2",
					"sWRS", "dLKD", "zSRS", "jTFS", "bZ" };
			ExportExcel.exportExcel(workbook, "现场证据", EvidenceInfoTitle,
					EvidenceInfoColumn, EvidenceInfolist);

			// 驾驶员
			DriverService DriverService = new DriverService();
			List<DriverInfo> DriverInfolist = DriverService.queryList();
			String[] DriverInfoTitle = { "ID", "姓名", "性别", "电话", "驾驶证",
					"驾驶证到期时间", "地址", "创建时间", "备注" };
			String[] DriverInfoColumn = { "id", "name", "sex", "tel",
					"license", "licenseExpire", "address", "createDate",
					"remark" };
			ExportExcel.exportExcel(workbook, "驾驶员", DriverInfoTitle,
					DriverInfoColumn, DriverInfolist);

			// 事故登记表
			AccidentService AccidentService = new AccidentService();
			List<AccidentInfo> AccidentInfolist = AccidentService.queryList();
			String[] AccidentInfoTitle = { "ID", "事故编号", "事故地点", "天气", "轻伤人数",
					"事故时间", "失踪人数", "直接经济损失", "事故原因1", "事故原因2", "死亡人数", "道路宽度",
					"重伤人数", "交通方式", "备注" };
			String[] AccidentInfoColumn = { "id", "userID", "trafficMode",
					"accidentSite", "createDate", "content", "status", "name",
					"tel", "sex", "sWRS", "dLKD", "zSRS", "jTFS", "bZ" };
			ExportExcel.exportExcel(workbook, "事故登记表", AccidentInfoTitle,
					AccidentInfoColumn, AccidentInfolist);

			// 事故审批表
			AccidentApprovalService AccidentApprovalService = new AccidentApprovalService();
			List<AccidentApprovalInfo> AccidentApprovalInfolist = AccidentApprovalService
					.queryList();

			String[] AccidentApprovalInfoTitle = { "ID", "警员ID", "事故编号",
					"审批内容", "填写时间", "状态" };
			String[] AccidentApprovalInfoColumn = { "id", "userID",
					"accidentNo", "remark", "createDate", "status" };

			ExportExcel.exportExcel(workbook, "事故审批表",
					AccidentApprovalInfoTitle, AccidentApprovalInfoColumn,
					AccidentApprovalInfolist);

			// 事故定责表
			AccidentResponseService AccidentResponseService = new AccidentResponseService();
			List<AccidentResponseInfo> AccidentResponseInfolist = AccidentResponseService
					.queryList();
			
			String[] AccidentResponseInfoTitle = { "ID", "警员ID", "事故编号",
					"审批内容", "填写时间", "状态" };
			String[] AccidentResponseInfoColumn = { "id", "userID",
					"accidentNo", "remark", "createDate", "status" };
			
			ExportExcel
					.exportExcel(workbook, "事故定责表",AccidentResponseInfoTitle,AccidentResponseInfoColumn, AccidentResponseInfolist);
		}

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
