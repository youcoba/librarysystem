package slib.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import slib.bean.ReturnBean;

public class ReturnDAO {

	private Connection con;

	public ReturnDAO() throws DAOException {
		getConnection();
	}

	// 全検索
	public List<ReturnBean> searchAllMember() throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select * from rental where rental_return is null order by user_id, rental_id";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();

			List<ReturnBean> list = new ArrayList<>();
			while (rs.next()) {
				int userId = rs.getInt(3);
				int rentalId = rs.getInt(1);
				int bookStateId = rs.getInt(2);
				String rentalRent = rs.getString(4);
				String rentalReturn = rs.getString(5);
				String rentalDeadline = rs.getString(6);
				String comment = rs.getString(7);
				ReturnBean bean = new ReturnBean(userId, rentalId, bookStateId, rentalRent, rentalReturn,
						rentalDeadline, comment);
				list.add(bean);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	// 会員ID検索
	public List<ReturnBean> searchMember(int userId) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select * from rental where user_id=? and rental_return is null order by rental_id";
			st = con.prepareStatement(sql);
			st.setInt(1, userId);
			rs = st.executeQuery();
			List<ReturnBean> list = new ArrayList<>();
			while (rs.next()) {
				int rentalId = rs.getInt(1);
				int bookStateId = rs.getInt(2);
				String rentalRent = rs.getString(4);
				String rentalReturn = rs.getString(5);
				String rentalDeadline = rs.getString(6);
				String comment = rs.getString(7);

				ReturnBean bean = new ReturnBean(userId, rentalId, bookStateId, rentalRent, rentalReturn,
						rentalDeadline, comment);
				list.add(bean);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	// 返却
	public List<ReturnBean> returnDoc(int rentalId) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "update rental set rental_return = current_date where rental_id=?";
			st = con.prepareStatement(sql);
			st.setInt(1, rentalId);
			st.executeUpdate();
			st.close();
			
			sql = "select * from rental where rental_id=?";
			st = con.prepareStatement(sql);
			st.setInt(1, rentalId);
			rs = st.executeQuery();
			List<ReturnBean> list = new ArrayList<>();
			while (rs.next()) {
				int userId = rs.getInt(3);
				int bookStateId = rs.getInt(2);
				String rentalRent = rs.getString(4);
				String rentalReturn = rs.getString(5);
				String rentalDeadline = rs.getString(6);
				String comment = rs.getString(7);

				ReturnBean bean = new ReturnBean(userId, rentalId, bookStateId, rentalRent, rentalReturn,
						rentalDeadline, comment);
				list.add(bean);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	private void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}

	private void getConnection() throws DAOException {
		try {
			Class.forName("org.postgresql.Driver");
			ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
			String url = bundle.getString("url");
//			String url = "jdbc:postgresql://192.168.10.122:5432/webbook";
			String user = "slib";
			String pass = "himitu";
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			throw new DAOException("接続に失敗しました。");
		}
	}

}
