package co.hee.lms.dept.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.hee.lms.common.DataSource;
import co.hee.lms.dept.service.DeptService;
import co.hee.lms.dept.vo.DeptVO;

public class DeptServiceImpl implements DeptService{
	private DataSource dataSource = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	@Override
	public List<DeptVO> deptSelectAll() {
		List<DeptVO> list = new ArrayList<DeptVO>();
		DeptVO vo;
		String sql = "select * from dept";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new DeptVO();
				vo.setDeptCode(rs.getString("deptcode"));
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
	public DeptVO deptSelect(DeptVO vo) { // 학과명 조회
		String sql = "select * from dept where deptname like ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, "%" + vo.getDeptName() + "%");
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new DeptVO();
				vo.setDeptCode(rs.getString("deptcode"));
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
	public int deptInsert(DeptVO vo) {
		int n = 0;
		String sql = "insert into dept values (?, ?)";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getDeptCode());
			psmt.setString(2, vo.getDeptName());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int deptUpdate(DeptVO vo) { // 학과명 변경
		int n = 0;
		String sql = "update dept set deptname = ? where deptcode = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getDeptName());
			psmt.setString(2, vo.getDeptCode());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int deptDelete(DeptVO vo) {
		int n = 0;
		String sql = "delete from dept where deptcode = ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getDeptCode());
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
	public int countDept(DeptVO vo) {
		int row = 0;
		String sql = "select count(*) as count from dept";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt("count");
				System.out.println(">> 전 체 학 과 : "+row+"\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public int selectInit(DeptVO vo) {
		String sql = "select * from dept where deptname like ?";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, "%" + vo.getDeptName() + "%");
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
