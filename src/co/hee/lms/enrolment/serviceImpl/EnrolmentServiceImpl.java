package co.hee.lms.enrolment.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.hee.lms.common.DataSource;
import co.hee.lms.enrolment.service.EnrolmentService;
import co.hee.lms.enrolment.vo.EnrolmentVO;

public class EnrolmentServiceImpl implements EnrolmentService{
	private DataSource dataSource = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	@Override
	public List<EnrolmentVO> enrolmentSelectAll() {
		List<EnrolmentVO> list = new ArrayList<EnrolmentVO>();
		EnrolmentVO vo;
		String sql = "select * from enrolment order by studentid";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new EnrolmentVO();
				vo.setCourseName(rs.getString("courseName"));
				vo.setProfessorName(rs.getString("professorName"));
				vo.setStudentId(rs.getString("studentId"));
				vo.setStudentName(rs.getString("studentName"));
				vo.setCourseGrade(rs.getString("courseGrade"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public List<EnrolmentVO> enrolmentSelectStudentAll(EnrolmentVO vo) {
		List<EnrolmentVO> list = new ArrayList<EnrolmentVO>();
		String sql = "select * from enrolment where studentid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getStudentId());
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new EnrolmentVO();
				vo.setCourseName(rs.getString("courseName"));
				vo.setProfessorName(rs.getString("professorName"));
				vo.setStudentId(rs.getString("studentId"));
				vo.setStudentName(rs.getString("studentName"));
				vo.setCourseGrade(rs.getString("courseGrade"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	@Override
	public EnrolmentVO enrolmentSelect(EnrolmentVO vo) {
		String sql = "select * from enrolment where studentid = ? and courseid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getStudentId());
			psmt.setString(2, vo.getCourseId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setCourseName(rs.getString("courseName"));
				vo.setProfessorName(rs.getString("professorName"));
				vo.setStudentId(rs.getString("studentId"));
				vo.setStudentName(rs.getString("studentName"));
				vo.setCourseGrade(rs.getString("courseGrade"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	@Override
	public int enrolmentInsert(EnrolmentVO vo) {
		int n = 0;
		String sql = "insert into enrolment values(?,?,?,?,?,?,?,0)";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getStudentId());
			psmt.setString(2, vo.getStudentName());
			psmt.setString(3, vo.getCourseId());
			psmt.setString(4, vo.getCourseName());
			psmt.setString(5, vo.getProfessorId());
			psmt.setString(6, vo.getProfessorName());
			psmt.setInt(7, vo.getCourseScore());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int enrolmentUpdate(EnrolmentVO vo) {
		int n = 0;
		String sql = "update enrolment set coursescore = ? "
				+ "where studentid = ? and courseid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getCourseScore());
			psmt.setString(2, vo.getStudentId());
			psmt.setString(3, vo.getCourseId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int enrolmentDelete(EnrolmentVO vo) {
		int n = 0;
		String sql = "delete from enrolment where studentid = ? and courseid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getStudentId());
			psmt.setString(2, vo.getCourseId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}
	
	private void close() {
		try {
			if( rs != null) rs.close();
			if( psmt != null) psmt.close();
			if( conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int selectInit(EnrolmentVO vo) {
		String sql = "select * from enrolment where studentid = ? and courseid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getStudentId());
			psmt.setString(2, vo.getCourseId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				return 1;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return -1;
	}

	@Override
	public String scoreCheck(EnrolmentVO vo) {
		int score = 0;
		String grade = null;
		String sql = "select coursescore as score from enrolment where studentid = ? and courseid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getStudentId());
			psmt.setString(2, vo.getCourseId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				score = rs.getInt("score");
				switch (score/10) {
				case 10:
					grade = "A+";
					break;
				case 9:
					grade = "A0";
					break;
				case 8:
					grade = "B0";
					break;
				case 7:
					grade = "C0";
					break;
				case 6:
					grade = "D";
					break;
				default:
					grade = "F";
				}
			} else {
				grade = "F";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grade;
	}

	@Override
	public int gradeUpdate(EnrolmentVO vo) {
		int n = 0;
		String sql = "update enrolment set coursegrade = ? "
				+ "where studentid = ? and courseid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getCourseGrade());
			psmt.setString(2, vo.getStudentId());
			psmt.setString(3, vo.getCourseId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int courseCheck(String course) {
		String sql = "select * from enrolment where courseid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, course);
			rs = psmt.executeQuery();
			if(rs.next()) {
				return 1;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}


}
