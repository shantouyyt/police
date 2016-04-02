package Dal;

import java.util.List;

import DBManager.JDBCJNDI;
import Model.OutPoliceInfo;
import Model.UsersInfo;
import Utils.StringHelper;
import Utils.JqTable.jqOutInfo;

public class OutPoliceDal {

	public int Insert(OutPoliceInfo info) {

		String sql = "insert into OutPolice(InPoliceID,UserID,CreateDate) values(?,?,?)";
		Object[] para = new Object[] { info.getInPoliceID(), info.getUserID(),
				info.getCreateDate(), };
		return JDBCJNDI.update(sql, para, false);
	}

	public jqOutInfo<OutPoliceInfo> List(OutPoliceInfo info, int iDisplayStart,
			int iDisplayLength) {

		String sql = "select * from OutPolice where 1=1 ";

		StringBuilder sb = new StringBuilder();

		if (!StringHelper.IsStrNull(info.getCreateDate())) {
			sb.append(" and CreateDate >='").append(info.getCreateDate())
					.append("' ");
		}
		if (!StringHelper.IsStrNull(info.getEndDate())) {
			sb.append(" and CreateDate <='").append(info.getEndDate())
					.append("' ");
		}
		if (info.getUserID() > 0) {
			sb.append(" and UserID =").append(info.getUserID());
		}
		if (info.getInPoliceID() > 0) {
			sb.append(" and InPoliceID =").append(info.getInPoliceID());
		}

		String countsql = "select count(1) from OutPolice where 1=1 ";
		int count = JDBCJNDI.count(countsql + sb.toString());

		List<OutPoliceInfo> list = JDBCJNDI.findPage(OutPoliceInfo.class, sql
				+ sb.toString(), iDisplayStart, iDisplayLength);

		jqOutInfo<OutPoliceInfo> joi = new jqOutInfo<OutPoliceInfo>("", count,
				count, list);
		return joi;
	}

	public int Delete(int InPoliceID) {
		String sql = "delete from  OutPolice where InPoliceID=" + InPoliceID;
		return JDBCJNDI.update(sql, null, false);
	}

	public UsersInfo GetInfoByID(int id) {
		String sql = "select * from OutPolice where id=" + id;
		return JDBCJNDI.queryInfo(UsersInfo.class, sql, null);
	}

	public List<OutPoliceInfo> queryList() {
		String sql = "select * from OutPolice";
		return JDBCJNDI.queryList(OutPoliceInfo.class, sql, null);
	}

}
