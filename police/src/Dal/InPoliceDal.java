package Dal;

import java.util.List;

import DBManager.JDBCJNDI;
import Model.InPoliceInfo;
import Utils.StringHelper;
import Utils.JqTable.jqOutInfo;

public class InPoliceDal {

	public int Insert(InPoliceInfo info) {

		String sql = "insert into InPolice(Name,Sex,CreateDate,Remark,Tel) values(?,?,?,?,?)";
		Object[] para = new Object[] { info.getName(), info.getSex(),
				info.getCreateDate(), info.getRemark(), info.getTel() };
		return JDBCJNDI.update(sql, para, false);
	}

	public int Update(InPoliceInfo info) {
		String sql = "update InPolice set Name=?,Sex=?,Remark=?,Tel=? where id=?";
		Object[] para = new Object[] { info.getName(), info.getSex(),
				info.getRemark(), info.getTel(), info.getId() };
		return JDBCJNDI.update(sql, para, false);
	}

	public int Delete(int id) {
		String sql = "delete from  InPolice where id=" + id;
		return JDBCJNDI.update(sql, null, false);
	}

	public InPoliceInfo GetInfoByID(int id) {
		String sql = "select * from InPolice where id=" + id;
		return JDBCJNDI.queryInfo(InPoliceInfo.class, sql, null);
	}

	public jqOutInfo<InPoliceInfo> List(InPoliceInfo info, int iDisplayStart,
			int iDisplayLength) {

		String sql = "select * from InPolice where 1=1 ";

		StringBuilder sb = new StringBuilder();
		if (!StringHelper.IsStrNull(info.getName())) {
			sb.append(" and Name like '%").append(info.getName()).append("%' ");
		}
		if (!StringHelper.IsStrNull(info.getCreateDate())) {
			sb.append(" and CreateDate >='").append(info.getCreateDate())
					.append("' ");
		}
		if (!StringHelper.IsStrNull(info.getEndDate())) {
			sb.append(" and CreateDate <='").append(info.getEndDate())
					.append("' ");
		}

		String countsql = "select count(1) from InPolice where 1=1 ";
		int count = JDBCJNDI.count(countsql + sb.toString());

		List<InPoliceInfo> list = JDBCJNDI.findPage(InPoliceInfo.class, sql
				+ sb.toString(), iDisplayStart, iDisplayLength);

		jqOutInfo<InPoliceInfo> joi = new jqOutInfo<InPoliceInfo>("", count,
				count, list);
		return joi;
	}
}
