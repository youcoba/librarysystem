package slib.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import slib.bean.ReturnBean;
import slib.dao.DAOException;
import slib.dao.ReturnDAO;

/**
 * Servlet implementation class ReturnServlet
 */
@WebServlet("/ReturnServlet")
public class ReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			ReturnDAO dao = new ReturnDAO();
			if (action.equals("search")) {
				String id = request.getParameter("userId");

				if (id == null || id.length() == 0) { // 全検索
					List<ReturnBean> info = dao.searchAllMember();
					request.setAttribute("infos", info);
					gotoPage(request, response, "/return.jsp");

				} else { // 会員ID検索
					int userid = Integer.parseInt(id);
					List<ReturnBean> info = dao.searchMember(userid);
					if (info.size() == 0) {
						request.setAttribute("message", "条件に該当する資料がありませんでした");
					} else {
						request.setAttribute("infos", info);
					}
					gotoPage(request, response, "/return.jsp");
				}
			}
			// 返却
			else if (action.equals("return")) {
				String id = request.getParameter("rentalId");
				int rentalid = Integer.parseInt(id);
				List<ReturnBean> info = dao.returnDoc(rentalid);
				request.setAttribute("infos", info);
				gotoPage(request, response, "/returnComp.jsp");
			} else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/error.jsp");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/error.jsp");
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
