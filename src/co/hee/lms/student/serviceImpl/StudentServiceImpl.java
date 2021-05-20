package co.hee.lms.student.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.hee.lms.common.DataSource;
import co.hee.lms.common.LoginService;
import co.hee.lms.student.service.StudentService;
import co.hee.lms.student.vo.StudentVO;

public class StudentServiceImpl implements StudentService, LoginService<StudentVO> {
	private DataSource dataSource = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	@Override
	public List<StudentVO> studentSelectAll() {
		List<StudentVO> list = new ArrayList<StudentVO>();
		StudentVO vo;
		String sql = "select a.*, b.deptname from student a, dept b	where a.deptcode = b.deptcode "
				+ "order by studentid";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new StudentVO();
				vo.setStudentId(rs.getString("studentId"));
				vo.setStudentName(rs.getString("studentName"));
				vo.setDeptName(rs.getString("deptname"));
				vo.setTel(rs.getString("tel"));
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
	public StudentVO studentSelect(StudentVO vo) {
		String sql = "select a.*, b.deptname from student a, dept b where a.deptcode = b.deptcode and studentid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getStudentId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo = new StudentVO();
				vo.setStudentId(rs.getString("studentId"));
				vo.setStudentName(rs.getString("studentName"));
				vo.setDeptName(rs.getString("deptname"));
				vo.setTel(rs.getString("tel"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	@Override
	public int studentInsert(StudentVO vo) {
		int n = 0;
		String sql = "insert into student values(?,?,?,?,?)";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getStudentId());
			psmt.setString(2, vo.getStudentName());
			psmt.setString(3, vo.getDeptCode());
			psmt.setString(4, vo.getTel());
			psmt.setString(5, vo.getPassword());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int studentUpdate(StudentVO vo) { // 전화번호 수정
		int n = 0;
		String sql = "update student set tel = ? where studentid=?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTel());
			psmt.setString(2, vo.getStudentId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}
	
	public int studentPasswordUpdate(StudentVO vo) { // 전화번호 수정
		int n = 0;
		String sql = "update student set password = ? where studentid=?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getPassword());
			psmt.setString(2, vo.getStudentId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int studentDelete(StudentVO vo) {
		int n = 0;
		String sql = "delete from student where studentid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getStudentId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public StudentVO login(StudentVO vo) {
		String sql = "select * from student where studentid = ? and password = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getStudentId());
			psmt.setString(2, vo.getPassword());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setStudentName(rs.getString("studentname"));
				vo.setTel(rs.getString("tel"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
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
	public int idCheck(String id) {
		String sql = "select studentname from student where studentid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
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
	public int passwordCheck(StudentVO vo) {
		String sql = "select studentname from student where studentid = ? and password = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getStudentId());
			psmt.setString(2, vo.getPassword());
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
	public int countStudent(StudentVO vo) {
		int row = 0;
		String sql = "select count(*) as count from student";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt("count");
				System.out.println(">> 전 체 학 생 수 : "+row+" 명\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public int selectInit(StudentVO vo) {
		String sql = "select a.*, b.deptname from student a, dept b where a.deptcode = b.deptcode and studentid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getStudentId());
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
