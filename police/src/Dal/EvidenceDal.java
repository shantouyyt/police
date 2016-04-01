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

	public EvidenceInfo GetInfoByAccidentNo(String AccidentNo) {
		String sql = "select * from Evidence where AccidentNo='" + AccidentNo
				+ "'";
		return JDBCJNDI.queryInfo(EvidenceInfo.class, sql, null);
	}

	public int Update(EvidenceInfo info) {
		String sql = "update Evidence set SGDD=?,TQ=?,QSRS=?,CreateDate=?,SZRS=?,ZJJJSS=?,YJ1=?,YJ2=?";
		sql += ",SWRS=?,DLKD=?,ZSRS=?,JTFS=?,BZ=? where id=?";

		Object[] para = new Object[] { info.getsGDD(), info.gettQ(),
				info.getqSRS(), info.getCreateDate(), info.getsZRS(),
				info.getzJJJSS(), info.getyJ1(), info.getyJ2(), info.getsWRS(),
				info.getdLKD(), info.getzSRS(), info.getjTFS(), info.getbZ(),info.getId() };
		return JDBCJNDI.update(sql, para, false);
	}

	public jqOutInfo<EvidenceInfo> List(EvidenceInfo info, int iDisplayStart,
			int iDisplayLength) {

		String sql = "select * from Evidence where 1=1 ";

		StringBuilder sb = new StringBuilder();
		if (!StringHelper.IsStrNull(info.getsGDD())) {
			sb.append(" and SGDD like '%").append(info.getsGDD()).append("%' ");
		}
		String countsql = "select count(1) from Evidence where 1=1 ";
		int count = JDBCJNDI.count(countsql + sb.toString());

		List<EvidenceInfo> list = JDBCJNDI.findPage(EvidenceInfo.class, sql
				+ sb.toString(), iDisplayStart, iDisplayLength);

		jqOutInfo<EvidenceInfo> joi = new jqOutInfo<EvidenceInfo>("", count,
				count, list);
		return joi;
	}
}
