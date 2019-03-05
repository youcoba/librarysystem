package slib.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import slib.bean.MemberBean;
import slib.bean.RentBean;
import slib.dao.AddMemberDAO;
import slib.dao.DAOException;
import slib.dao.RentDAO;

/**
 * Servlet implementation class RentServlet
 */
@WebServlet("/RentServlet")
public class RentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");

			RentDAO dao = new RentDAO();

			// セッション開始
			HttpSession session = request.getSession();

			int[] bookStateIds = new int[5];

			// 「確認画面へ」ボタンが押された場合
			if (action.equals("confirm")) {
				// パラメータ取得
				String suserId = request.getParameter("userId");
				String[] sbooks = {
						request.getParameter("bookStateId1"),
						request.getParameter("bookStateId2"),
						request.getParameter("bookStateId3"),
						request.getParameter("bookStateId4"),
						request.getParameter("bookStateId5")
				};
				int count = 0;
				for(int i = 0; i < 5; i++) {
					if(!sbooks[i].isEmpty()) {
						bookStateIds[i] = Integer.parseInt(sbooks[i]);
					} else {
						bookStateIds[i] = 0;
						count++;
					}
				}
				if(count == 5) {
					request.setAttribute("message", "会員ID又は資料IDが入力されていません。");
					gotoPage(request, response, "/error.jsp");
					return;
				}
				
				// 入力されない可能性があるところは初期化
				int userId = 0;
				
				if(suserId.isEmpty()) {
					request.setAttribute("message", "会員ID又は資料IDが入力されていません。");
					gotoPage(request, response, "/error.jsp");
					return;
				} else {
						userId = Integer.parseInt(suserId);
					}
					

				// スコープに入れる
				session.setAttribute("userid", userId);
				session.setAttribute("bookid", bookStateIds);

				// 入力情報をBeanに追加
				List<RentBean> list = new ArrayList<>();

				for (int i = 0; i < bookStateIds.length; i++) {
					if (bookStateIds[i] == 0) {
						continue;
					} else {
						RentBean bean = new RentBean();
						bean.setUserId(userId);
						bean.setBookStateId(bookStateIds[i]);
						list.add(bean);
					}
				}
				// スコープに入れて
				session.setAttribute("addrental", list);
				// 確認画面に転送
				gotoPage(request, response, "/rentConf.jsp");
				// 「貸出処理」ボタンが押された時
			} else if (action.equals("comp")) {
				// 登録内容を取得
				List<RentBean> list = (List<RentBean>) session.getAttribute("addrental");
				String[] returnDeadLines = new String[list.size()];
				// 返却期日を過ぎた貸出がある場合
				if (dao.checkDeadLine((int) session.getAttribute("userid"))) {
					request.setAttribute("message", "返却期日が過ぎている貸出があります。<br><br><a href='/slibsys/searchRent.jsp' class='btn-flat-border'>貸出履歴の検索へ</a>");
					gotoPage(request, response, "/error.jsp");
					return;
					// 合計貸出冊数が5冊を超えている場合
				} else if (list.size() + dao.checkRental((int) session.getAttribute("userid")) > 5) {
					request.setAttribute("message", "貸出が5冊を超えています。<br><br><a href='/slibsys/rent.jsp' class='btn-flat-border'>入力画面に戻る</a>");
					gotoPage(request, response, "/error.jsp");
					return;
				} else {
					// 返却期日を取得
					for (int i = 0; i < list.size(); i++) {
						returnDeadLines[i] = dao.calcDeadLine(list.get(i).getBookStateId());
						list.get(i).setReturnDeadLine(returnDeadLines[i]);
					}
				}
				// 貸出IDを取得
				int[] rentalIds = dao.addRental(list);

				// 追加後、追加レコードを表示
				List<RentBean> list2 = dao.completeAddRental(rentalIds, returnDeadLines);
				request.setAttribute("addrental2", list2);
				// 完了画面に転送
				gotoPage(request, response, "/rentComp.jsp");
				session.removeAttribute("addrental");
				session.removeAttribute("addrental2");
			}
		} catch (java.lang.NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("message", "入力に誤りがあります。");
			gotoPage(request, response, "/error.jsp");
		}catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String string)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(string);
		rd.forward(request, response);
		// TODO 自動生成されたメソッド・スタブ

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
