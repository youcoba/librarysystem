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

import slib.bean.ReturnBean;
import slib.dao.DAOException;
import slib.dao.SearchRentDAO;

/**
 * Servlet implementation class SearchRentServlet
 */
@WebServlet("/SearchRentServlet")
public class SearchRentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			SearchRentDAO dao = new SearchRentDAO();
			if (action.equals("search")) {
				String userid = request.getParameter("userId");
				String bookstateid = request.getParameter("bookStateId");
				session.setAttribute("userid", userid);
				session.setAttribute("bookstateid", bookstateid);

				if (userid.isEmpty() &&  bookstateid.isEmpty() ) { // 全検索
					List<ReturnBean> info = dao.searchAllRent();
					request.setAttribute("infos", info);
					gotoPage(request, response, "/searchRent.jsp");

				} else { // ID検索
					int userId = 0;
					int bookStateId = 0;
					if(!((String)session.getAttribute("userid")).isEmpty()) {
						userId = Integer.parseInt(userid);
					} 
					if(!((String)session.getAttribute("bookstateid")).isEmpty()) {
						bookStateId = Integer.parseInt(bookstateid);
					}
					List<ReturnBean> info = dao.searchRental(userId, bookStateId);
					if (info.size() == 0) {
						request.setAttribute("message", "条件に該当する資料がありませんでした");
					} else {
						request.setAttribute("infos", info);
					}
					gotoPage(request, response, "/searchRent.jsp");
				} 
			} else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/error.jsp");
				return;
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/error.jsp");
			return;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("message", "会員ID、資料IDは数字を入力してください。");
			gotoPage(request, response, "/error.jsp");
			return;
		}

	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
