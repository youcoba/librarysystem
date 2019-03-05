package slib.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import slib.bean.RentBean;

public class RentDAO {

	private Connection con;

	public RentDAO() throws DAOException {
		getConnection();
	}

	// 未返却の貸出を返すメソッド
	public int checkRental(int userId) throws DAOException {

		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from rental where user_id = ? and rental_return is null";
			st = con.prepareStatement(sql);
			st.setInt(1, userId);
			rs = st.executeQuery();
			int rent = 0;
			while (rs.next()) {
				rent = rs.getInt(1);
			}
			return rent;
		} catch (

		Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	// 返却期日の過ぎた未返却の貸出があるかどうかを返すメソッド
	public boolean checkDeadLine(int userId) throws DAOException {

		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from rental where user_id = ? and rental_return is null and rental_deadline < current_date";
			st = con.prepareStatement(sql);
			st.setInt(1, userId);
			rs = st.executeQuery();
			int rent = 0;
			while (rs.next()) {
				rent = rs.getInt(1);
			}
			if (rent != 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	// 返却期日を算出するメソッド
	public String calcDeadLine(int bookStateId) throws DAOException {

		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		String returnDeadLine;
		try {
			// 資料IDを元にISBN番号を取得
			String sql = "select * from bookstate where bookstate_id=?";
			st = con.prepareStatement(sql);
			st.setInt(1, bookStateId);
			rs = st.executeQuery();
			String isbn = null;
			if (rs.next()) {
				isbn = rs.getString(2);
			}
			rs = null;
			st = null;

			// ISBN番号を元に目録から出版日を取得
			sql = "select * from bookinfo where bookinfo_isbn=?";
			st = con.prepareStatement(sql);
			st.setString(1, isbn);
			rs = st.executeQuery();
			String publishDay = null;
			while (rs.next()) {
				publishDay = rs.getString("publish_day");
			}

			// 出版日を計算のために変換
			String[] pDay = publishDay.split("-");
			// 出版日の3ヶ月後を算出
			Calendar pub = Calendar.getInstance();
			pub.set(Integer.parseInt(pDay[0]), Integer.parseInt(pDay[1]), Integer.parseInt(pDay[2]));
			pub.add(Calendar.MONTH, 3);

			Calendar today = Calendar.getInstance();

			// 返却期日を算出
			if (pub.compareTo(today) >= 0) {
				// 出版日が3ヶ月以内ならば
				today.add(Calendar.DAY_OF_MONTH, 10); // 今日から10日後に返却
				int deadyear = today.get(Calendar.YEAR);
				int deadmonth = today.get(Calendar.MONTH) + 1;
				int deadday = today.get(Calendar.DATE);
				returnDeadLine = String.valueOf(deadyear) + "-" + String.valueOf(deadmonth) + "-"
						+ String.valueOf(deadday);
				return returnDeadLine;
			} else {
				// それ以外
				today.add(Calendar.DAY_OF_MONTH, 15); // 今日から15日後に返却
				int deadyear = today.get(Calendar.YEAR);
				int deadmonth = today.get(Calendar.MONTH) + 1;
				int deadday = today.get(Calendar.DATE);
				returnDeadLine = String.valueOf(deadyear) + "-" + String.valueOf(deadmonth) + "-"
						+ String.valueOf(deadday);
				return returnDeadLine;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	// 貸出情報を貸出台帳に追加するメソッド
	public int[] addRental(List<RentBean> list) throws DAOException {

		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		int[] rentalIds = new int[list.size()];

		try {
			for (int i = 0; i < list.size(); i++) {
				// 貸出IDの取得
				int rentalId = 0;
				String sql = "select nextval('rental_rental_id_seq')";
				st = con.prepareStatement(sql);
				rs = st.executeQuery();
				while (rs.next()) {
					rentalId = rs.getInt(1);
				}
				rentalIds[i] = rentalId;

				st = null;
				rs = null;

				// 貸出情報を貸出台帳に追加
				sql = "insert into rental values(?,?,?, default, null,?,?)";
				st = con.prepareStatement(sql);
				st.setInt(1, rentalId);
				st.setInt(2, list.get(i).getBookStateId());
				st.setInt(3, list.get(i).getUserId());
				Date returnDeadLine = Date.valueOf(list.get(i).getReturnDeadLine());
				st.setDate(4, returnDeadLine);
				st.setString(5, list.get(i).getComment());
				int rows = st.executeUpdate();
			}
			return rentalIds;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	// 登録完了画面に表示する内容を取得するメソッド
	public List<RentBean> completeAddRental(int[] rentalIds, String[] returnDeadLines) throws DAOException {

		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		List<RentBean> list = new ArrayList<>();

		try {
			for (int i = 0; i < rentalIds.length; i++) {
				// 貸出IDを元に貸出台帳を表示
				String sql = "select * from rental where rental_id=?";
				st = con.prepareStatement(sql);
				st.setInt(1, rentalIds[i]);
				rs = st.executeQuery();

				// 貸出台帳の内容を取得
				if (rs.next()) {
					int bookStateId = rs.getInt(2);
					int userId = rs.getInt(3);
					String rentalDate = rs.getString(4);
					String returnDate = rs.getString(5);
					String comment = rs.getString(7);

					// Beanに入れる
					RentBean bean = new RentBean(userId, bookStateId, rentalIds[i], rentalDate, returnDate,
							returnDeadLines[i], comment);
					list.add(bean);
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
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
			// String url = "jdbc:postgresql://192.168.10.122:5432/webbook";
//			String url = "jdbc:postgresql:webbook";
			ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
			String url = bundle.getString("url");
			String user = "slib";
			String pass = "himitu";
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			throw new DAOException("接続に失敗しました。");
		}
	}

}
