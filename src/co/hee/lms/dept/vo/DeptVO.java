package co.hee.lms.dept.vo;

public class DeptVO {
	private String deptCode;
	private String deptName;
	
	public DeptVO() {
		
	}
	
	public String toString() {
		System.out.println("=====================");
		System.out.println("학과코드 : "+deptCode);
		System.out.println("학과명  : "+deptName);
		return null;
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
	
	
}
