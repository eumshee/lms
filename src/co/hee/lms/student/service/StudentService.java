package co.hee.lms.student.service;

/*
 *  interface; ~Service --> 구현체; ~serviceImpl
 */

import java.util.List;
import co.hee.lms.student.vo.StudentVO;

public interface StudentService {
	List<StudentVO> studentSelectAll();
	StudentVO studentSelect(StudentVO vo);
	int studentInsert(StudentVO vo);
	int studentUpdate(StudentVO vo);
	int studentPasswordUpdate(StudentVO vo);
	int studentDelete(StudentVO vo);
	StudentVO login(StudentVO vo);
	int idCheck(String id);
	int passwordCheck(StudentVO vo);
	int countStudent(StudentVO vo);
	int selectInit(StudentVO vo);
	
}
