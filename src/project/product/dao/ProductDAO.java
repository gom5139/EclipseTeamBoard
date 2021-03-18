package project.product.dao;

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

import project.product.model.ProductVO;

public class ProductDAO {
	public int getListCount() {
		int i = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "select count(*) from product ";
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

	public List<ProductVO> getProductList(int page, int limit) {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "select * from (select rownum rnum, products_num, category, products_name, energy_efcnc,";
			sql += "price, liter, image from product)";
			//sql += " from (select * from jboard order by answer_num desc, answer_seq asc))";
			sql += " where rnum>=? and rnum<=?";
			System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, startrow);
			preparedStatement.setInt(2, endrow);
			resultSet = preparedStatement.executeQuery();
			System.out.println("1");
			while (resultSet.next()) {
			ProductVO  productVO = new ProductVO();
			productVO.setProducts_num(resultSet.getInt("products_num"));
			productVO.setCategory(resultSet.getString("category"));
			productVO.setProducts_name(resultSet.getString("products_name"));
			productVO.setEnergy_efcnc(resultSet.getString("energy_efcnc"));
			productVO.setPrice(resultSet.getString("price"));
			productVO.setLiter(resultSet.getString("liter"));
			productVO.setImage(resultSet.getString("image"));
			list.add(productVO);
			System.out.println(productVO);
			}
			return list;
		} catch (Exception e) {
			System.out.println("상품 목록 보기 실패" + e);
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
	public boolean ProductInsert(ProductVO productVO) {
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

			sql = "select max(products_num) from product";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				num = resultSet.getInt(1) + 1;
			} else {
				num = 1;
			}
			preparedStatement.close();
			sql = "insert into product (products_num,category,products_name,energy_efcnc,price,liter,image)";
			sql += " values(?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			preparedStatement.setString(2, productVO.getCategory());
			preparedStatement.setString(3, productVO.getProducts_name());
			preparedStatement.setString(4, productVO.getEnergy_efcnc());
			preparedStatement.setString(5, productVO.getPrice());
			preparedStatement.setString(6, productVO.getLiter());
			preparedStatement.setString(7, productVO.getImage());
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
//	public void setReadCountUpdate(int num) {
//
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//
//		try {
//			Context context = new InitialContext();
//			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
//			connection = dataSource.getConnection();
//
//			String sql = "update jboard set read_count = read_count+1 where num = " + num;
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.executeQuery();
//		} catch (Exception e) {
//			System.out.println("조회수 업데이트 실패" + e);
//		} finally {
//			try {
//				preparedStatement.close();
//				connection.close();
//			} catch (SQLException e2) {
//				e2.printStackTrace();
//			}
//		}
//	}
	public ProductVO getDetail(int products_num) {
		ProductVO  productVO  = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			String sql = "select * from product where products_num = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, products_num);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				productVO = new ProductVO();
				productVO.setProducts_num(resultSet.getInt("products_num"));
				productVO.setProducts_name(resultSet.getString("products_name"));
				productVO.setCategory(resultSet.getString("category"));
				productVO.setEnergy_efcnc(resultSet.getString("energy_efcnc"));
				productVO.setLiter(resultSet.getString("liter"));
				productVO.setPrice(resultSet.getString("price"));
				productVO.setImage(resultSet.getString("image"));
				}
			return productVO;
			
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
	public boolean ProductUpdate(ProductVO VO) {
		String fileName = VO.getOld_file();
		String realFolder = "";
		realFolder = realFolder + fileName;
		File file = new File(realFolder);
		if (VO.getImage() == null) {
			VO.setImage(fileName);
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
			String sql = "update product set category = ? ,products_name= ? ,energy_efcnc= ? ,price=?,liter=?,image=?";
			sql += "where products_num=?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, VO.getCategory());
			preparedStatement.setString(2, VO.getProducts_name());
			preparedStatement.setString(3, VO.getEnergy_efcnc());
			preparedStatement.setString(4, VO.getPrice());
			preparedStatement.setString(5, VO.getLiter());
			preparedStatement.setString(6, VO.getImage());
			preparedStatement.setInt(7, VO.getProducts_num());
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("상품 수정 실패: " + e);
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


	public boolean ProductDelete(int products_num) {

		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "delete from product where products_num=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, products_num);
			result = preparedStatement.executeUpdate();
			if (result == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("글 삭제 실패 :" + e);
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
	public int getSearchListConut(String category) {

		int i = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			String sql = "select count(*) from product where category=?";
			System.out.println(sql);
			System.out.println(category);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, category);
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
	public List<ProductVO> getSearchList(String category, int page, int limit) {


		List<ProductVO> list = new ArrayList<ProductVO>();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

//			String sql = "select * from (select rownum rnum,num,name,subject,content,";
//			sql += "attached_file,answer_num,answer_lev,answer_seq,read_count,write_date";
//			sql += " from (select * from jboard order by answer_num desc, answer_seq asc) ";
//			sql += " where " + searchCall + ")";
//			sql += " where rnum>=? and rnum<=?";
			
			String sql = "select * from (select rownum rnum, products_num, category, products_name, energy_efcnc, price, liter, image from product";
			sql += " where category=?) where rnum>=? and rnum<=?";
//			SELECT * FROM product where category = '정수기 냉장고';
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, category);
			preparedStatement.setInt(2, startrow);
			preparedStatement.setInt(3, endrow);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				ProductVO VO = new ProductVO();
				VO.setProducts_num(resultSet.getInt("products_num"));
				VO.setCategory(resultSet.getString("category"));
				VO.setProducts_name(resultSet.getString("products_name"));
				VO.setEnergy_efcnc(resultSet.getString("energy_efcnc"));
				VO.setPrice(resultSet.getString("price"));
				VO.setLiter(resultSet.getString("liter"));
				VO.setImage(resultSet.getString("image"));
				System.out.println(VO);
				list.add(VO);
				
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
}
