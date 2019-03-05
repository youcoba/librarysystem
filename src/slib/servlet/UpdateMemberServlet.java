package slib.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.objects.NativeURIError;
import slib.bean.MemberBean;
import slib.dao.DAOException;
import slib.dao.UpdateMemberDAO;

/**
 * Servlet implementation class AddMemberServlet
 */
@WebServlet("/UpdateMemberServlet")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("UTF-8");
			//パラメータで振り分けるのならここで解析
			String action = request.getParameter("action");
			//DAOの生成
			UpdateMemberDAO dao = new UpdateMemberDAO();
			
			if (action.equals("update")) {
				//IDを取得
				int userId = Integer.parseInt(request.getParameter("id"));
				session.setAttribute("id", userId); //検索結果が複数出たときの更新ボタンおしたあとの挙動について再検討
				
				//変更前の情報を取得
				MemberBean before = dao.showMember(userId);
				session.setAttribute("before", before);
				String[] birth = dao.splitBirthday(before.getBirthday());
				session.setAttribute("birthday", birth);
				gotoPage(request, response, "/updateMemberInfo.jsp");
			} else
			//確認画面表示
			if(action.equals("conf")) {
				//パラメータを取得
				String familyName = request.getParameter("familyName");
				String lastName = request.getParameter("lastName");
				String postal = request.getParameter("postal");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");
				String byear = request.getParameter("byear");
				String bmonth = request.getParameter("bmonth");
				String bdate = request.getParameter("bdate");
				String birthday = byear + "-" + bmonth + "-" + bdate; //SQLのDATE型にあわせる
				//入力エラーチェック
				if(familyName.isEmpty() || lastName.isEmpty() || postal.isEmpty() || address.isEmpty() || tel.isEmpty() || email.isEmpty()) {
					request.setAttribute("message", "必須項目が未入力です。");
					gotoPage(request, response, "/error.jsp");
					return;
				} else
				if((!postal.isEmpty() && postal.length() != 7) || (!email.isEmpty() && !(email.contains("@") && email.contains(".")))) {
					request.setAttribute("message", "入力エラーです。郵便番号またはメールアドレスの入力に誤りがあります。");
					gotoPage(request, response, "/error.jsp");
					return;
				} else {
					try {
						int year = Integer.parseInt(byear);
						int month = Integer.parseInt(bmonth);
						int date = Integer.parseInt(bdate);
						if(year == 0 || 1899 > year || month <= 0 || month >= 13 || date <= 0 || date >= 32) { //120歳以上の人はいない前提
							request.setAttribute("message", "入力エラーです。生年月日を正しく入力してください。");
							gotoPage(request, response, "/error.jsp");
							return;
						}
					} catch(NumberFormatException e) {
						request.setAttribute("message", "入力エラーです。生年月日を正しく入力してください。");
						gotoPage(request, response, "/error.jsp");
						return;
					}
					MemberBean bean = new MemberBean();
					bean.setId((int)session.getAttribute("id"));
					bean.setFamilyName(familyName);
					bean.setLastName(lastName);
					bean.setPostal(postal);
					bean.setAddress(address);
					bean.setTel(tel);
					bean.setEmail(email);
					bean.setBirthday(birthday);
					session.setAttribute("updatemember", bean);
					gotoPage(request, response, "/updateMemberInfoConf.jsp");
				}
			} else 
			if(action.equals("comp")) {
				MemberBean update = (MemberBean)session.getAttribute("updatemember"); 
				//更新処理
				dao.updateMember(update);
				//更新後、レコードを表示
				MemberBean show = dao.showMember((int)session.getAttribute("id"));
				request.setAttribute("showmember", show);
				gotoPage(request, response, "/updateMemberInfoComp.jsp");
				session.removeAttribute("updatemember");
				session.removeAttribute("id");
				session.removeAttribute("birthday");
				session.invalidate();
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/error.jsp");
			session.invalidate();
		}
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException{
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
