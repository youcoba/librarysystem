package slib.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import slib.bean.MemberBean;
import slib.dao.AddMemberDAO;
import slib.dao.DAOException;

/**
 * Servlet implementation class AddMemberServlet
 */
@WebServlet("/AddMemberServlet")
public class AddMemberServlet extends HttpServlet {
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
			AddMemberDAO add = new AddMemberDAO();
			//パラーメータの取得
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
			
			
			//確認画面表示
			if(action.equals("conf")) {
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
					MemberBean bean = new MemberBean();
					bean.setFamilyName(familyName);
					bean.setLastName(lastName);
					bean.setPostal(postal);
					bean.setAddress(address);
					bean.setTel(tel);
					bean.setEmail(email);
					bean.setBirthday(birthday);
					session.setAttribute("addmember", bean);
					gotoPage(request, response, "/addMemberConf.jsp");
				}
			} else 
			if(action.equals("comp")) {
				MemberBean member = (MemberBean)session.getAttribute("addmember"); 
				//追加処理&会員ID取得
				int userId = add.addMember(member);
				//追加後、追加レコードを表示
				List<MemberBean> list = add.completeAddMember(userId);
				request.setAttribute("addmember", list);
				gotoPage(request, response, "/addMemberComp.jsp");
				session.removeAttribute("addmember");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/error.jsp");
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
