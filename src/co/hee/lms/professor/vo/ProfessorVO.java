package co.hee.lms.professor.vo;

public class ProfessorVO {
	private String professorId;
	private String professorName;
	private String deptCode;
	private String deptName;
	private String password;
	
	public ProfessorVO() {
		
	}
	
	public String toString() {
		System.out.println("========================");
		System.out.println("교번    : "+professorId);
		System.out.println("학과명  : "+deptName);
		System.out.println("교수이름 : "+professorName);
		return null;
	}

	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
