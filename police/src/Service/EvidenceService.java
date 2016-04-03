package Service;

import java.util.List;

import Dal.EvidenceDal;
import Model.AccidentApprovalInfo;
import Model.AccidentInfo;
import Model.AccidentResponseInfo;
import Model.EvidenceInfo;
import Model.Result;
import Utils.StringHelper;
import Utils.JqTable.jqOutInfo;

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
	
	public jqOutInfo<EvidenceInfo> List(EvidenceInfo info,
			int iDisplayStart, int iDisplayLength) {

		return dal.List(info, iDisplayStart, iDisplayLength);
	}
	
	public List<EvidenceInfo> queryList(){
		return dal.queryList();
	}

}
