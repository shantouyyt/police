package Dal;

import java.util.List;

import DBManager.JDBCJNDI;
import Model.DriverInfo;
import Model.EvidenceInfo;
import Utils.StringHelper;
import Utils.JqTable.jqOutInfo;

public class EvidenceDal {
	public int Insert(EvidenceInfo info) {
		String sql = "insert into Evidence(AccidentNO,SGDD,TQ,QSRS,CreateDate,SZRS,ZJJJSS,YJ1,YJ2,SWRS,DLKD,ZSRS,JTFS,BZ) ";
		sql += " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] para = new Object[] { info.getAccidentNO(), info.getsGDD(),
				info.gettQ(), info.getqSRS(), info.getCreateDate(),
				info.getsZRS(), info.getzJJJSS(), info.getyJ1(), info.getyJ2(),
				info.getsWRS(), info.getdLKD(), info.getzSRS(), info.getbZ() };
		return JDBCJNDI.update(sql, para, false);
	}

	public int Delete(int id) {
		String sql = "delete from  Evidence where id=" + id;
		return JDBCJNDI.update(sql, null, false);
	}

	public EvidenceInfo GetInfoByID(int id) {
		String sql = "select * from Evidence where id=" + id;
		return JDBCJNDI.queryInfo(EvidenceInfo.class, sql, null);
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
}
