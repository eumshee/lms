package co.hee.lms.student.vo;

public class StudentVO {
	private String studentId;
	private String studentName;
	private String deptCode;
	private String deptName;
	private String tel;
	private String password;
	
	public StudentVO() {
		
	}
	
	@Override
	public String toString() {
		System.out.println("=======================");
		System.out.println(" 학번    : "+ studentId);
		System.out.println(" 이름    : "+ studentName);
		System.out.println(" 학과    : "+ deptName);
		System.out.println(" 전화번호 : "+ tel);
		
		return null;
	}

	public String getDeptName() {
		return deptName;
	}
	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
