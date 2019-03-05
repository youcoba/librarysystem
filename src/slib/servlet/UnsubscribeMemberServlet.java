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
import slib.dao.UnsubscribeMemberDAO;
import slib.dao.UpdateMemberDAO;

/**
 * Servlet implementation class AddMemberServlet
 */
@WebServlet("/UnsubscribeMemberServlet")
public class UnsubscribeMemberServlet extends HttpServlet {
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
			UnsubscribeMemberDAO dao = new UnsubscribeMemberDAO();
			//IDを取得
			if (action.equals("unsubscribe")) {
				int userId = Integer.parseInt(request.getParameter("id"));
				session.setAttribute("id", userId);
				//確認画面を表示
				MemberBean bean = dao.showMember(userId);
				session.setAttribute("show", bean);
				gotoPage(request, response, "/unsubscribeMember.jsp");
			} else
			if(action.equals("comp")) {
				MemberBean unsubscribe = (MemberBean)session.getAttribute("show"); 
				//未返却の貸出がないかチェック
				if(dao.checkRental((int)session.getAttribute("id"))) {
					//退会処理&表示
					MemberBean unsubscribed = dao.unsubscribeMember(unsubscribe);
					request.setAttribute("unsubscribed", unsubscribed);
					gotoPage(request, response, "/unsubscribeMemberComp.jsp");
					session.removeAttribute("id");
				} else {
					request.setAttribute("message", "未返却の貸出があります。返却後に退会処理が可能になります。");
					gotoPage(request, response, "/error.jsp");
					return;
				}
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
