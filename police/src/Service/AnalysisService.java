package Service;

import Dal.AnalysisDal;
import Model.AnalysisInfo;
import Model.Result;
import Utils.StringHelper;
import Utils.JqTable.jqOutInfo;

public class AnalysisService {
	private AnalysisDal dal = new AnalysisDal();
	
	public Result Insert(AnalysisInfo info) {
		if (StringHelper.IsStrNull(info.getRemark())) {
			return Result.Fail("分析不能为空");
		}
		info.setCreateDate(StringHelper.GetCurrentDate());
		int Count = dal.Insert(info);
		if (Count == 0) {
			return Result.Fail("添加失败");
		}
		return Result.Success("添加成功");

	}
	public jqOutInfo<AnalysisInfo> List(AnalysisInfo info, int iDisplayStart,
			int iDisplayLength) {

		return dal.List(info, iDisplayStart, iDisplayLength);
	}
}
