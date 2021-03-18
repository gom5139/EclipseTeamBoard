package project.basket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import project.basket.model.BasketVO;
import project.product.model.ProductVO;

public class BasketDAO {
	public List<BasketVO> getBasketList(String m_id) {
		ArrayList<BasketVO> list = new ArrayList<BasketVO>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			
			String sql = "select * from basket";
			sql += " where m_id=?";
			System.out.println(sql);
			System.out.println(m_id);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, m_id);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
			BasketVO VO = new BasketVO();
			VO.setBasketID(resultSet.getInt("basketid"));
			VO.setGoods_num(resultSet.getInt("goods_num"));
			VO.setGoods_name(resultSet.getString("goods_name"));
			VO.setM_id(resultSet.getString("m_id"));
			VO.setPrice(resultSet.getString("price"));
			VO.setImage(resultSet.getString("image"));
			list.add(VO);
			//System.out.println(VO);
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



	public boolean getBasketInsert(ProductVO productVO, String m_id) {
		int basketid = 0;
		String sql = "";
		int result = 0;
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			sql = "select max(basketid) from basket";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				basketid = resultSet.getInt(1) + 1;
			} else {
				basketid = 1;
			}
			
			preparedStatement.close();
			sql = "insert into basket (basketid,m_id,goods_num,goods_name,price,image)";
			sql += " values(?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, basketid);
			preparedStatement.setString(2, m_id);
			preparedStatement.setInt(3, productVO.getProducts_num());
			preparedStatement.setString(4, productVO.getProducts_name());
			preparedStatement.setString(5, productVO.getPrice());
			preparedStatement.setString(6, productVO.getImage());
			result = preparedStatement.executeUpdate();
			
			if (result == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("장바구니 등록 실패" + e);
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

	public Boolean basketDelete(int basketid, String m_id) {
		int result = 0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		//ResultSet resultSet = null;
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "delete from basket where basketid=? and m_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, basketid);
			preparedStatement.setString(2, m_id);
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



	public ProductVO getProductInfo(int products_num) {
		String sql = "";
		ResultSet resultSet = null;
		ProductVO productVO = new ProductVO();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			sql = "select * from product where products_num = ?";
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
			System.out.println("장바구니 등록 실패" + e);
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

//	public boolean basketAdd(int goods_num, String m_id) {
//		int basketid = 0;
//		String sql="";
//		int result = 0;
//		ResultSet resultSet = null;
//		ResultSet resultSet2 = null;
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//
//		ProductVO productVO = new ProductVO();
//		try {
//			Context context = new InitialContext();
//			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
//			connection = dataSource.getConnection();
//			
//			sql = "select max(basketid) from basket";
//			preparedStatement= connection.prepareStatement(sql);
//			resultSet = preparedStatement.executeQuery();
//			
//			if (resultSet.next()) {
//				basketid = resultSet.getInt(1) + 1;
//			} else {
//				basketid = 1;
//			}
//			System.out.println("close전");
//			preparedStatement.close();
//			System.out.println("close후");
//			
//			sql= "select * from product where products_num = ?";
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt(1, products_num);
//			resultSet2 = preparedStatement.executeQuery();
//			if (resultSet.next()) {
//				productVO = new ProductVO();
//				productVO.setProducts_num(resultSet.getInt("products_num"));
//				productVO.setProducts_name(resultSet.getString("products_name"));
//				productVO.setCategory(resultSet.getString("category"));
//				productVO.setEnergy_efcnc(resultSet.getString("energy_efcnc"));
//				productVO.setLiter(resultSet.getString("liter"));
//				productVO.setPrice(resultSet.getString("price"));
//				productVO.setImage(resultSet.getString("image"));
//			}
//			
//			sql = "insert into basket (basketid,m_id,goods_num,goods_name,price,image)";
//			sql += " values(?,?,?,?,?,?)";
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt(1, basketid);
//			preparedStatement.setString(2, m_id);
//			preparedStatement.setInt(3, productVO.getProducts_num());
//			preparedStatement.setString(4, productVO.getProducts_name());
//			preparedStatement.setString(5, productVO.getPrice());
//			preparedStatement.setString(6, productVO.getImage());
//
//			System.out.println(basketid);
//			System.out.println(m_id);
//			System.out.println(productVO.getProducts_num());
//			System.out.println(productVO.getProducts_name());
//			System.out.println(productVO.getPrice());
//			result = preparedStatement.executeUpdate();
//			//resultSet =preparedStatement.executeUpdate();
//			if (result == 0) {
//				return false;
//			}
//			return true;
//		} catch (Exception e) {
//			System.out.println("장바구니 등록 실패" + e);
//		} finally {
//			try {
//				resultSet.close();
//				resultSet2.close();
//				preparedStatement.close();
//				connection.close();
//			} catch (SQLException e2) {
//				e2.printStackTrace();
//			}
//		}
//		return false;
//	}
}
