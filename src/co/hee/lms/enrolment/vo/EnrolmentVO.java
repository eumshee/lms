package co.hee.lms.enrolment.vo;

public class EnrolmentVO {
	private String studentId;
	private String studentName;
	private String courseId;
	private String courseName;
	private String professorId;
	private String professorName;
	private int courseScore;
	private String courseGrade;
	
	public EnrolmentVO() {
		
	}
	
	public String toString() {
		System.out.println("==========================");
		System.out.println("수강강좌 : "+courseName);
		System.out.println("담당교수 : "+professorName);
		System.out.println("학 번  : "+studentId);
		System.out.println("이 름  : "+studentName);
		System.out.println("학 점  : "+courseGrade);
		return null;
	}

	public String toString2() {
		System.out.println("==========================");
		System.out.println("강좌 : "+courseName);
		System.out.println("교수 : "+professorName);
		return null;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
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

	public int getCourseScore() {
		return courseScore;
	}

	public void setCourseScore(int courseScore) {
		this.courseScore = courseScore;
	}

	public String getCourseGrade() {
		return courseGrade;
	}

	public void setCourseGrade(String courseGrade) {
		this.courseGrade = courseGrade;
	}
	
	
}
