package Service;

import Dal.EvidenceDal;
import Model.AccidentApprovalInfo;
import Model.EvidenceInfo;
import Model.Result;
import Utils.StringHelper;

public class EvidenceService {
	private EvidenceDal dal = new EvidenceDal();
	
	public Result Insert(EvidenceInfo info) {

		if (StringHelper.IsStrNull(info.getAccidentNO())) {
			return Result.Fail("事故不能为空");
		}
		int Count = dal.Insert(info);
		if (Count == 0) {
			return Result.Fail("添加失败");
		}
		return Result.Success("添加成功");
	}
	
	public EvidenceInfo GetInfoByID(int id){
		return dal.GetInfoByID(id);
	}
	
	public Result Delete(int id){
		if(id==0){
			return Result.Fail("删除失败");
		}
		int ret = dal.Delete(id);
		if(ret > 0){
			return Result.Success("删除成功");
		}
		return Result.Fail("删除失败");
	}
	
	public EvidenceInfo GetInfoByAccidentNo(String AccidentNo) {
		return dal.GetInfoByAccidentNo(AccidentNo);
	}

}
