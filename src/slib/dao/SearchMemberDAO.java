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

import com.sun.org.apache.xml.internal.serializer.ElemDesc;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import slib.bean.MemberBean;

public class SearchMemberDAO {
	private Connection con;
	
	private void getConnection() throws DAOException {
		try {
			Class.forName("org.postgresql.Driver");
//			String url = "jdbc:postgresql://192.168.10.144:5432/webbook";
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
	
	public SearchMemberDAO() throws DAOException{
		getConnection();
	}
	
	public List<MemberBean> searchMember(String email) throws DAOException {
		
		if(con == null) 
			getConnection();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//メールアドレスをもとに検索
			String sql = "select * from users where user_email=?";
			st = con.prepareStatement(sql);
			//メールアドレスをプレースホルダーに
			st.setString(1, email);
			//SQLの実行
			rs = st.executeQuery();
			if(rs.next()) {
				int id = rs.getInt(1);
				String familyName = rs.getString(2);
				String lastName = rs.getString(3);
				String postal = rs.getString(4);
				String address = rs.getString(5);
				String tel = rs.getString(6);
				String birthday = rs.getString(8);
				String joinday = rs.getString(9);
				String unsbucribeday = rs.getString(10);
				MemberBean bean = new MemberBean(id, familyName, lastName, postal, address, tel, email, birthday, joinday, unsbucribeday);
				List<MemberBean> list = new ArrayList<>();
				list.add(bean);
				return list;
			} else {
			//該当する会員なし
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
	
	public List<MemberBean> searchMember() throws DAOException {
		
		if(con == null) 
			getConnection();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//メールアドレスをもとに検索
			String sql = "select * from users order by user_id";
			st = con.prepareStatement(sql);
			//SQLの実行
			rs = st.executeQuery();
			List<MemberBean> list = new ArrayList<>();
			while(rs.next()) {
				int id = rs.getInt(1);
				String familyName = rs.getString(2);
				String lastName = rs.getString(3);
				String postal = rs.getString(4);
				String address = rs.getString(5);
				String tel = rs.getString(6);
				String email = rs.getString(7);
				String birthday = rs.getString(8);
				String joinday = rs.getString(9);
				String unsbucribeday = rs.getString(10);
				MemberBean bean = new MemberBean(id, familyName, lastName, postal, address, tel, email, birthday, joinday, unsbucribeday);
				list.add(bean);
			}
			return list;
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
}
