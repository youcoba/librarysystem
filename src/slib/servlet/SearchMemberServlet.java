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
import slib.dao.DAOException;
import slib.dao.SearchMemberDAO;

/**
 * Servlet implementation class AddMemberServlet
 */
@WebServlet("/SearchMemberServlet")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("UTF-8");
			//パラメータで振り分けるのならここで解析
//			String action = request.getParameter("action");
			//DAOの生成
			SearchMemberDAO dao = new SearchMemberDAO();
			//パラーメータの取得
			String email = request.getParameter("email");
			//検索の実行
			List<MemberBean> infos;
			if(email != "") {
				infos = dao.searchMember(email);
				if(infos == null) {
					request.setAttribute("message", "該当する会員が見つかりませんでした。");
				}
			} else {
				infos = dao.searchMember();
			}
			//リクエストスコープに結果を入れる
			request.setAttribute("info", infos);
			gotoPage(request, response, "/searchMember.jsp");
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
