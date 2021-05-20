package co.hee.lms.professor.service;

import java.util.List;

import co.hee.lms.professor.vo.ProfessorVO;

public interface ProfessorService {
	List<ProfessorVO> professorSelectAll();
	ProfessorVO professorSelect(ProfessorVO vo);
	int professorInsert(ProfessorVO vo);
	int professorUpdate(ProfessorVO vo);
	int professorDelete(ProfessorVO vo);
	ProfessorVO login(ProfessorVO vo);
	int idCheck(String id);
	int passwordCheck(ProfessorVO vo);
	int countProfessor(ProfessorVO vo);
	int selectInit(ProfessorVO vo);
}
