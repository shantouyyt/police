package Service;

import Dal.AccidentResponseDal;
import Model.AccidentInfo;
import Model.AccidentResponseInfo;
import Model.Result;
import Utils.StringHelper;
import Utils.JqTable.jqOutInfo;

public class AccidentResponseService {

	private AccidentResponseDal dal = new AccidentResponseDal();

	public Result Insert(AccidentResponseInfo info) {

		if (StringHelper.IsStrNull(info.getRemark())) {
			return Result.Fail("定责内容不能为空");
		}
		if (StringHelper.IsStrNull(info.getAccidentNo())) {
			return Result.Fail("事故编号不能为空");
		}
		info.setCreateDate(StringHelper.GetCurrentDate());
		info.setStatus(2);

		int Count = dal.Insert(info);
		if (Count == 0) {
			return Result.Fail("添加失败");
		}
		return Result.Success("添加成功");
	}

	public AccidentResponseInfo GetInfoByID(int id) {
		return dal.GetInfoByID(id);
	}

	public AccidentResponseInfo GetInfoByAccidentNo(String AccidentNo) {
		return dal.GetInfoByAccidentNo(AccidentNo);
	}

	public Result UpdateStatus(AccidentResponseInfo info) {
		int ret = dal.UpdateStatus(info.getStatus(), info.getId());
		if (ret > 0) {
			return Result.Success("操作成功");
		}
		return Result.Fail("操作失败");
	}

	public jqOutInfo<AccidentResponseInfo> List(AccidentResponseInfo info,
			int iDisplayStart, int iDisplayLength) {

		return dal.List(info, iDisplayStart, iDisplayLength);
	}
	
	public Result Update(AccidentResponseInfo info) {
		if (StringHelper.IsStrNull(info.getRemark())) {
			return Result.Fail("定责内容不能为空");
		}
		if (StringHelper.IsStrNull(info.getAccidentNo())) {
			return Result.Fail("事故编号不能为空");
		}
		int ret = dal.Update(info);
		if (ret > 0) {
			return Result.Success("修改成功");
		}
		return Result.Fail("修改失败");

	}

}
