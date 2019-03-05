package slib.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import slib.bean.BookInfoBean;
import slib.bean.BookStateBean;
import slib.bean.SearchDocumentBean;

public class SearchDocumentDAO {

	private Connection con;

	public SearchDocumentDAO() throws DAOException {
	}

	// 全検索
	public List<SearchDocumentBean> searchAllDocument() throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select bookstate_id, bookinfo_isbn, category_code,category_name, bookinfo_name,bookinfo_author, publisher,publish_day, stock_day, disposal_day, s.comment from bookinfo i natural join category c  join bookstate s using(bookinfo_isbn) order by bookstate_id";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();

			List<SearchDocumentBean> list = new ArrayList<>();
			while (rs.next()) {
				int bookStateId = rs.getInt(1);
				String bookInfoIsbn = rs.getString(2);
				int categoryCode = rs.getInt(3);
				String categoryName = rs.getString(4);
				String bookInfoName = rs.getString(5);
				String bookInfoAuthor = rs.getString(6);
				String publisher = rs.getString(7);
				String publishDay = rs.getString(8);
				String stockDay = rs.getString(9);
				String disposalDay = rs.getString(10);
				String comment = rs.getString(11);

				SearchDocumentBean bean = new SearchDocumentBean(bookStateId, bookInfoIsbn, categoryCode, categoryName,
						bookInfoName, bookInfoAuthor, publisher, publishDay, stockDay, disposalDay, comment);
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

	// 資料ID検索
	public List<SearchDocumentBean> searchDocument(int bookStateId) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select bookstate_id, bookinfo_isbn, category_code,category_name, bookinfo_name,bookinfo_author, publisher,publish_day, stock_day, disposal_day, s.comment from bookstate s join bookinfo i using(bookinfo_isbn) join category c using(category_code) where bookstate_id = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, bookStateId);
			rs = st.executeQuery();
			List<SearchDocumentBean> list = new ArrayList<>();
			while (rs.next()) {
				String bookInfoIsbn = rs.getString(2);
				int categoryCode = rs.getInt(3);
				String categoryName = rs.getString(4);
				String bookInfoName = rs.getString(5);
				String bookInfoAuthor = rs.getString(6);
				String publisher = rs.getString(7);
				String publishDay = rs.getString(8);
				String stockDay = rs.getString(9);
				String disposalDay = rs.getString(10);
				String comment = rs.getString(11);

				SearchDocumentBean bean = new SearchDocumentBean(bookStateId, bookInfoIsbn, categoryCode, categoryName,
						bookInfoName, bookInfoAuthor, publisher, publishDay, stockDay, disposalDay, comment);
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

	// 台帳表示
	public List<BookStateBean> showBookState(int bookStateId) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select bookstate_id, bookinfo_isbn, bookinfo_name,stock_day,disposal_day,s.comment from bookstate s join bookinfo i using(bookinfo_isbn) where bookstate_id = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, bookStateId);
			rs = st.executeQuery();
			List<BookStateBean> list = new ArrayList<>();
			while (rs.next()) {
				String bookISBN = rs.getString(2);
				String bookInfoName = rs.getString(3);
				String stockDay = rs.getString(4);
				String disposalDay = rs.getString(5);
				String stateComment = rs.getString(6);

				BookStateBean bean = new BookStateBean(bookStateId, bookISBN, bookInfoName, stockDay, disposalDay,
						stateComment);
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

	// 目録表示
	public List<BookInfoBean> showBookInfo(String bookISBN) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select * from bookinfo where bookinfo_isbn = ?";
			st = con.prepareStatement(sql);
			st.setString(1, bookISBN);
			rs = st.executeQuery();
			List<BookInfoBean> list = new ArrayList<>();
			while (rs.next()) {
				int categoryCode = rs.getInt(2);
				String bookInfoName = rs.getString(3);
				String bookInfoAuthor = rs.getString(4);
				String publishDay = rs.getString(5);
				String publisher = rs.getString(6);

				BookInfoBean bean = new BookInfoBean(bookISBN, categoryCode, bookInfoName, bookInfoAuthor, publishDay,
						publisher);
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

	// 台帳更新
	public List<BookStateBean> updateBookState(BookStateBean bean) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "update bookstate set stock_day =?,disposal_day =?,comment =? where bookstate_id = ?";
			st = con.prepareStatement(sql);
			Date stockday = Date.valueOf(bean.getStockDay());
			st.setDate(1, stockday);
			Date disposalday = Date.valueOf(bean.getDisposalDay());
			st.setDate(2, disposalday);
			st.setString(3, bean.getStateComment());
			st.setInt(4, bean.getBookStateId());
			st.executeUpdate();
			st.close();
			sql = "select bookstate_id, bookinfo_isbn, bookinfo_name,stock_day,disposal_day,s.comment from bookstate s join bookinfo i using(bookinfo_isbn) where bookstate_id = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, bean.getBookStateId());
			rs = st.executeQuery();
			List<BookStateBean> list = new ArrayList<>();
			while (rs.next()) {
				int bookStateId = rs.getInt(1);
				String bookISBN = rs.getString(2);
				String stockDay = rs.getString(3);
				String disposalDay = rs.getString(4);
				String stateComment = rs.getString(5);

				BookStateBean updatebean = new BookStateBean(bookStateId, bookISBN, stockDay, disposalDay,
						stateComment);
				list.add(updatebean);
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

	// 目録更新
	public List<BookInfoBean> updateBookInfo(BookInfoBean updateinfo) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "update bookinfo set category_code =?,bookinfo_name =?,bookinfo_author = ?, publish_day=?,publisher=? where bookinfo_isbn = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, updateinfo.getCategoryCode());
			st.setString(2, updateinfo.getBookInfoName());
			// System.out.println(updateinfo.getBookInfoName());
			st.setString(3, updateinfo.getBookInfoAuthor());
			Date publishday = Date.valueOf(updateinfo.getPublishDay());
			st.setDate(4, publishday);
			st.setString(5, updateinfo.getPublisher());
			// System.out.println(updateinfo.getPublisher());

			st.setString(6, updateinfo.getBookISBN());
			// System.out.println(updateinfo.getBookISBN());

			st.executeUpdate();
			st.close();
			sql = "select * from bookinfo where bookinfo_isbn = ?";
			st = con.prepareStatement(sql);
			st.setString(1, updateinfo.getBookISBN());
			rs = st.executeQuery();
			List<BookInfoBean> list = new ArrayList<>();
			while (rs.next()) {
				String bookISBN = rs.getString(1);
				int categoryCode = rs.getInt(2);
				String bookInfoName = rs.getString(3);
				String bookInfoAuthor = rs.getString(4);
				String publishDay = rs.getString(5);
				String publisher = rs.getString(5);

				BookInfoBean updatebean = new BookInfoBean(bookISBN, categoryCode, bookInfoName, bookInfoAuthor,
						publishDay, publisher);
				list.add(updatebean);
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

	// 廃棄
	public List<SearchDocumentBean> disposalDocument(int bookStateId) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "update bookstate set disposal_day = current_date where bookstate_id = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, bookStateId);
			st.executeUpdate();
			st.close();
			sql = "select bookstate_id, bookinfo_isbn, category_code,category_name, bookinfo_name,bookinfo_author, publisher,publish_day, stock_day, disposal_day, s.comment from bookstate s join bookinfo i using(bookinfo_isbn) join category c using(category_code) where bookstate_id = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, bookStateId);
			rs = st.executeQuery();
			List<SearchDocumentBean> list = new ArrayList<>();
			while (rs.next()) {
				String bookInfoIsbn = rs.getString(2);
				int categoryCode = rs.getInt(3);
				String categoryName = rs.getString(4);
				String bookInfoName = rs.getString(5);
				String bookInfoAuthor = rs.getString(6);
				String publisher = rs.getString(7);
				String publishDay = rs.getString(8);
				String stockDay = rs.getString(9);
				String disposalDay = rs.getString(10);
				String comment = rs.getString(11);

				SearchDocumentBean bean = new SearchDocumentBean(bookStateId, bookInfoIsbn, categoryCode, categoryName,
						bookInfoName, bookInfoAuthor, publisher, publishDay, stockDay, disposalDay, comment);
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

	public String[] splitBirthday(String birthday) {
		String[] birth = birthday.split("-");
		return birth;
	}

	private void getConnection() throws DAOException {
		try {
			Class.forName("org.postgresql.Driver");
			ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
			String url = bundle.getString("url");
			// String url = "jdbc:postgresql://192.168.10.122:5432/webbook";
			String user = "slib";
			String pass = "himitu";
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			throw new DAOException("接続に失敗しました。");
		}
	}
}
