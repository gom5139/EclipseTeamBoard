package project.qna.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import project.qna.model.QnaVO;



public class QnaDAO {
	public QnaDAO() {
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			System.out.println(dataSource + "연결되었습니다.");
		} catch (Exception e) {
			System.out.println("DataBase 연결 실패" + e);
			// TODO: handle exception
			return;
		}
	}

	public int getListCount() {
		int i = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "select count(*) from jboard ";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				i = resultSet.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("글 개수 구하기 실패" + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}

	public List<QnaVO> getQnaList(int page, int limit) {
		ArrayList<QnaVO> list = new ArrayList<QnaVO>();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "select * from (select rownum rnum, num, m_id, subject, content,";
			sql += "attached_file, answer_num, answer_lev, answer_seq, read_count, write_date";
			sql += " from (select * from jboard order by answer_num desc, answer_seq asc))";
			sql += " where rnum>=? and rnum<=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, startrow);
			preparedStatement.setInt(2, endrow);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				QnaVO qnaVO = new QnaVO();
				qnaVO.setNum(resultSet.getInt("num"));
				qnaVO.setM_id(resultSet.getString("m_id"));
				qnaVO.setSubject(resultSet.getString("subject"));
				qnaVO.setContent(resultSet.getString("content"));
				qnaVO.setAttached_file(resultSet.getString("attached_file"));
				qnaVO.setAnswer_num(resultSet.getInt("answer_num"));
				qnaVO.setAnswer_lev(resultSet.getInt("answer_lev"));
				qnaVO.setAnswer_seq(resultSet.getInt("answer_seq"));
				qnaVO.setRead_count(resultSet.getInt("read_count"));
				qnaVO.setWrite_date(resultSet.getDate("write_date"));
				list.add(qnaVO);
			}
			return list;
		} catch (Exception e) {
			System.out.println("글 목록 보기 실패" + e);
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

	public boolean QnaInsert(QnaVO qnaVO) {
		int num = 0;
		String sql = "";
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			sql = "select max(num) from jboard";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				num = resultSet.getInt(1) + 1;
			} else {
				num = 1;
			}
			preparedStatement.close();
			sql = "insert into jboard (num,m_id,subject,content,attached_file,";
			sql += "answer_num,answer_lev,answer_seq,read_count,write_date)";
			sql += " values(?,?,?,?,?,?,?,?,?,sysdate)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			preparedStatement.setString(2, qnaVO.getM_id());
			preparedStatement.setString(3, qnaVO.getSubject());
			preparedStatement.setString(4, qnaVO.getContent());
			preparedStatement.setString(5, qnaVO.getAttached_file());
			preparedStatement.setInt(6, num);
			preparedStatement.setInt(7, 0);
			preparedStatement.setInt(8, 0);
			preparedStatement.setInt(9, 0);
			result = preparedStatement.executeUpdate();
			if (result == 0) {
				return false;
			}
			return true;

		} catch (Exception e) {
			System.out.println("글 등록 실패" + e);
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

	public QnaVO getDetail(int num) {
		QnaVO qnaVO = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			String sql = "select * from jboard where num = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				qnaVO = new QnaVO();
				qnaVO.setNum(resultSet.getInt("num"));
				qnaVO.setM_id(resultSet.getString("m_id"));
				qnaVO.setSubject(resultSet.getString("subject"));
				qnaVO.setContent(resultSet.getString("content"));
				qnaVO.setAttached_file(resultSet.getString("attached_file"));
				qnaVO.setAnswer_num(resultSet.getInt("answer_num"));
				qnaVO.setAnswer_lev(resultSet.getInt("answer_lev"));
				qnaVO.setAnswer_seq(resultSet.getInt("answer_seq"));
				qnaVO.setRead_count(resultSet.getInt("read_count"));
				qnaVO.setWrite_date(resultSet.getDate("write_date"));
			}
			return qnaVO;
		} catch (Exception e) {
			System.out.println("글 내용 보기 실패" + e);
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

	public void setReadCountUpdate(int num) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			String sql = "update jboard set read_count = read_count+1 where num = " + num;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeQuery();
		} catch (Exception e) {
			System.out.println("조회수 업데이트 실패" + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	public int QnaReply(QnaVO qnaVO) {
		int num = 0;
		String sql = "";

		int answer_num = qnaVO.getAnswer_num();
		int answer_lev = qnaVO.getAnswer_lev();
		int answer_seq = qnaVO.getAnswer_seq();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			sql = "select max(num) from jboard";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				num = resultSet.getInt(1) + 1;
			} else {
				num = 1;
			}
			preparedStatement.close();
			sql = "update jboard set answer_seq=answer_seq+1";
			sql += " where answer_num=? and answer_seq>?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, answer_num);
			preparedStatement.setInt(2, answer_seq);
			preparedStatement.executeUpdate();
			answer_seq = answer_seq + 1;
			answer_lev = answer_lev + 1;

			sql = "insert into jboard (num,m_id,subject,content,attached_file,";
			sql += "answer_num,answer_lev,answer_seq,read_count,write_date)";
			sql += " values(?,?,?,?,?,?,?,?,?,sysdate)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			preparedStatement.setString(2, qnaVO.getM_id());
			preparedStatement.setString(3, qnaVO.getSubject());
			preparedStatement.setString(4, qnaVO.getContent());
			preparedStatement.setString(5, qnaVO.getAttached_file());
			preparedStatement.setInt(6, answer_num);
			preparedStatement.setInt(7, answer_lev);
			preparedStatement.setInt(8, answer_seq);
			preparedStatement.setInt(9, 0);
			preparedStatement.executeUpdate();
			return num;
		} catch (Exception e) {
			System.out.println("글 답변 실패" + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return 0;
	}

	public boolean QnaModify(QnaVO qnaVO) {

		String fileName = qnaVO.getOld_file();
		String realFolder = "";
		realFolder = realFolder + fileName;
		File file = new File(realFolder);
		if (qnaVO.getAttached_file() == null) {
			qnaVO.setAttached_file(fileName);
		} else {
			if (file.exists()) {
				file.delete();
			}
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			String sql = "update jboard set m_id=?, subject=?, content=?,attached_file=?";
			sql += " where num=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, qnaVO.getM_id());
			preparedStatement.setString(2, qnaVO.getSubject());
			preparedStatement.setString(3, qnaVO.getContent());
			preparedStatement.setString(4, qnaVO.getAttached_file());
			preparedStatement.setInt(5, qnaVO.getNum());
			preparedStatement.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println("글 수정 실패" + e);
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

	public boolean isQnaWriter(int num, String m_id) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			String sql = "select * from jboard where num = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			System.out.println(m_id);
			if (m_id.equals(resultSet.getString("m_id")) || m_id.equals("admin")) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("글쓴이 확인 실패" + e);
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

	public boolean QnaDeletet(int num) {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			String sql = "delete from jboard where num=?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			result = preparedStatement.executeUpdate();
			if (result == 0) {
				return false;
			}
			return true;

		} catch (Exception e) {
			System.out.println("글 삭제 실패" + e);
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

	public List<QnaVO> getSearchList(String keyword, String keyfield, int page, int limit) {
		String searchCall = "";

		if (!"".equals(keyword)) {
			if ("all".equals(keyfield)) {
				searchCall = "(subject like '%' || '" + keyword + "' || '%' ) or ( m_id like '%' || '" + keyword
						+ "' || '%' ) or ( content like '%' || '" + keyword + "' || '%')";
			} else if ("subject".equals(keyfield)) {
				searchCall = " subject like '%' || '" + keyword + "' || '%'";
			} else if ("m_id".equals(keyfield)) {
				searchCall = " m_id like '%' || '" + keyword + "' || '%'";
			} else if ("content".equals(keyfield)) {
				searchCall = " content like '%' || '" + keyword + "' || '%'";
			}
		}
		List<QnaVO> list = new ArrayList<QnaVO>();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			String sql = "select * from (select rownum rnum,num,m_id,subject,content,";
			sql += "attached_file,answer_num,answer_lev,answer_seq,read_count,write_date";
			sql += " from (select * from jboard order by answer_num desc, answer_seq asc) ";
			sql += " where " + searchCall + ")";
			sql += " where rnum>=? and rnum<=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, startrow);
			preparedStatement.setInt(2, endrow);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				QnaVO qnaVO = new QnaVO();
				qnaVO.setNum(resultSet.getInt("num"));
				qnaVO.setM_id(resultSet.getString("m_id"));
				qnaVO.setSubject(resultSet.getString("subject"));
				qnaVO.setContent(resultSet.getString("content"));
				qnaVO.setAttached_file(resultSet.getString("attached_file"));
				qnaVO.setAnswer_num(resultSet.getInt("answer_num"));
				qnaVO.setAnswer_lev(resultSet.getInt("answer_lev"));
				qnaVO.setAnswer_seq(resultSet.getInt("answer_seq"));
				qnaVO.setRead_count(resultSet.getInt("read_count"));
				qnaVO.setWrite_date(resultSet.getDate("write_date"));
				list.add(qnaVO);
			}
			return list;

		} catch (Exception e) {
			System.out.println("search 에러" + e);
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

	public int getSearchListConut(String keyword, String keyfield) {
		String searchCall = "";

		if (!"".equals(keyword)) {
			if ("all".equals(keyfield)) {
				searchCall = "(subject like '%' || '" + keyword + "' || '%' ) or ( m_id like '%' || '" + keyword
						+ "' || '%' ) or ( content like '%' || '" + keyword + "' || '%')";
			} else if ("subject".equals(keyfield)) {
				searchCall = " subject like '%' || '" + keyword + "' || '%'";
			} else if ("m_id".equals(keyfield)) {
				searchCall = " m_id like '%' || '" + keyword + "' || '%'";
			} else if ("content".equals(keyfield)) {
				searchCall = " content like '%' || '" + keyword + "' || '%'";
			}
		}
		int i = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			String sql = "select count(*) from jboard where" + searchCall;
			System.out.println("연결이 되었습니다.");

			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				i = resultSet.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("글의 개수 구하기 실패" + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}
}
