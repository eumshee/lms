package co.hee.lms.course.service;

import java.util.List;

import co.hee.lms.course.vo.CourseVO;

public interface CourseService {
	List<CourseVO> courseSelectAll();
	List<CourseVO> courseProfessorSelectAll(CourseVO vo);
	List<CourseVO> courseIdSelectAll();
	CourseVO courseSelect(CourseVO vo);
	int courseInsert(CourseVO vo);
	int courseUpdate(CourseVO vo);
	int courseDelete(CourseVO vo);
	int countCourse(CourseVO vo);
	int selectInit(CourseVO vo);
	int courseCheck(String courId);
}
