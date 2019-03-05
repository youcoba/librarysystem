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

import com.sun.glass.ui.Menu;

import slib.bean.MemberBean;
import slib.bean.ReturnBean;

public class UnsubscribeMemberDAO {
	private Connection con;
	
	private void getConnection() throws DAOException {
		try {
			Class.forName("org.postgresql.Driver");
//			String url = "jdbc:postgresql://192.168.10.122:5432/webbook";
			ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
			String url = bundle.getString("url");
//			String url = "jdbc:postgresql:webbook";
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
	
	public UnsubscribeMemberDAO() throws DAOException{
		getConnection();
	}
	
	public boolean checkRental(int userId) throws DAOException {
		
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
					rent  = rs.getInt(1);
				}
				if(rent == 0) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			} finally {
				try {
					if (st != null)st.close();
					if (rs != null)rs.close();
					close();
				} catch (Exception e) {
					throw new DAOException("リソースの解放に失敗しました。");
				}
			}
		}

	public MemberBean unsubscribeMember(MemberBean bean) throws DAOException {
		
		if(con == null) 
			getConnection();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//退会年月日を追加
			String sql = "update users set user_unsubscribeday=current_date where user_id=?";
			st = con.prepareStatement(sql);
			//会員IDをプレースホルダーに格納
			st.setInt(1, bean.getId());
			//SQLの実行
			int rows = st.executeUpdate();
			st = null;
			//更新した情報を取得
			sql = "select * from users where user_id=?";
			st = con.prepareStatement(sql);
			st.setInt(1, bean.getId());
			rs = st.executeQuery();
			if(rs.next()) {
				int id = rs.getInt(1);
				String familyName = rs.getString(2);
				String lastName = rs.getString(3);
				String postal = rs.getString(4);
				String address = rs.getString(5);
				String email = rs.getString(7);
				String tel = rs.getString(6);
				String birthday = rs.getString(8);
				String joinday = rs.getString(9);
				String unsubscribeday = rs.getString(10);
				MemberBean unsubscribe = new MemberBean(id, familyName, lastName, postal, address, tel, email, birthday, joinday, unsubscribeday);
				return unsubscribe;
			} else {
				return null;
			}
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
	
	
	public MemberBean showMember(int userId) throws DAOException {
		if(con == null) 
			getConnection();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//SQL
			String sql = "select * from users where user_id=?";
			st = con.prepareStatement(sql);
			//渡ってきたuserIDをプレースホルダーにいれる
			st.setInt(1, userId);
			rs = st.executeQuery();
			MemberBean bean = new MemberBean();
			if(rs.next()) {
				bean.setId(rs.getInt(1));
				bean.setFamilyName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setPostal(rs.getString(4));
				bean.setAddress(rs.getString(5));
				bean.setTel(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setBirthday(rs.getString(8));
				bean.setJoinday(rs.getString(9));
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
