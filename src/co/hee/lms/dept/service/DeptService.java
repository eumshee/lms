package co.hee.lms.dept.service;

import java.util.List;

import co.hee.lms.dept.vo.DeptVO;

public interface DeptService {
	List<DeptVO> deptSelectAll();
	DeptVO deptSelect(DeptVO vo);
	int deptInsert(DeptVO vo);
	int deptUpdate(DeptVO vo);
	int deptDelete(DeptVO vo);
	int countDept(DeptVO vo);
	int selectInit(DeptVO vo);
}
