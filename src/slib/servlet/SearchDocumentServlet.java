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

import slib.bean.BookInfoBean;
import slib.bean.BookStateBean;
import slib.bean.SearchDocumentBean;
import slib.dao.DAOException;
import slib.dao.SearchDocumentDAO;

/**
 * Servlet implementation class SearchDocumentServret
 */
@WebServlet("/SearchDocumentServlet")
public class SearchDocumentServlet extends HttpServlet {
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
			SearchDocumentDAO dao = new SearchDocumentDAO();
			if (action.equals("search")) {
				String bookstateid = request.getParameter("bookStateId");
				// 全検索
				if (bookstateid == null || bookstateid.length() == 0) {
					List<SearchDocumentBean> info = dao.searchAllDocument();
					request.setAttribute("infos", info);
					gotoPage(request, response, "/searchDocument.jsp");
					return;
					// ID検索
				} else {
					int bookStateId = Integer.parseInt(bookstateid);
					List<SearchDocumentBean> info = dao.searchDocument(bookStateId);
					if (info.size() == 0) {
						request.setAttribute("message", "条件に該当する資料がありませんでした");
					} else {
						request.setAttribute("infos", info);
					}
					gotoPage(request, response, "/searchDocument.jsp");
				}
			}

			// 更新メニュー
			else if (action.equals("update")) {
				String bookstateid = request.getParameter("bookStateId");
				String bookinfoisbn = request.getParameter("bookInfoIsbn");
				int bookStateId = Integer.parseInt(bookstateid);
				session.setAttribute("id", bookStateId);
				session.setAttribute("isbn", bookinfoisbn);
				gotoPage(request, response, "/updateDocument.jsp");
			}
			// 資料台帳表示
			else if (action.equals("showState")) {
				Integer bookStateId = (Integer) session.getAttribute("id");
				List<BookStateBean> info = dao.showBookState(bookStateId);
				request.setAttribute("infos", info);
				String[] stock = dao.splitBirthday(info.get(0).getStockDay());
				request.setAttribute("stockDay", stock);
				String[] disposal = dao.splitBirthday(info.get(0).getDisposalDay());
				request.setAttribute("disposalDay", disposal);
				gotoPage(request, response, "/updateDocumentState.jsp");
			}

			// 資料台帳確認
			else if (action.equals("updateStateConf")) {
				Integer bookStateId = (Integer) session.getAttribute("id");
				String bookinfoname = request.getParameter("bookInfoName");
				String stockdayY = request.getParameter("stockDayY");
				String stockdayM = request.getParameter("stockDayM");
				String stockdayD = request.getParameter("stockDayD");
				String disposaldayY = request.getParameter("disposalDayY");
				String disposaldayM = request.getParameter("disposalDayM");
				String disposaldayD = request.getParameter("disposalDayD");
				String statecomment = request.getParameter("stateComment");
				String stockday = stockdayY + "-" + stockdayM + "-" + stockdayD;
				String disposalday = disposaldayY + "-" + disposaldayM + "-" + disposaldayD;

				if (stockdayY.isEmpty() || stockdayM.isEmpty() || stockdayD.isEmpty() || disposaldayY.isEmpty()
						|| disposaldayM.isEmpty() || disposaldayD.isEmpty()) {
					request.setAttribute("message", "必須項目が未入力です。");
					gotoPage(request, response, "/error.jsp");
					return;
				} else if (!stockdayY.isEmpty() && stockdayY.length() != 4
						|| !stockdayM.isEmpty() && stockdayM.length() != 2
						|| !stockdayD.isEmpty() && stockdayD.length() != 2
						|| !disposaldayY.isEmpty() && disposaldayY.length() != 4
						|| !disposaldayM.isEmpty() && disposaldayM.length() != 2
						|| !disposaldayD.isEmpty() && disposaldayD.length() != 2) {
					request.setAttribute("message", "入力エラーです。入荷年月日または廃棄年月日の入力に誤りがあります。");
					gotoPage(request, response, "/error.jsp");
					return;
				} else {
					BookStateBean bean = new BookStateBean();
					bean.setBookStateId(bookStateId);
					bean.setBookInfoName(bookinfoname);
					bean.setStockDay(stockday);
					bean.setDisposalDay(disposalday);
					bean.setStateComment(statecomment);

					session.setAttribute("updatestate", bean);

					gotoPage(request, response, "/updateDocumentStateConf.jsp");
				}
			}

