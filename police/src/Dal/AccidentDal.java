package Dal;

import java.util.List;

import DBManager.JDBCJNDI;
import Model.AccidentInfo;
import Model.DriverInfo;
import Model.InPoliceInfo;
import Utils.StringHelper;
import Utils.JqTable.jqOutInfo;

public class AccidentDal {

	public int Insert(AccidentInfo info) {
		String sql = "insert into Accident(UserID,TrafficMode,AccidentSite,CreateDate,Content,Status,Name,Tel,Sex) values(?,?,?,?,?,?,?,?,?)";
		Object[] para = new Object[] { info.getUserID(), info.getTrafficMode(),
				info.getAccidentSite(), info.getCreateDate(),
				info.getContent(), info.getStatus(), info.getName(),
				info.getTel(), info.getSex() };
		return JDBCJNDI.update(sql, para, false);
	}

	public int Update(AccidentInfo info) {
		String sql = "update Accident set TrafficMode=?,AccidentSite=?,Content=?,Name=?,Tel=?,Sex=? where ID=?";
		Object[] para = new Object[] { info.getTrafficMode(),
				info.getAccidentSite(), info.getContent(), info.getName(),
				info.getTel(), info.getSex(), info.getId() };
		return JDBCJNDI.update(sql, para, false);
	}

	/**
	 * 更新状态
	 * 
	 * @param Status
	 * @param ID
	 * @return
	 */
	public int UpdateStatus(int Status, int ID) {
		String sql = "update Accident set Status=? where ID=?";
		Object[] para = new Object[] { Status, ID };
		return JDBCJNDI.update(sql, para, false);
	}

	public jqOutInfo<AccidentInfo> List(AccidentInfo info, int iDisplayStart,
			int iDisplayLength) {

		String sql = "select Accident.*,accidentapproval.status as approvalstatus from Accident left join accidentapproval on accident.id = accidentapproval.AccidentNo  where 1=1 ";

		StringBuilder sb = new StringBuilder();
		if (info.getId() > 0) {
			sb.append(" and Accident.ID =").append(info.getId()).append("");
		}
		if(info.getStatus() > 0){
			sb.append(" and Accident.Status =").append(info.getStatus()).append("");
		}
		if(info.getApprovalstatus()==2){
			//待审批
			sb.append(" and accidentapproval.Status is null ");
		}
		else if(info.getApprovalstatus() > 0){
			sb.append(" and accidentapproval.Status =").append(info.getApprovalstatus()).append("");
		}
		
		if (info.getUserID() > 0) {
			sb.append(" and Accident.UserID =").append(info.getUserID()).append("");
		}
		if (!StringHelper.IsStrNull(info.getCreateDate())) {
			sb.append(" and Accident.CreateDate >='").append(info.getCreateDate())
					.append("' ");
		}
		if (!StringHelper.IsStrNull(info.getEndDate())) {
			sb.append(" and Accident.CreateDate <='").append(info.getEndDate())
					.append("' ");
		}
		if (!StringHelper.IsStrNull(info.getAccidentSite())) {
			sb.append(" and Accident.AccidentSite like '%")
					.append(info.getAccidentSite()).append("%' ");
		}

		String countsql = "select count(1) from Accident where 1=1 ";
		int count = JDBCJNDI.count(countsql + sb.toString());

		List<AccidentInfo> list = JDBCJNDI.findPage(AccidentInfo.class, sql
				+ sb.toString(), iDisplayStart, iDisplayLength);

		jqOutInfo<AccidentInfo> joi = new jqOutInfo<AccidentInfo>("", count,
				count, list);
		return joi;
	}

	public AccidentInfo GetInfoByID(int id) {
		String sql = "select * from Accident where id=" + id;
		return JDBCJNDI.queryInfo(AccidentInfo.class, sql, null);
	}

	public int Delete(int id) {
		String sql = "delete from  Accident where id=" + id;
		return JDBCJNDI.update(sql, null, false);
	}
}
