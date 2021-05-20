package co.hee.lms.professor.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.hee.lms.common.DataSource;
import co.hee.lms.common.LoginService;
import co.hee.lms.professor.service.ProfessorService;
import co.hee.lms.professor.vo.ProfessorVO;

public class ProfessorServiceImpl implements ProfessorService, LoginService<ProfessorVO>{
	private DataSource dataSource = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	@Override
	public List<ProfessorVO> professorSelectAll() {
		List<ProfessorVO> list = new ArrayList<ProfessorVO>();
		ProfessorVO vo;
		String sql = "select p.*, d.deptname from professor p, dept d where p.deptcode=d.deptcode";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new ProfessorVO();
				vo.setProfessorId(rs.getString("professorId"));
				vo.setProfessorName(rs.getString("professorName"));
				vo.setDeptName(rs.getString("deptname"));
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
	public ProfessorVO professorSelect(ProfessorVO vo) {
		String sql = "select p.*, d.deptname from professor p, dept d where p.deptcode=d.deptcode and professorid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getProfessorId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo = new ProfessorVO();
				vo.setProfessorId(rs.getString("professorId"));
				vo.setProfessorName(rs.getString("professorName"));
				vo.setDeptName(rs.getString("deptname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	@Override
	public int professorInsert(ProfessorVO vo) {
		int n = 0;
		String sql = "insert into professor values(?,?,?,?)";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getProfessorId());
			psmt.setString(2, vo.getProfessorName());
			psmt.setString(3, vo.getDeptCode());
			psmt.setString(4, vo.getPassword());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int professorUpdate(ProfessorVO vo) {
		int n = 0;
		String sql = "update professor set password = ? where professorid=?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getPassword());
			psmt.setString(2, vo.getProfessorId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int professorDelete(ProfessorVO vo) {
		int n = 0;
		String sql = "delete from professor where professorid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getProfessorId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public ProfessorVO login(ProfessorVO vo) {
		String sql = "select * from professor where professorid = ? and password = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getProfessorId());
			psmt.setString(2, vo.getPassword());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setProfessorName(rs.getString("professorName"));
				vo.setDeptCode(rs.getString("deptcode"));
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
		String sql = "select professorname from professor where professorid = ?";
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
	public int passwordCheck(ProfessorVO vo) {
		String sql = "select professorname from professor where professorid = ? and password = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getProfessorId());
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
	public int countProfessor(ProfessorVO vo) {
		int row = 0;
		String sql = "select count(*) as count from professor";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt("count");
				System.out.println(">> 전 체 교 수 : "+row+" 명\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public int selectInit(ProfessorVO vo) {
		String sql = "select p.*, d.deptname from professor p, dept d where p.deptcode=d.deptcode and professorid = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getProfessorId());
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
