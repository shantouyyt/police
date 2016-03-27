package Service;

import Dal.AccidentApprovalDal;
import Model.AccidentApprovalInfo;
import Model.AccidentInfo;
import Model.Result;
import Utils.StringHelper;

public class AccidentApprovalService {

	private AccidentApprovalDal dal = new AccidentApprovalDal();
	
	public Result Insert(AccidentApprovalInfo info) {

		if (StringHelper.IsStrNull(info.getRemark())) {
			return Result.Fail("审批内容不能为空");
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

	public AccidentApprovalInfo GetInfoByID(int id) {
		return dal.GetInfoByID(id);
	}
	
	public AccidentApprovalInfo GetInfoByAccidentNo(String AccidentNo) {
		return dal.GetInfoByAccidentNo(AccidentNo);
	}

}
