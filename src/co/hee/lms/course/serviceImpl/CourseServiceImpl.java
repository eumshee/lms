package co.hee.lms.course.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.hee.lms.common.DataSource;
import co.hee.lms.course.service.CourseService;
import co.hee.lms.course.vo.CourseVO;

public class CourseServiceImpl implements CourseService {
	private DataSource dataSource = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	@Override
	public List<CourseVO> courseSelectAll() {
		List<CourseVO> list = new ArrayList<CourseVO>();
		CourseVO vo;
		String sql = "select c.*, p.professorname from course c, professor p where c.professorid=p.professorid "
				+ "order by c.courseid desc";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new CourseVO();
				vo.setCourseId(rs.getString("courseid"));
				vo.setGrade(rs.getString("grade"));
				vo.setCourseName(rs.getString("coursename"));
				vo.setProfessorName(rs.getString("professorname"));
				vo.setParticipants(rs.getInt("participants"));
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
	public List<CourseVO> courseProfessorSelectAll(CourseVO vo) {
		List<CourseVO> list = new ArrayList<CourseVO>();
		String sql = "select c.*, p.professorname from course c, professor p where c.professorid=p.professorid "
				+ "and p.professorid = ? "
				+ "order by c.courseid desc";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getProfessorId());
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new CourseVO();
				vo.setCourseId(rs.getString("courseid"));
				vo.setGrade(rs.getString("grade"));
				vo.setCourseName(rs.getString("coursename"));
				vo.setProfessorName(rs.getString("professorname"));
				vo.setParticipants(rs.getInt("participants"));
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
	public CourseVO courseSelect(CourseVO vo) { // 강좌명 검색
		String sql = "select c.*, p.professorname from course c, professor p where c.professorid=p.professorid "
				+ "and c.courseid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getCourseId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo = new CourseVO();
				vo.setCourseId(rs.getString("courseid"));
				vo.setGrade(rs.getString("grade"));
				vo.setCourseName(rs.getString("coursename"));
				vo.setProfessorName(rs.getString("professorname"));
				vo.setParticipants(rs.getInt("participants"));
				vo.setProfessorId(rs.getString("professorId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	@Override
	public int courseInsert(CourseVO vo) {
		int n = 0;
		String sql = "insert into course values(?,?,?,?,?,?)";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getCourseId());
			psmt.setString(2, vo.getGrade());
			psmt.setString(3, vo.getCourseName());
			psmt.setString(4, vo.getDeptCode());
			psmt.setString(5, vo.getProfessorId());
			psmt.setInt(6, vo.getParticipants());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int courseUpdate(CourseVO vo) { // 수강인원 변경
		int n = 0;
		String sql = "update course set participants = ? where courseid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getParticipants());
			psmt.setString(2, vo.getCourseId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int courseDelete(CourseVO vo) { // 해당 강좌번호 삭제
		int n = 0;
		String sql = "delete from course where courseid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getCourseId());
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
	public int countCourse(CourseVO vo) {
		int row = 0;
		String sql = "select count(*) as count from course";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt("count");
				System.out.println(">> 전 체 과 목 : "+row+"\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public int selectInit(CourseVO vo) {
		String sql = "select c.*, p.professorname from course c, professor p where c.professorid=p.professorid "
				+ "and c.courseid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getCourseId());
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
	public List<CourseVO> courseIdSelectAll() {
		List<CourseVO> list = new ArrayList<CourseVO>();
		CourseVO vo;
		String sql = "select courseid, coursename from course order by courseid desc";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new CourseVO();
				vo.setCourseId(rs.getString("courseid"));
				vo.setCourseName(rs.getString("coursename"));
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
	public int courseCheck(String courId) {
		String sql = "select coursename from course where courseid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, courId);
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
