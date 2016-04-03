package Service;

import java.util.List;

import Dal.OutPoliceDal;
import Model.InPoliceInfo;
import Model.OutPoliceInfo;
import Model.Result;
import Utils.StringHelper;
import Utils.JqTable.jqOutInfo;

public class OutPoliceService {
	private OutPoliceDal dal = new OutPoliceDal();

	public Result Insert(OutPoliceInfo info) {

		int Count = dal.Insert(info);
		if (Count == 0) {
			return Result.Fail("添加失败");
		}
		return Result.Success("添加成功");
	}

	public jqOutInfo<OutPoliceInfo> List(OutPoliceInfo info, int iDisplayStart,
			int iDisplayLength) {

		return dal.List(info, iDisplayStart, iDisplayLength);
	}

	public jqOutInfo<OutPoliceInfo> ListDistinct(OutPoliceInfo info,
			int iDisplayStart, int iDisplayLength) {

		return dal.ListDistinct(info, iDisplayStart, iDisplayLength);
	}

	public Result Delete(int InPoliceID) {
		if (InPoliceID == 0) {
			return Result.Fail("删除失败");
		}
		int ret = dal.Delete(InPoliceID);
		if (ret > 0) {
			return Result.Success("删除成功");
		}
		return Result.Fail("删除失败");
	}
	
	public List<OutPoliceInfo> queryList(){
		return dal.queryList();
	}
}
