package project.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import project.member.model.MemberVO;



public class MemberDAO {
	public MemberDAO() {
		try {
			Context context = new InitialContext();
			DataSource datasource = (DataSource) context.lookup("java:comp/env/jdbc");

			System.out.println(datasource + "연결되었습니다.");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}

	public ArrayList<MemberVO> MyPage(String m_id) {
		ArrayList<MemberVO> arrayList = new ArrayList<MemberVO>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			String sql = "select * from member";
			sql += " where m_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, m_id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MemberVO memberVO = new MemberVO();
				memberVO.setM_name(resultSet.getString("m_name"));
				memberVO.setM_password(resultSet.getString("m_password"));
				memberVO.setM_email(resultSet.getString("m_email"));
				memberVO.setM_phonenumber(resultSet.getString("m_phonenumber"));
				memberVO.setM_address(resultSet.getString("m_address"));
				arrayList.add(memberVO);
			}

			return arrayList;
		} catch (Exception e) {
			System.out.println("회원정보 보기 실패 : " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	public boolean Member(MemberVO MemberVO) {
		String sql = "";
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			sql = "insert into member (m_name,m_id,m_password,m_email,m_phonenumber,m_address)";
			sql += " values(?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, MemberVO.getM_name());
			preparedStatement.setString(2, MemberVO.getM_id());
			preparedStatement.setString(3, MemberVO.getM_password());
			preparedStatement.setString(4, MemberVO.getM_email());
			preparedStatement.setString(5, MemberVO.getM_phonenumber());
			preparedStatement.setString(6, MemberVO.getM_address());
			result = preparedStatement.executeUpdate();

			if (result == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("글 등록 실패 : " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	public boolean Login(String m_id, String m_password) {
		// ArrayList<MemberVO> arrayList = new ArrayList<MemberVO>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			String sql = "select * from member";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				resultSet.getString("m_id");
				resultSet.getString("m_password");
				if (m_id.equals(resultSet.getString("m_id"))) {
					if (m_password.equals(resultSet.getString("m_password"))) {
						return true;
					}
				}
			}
//			while (resultSet.next()) {	
//				MemberVO memberVO = new MemberVO();
//				memberVO.setM_id(resultSet.getString("m_id"));
//				memberVO.setM_password(resultSet.getString("m_password"));
//				arrayList.add(memberVO);
//			}

//			for (int i = 0; i < arrayList.size(); i++) {
//				if (m_id.equals(arrayList.get(i).getM_id())) {
//					if (m_password.equals(arrayList.get(i).getM_password())) {
//						return true;
//					}
//				}
//			}			

		} catch (Exception e) {
			System.out.println("로그인 실패 : " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	public boolean Modify(MemberVO memberVO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "update member set m_name=?, m_password=?, m_email=?, m_phonenumber=?, m_address=?";
			sql += " where m_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberVO.getM_name());
			preparedStatement.setString(2, memberVO.getM_password());
			preparedStatement.setString(3, memberVO.getM_email());
			preparedStatement.setString(4, memberVO.getM_phonenumber());
			preparedStatement.setString(5, memberVO.getM_address());
			preparedStatement.setString(6, memberVO.getM_id());
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("회원정보 수정 실패 : " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;

	}

	public boolean Delete(String m_id) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "delete from member where m_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, m_id);
			preparedStatement.executeUpdate();

			return true;
		} catch (Exception e) {
			System.out.println("글 삭제 실패 : " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	public boolean PasswordCheck(String m_id, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "select * from member where m_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, m_id);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			if (password.equals(resultSet.getString("m_password"))) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("회원 확인 실패 : " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;

	}
	
	public boolean IdCheck(String m_id) {
		ArrayList<MemberVO> arrayList = new ArrayList<MemberVO>();
		boolean result = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "select m_id from member";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MemberVO memberVO = new MemberVO();
				memberVO.setM_id(resultSet.getString("m_id"));
				arrayList.add(memberVO);
			}
			for (int i = 0; i < arrayList.size(); i++) {
				if (m_id.equals(arrayList.get(i).getM_id())) {
					result = true;
				}
			}
			if (result==false) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("회원 확인 실패 : " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
		
	}
}
