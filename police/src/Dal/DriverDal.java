package Dal;

import java.util.List;

import DBManager.JDBCJNDI;
import Model.DriverInfo;
import Model.InPoliceInfo;
import Model.UsersInfo;
import Utils.StringHelper;
import Utils.JqTable.jqOutInfo;

public class DriverDal {
	public int Insert(DriverInfo info) {
		String sql = "insert into Driver(Name,Sex,Tel,License,LicenseExpire,Address,CreateDate,Remark) values(?,?,?,?,?,?,?,?)";
		Object[] para = new Object[] { info.getName(), info.getSex(),
				info.getTel(), info.getLicense(), info.getLicenseExpire(),
				info.getAddress(), info.getCreateDate(), info.getRemark() };
		return JDBCJNDI.update(sql, para, false);
	}

	public int Delete(int id) {
		String sql = "delete from  Driver where id=" + id;
		return JDBCJNDI.update(sql, null, false);
	}

	public DriverInfo GetInfoByID(int id) {
		String sql = "select * from Driver where id=" + id;
		return JDBCJNDI.queryInfo(DriverInfo.class, sql, null);
	}

	public int Update(DriverInfo info) {
		String sql = "update Driver set Name=?,Sex=?,Tel=?,License=?,LicenseExpire=?,Address=?,Remark=? where id=?";
		Object[] para = new Object[] { info.getName(), info.getSex(),
				info.getTel(), info.getLicense(), info.getLicenseExpire(),
				info.getAddress(), info.getRemark(), info.getId() };
		return JDBCJNDI.update(sql, para, false);
	}

	public jqOutInfo<DriverInfo> List(DriverInfo info, int iDisplayStart,
			int iDisplayLength) {

		String sql = "select * from Driver where 1=1 ";

		StringBuilder sb = new StringBuilder();
		if (!StringHelper.IsStrNull(info.getName())) {
			sb.append(" and Name like '%").append(info.getName()).append("%' ");
		}
		if (!StringHelper.IsStrNull(info.getLicense())) {
			sb.append(" and License like '%").append(info.getLicense())
					.append("%' ");
		}

		String countsql = "select count(1) from Driver where 1=1 ";
		int count = JDBCJNDI.count(countsql + sb.toString());

		List<DriverInfo> list = JDBCJNDI.findPage(DriverInfo.class,
				sql + sb.toString(), iDisplayStart, iDisplayLength);

		jqOutInfo<DriverInfo> joi = new jqOutInfo<DriverInfo>("", count, count,
				list);
		return joi;
	}
	
	public List<DriverInfo> queryList() {
		String sql = "select * from Driver";
		return JDBCJNDI.queryList(DriverInfo.class, sql, null);
	}
}
