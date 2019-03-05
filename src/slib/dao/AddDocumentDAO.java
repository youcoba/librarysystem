package slib.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.org.apache.regexp.internal.recompile;

import slib.bean.BookInfoBean;
import slib.bean.BookStateBean;
import slib.bean.MemberBean;

public class AddDocumentDAO {
	private Connection con;
	
	private void getConnection() throws DAOException {
		try {
			Class.forName("org.postgresql.Driver");
//			String url = "jdbc:postgresql://192.168.10.122:5432/webbook";
//			String url = "jdbc:postgresql:webbook";
//			String url = "jdbc:postgresql:webbook";
			ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
			String url = bundle.getString("url");
			String user = "slib";
			String pass = "himitu";
			con = DriverManager.getConnection(url, user, pass);
		} catch(Exception e) {
			throw new DAOException("接続に失敗しました。");
		}
	}
	
	private void close() throws SQLException {
		if(con != null) {
			con.close();
			con = null;
		}
	}
	
	public AddDocumentDAO() throws DAOException{
		getConnection();
	}
	
	
	public boolean checkISBN(String isbn) throws DAOException {
		if(con == null) 
			getConnection();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from bookinfo where bookinfo_isbn=?";
			st = con.prepareStatement(sql);
			st.setString(1, isbn);
			rs = st.executeQuery();
			if(rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				//リソースの解放
				if(rs != null) rs.close();
				if(st != null) st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}
	
	public int addBook(BookStateBean bean, int number) throws DAOException {
		
		if(con == null) 
			getConnection();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			int bookId = 0;
			for(int i = 0; i < number; i++) { //本の冊数分ループ処理
				//資料ID取得
				String sql = "select nextval('bookstate_bookstate_id_seq')";
				st = con.prepareStatement(sql);
				rs = st.executeQuery();
				if(rs.next()) {
					bookId = rs.getInt(1);
				}
				//資料情報の追加
				sql = "insert into bookstate(bookstate_id, bookinfo_isbn, stock_day, comment) values(?, ?, ?, ?)";
				st = con.prepareStatement(sql);
				//資料情報をプレースホルダーに格納
				st.setInt(1, bookId);
				st.setString(2, bean.getBookISBN());
				Date date = Date.valueOf(bean.getStockDay()); // SQLのDATE型に変換
				st.setDate(3, date);
				st.setString(4, bean.getStateComment());
				//SQLの実行
				st.executeUpdate();
			}
			return bookId;
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				//リソースの解放
				if(st != null) st.close();
				if(rs != null) rs.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}
	
	public void addBookInfo(String isbn) throws DAOException {
		
		if(con == null) 
			getConnection();
		
		PreparedStatement st = null;
		try {
			//目録にISBN追加
			String sql = "insert into bookinfo values(?, ?, ?, ?, ?, ?)";
			st = con.prepareStatement(sql);
			st.setString(1, isbn);
			st.setInt(2, 0);
			st.setString(3, "-");
			st.setString(4, "-");
			st.setDate(5, Date.valueOf("2000-01-01"));
			st.setString(6, "-");
			//SQLの実行
			st.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				//リソースの解放
				if(st != null) 
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}
	
	public void updateBookInfo(BookInfoBean bean, String isbn) throws DAOException {
		
		if(con == null) 
			getConnection();
		
		PreparedStatement st = null;
		try {
			//目録にISBN追加
			String sql = "update bookinfo set category_code=?, bookinfo_name=?, bookinfo_author=?, publish_day=?, publisher=?, comment=? where bookinfo_isbn=?";
			st = con.prepareStatement(sql);
			st.setInt(1, bean.getCategoryCode());
			st.setString(2, bean.getBookInfoName());
			st.setString(3, bean.getBookInfoAuthor());
			st.setDate(4, Date.valueOf(bean.getPublishDay()));
			st.setString(5, bean.getPublisher());
			st.setString(6, bean.getInfoComment());
			st.setString(7, isbn);
			//SQLの実行
			st.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				//リソースの解放
				if(st != null) 
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}
	
	public List<BookStateBean> showBookState(int bookId1, int bookId2) throws DAOException {
		if(con == null) 
			getConnection();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//SQL
			String sql = "select * from bookstate where bookstate_id between ? and ?";
			st = con.prepareStatement(sql);
			//渡ってきた資料IDをプレースホルダーにいれる
			st.setInt(1, bookId1);
			st.setInt(2, bookId2);
			rs = st.executeQuery();
			List<BookStateBean> list = new ArrayList<>();
			while(rs.next()) {
				int id = rs.getInt(1);
				String isbn = rs.getString(2);
				String stockDay = rs.getString(3);
				String disposalDay = rs.getString(4);
				String stateComment = rs.getString(5);
				BookStateBean bean = new BookStateBean(id, isbn, stockDay, disposalDay, stateComment);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				//リソースの解放
				if(rs != null)rs.close();
				if(st != null)st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}
	
	public BookInfoBean showBookInfo(String isbn) throws DAOException {
		if(con == null) 
			getConnection();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//SQL
			String sql = "select * from bookinfo where bookinfo_isbn=?";
			st = con.prepareStatement(sql);
			//渡ってきたISBNをプレースホルダーにいれる
			st.setString(1, isbn);
			rs = st.executeQuery();
			BookInfoBean bean = new BookInfoBean();
			if(rs.next()) {
				bean.setBookISBN(isbn);
				bean.setCategoryCode(rs.getInt(2));
				bean.setBookInfoName(rs.getString(3));
				bean.setBookInfoAuthor(rs.getString(4));
				bean.setPublishDay(rs.getString(5));
				bean.setPublisher(rs.getString(6));
				bean.setInfoComment(rs.getString(7));
			}
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				//リソースの解放
				if(rs != null)rs.close();
				if(st != null)st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}
}