			// 資料台帳更新
			else if (action.equals("updateState")) {
				BookStateBean update = (BookStateBean) session.getAttribute("updatestate");
				List<BookStateBean> info = dao.updateBookState(update);
				request.setAttribute("infos", info);
				gotoPage(request, response, "/updateDocumentStateComp.jsp");
				session.removeAttribute("updatestate");
				session.removeAttribute("infos");
				session.removeAttribute("id");
				session.removeAttribute("isbn");

			}

			// 資料目録表示
			else if (action.equals("showInfo")) {
				String bookISBN = (String) session.getAttribute("isbn");
				List<BookInfoBean> info = dao.showBookInfo(bookISBN);
				request.setAttribute("infos", info);
				String[] publish = dao.splitBirthday(info.get(0).getPublishDay());
				request.setAttribute("publishDay", publish);
				gotoPage(request, response, "/updateDocumentInfo.jsp");
			}

			// 資料目録確認
			else if (action.equals("updateInfoConf")) {
				String categorycode = request.getParameter("categoryCode");
				String bookISBN = (String) session.getAttribute("isbn");
				String bookInfoName = request.getParameter("bookInfoName");
				String bookinfoauthor = request.getParameter("bookInfoAuthor");
				String publishdayY = request.getParameter("publishDayY");
				String publishdayM = request.getParameter("publishDayM");
				String publishdayD = request.getParameter("publishDayD");
				String publisher = request.getParameter("publisher");
				String publishday = publishdayY + "-" + publishdayM + "-" + publishdayD;
				if (bookInfoName.isEmpty() || categorycode.isEmpty() || bookinfoauthor.isEmpty()
						|| publishdayY.isEmpty() || publishdayM.isEmpty() || publishdayD.isEmpty()) {
					request.setAttribute("message", "必須項目が未入力です。");
					gotoPage(request, response, "/error.jsp");
					return;
				} else if (!categorycode.isEmpty() && categorycode.length() != 1
						|| !publishdayY.isEmpty() && publishdayY.length() != 4
						|| !publishdayM.isEmpty() && publishdayM.length() != 2
						|| !publishdayD.isEmpty() && publishdayD.length() != 2)

				{
					request.setAttribute("message", "入力エラーです。入荷年月日または廃棄年月日の入力に誤りがあります。");
					gotoPage(request, response, "/error.jsp");

				} else {
					int categoryCode = Integer.parseInt(categorycode);
					BookInfoBean bean = new BookInfoBean();

					bean.setBookISBN(bookISBN);
					bean.setBookInfoName(bookInfoName);
					bean.setCategoryCode(categoryCode);
					bean.setBookInfoAuthor(bookinfoauthor);
					bean.setPublishDay(publishday);
					bean.setPublisher(publisher);

					session.setAttribute("updateinfo", bean);

					gotoPage(request, response, "/updateDocumentInfoConf.jsp");
				}
			}
			// 資料目録更新
			else if (action.equals("updateInfo")) {
				BookInfoBean update = (BookInfoBean) session.getAttribute("updateinfo");
				dao.updateBookInfo(update);
				gotoPage(request, response, "/updateDocumentInfoComp.jsp");
				session.removeAttribute("updateinfo");
				session.removeAttribute("updateinfocomp");
				session.removeAttribute("id");
				session.removeAttribute("isbn");
			}

			// 廃棄
			else if (action.equals("dispose")) {
				String bookstateid = request.getParameter("bookStateId");
				int bookStateId = Integer.parseInt(bookstateid);
				List<SearchDocumentBean> info = dao.disposalDocument(bookStateId);
				request.setAttribute("infos", info);
				gotoPage(request, response, "/disposeDocumentComp.jsp");
			} else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/Err.jsp");
			}

		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/Err.jsp");
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
