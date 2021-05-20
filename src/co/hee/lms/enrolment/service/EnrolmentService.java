package co.hee.lms.enrolment.service;

import java.util.List;

import co.hee.lms.enrolment.vo.EnrolmentVO;

public interface EnrolmentService {
	List<EnrolmentVO> enrolmentSelectAll();
	List<EnrolmentVO> enrolmentSelectStudentAll(EnrolmentVO vo);
	EnrolmentVO enrolmentSelect(EnrolmentVO vo);
	int enrolmentInsert(EnrolmentVO vo);
	int enrolmentUpdate(EnrolmentVO vo);
	int enrolmentDelete(EnrolmentVO vo);
	int selectInit(EnrolmentVO vo);
	String scoreCheck(EnrolmentVO vo);
	int gradeUpdate(EnrolmentVO vo);
	int courseCheck(String course);
	
}
