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

import slib.bean.MemberBean;

public class AddMemberDAO {
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
	
	public AddMemberDAO() throws DAOException{
		getConnection();
	}
	
	public int addMember(MemberBean bean) throws DAOException {
		
		if(con == null) 
			getConnection();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//会員ID取得
			int userId = 0;
			String sql = "select nextval('users_user_id_seq')";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if(rs.next()) {
				userId = rs.getInt(1);
			}
			rs.close();
			st.close();
			//会員情報の追加
			sql = "insert into users values(?, ?, ?, ?, ?, ?, ?, ?)";
			st = con.prepareStatement(sql);
			//会員情報をプレースホルダーに格納
			st.setInt(1, userId);
			st.setString(2, bean.getFamilyName());
			st.setString(3, bean.getLastName());
			st.setString(4, bean.getPostal());
			st.setString(5, bean.getAddress());
			st.setString(6, bean.getTel());
			st.setString(7, bean.getEmail());
			Date date = Date.valueOf(bean.getBirthday()); // SQLのDATE型に変換
			st.setDate(8, date);
			//SQLの実行
			int rows = st.executeUpdate();
			return userId;
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
	
	public List<MemberBean> completeAddMember(int userId) throws DAOException {
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
			List<MemberBean> list = new ArrayList<>();
			if(rs.next()) {
				int id = rs.getInt(1);
				String familyName = rs.getString(2);
				String lastName = rs.getString(3);
				String postal = rs.getString(4);
				String address = rs.getString(5);
				String tel = rs.getString(6);
				String email = rs.getString(7);
				String birthday = rs.getString(8);
				String joinday = rs.getString(9);
				MemberBean bean = new MemberBean(id, familyName, lastName, postal, address, tel, email, birthday, joinday);
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
}
