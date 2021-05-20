package co.hee.lms.course.vo;

public class CourseVO {
	private String courseId;
	private String grade;
	private String courseName;
	private String deptCode;
	private String professorId;
	private String professorName;
	private String studentId;
	private int participants;

	public CourseVO() {
		
	}
	
	public String toString() {
		System.out.println("==========================");
		System.out.println("강좌번호 : "+ courseId);
		System.out.println("강좌학년 : "+ grade);
		System.out.println("강좌명  : "+ courseName);
		System.out.println("강의교수 : "+ professorName);
		System.out.println("수강인원 : "+ participants);
		return null;
	}
	
	public String toString2() {
		System.out.println("==========================");
		System.out.println("강좌번호 : "+ courseId);
		System.out.println("강좌명  : "+ courseName);
		return null;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	public int getParticipants() {
		return participants;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	
	
	
	
}
