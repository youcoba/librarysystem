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

public class UpdateMemberDAO {
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
	
	public UpdateMemberDAO() throws DAOException{
		getConnection();
	}
	
	public List<MemberBean> updateMember(MemberBean bean) throws DAOException {
		
		if(con == null) 
			getConnection();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//会員情報の更新
			String sql = "update users set user_family_name=?, user_last_name=?, user_postal=?, user_address=?, user_tel=?, user_email=?, user_birthday=? where user_id=?";
			st = con.prepareStatement(sql);
			//会員情報をプレースホルダーに格納
			st.setString(1, bean.getFamilyName());
			st.setString(2, bean.getLastName());
			st.setString(3, bean.getPostal());
			st.setString(4, bean.getAddress());
			st.setString(5, bean.getTel());
			st.setString(6, bean.getEmail());
			Date date = Date.valueOf(bean.getBirthday()); // SQLのDATE型に変換
			st.setDate(7, date);
			st.setInt(8, bean.getId());
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
				MemberBean updatebean = new MemberBean(id, familyName, lastName, postal, address, tel, email, birthday, joinday);
				List<MemberBean> list = new ArrayList<>();
				list.add(updatebean);
				return list;
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
	
	public String[] splitBirthday(String birthday) {
		String[] birth = birthday.split("-");
		return birth;
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
