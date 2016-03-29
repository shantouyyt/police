package Dal;

import java.util.List;

import DBManager.JDBCJNDI;
import Model.AccidentApprovalInfo;
import Model.AccidentResponseInfo;
import Utils.StringHelper;
import Utils.JqTable.jqOutInfo;

public class AccidentResponseDal {

	public int Insert(AccidentResponseInfo info) {
		String sql = "insert into AccidentResponse(UserID,AccidentNo,Remark,Status,CreateDate) values(?,?,?,?,?)";
		Object[] para = new Object[] { info.getUserID(), info.getAccidentNo(),
				info.getRemark(), info.getStatus(), info.getCreateDate() };
		return JDBCJNDI.update(sql, para, false);
	}

	public int UpdateStatus(int Status, int ID) {
		String sql = "update AccidentResponse set Status=? where ID=?";
		Object[] para = new Object[] { Status, ID };
		return JDBCJNDI.update(sql, para, false);
	}

	public jqOutInfo<AccidentResponseInfo> List(AccidentResponseInfo info,
			int iDisplayStart, int iDisplayLength) {

		String sql = "select * from AccidentResponse where 1=1 ";

		StringBuilder sb = new StringBuilder();
		if (!StringHelper.IsStrNull(info.getAccidentNo())) {
			sb.append(" and AccidentNo like '%").append(info.getAccidentNo())
					.append("%' ");
		}
		if (info.getStatus() > 0) {
			sb.append(" and Status =").append(info.getStatus()).append("");
		}
		if (info.getUserID() > 0) {
			sb.append(" and UserID =").append(info.getUserID()).append("");
		}
		if (!StringHelper.IsStrNull(info.getCreateDate())) {
			sb.append(" and CreateDate >='").append(info.getCreateDate())
					.append("' ");
		}
		if (!StringHelper.IsStrNull(info.getEndDate())) {
			sb.append(" and CreateDate <='").append(info.getEndDate())
					.append("' ");
		}

		String countsql = "select count(1) from AccidentResponse where 1=1 ";
		int count = JDBCJNDI.count(countsql + sb.toString());

		List<AccidentResponseInfo> list = JDBCJNDI.findPage(
				AccidentResponseInfo.class, sql + sb.toString(), iDisplayStart,
				iDisplayLength);

		jqOutInfo<AccidentResponseInfo> joi = new jqOutInfo<AccidentResponseInfo>(
				"", count, count, list);
		return joi;
	}

	public AccidentResponseInfo GetInfoByID(int id) {
		String sql = "select * from AccidentResponse where id=" + id;
		return JDBCJNDI.queryInfo(AccidentResponseInfo.class, sql, null);
	}

	public AccidentResponseInfo GetInfoByAccidentNo(String AccidentNo) {
		String sql = "select * from AccidentResponse where AccidentNo='"
				+ AccidentNo + "'";
		return JDBCJNDI.queryInfo(AccidentResponseInfo.class, sql, null);
	}

	public int Delete(int id) {
		String sql = "delete from  AccidentResponse where id=" + id;
		return JDBCJNDI.update(sql, null, false);
	}

	public int Update(AccidentResponseInfo info) {
		String sql = "update AccidentResponse set Remark=? where ID=?";
		Object[] para = new Object[] { info.getRemark(), info.getId() };
		return JDBCJNDI.update(sql, para, false);
	}

}
