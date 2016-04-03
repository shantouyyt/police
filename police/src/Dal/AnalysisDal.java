package Dal;

import java.util.List;

import DBManager.JDBCJNDI;
import Model.AnalysisInfo;
import Model.EvidenceInfo;
import Utils.StringHelper;
import Utils.JqTable.jqOutInfo;

public class AnalysisDal {
	
	public int Insert(AnalysisInfo info) {
		String sql = "insert into Analysis(Remark,CreateDate) ";
		sql += " values(?,?)";
		Object[] para = new Object[] { info.getRemark(),info.getCreateDate() };
		return JDBCJNDI.update(sql, para, false);
	}
	
	public jqOutInfo<AnalysisInfo> List(AnalysisInfo info, int iDisplayStart,
			int iDisplayLength) {

		String sql = "select * from Analysis where 1=1 ";

		StringBuilder sb = new StringBuilder();

		if (!StringHelper.IsStrNull(info.getCreateDate())) {
			sb.append(" and CreateDate >='").append(info.getCreateDate())
					.append("' ");
		}
		if (!StringHelper.IsStrNull(info.getEndDate())) {
			sb.append(" and CreateDate <='").append(info.getEndDate())
					.append("' ");
		}
		String countsql = "select count(1) from Analysis where 1=1 ";
		int count = JDBCJNDI.count(countsql + sb.toString());

		List<AnalysisInfo> list = JDBCJNDI.findPage(AnalysisInfo.class, sql
				+ sb.toString(), iDisplayStart, iDisplayLength);

		jqOutInfo<AnalysisInfo> joi = new jqOutInfo<AnalysisInfo>("", count,
				count, list);
		return joi;
	}
	
	public int Delete(int id) {
		String sql = "delete from  Analysis where id=" + id;
		return JDBCJNDI.update(sql, null, false);
	}
}
