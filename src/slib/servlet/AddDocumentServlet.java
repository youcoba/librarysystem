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
import slib.bean.MemberBean;
import slib.dao.AddDocumentDAO;
import slib.dao.AddMemberDAO;
import slib.dao.DAOException;

/**
 * Servlet implementation class AddMemberServlet
 */
@WebServlet("/AddDocumentServlet")
public class AddDocumentServlet extends HttpServlet {
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
			AddDocumentDAO dao = new AddDocumentDAO();
			
			//確認画面表示
			if(action.equals("conf")) {
				//パラーメータの取得
				String isbn = request.getParameter("isbn");
				session.setAttribute("isbn", isbn);
				int number = 0; //冊数
					try {
						number = Integer.parseInt(request.getParameter("number"));
					} catch (NumberFormatException e) {
						request.setAttribute("message", "必須項目が未入力です。");
						gotoPage(request, response, "/error.jsp");
						return;
					}
				String year = request.getParameter("year");
				String month = request.getParameter("month");
				String date = request.getParameter("date");
				String stockDay = year + "-" + month + "-" + date; //SQLのDATE型にあわせる
				//入力エラーチェック
				if(isbn.isEmpty() || number == 0) {
					request.setAttribute("message", "必須項目が未入力です。");
					gotoPage(request, response, "/error.jsp");
					return;
				} else {
					number = Integer.parseInt(request.getParameter("number"));
					session.setAttribute("number", number);
					BookStateBean bean = new BookStateBean();
					bean.setBookISBN(isbn);
					bean.setStockDay(stockDay);
					session.setAttribute("adddocument", bean);
					gotoPage(request, response, "/addDocumentStateConf.jsp");
				}
			} else 
			if(action.equals("comp")) {
				BookStateBean bean = (BookStateBean)session.getAttribute("adddocument"); 
				//ISBN番号検索
				if(dao.checkISBN(bean.getBookISBN())) {
					//追加処理&資料ID取得
					int bookId2 = dao.addBook(bean, (int)session.getAttribute("number"));
					int bookId1 = bookId2 - (int)session.getAttribute("number") + 1;
					//追加後、追加レコードを表示
					List<BookStateBean> list = dao.showBookState(bookId1, bookId2);
					request.setAttribute("addbookstate", list);
					gotoPage(request, response, "/addDocumentStateComp.jsp");
					session.removeAttribute("number");
					session.removeAttribute("adddocument");
				} else {
					//目録に登録（とりあえずISBNだけ）
					dao.addBookInfo(bean.getBookISBN());
					//追加処理&資料ID取得
					int bookId2 = dao.addBook(bean, (int)session.getAttribute("number"));
					int bookId1 = bookId2 - (int)session.getAttribute("number") + 1;
					//追加後、追加レコードを表示
					List<BookStateBean> list = dao.showBookState(bookId1, bookId2);
					request.setAttribute("addbookstate", list);
					gotoPage(request, response, "/addDocumentInfo.jsp");
					session.removeAttribute("number");
					session.removeAttribute("adddocument");
				}
			} else
			if(action.equals("conf2")) {
				//パラメータ取得
				String bookName = request.getParameter("bookName");
				int categoryCode = Integer.parseInt(request.getParameter("category"));
				String author = request.getParameter("author");
				String publisher = request.getParameter("publisher");
				String publishDay = request.getParameter("pyear") + "-" + request.getParameter("pmonth") + "-" + request.getParameter("pdate");
				String comment = request.getParameter("comment");
				if(bookName.isEmpty() || author.isEmpty() || publisher.isEmpty() || publishDay.isEmpty()) {
					request.setAttribute("message", "必須項目が未入力です。");
					gotoPage(request, response, "/error.jsp");
					return;
				} else {
					BookInfoBean bean = new BookInfoBean((String)session.getAttribute("isbn"), categoryCode, bookName, author, publishDay, publisher, comment);
					session.setAttribute("bookinfo", bean);
					gotoPage(request, response, "/addDocumentInfoConf.jsp");
				}
			} else
			if(action.equals("comp2")) {
				//目録を更新	
				BookInfoBean bean = (BookInfoBean)session.getAttribute("bookinfo");
				dao.updateBookInfo(bean, (String)session.getAttribute("isbn"));
				//更新した目録を表示
				BookInfoBean compbean = dao.showBookInfo((String)session.getAttribute("isbn"));			
				session.setAttribute("infocomp", compbean);
				gotoPage(request, response, "/addDocumentInfoComp.jsp");
				session.removeAttribute("bookinfo");
				session.removeAttribute("isbn");
				session.removeAttribute("infocomp");
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
