package project.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import project.admin.model.AdminVO;

public class AdminDAO {
	public AdminDAO() {
		try {
			Context context = new InitialContext();
			DataSource datasource = (DataSource) context.lookup("java:comp/env/jdbc");

			System.out.println(datasource + "연결되었습니다.");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}
	
	public ArrayList<AdminVO> AdminMember() {
		ArrayList<AdminVO> arrayList = new ArrayList<AdminVO>();

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
				AdminVO adminVO = new AdminVO();
				adminVO.setM_name(resultSet.getString("m_name"));
				adminVO.setM_id(resultSet.getString("m_id"));
				adminVO.setM_email(resultSet.getString("m_email"));
				adminVO.setM_phonenumber(resultSet.getString("m_phonenumber"));
				adminVO.setM_address(resultSet.getString("m_address"));
				arrayList.add(adminVO);
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
}
